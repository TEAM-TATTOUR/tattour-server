package org.tattour.server.domain.sticker.repository.impl;

import static org.tattour.server.domain.sticker.domain.QSticker.*;
import static org.tattour.server.domain.sticker.domain.QStickerStyle.*;
import static org.tattour.server.domain.sticker.domain.QStickerTheme.*;

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
				.leftJoin(sticker.orders)
				.where(sticker.isCustom.eq(true), sticker.state.eq(true))
				.groupBy(sticker.id)
				.orderBy(sticker.orders.size().desc())
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
}
