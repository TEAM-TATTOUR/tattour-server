package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.custom.model.CustomProcess;
import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomReq;

@Getter
@NoArgsConstructor
public class PatchCustomProcessReq {

	@NotNull
	@Schema(description = "커스텀 id")
	Integer customId;


	@NotNull
	@Schema(example = "receiving, receiptComplete, receiptFailed, shipping, shipped", type = "receiving")
	String process;

	public UpdateCustomReq newUpdateCustomReq(Integer userId) {
		CustomProcess customProcess = CustomProcess.getProcess(process);
		return UpdateCustomReq.of(userId, customId, customProcess);
	}
}
