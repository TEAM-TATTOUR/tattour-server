package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;

@Getter
@NoArgsConstructor
public class UpdateCustomProcessReq {

	@NotNull
	@Schema(description = "커스텀 id")
	Integer customId;


	@NotNull
	@Schema(example = "receiving, receiptComplete, receiptFailed, shipping, shipped", type = "receiving")
	String process;

	public UpdateCustomInfo newUpdateCustomInfo(Integer userId) {

		CustomProcess customProcess = CustomProcess.getProcess(process);

		return UpdateCustomInfo.from(userId, customId, customProcess);

	}

}
