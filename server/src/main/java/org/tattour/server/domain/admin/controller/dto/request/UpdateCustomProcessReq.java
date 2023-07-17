package org.tattour.server.domain.admin.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;

@Getter
@NoArgsConstructor
public class UpdateCustomProcessReq {

	Integer customId;
	String process;

	public UpdateCustomInfo newUpdateCustomInfo(Integer userId) {
		CustomProcess customProcess = CustomProcess.getProcess(process);
		return UpdateCustomInfo.from(userId, customId, customProcess);
	}
}
