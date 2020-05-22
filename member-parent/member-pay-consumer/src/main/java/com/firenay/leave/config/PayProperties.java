package com.firenay.leave.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: PayProperties</p>
 * Description：
 * date：2020/5/22 10:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "ali.pay")
public class PayProperties {

	private String appId;

	private String merchantPrivateKey;

	private String alipayPublicKey;

	private String notifyUrl;

	private String returnUrl;

	private String signType;

	private String charset;

	private String gatewayUrl;
}
