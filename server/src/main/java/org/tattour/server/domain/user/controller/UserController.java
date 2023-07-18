package org.tattour.server.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomSummaryList;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.user.controller.dto.request.DeleteProductLikedReq;
import org.tattour.server.domain.user.controller.dto.request.PostPointChargeRequest;
import org.tattour.server.domain.user.controller.dto.request.PostProductLikedReq;
import org.tattour.server.domain.user.controller.dto.request.PostUserShippingAddrReq;
import org.tattour.server.domain.user.provider.dto.request.CheckDuplicationReqDto;
import org.tattour.server.domain.user.provider.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedListRes;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.DeleteProductLikedInfo;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.impl.ProductLikedServiceImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.service.impl.UserShippingAddressServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.sms.provider.impl.PhoneNumberVerificationCodeProviderImpl;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialServiceProvider;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.SocialLoginRequest;
import org.tattour.server.domain.user.controller.dto.request.GetVerifyCodeReq;
import org.tattour.server.domain.user.controller.dto.request.LoginReq;
import org.tattour.server.domain.user.controller.dto.request.PatchUserInfoReq;
import org.tattour.server.domain.user.controller.dto.response.GetVerifyCodeRes;
import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.SocialLoginResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "User", description = "User API Document")
public class UserController {

	private final SocialServiceProvider socialServiceProvider;
	private final UserServiceImpl userService;
	private final UserProviderImpl userProvider;
	private final CustomService customService;
	private final PhoneNumberVerificationCodeProviderImpl phoneNumberVerificationCodeProvider;
	private final ProductLikedServiceImpl productLikedService;
	private final ProductLikedProviderImpl productLikedProvider;
	private final UserShippingAddressServiceImpl userShippingAddressService;
	private final PointServiceImpl pointService;
	private final JwtService jwtService;


	@Operation(summary = "소셜 회원가입 / 로그인", description = "소셜 회원가입 / 로그인 api")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "로그인에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = LoginRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/signup")
	public ResponseEntity<?> login(
			@Parameter(description = "Authentication Code", required = true) @RequestHeader("code") String code,
			@RequestBody @Valid LoginReq req) {
		SocialService socialService = socialServiceProvider.getSocialService(req.getSocialPlatform());

		// 로그인
		SocialLoginResponse response = socialService.login(SocialLoginRequest.of(code));

		// jwt 토큰 발급
		return BaseResponse.success(SuccessType.LOGIN_SUCCESS,
			LoginRes.of(
					response.getUserId(),
					jwtService.issuedToken(response.getUserId()),
					response.isUserExist()));
	}


	@Operation(summary = "user 이름, 전화번호 추가", description = "회원가입 시 user 이름, 전화번호 추가")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "갱신에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PatchMapping("/profile")
	public ResponseEntity<?> updateUserProfile(
//			@Parameter(name = "Authorization", description = "JWT access token") @RequestHeader(required = false) String token,
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PatchUserInfoReq req
	) {
		userService.updateUserInfo(
				UpdateUserInfoReq.of(
						userId,
						req.getName(),
						req.getPhoneNumber()));

		return BaseResponse.success(SuccessType.UPDATE_SUCCESS);
	}


	@Operation(summary = "user profile 정보 가져오기", description = "user 이름, 포인트")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = GetUserProfileRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/profile")
	public ResponseEntity<?> getUserProfile(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS, userProvider.getUserProfile(userId));
	}


	@Operation(summary = "user 로그아웃", description = "user 로그아웃")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "로그아웃에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PatchMapping("/logout")
	public ResponseEntity<?> userLogout(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		userService.userLogout(userId);
		return BaseResponse.success(SuccessType.LOGOUT_SUCCESS);
	}


	@Operation(summary = "인증번호 검증", description = "user 전화번호 인증번호 검증")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "인증코드 검증에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = GetVerifyCodeRes.class))),
			@ApiResponse(
					responseCode = "202",
					description = "인증번호 검증에 실패했습니다.",
					content = @Content(schema = @Schema(implementation = GetVerifyCodeRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "유효한 인증번호가 존재하지 않습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/phoneNum/verification")
	public ResponseEntity<?> verififyCode(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid GetVerifyCodeReq req) {

		if (phoneNumberVerificationCodeProvider
				.compareVerficationCode(userId, req.getVerificationCode())) {
			return BaseResponse.success(
					SuccessType.CODE_VERIFICATION_SUCCESS,
					GetVerifyCodeRes.of(true));
		} else {
			return BaseResponse.success(SuccessType.CODE_VALIDATION_FAIL,
				GetVerifyCodeRes.of(false));
		}
	}


	@Operation(summary = "좋아요 누른 타투 저장", description = "user 좋아요 누른 타투 저장")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "생성에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 스티커입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "409",
					description = "이미 존재하는 좋아요한 상품입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/productLiked/save")
	public ResponseEntity<?> saveProductLiked(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostProductLikedReq req
	) {
		// 중복 검사
		if (productLikedProvider.checkDuplicationByStickerId(CheckDuplicationReqDto.of(userId, req.getStickerId())))
			throw new BusinessException(ErrorType.ALREADY_EXIST_PRODUCTLIKED_EXCEPTION);

		productLikedService.saveProductLiked(SaveProductLikedReq.of(userId, req.getStickerId()));

		return BaseResponse.success(SuccessType.CREATE_SUCCESS);
	}


	@Operation(summary = "좋아요 누른 타투 삭제", description = "user 좋아요 누른 타투 삭제")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "삭제에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 데이터입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@DeleteMapping("/productLiked/delete")
	public ResponseEntity<?> deleteProductLiked(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid DeleteProductLikedReq req
	) {
		productLikedService.deleteProductLiked(
				DeleteProductLikedInfo.of(userId, req.getStickerId()));

		return BaseResponse.success(SuccessType.DELETE_SUCCESS);
	}


	@Operation(summary = "좋아요 누른 타투 불러오기", description = "user 좋아요 누른 타투 스티커 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ProductLikedListRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/productLiked/saved")
	public ResponseEntity<?> getProductLiked(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
			productLikedProvider.getLikedProductsByUserId(userId));
	}


	@Operation(summary = "배송지 등록", description = "user 배송지 등록")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "생성에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/address")
	public ResponseEntity<?> createShippingAddr(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostUserShippingAddrReq req
	) {
		userShippingAddressService.saveUserShippingAddr(
			SaveUserShippingAddrReq.of(
				userId,
				req.getRecipientName(),
				req.getContact(),
				req.getMailingAddress(),
				req.getBaseAddress(),
				req.getDetailAddress()));

		return BaseResponse.success(SuccessType.CREATE_SUCCESS);
	}


	@Operation(summary = "포인트 충전 신청", description = "user 포인트 충전 신청")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "생성에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/point/charge")
	public ResponseEntity<?> createPointChargeRequest(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostPointChargeRequest req
	) {
        pointService.savePointChargeRequest(
                SavePointChargeRequestReq.of(userId, req.getChargeAmount()));
        Integer resultPoint = userService.updateUserPoint(UpdateUserPointReq.of(userId, req.getChargeAmount()));
        pointService.savePointLog(
                SaveUserPointLogReq.of("포인트 충전 요청", null, req.getChargeAmount(), resultPoint, userId));

		return BaseResponse.success(SuccessType.CREATE_POINT_CHARGE_REQUEST_SUCCESS);
	}

	@GetMapping("/custom/complete")
	@Operation(summary = "신청한 커스텀 도안 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",
			description = "success",
			content = @Content(schema = @Schema(implementation = CustomSummaryList.class))),
		@ApiResponse(responseCode = "400, 500",
			description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getUserCustomCompleteList(
		@Parameter(hidden = true) @UserId Integer userId
	) {
		CustomSummaryList response = customService.getCustomSummaryCompleteListByUserId(userId);
		return BaseResponse.success(SuccessType.READ_COMPLETE_CUSTOM_SUMMARY_SUCCESS, response);
	}

	@GetMapping("/custom/incomplete")
	@Operation(summary = "커스텀 도안 임시저장 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",
			description = "success",
			content = @Content(schema = @Schema(implementation = CustomSummaryList.class))),
		@ApiResponse(responseCode = "400, 500",
			description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getUserCustomIncompleteList(
		@Parameter(hidden = true) @UserId Integer userId
	) {
		CustomSummaryList response = customService.getCustomSummaryInCompleteListByUserId(userId);
		return BaseResponse.success(SuccessType.READ_INCOMPLETE_CUSTOM_SUMMARY_SUCCESS, response);
	}

	@GetMapping("/custom/{customId}")
	@Operation(summary = "내 도안 상세정보 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",
			description = "success",
			content = @Content(schema = @Schema(implementation = CustomInfo.class))),
		@ApiResponse(responseCode = "400, 500",
			description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getOneUserCustomInfo(
		@Parameter(hidden = true) @UserId Integer userId,
		@PathVariable(value = "customId") Integer customId
	) {
		Custom custom = customService.getCustomById(customId, userId);
		CustomInfo response = CustomInfo.of(custom);
		return BaseResponse.success(SuccessType.READ_ONE_CUSTOM_SUCCESS, response);
	}
}
