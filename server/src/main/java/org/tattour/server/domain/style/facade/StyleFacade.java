package org.tattour.server.domain.style.facade;

import org.tattour.server.domain.style.facade.dto.response.ReadStyleListRes;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleSummaryListRes;

public interface StyleFacade {

    ReadStyleListRes readAllStyle();

    ReadStyleSummaryListRes readAllStyleSummary();
}
