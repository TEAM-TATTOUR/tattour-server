package org.tattour.server.domain.style.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.style.facade.StyleFacade;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleListRes;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleSummaryListRes;
import org.tattour.server.domain.style.model.Style;
import org.tattour.server.domain.style.provider.StyleProvider;

@Service
@RequiredArgsConstructor
public class StyleFacadeImpl implements StyleFacade {

    private final StyleProvider styleProvider;

    @Override
    @Transactional(readOnly = true)
    public ReadStyleListRes readAllStyle() {
        List<Style> styles = styleProvider.getAll();
        return ReadStyleListRes.from(styles);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadStyleSummaryListRes readAllStyleSummary() {
        List<Style> styles = styleProvider.getAll();
        return ReadStyleSummaryListRes.from(styles);
    }
}
