package org.tattour.server.domain.cart.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser_Id(int userId);

    Optional<Cart> findByIdAndUser_Id(int id, int userId);

    Optional<Cart> findCartByUserAndSticker(User user, Sticker sticker);
}