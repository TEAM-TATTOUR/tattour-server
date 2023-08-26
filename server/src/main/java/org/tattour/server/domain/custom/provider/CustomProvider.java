package org.tattour.server.domain.custom.provider;

import java.util.List;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomProvider {

	Custom getCustomById(Integer customId, Integer userId);

	List<Custom> getCustomByUserIdAfterDate(int userId, String date);

	List<Custom> getAllByUserIdAndIsCompleted(Integer userId);

	List<Custom> getAllByUserIdAndIsCompletedFalse(Integer userId);
}
