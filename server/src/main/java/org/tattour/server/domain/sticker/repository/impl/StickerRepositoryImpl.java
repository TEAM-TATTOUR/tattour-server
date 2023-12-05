package org.tattour.server.domain.sticker.repository.impl;

import static org.tattour.server.domain.order.domain.QOrderedProduct.*;
import static org.tattour.server.domain.sticker.domain.QSticker.*;
import static org.tattour.server.domain.sticker.domain.QStickerStyle.*;
import static org.tattour.server.domain.sticker.domain.QStickerTheme.*;
import static org.tattour.server.domain.style.domain.QStyle.*;
import static org.tattour.server.domain.theme.domain.QTheme.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.custom.StickerRepositoryCustom;

@RequiredArgsConstructor
public class StickerRepositoryImpl implements StickerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Sticker> findAllByStateAndIsCustomInOrderOrder() {
        return queryFactory
                .select(sticker)
                .from(sticker)
                .leftJoin(sticker.orderedProducts, orderedProduct)
                .where(sticker.isCustom.eq(true), sticker.state.eq(true))
                .groupBy(sticker.id)
                .orderBy(sticker.orderedProducts.size().desc())
                .fetch();
    }

    @Override
    public List<Sticker> findAllSameThemeOrStyleById(Integer id) {
        return queryFactory
                .select(sticker).distinct()
                .from(sticker)
                .leftJoin(sticker.stickerThemes, stickerTheme)
                .leftJoin(sticker.stickerStyles, stickerStyle)
                .where(sticker.state.eq(true))
                .where(stickerTheme.theme.in(
                        queryFactory
                                .select(stickerTheme.theme)
                                .from(stickerTheme)
                                .where(stickerTheme.sticker.id.eq(id))
                ).or(stickerStyle.style.in(
                        queryFactory
                                .select(stickerStyle.style)
                                .from(stickerStyle)
                                .where(stickerStyle.sticker.id.eq(id))
                )))
                .orderBy(sticker.id.asc())
                .fetch();
    }

    @Override
    public List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderOrder(
            String themeName,
            String styleName) {
        return queryFactory
                .select(sticker).distinct()
                .from(sticker)
                .leftJoin(sticker.stickerThemes, stickerTheme)
                .leftJoin(sticker.stickerStyles, stickerStyle)
                .leftJoin(sticker.orderedProducts, orderedProduct)
                .fetchJoin()
                .where(sticker.state.eq(true))
                .where(stickerTheme.theme.in(
                        queryFactory
                                .select(theme)
                                .from(theme)
                                .where(eqThemeName(themeName))
                ).and(stickerStyle.style.in(
                        queryFactory
                                .select(style)
                                .from(style)
                                .where(eqStyleName(styleName))
                )))
                .fetch();
    }

    @Override
    public List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPrice(String themeName,
                                                                            String styleName) {
        return queryFactory
                .select(sticker).distinct()
                .from(sticker)
                .leftJoin(sticker.stickerThemes, stickerTheme)
                .leftJoin(sticker.stickerStyles, stickerStyle)
                .leftJoin(sticker.orderedProducts, orderedProduct)
                .fetchJoin()
                .where(sticker.state.eq(true))
                .where(stickerTheme.theme.in(
                        queryFactory
                                .select(theme)
                                .from(theme)
                                .where(eqThemeName(themeName))
                ).and(stickerStyle.style.in(
                        queryFactory
                                .select(style)
                                .from(style)
                                .where(eqStyleName(styleName))
                )))
                .orderBy(sticker.price.asc())
                .fetch();
    }

    @Override
    public List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPriceDesc(String themeName,
                                                                                String styleName) {
        return queryFactory
                .select(sticker).distinct()
                .from(sticker)
                .leftJoin(sticker.stickerThemes, stickerTheme)
                .leftJoin(sticker.stickerStyles, stickerStyle)
                .leftJoin(sticker.orderedProducts, orderedProduct)
                .fetchJoin()
                .where(sticker.state.eq(true))
                .where(stickerTheme.theme.in(
                        queryFactory
                                .select(theme)
                                .from(theme)
                                .where(eqThemeName(themeName))
                ).and(stickerStyle.style.in(
                        queryFactory
                                .select(style)
                                .from(style)
                                .where(eqStyleName(styleName))
                )))
                .orderBy(sticker.price.desc())
                .fetch();
    }

    @Override
    public List<Sticker> findAllByThemeNameOrStyleNameOrNameContaining(String word) {
        return queryFactory
                .select(sticker).distinct()
                .from(sticker)
                .leftJoin(sticker.stickerThemes, stickerTheme)
                .leftJoin(sticker.stickerStyles, stickerStyle)
                .where(sticker.state.eq(true))
                .where(stickerTheme.theme.in(
                        queryFactory
                                .select(theme)
                                .from(theme)
                                .where(theme.name.contains(word))
                ).or(stickerStyle.style.in(
                        queryFactory
                                .select(style)
                                .from(style)
                                .where(style.name.contains(word))
                )).or(sticker.name.contains(word)))
                .fetch();
    }

    private BooleanExpression eqStyleName(String styleName) {
        return styleName == null ? null : style.name.eq(styleName);
    }

    private BooleanExpression eqThemeName(String themeName) {
        return themeName == null ? null : theme.name.eq(themeName);
    }
}