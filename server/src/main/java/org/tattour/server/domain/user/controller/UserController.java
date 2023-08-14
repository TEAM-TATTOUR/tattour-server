package org.tattour.server.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.facade.CustomFacade;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;
import org.tattour.server.domain.custom.provider.impl.CustomProviderImpl;
import org.tattour.server.domain.point.facade.PointFacade;
import org.tattour.server.domain.point.facade.dto.request.CreatePointChargeRequestReq;
import org.tattour.server.domain.user.controller.dto.request.PostPointChargeRequest;
import org.tattour.server.domain.user.controller.dto.request.PostProductLikedReq;
import org.tattour.server.domain.user.controller.dto.request.PostUserShippingAddrReq;
import org.tattour.server.domain.user.facade.UserFacade;
import org.tattour.server.domain.user.facade.dto.request.CompareVerificationCodeReq;
import org.tattour.server.domain.user.facade.dto.request.CreateLoginReq;
import org.tattour.server.domain.user.facade.dto.request.RemoveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.domain.user.facade.dto.response.ProductLikedListRes;
import org.tattour.server.domain.user.facade.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.facade.dto.request.UpdateUserProfileReq;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.domain.user.controller.dto.request.PostLoginReq;
import org.tattour.server.domain.user.controller.dto.request.PatchUserInfoReq;
import org.tattour.server.domain.user.controller.dto.response.PostLoginRes;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "User", description = "User API Document")
public class UserController {

	private final UserFacade userFacade;
	private final PointFacade pointFacade;

	private final CustomProviderImpl customProvider;
	private final CustomFacade customFacade;

	@Operation(summary = "소셜 회원가입 / 로그인", description = "소셜 회원가입 / 로그인 api")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "로그인에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = PostLoginRes.class))),
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
	public ResponseEntity<?> signup(
			@Parameter(description = "Authentication Code", required = true) @RequestHeader("code") String code,
			@RequestBody @Valid PostLoginReq req,
			HttpServletRequest request) {

		return BaseResponse.success(SuccessType.LOGIN_SUCCESS,
				userFacade.signup(CreateLoginReq.of(req.getSocialPlatform(), code,
						request.getHeader("referer"))));
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
		userFacade.userLogout(userId);
		return BaseResponse.success(SuccessType.LOGOUT_SUCCESS);
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
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PatchUserInfoReq req
	) {
		userFacade.updateUserProfile(
				UpdateUserProfileReq.of(
						userId,
						req.getName(),
						req.getPhoneNumber()));

		return BaseResponse.success(SuccessType.UPDATE_SUCCESS);
	}


	@Operation(summary = "user profile 정보 가져오기", description = "user 이름, 포인트 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ReadUserProfileRes.class))),
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
		return BaseResponse.success(SuccessType.GET_SUCCESS, userFacade.readUserProfile(userId));
	}


	@Operation(summary = "인증번호 검증", description = "user 전화번호 인증번호 검증")
	@ApiResponses(value = {
			// TODO : 명세서 Schema 검증하기
			@ApiResponse(
					responseCode = "200",
					description = "인증코드 검증에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "202",
					description = "인증번호 검증에 실패했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
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
	@GetMapping("/phonenumber/verification")
	public ResponseEntity<?> verifyCode(
			@Parameter(hidden = true) @UserId Integer userId,
			@Parameter(description = "인증번호", example = "123456")
			@RequestParam
			@NotNull(message = "verificationCode is null")
			@Min(100000)
			@Max(999999)
			Integer verificationCode) {
		if (userFacade.verifyCode(CompareVerificationCodeReq.of(userId, verificationCode))) {
			return BaseResponse.success(SuccessType.CODE_VERIFICATION_SUCCESS, true);
		} else {
			return BaseResponse.success(SuccessType.CODE_VALIDATION_FAIL, false);
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
	@PostMapping("/productliked/save")
	public ResponseEntity<?> saveProductLiked(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostProductLikedReq req
	) {
		userFacade.saveProductLiked(SaveProductLikedReq.of(userId, req.getStickerId()));

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
	@DeleteMapping("/productliked/sticker/{stickerId}/delete")
	public ResponseEntity<?> deleteProductLiked(
			@Parameter(hidden = true) @UserId Integer userId,
			@Parameter(description = "스티커 id", required = true)
			@PathVariable @NotNull(message = "stickerId is null") Integer stickerId
	) {
		userFacade.deleteProductLiked(RemoveProductLikedReq.of(userId, stickerId));

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
	@GetMapping("/productliked/saved")
	public ResponseEntity<?> getProductLiked(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				userFacade.readProductLikedByUserId(userId));
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
		userFacade.saveUserShippingAddr(
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
		pointFacade.createPointChargeRequest(
				CreatePointChargeRequestReq.of(userId, req.getChargeAmount()));
		return BaseResponse.success(SuccessType.CREATE_POINT_CHARGE_REQUEST_SUCCESS);
	}

	@GetMapping("/custom/complete")
	@Operation(summary = "신청한 커스텀 도안 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = ReadCustomSummaryListRes.class))),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getUserCustomCompleteList(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		ReadCustomSummaryListRes response =
				customFacade.getCustomSummaryCompleteListByUserId(userId);
		return BaseResponse.success(SuccessType.READ_COMPLETE_CUSTOM_SUMMARY_SUCCESS, response);
	}

	@GetMapping("/custom/incomplete")
	@Operation(summary = "커스텀 도안 임시저장 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = ReadCustomSummaryListRes.class))),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getUserCustomIncompleteList(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		ReadCustomSummaryListRes response =
				customFacade.getCustomSummaryInCompleteListByUserId(userId);
		return BaseResponse.success(SuccessType.READ_INCOMPLETE_CUSTOM_SUMMARY_SUCCESS, response);
	}

	@GetMapping("/custom/{customId}")
	@Operation(summary = "내 도안 상세정보 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = ReadCustomRes.class))),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getOneUserCustomInfo(
			@Parameter(hidden = true) @UserId Integer userId,
			@PathVariable(value = "customId") Integer customId
	) {
		ReadCustomRes response = customFacade.readCustomById(customId, userId);
		return BaseResponse.success(SuccessType.READ_ONE_CUSTOM_SUCCESS, response);
	}
}