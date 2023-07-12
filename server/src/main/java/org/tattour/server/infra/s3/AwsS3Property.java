package org.tattour.server.infra.s3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("cloud.aws.s3")
public class AwsS3Property {

	private String accessKey;
	private String secretKey;
	private String bucket;
	private String region;
}
