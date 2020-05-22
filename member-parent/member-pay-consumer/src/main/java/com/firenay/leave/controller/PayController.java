package com.firenay.leave.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.firenay.leave.api.MySQLRemoteService;
import com.firenay.leave.config.PayProperties;
import com.firenay.leave.entity.vo.OrderProjectVO;
import com.firenay.leave.entity.vo.OrderVO;
import com.firenay.leave.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Title: PayController</p>
 * Description：
 * date：2020/5/22 10:13
 */
@Controller
public class PayController {

	@Resource
	private PayProperties payProperties;

	@Resource
	private MySQLRemoteService mySQLRemoteService;

	private Logger logger = LoggerFactory.getLogger(PayController.class);

	@RequestMapping("/notify")
	public void notifyUrlMethod(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		//获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(
				params,
				payProperties.getAlipayPublicKey(),
				payProperties.getCharset(),
				payProperties.getSignType()); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if (signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			logger.info("out_trade_no = " + out_trade_no);
			logger.info("trade_no = " + trade_no);
			logger.info("trade_status = " + trade_status);

		} else {//验证失败
			//调试用，写文本函数记录程序运行情况是否正常
			//String sWord = AlipaySignature.getSignCheckContentV1(params);
			//AlipayConfig.logResult(sWord);

			logger.info("验证失败");
		}
	}

	@ResponseBody
	@RequestMapping("return")
	public String returnUrlMethod(HttpServletRequest request, HttpSession session) throws AlipayApiException, UnsupportedEncodingException {
// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(
				params,
				payProperties.getAlipayPublicKey(),
				payProperties.getCharset(),
				payProperties.getSignType()); //调用SDK验证签名

		// 这些都是支付宝返回给我们的
		if (signVerified) {
			// 商户订单号
			String orderNum = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String payOrderNum = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String orderAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			// 保存到数据库
			// 1.从Session域中获取generateOrder 方法中的 OrderVO对象
			OrderVO orderVO = (OrderVO) session.getAttribute("orderVO");

			// 2.将支付宝交易号设置到OrderVO对象中
			orderVO.setPayOrderNum(payOrderNum);

			// 3.发送给MySQL的远程接口
			ResultEntity<String> resultEntity = mySQLRemoteService.saveOrderRemote(orderVO);
			logger.info("Order save result = " + resultEntity.getResult());

			return "trade_no [支付宝订单号]:" + payOrderNum + "<br/>out_trade_no [商家订单号]:" + orderNum + "<br/>total_amount[金额]:" + orderAmount;
		} else {
			return "验签失败";
		}
	}

	/**
	 * 最终返回支付宝页面
	 */
	@ResponseBody
	@RequestMapping("generate/order")
	public String generateOrder(HttpSession session, OrderVO orderVO) throws AlipayApiException {

		OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
		orderVO.setOrderProjectVO(orderProjectVO);
		// 生成订单号
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String user = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		String orderNum = time + user;
		orderVO.setOrderNum(orderNum);
		// 计算订单总金额
		Double orderAmount = Double.valueOf(orderProjectVO.getSupportPrice() * orderProjectVO.getReturnCount() + orderProjectVO.getFreight());
		orderVO.setOrderAmount(orderAmount);
		session.setAttribute("orderVO", orderVO);
		// 调用支付宝接口
		return sendReqeustToAliPay(orderNum, orderAmount, orderProjectVO.getProjectName(), orderProjectVO.getReturnContent());
	}

	/**
	 * 调用支付宝接口
	 *
	 * @param outTradeNum 我们生成订单号
	 * @param totalAmount 订单总金额
	 * @param subject     订单标题
	 * @param body        订单商品表述
	 * @return 返回到页面上显示支付宝登录界面
	 */
	private String sendReqeustToAliPay(String outTradeNum, Double totalAmount, String subject, String body) throws AlipayApiException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(payProperties.getGatewayUrl(), payProperties.getAppId(), payProperties.getMerchantPrivateKey(), "json", payProperties.getCharset(), payProperties.getAlipayPublicKey(), payProperties.getSignType());

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(payProperties.getReturnUrl());
		alipayRequest.setNotifyUrl(payProperties.getNotifyUrl());

		//商户订单号，商户网站订单系统中唯一订单号，必填
		//付款金额，必填
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNum + "\","
				+ "\"total_amount\":\"" + totalAmount + "\","
				+ "\"subject\":\"" + subject + "\","
				+ "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		//		+ "\"total_amount\":\""+ total_amount +"\","
		//		+ "\"subject\":\""+ subject +"\","
		//		+ "\"body\":\""+ body +"\","
		//		+ "\"timeout_express\":\"10m\","
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		//请求
		return alipayClient.pageExecute(alipayRequest).getBody();
	}
}
