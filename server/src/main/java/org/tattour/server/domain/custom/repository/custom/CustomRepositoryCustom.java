package org.tattour.server.domain.custom.repository.custom;

import java.util.List;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomRepositoryCustom {

	List<Custom> findAllByUserIdAndIsCompleted(Integer userId);

	List<Custom> findAllByUserIdAndIsCompletedFalse(Integer userId);
}
