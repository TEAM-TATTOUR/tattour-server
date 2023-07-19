package org.tattour.server.domain.style.service;

import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.service.dto.response.StyleInfoList;
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;

public interface StyleService {

    Style getStyleById(Integer styleId);

    StyleInfoList getAllStyle();

    StyleSummaryList getAllStyleSummary();
}
