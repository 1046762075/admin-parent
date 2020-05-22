package com.firenay.leave.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: ShortMessageProperties</p>
 * Description：配置信息
 * date：2020/5/18 20:23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {

	/**
	 * test | email | PhoneNUm   三种模式
	 */
	private String mode = "test";

	private String host;

	private String path;

	private String method;

	private String appCode;

	private String sign;

	private String skin;

	/**
	 * 测试模式需要用的验证码
	 */
	private String code = "5201314";

	/**
	 * 邮箱模式用于接收账号
	 */
	private String emailAccess = "1046762075";

	/**
	 * 邮箱模式账号的授权码
	 */
	private String topic = " LEAVE ";

	private String content = "欢迎注册LEAVE众筹网, 您的验证码为：";
}
