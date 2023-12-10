package org.tattour.server.domain.custom.repository.impl;

import static org.tattour.server.domain.custom.model.QCustom.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.custom.repository.custom.CustomRepositoryCustom;

@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Custom> findAllByUserIdAndIsCompleted(Integer userId) {
        return queryFactory
                .select(custom)
                .from(custom)
                .where(custom.user.id.eq(userId), custom.isCompleted.eq(true))
                .fetch();
    }

    @Override
    public List<Custom> findAllByUserIdAndIsCompletedFalse(Integer userId) {
        return queryFactory
                .select(custom)
                .from(custom)
                .where(custom.user.id.eq(userId), custom.isCompleted.eq(false))
                .fetch();
    }
}
