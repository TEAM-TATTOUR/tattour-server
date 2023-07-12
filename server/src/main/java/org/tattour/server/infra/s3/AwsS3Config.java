package org.tattour.server.infra.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

	private final AwsS3Property awsS3Property;

	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
			awsS3Property.getAccessKey(), awsS3Property.getSecretKey());
		return AmazonS3ClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
			.withRegion(awsS3Property.getRegion())
			.build();
	}
}
