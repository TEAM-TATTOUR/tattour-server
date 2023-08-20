package org.tattour.server.domain.sticker.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.custom.StickerRepositoryCustom;

public interface StickerRepository extends
	Repository<Sticker, Integer>,
		StickerRepositoryCustom {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);

	@Query("select distinct s "
			+ "from Sticker s "
			+ "left join s.stickerThemes st "
			+ "left join s.stickerStyles ss "
			+ "left join fetch s.orders o "
			+ "where (st.theme in (select t from Theme t where (:theme is null or t.name = :theme))) "
			+ "and (ss.style in (select s2 from Style s2 where (:style is null or s2.name = :style))) "
			+ "and s.state = true "
			+ "order by s.price asc")
	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPrice(@Param("theme") String theme, @Param("style") String style);

	@Query("select distinct s "
			+ "from Sticker s "
			+ "left join s.stickerThemes st "
			+ "left join s.stickerStyles ss "
			+ "left join fetch s.orders o "
			+ "where (st.theme in (select t from Theme t where (:theme is null or t.name = :theme))) "
			+ "and (ss.style in (select s2 from Style s2 where (:style is null or s2.name = :style))) "
			+ "and s.state = true "
			+ "order by s.price desc")
	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPriceDesc(@Param("theme") String theme, @Param("style") String style);

	@Query("select distinct s from Sticker s "
			+ "left join s.stickerThemes st "
			+ "left join s.stickerStyles ss "
			+ "where (st.theme in (select tt from Theme tt where tt.name like %:word%)) "
			+ "or (ss.style in (select ss from Style ss where ss.name like %:word%)) "
			+ "or (s.name like %:word%) "
			+ "and s.state = true ")
	List<Sticker> findAllByThemeNameOrStyleNameOrNameContaining(@Param("word") String word);
}
