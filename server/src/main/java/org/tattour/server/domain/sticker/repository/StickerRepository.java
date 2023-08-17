package org.tattour.server.domain.sticker.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.springframework.data.repository.query.Param;

public interface StickerRepository {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);

	@Query("select s "
			+ "from Sticker s left join s.orders "
			+ "where s.isCustom = true and s.state = true "
			+ "group by s.id "
			+ "order by count(*) desc ")
	List<Sticker> findAllByStateAndIsCustomInOrderOrder();


	@Query("select distinct s1 "
			+ "from Sticker s1 "
			+ "left join s1.stickerThemes st1 "
			+ "left join s1.stickerStyles ss1 "
			+ "where ((st1.theme in (select st2.theme from StickerTheme st2 where st2.sticker.id = :id)) "
			+ "or (ss1.style in (select ss2.style from StickerStyle ss2 where ss2.sticker.id = :id))) "
			+ "and s1.state = true "
			+ "order by s1.id")
	List<Sticker> findAllSameThemeOrStyleById(@Param("id") Integer id);

	@Query("select distinct s "
			+ "from Sticker s "
			+ "left join s.stickerThemes st "
			+ "left join s.stickerStyles ss "
			+ "left join fetch s.orders o "
			+ "where (st.theme in (select t from Theme t where (:theme is null or t.name = :theme))) "
			+ "and (ss.style in (select s2 from Style s2 where (:style is null or s2.name = :style))) "
			+ "and s.state = true ")
	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderOrder(@Param("theme") String theme, @Param("style") String style);

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
