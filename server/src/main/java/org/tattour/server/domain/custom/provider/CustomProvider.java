package org.tattour.server.domain.custom.provider;

import java.util.List;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomProvider {

    Custom getCustomById(Integer customId, Integer userId);

    List<Custom> readCustomSummaryInfoAfterDateByUserId(int userId, String date);
}
