package org.tattour.server.global.config.s3;

import com.amazonaws.services.s3.AmazonS3;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.global.exception.BadRequestException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3 amazonS3;
	private final AwsS3Property awsS3Property;

//	public String uploadImage(MultipartFile multipartFile, String folder) {
//		String fileName = createFileName(multipartFile.getOriginalFilename());
//		ObjectMetadata objectMetadata = new ObjectMetadata();
//		objectMetadata.setContentLength(multipartFile.getSize());
//		objectMetadata.setContentType(multipartFile.getContentType());
//		try(InputStream inputStream = multipartFile.getInputStream()) {
//			amazonS3.putObject(new PutObjectRequest(bucket+"/image/"+ folder + "/image", fileName, inputStream, objectMetadata)
//				.withCannedAcl(CannedAccessControlList.PublicRead));
//			return amazonS3.getUrl(bucket+"/"+ folder + "/image", fileName).toString();
//		} catch(IOException e) {
//			throw new NotFoundException(ErrorType.NOT_FOUND_SAVE_IMAGE_EXCEPTION);
//		}
//	}

	public List<String> uploadImageList(List<MultipartFile> multipartFileList, String folder) {
		return null;
	}

	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
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
