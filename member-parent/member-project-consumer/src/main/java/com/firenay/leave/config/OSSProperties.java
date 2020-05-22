package com.firenay.leave.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: OSSProperties</p>
 * Description：
 * date：2020/5/19 18:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties {

	private String endPoint;
	private String bucketName;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketDomain;
}
