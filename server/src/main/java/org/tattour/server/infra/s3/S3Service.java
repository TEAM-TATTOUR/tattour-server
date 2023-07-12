package org.tattour.server.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.global.exception.BadRequestException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.exception.NotFoundException;

@Component
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3 amazonS3;
	private final AwsS3Property s3Property;

	public String uploadImage(MultipartFile multipartFile, String directoryPath) {
		String fileName = createFileName(directoryPath, multipartFile.getOriginalFilename());
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());
		try {
			InputStream inputStream = multipartFile.getInputStream();
			amazonS3.putObject(
				new PutObjectRequest(s3Property.getBucket() + "/image/" + directoryPath, fileName, inputStream,
					objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			return amazonS3.getUrl(s3Property.getBucket() + "/image/" + directoryPath, fileName).toString();
		} catch (Exception e) {
			throw new NotFoundException(ErrorType.NOT_FOUND_SAVE_IMAGE_EXCEPTION);
		}
	}

	public List<String> uploadImageList(List<MultipartFile> multipartFileList, String directoryPath) {
		for (MultipartFile multipartFile : multipartFileList) {
			uploadImage(multipartFile, directoryPath);
		}
		List<String> images = multipartFileList.stream()
			.map(multipartFile -> uploadImage(multipartFile, directoryPath))
			.collect(Collectors.toList());
		return images;
	}

	private String createFileName(String directoryPath, String fileName) {
		return directoryPath + UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}
	private String getFileExtension(String fileName) {
		if (fileName.length() == 0) {
			throw new NotFoundException(ErrorType.INVALID_IMAGE_EXCEPTION);
		}
		ArrayList<String> fileValidate = new ArrayList<>();
		fileValidate.add(".jpg");
		fileValidate.add(".jpeg");
		fileValidate.add(".png");
		fileValidate.add(".JPG");
		fileValidate.add(".JPEG");
		fileValidate.add(".PNG");
		String idxFileName = fileName.substring(fileName.lastIndexOf("."));
		if (!fileValidate.contains(idxFileName)) {
			throw new BadRequestException(ErrorType.INVALID_MULTIPART_EXTENSION_EXCEPTION);
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
