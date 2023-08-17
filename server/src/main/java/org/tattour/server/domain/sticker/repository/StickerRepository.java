package org.tattour.server.domain.sticker.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.springframework.data.repository.query.Param;

public interface StickerRepository {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);

	List<Sticker> findAll();

	// 인기 많은 순으로 스티커 조회
	@Query("select s from Sticker s left join s.orders "
			+ "where s.isCustom = true and s.state = true "
			+ "group by s.id "
			+ "order by count(*) desc ")
	List<Sticker> findAllByStateAndIsCustomInOrderOrder();


	@Query("select distinct s1 "
			+ "from Sticker s1 "
			+ "left join s1.stickerThemes st1 "
			+ "left join s1.stickerStyles ss1 "
			+ "where (st1.theme in (select st2.theme from StickerTheme st2 where st2.sticker.id = :id)) "
			+ "or (ss1.style in (select ss2.style from StickerStyle ss2 where ss2.sticker.id = :id)) "
			+ "order by s1.id")
	List<Sticker> findAllSameThemeOrStyleById(@Param("id") Integer id);

	List<Sticker> findAllByStateTrue();

	List<Sticker> findByNameContaining(String name);
}
