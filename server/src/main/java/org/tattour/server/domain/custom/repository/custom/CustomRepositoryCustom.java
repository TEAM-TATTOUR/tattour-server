package org.tattour.server.domain.custom.repository.custom;

import java.util.List;
import org.tattour.server.domain.custom.model.Custom;

public interface CustomRepositoryCustom {

    List<Custom> findAllByUserIdAndIsCompletedOrderByLastUpdatedAt(Integer userId);

    List<Custom> findAllByUserIdAndIsCompletedFalseOrderByLastUpdatedAt(Integer userId);
}
