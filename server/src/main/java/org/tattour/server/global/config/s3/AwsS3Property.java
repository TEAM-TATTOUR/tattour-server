package org.tattour.server.global.config.s3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("cloud.aws.s3")
public class AwsS3Property {

	private String accessKey;
	private String secretKey;
	private String bucket;
	private String region;
//	private String bucketUrl;
}
