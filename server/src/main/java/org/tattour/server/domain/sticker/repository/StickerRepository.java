package org.tattour.server.domain.sticker.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.tattour.server.domain.sticker.domain.Sticker;

public interface StickerRepository {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);

	List<Sticker> findAll();

	List<Sticker> findAllByIsCustomTrueAndStateTrue();

	// 인기 많은 순으로 스티커 조회
	@Query("select s from Sticker s left join s.orders "
			+ "where s.isCustom = true and s.state = true "
			+ "group by s.id "
			+ "order by count(s.id) desc ")
	List<Sticker> findAllByStateAndCustomInOrderOrder();


	List<Sticker> findAllByStateTrue();

	List<Sticker> findByNameContaining(String name);
}
