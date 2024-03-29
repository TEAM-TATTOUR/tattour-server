package org.tattour.server.domain.cart.service;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.cart.model.Cart;
import org.tattour.server.domain.sticker.model.Sticker;
import org.tattour.server.domain.user.model.User;

public interface CartService {
    List<Cart> findByUserId(int userId);

    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndSticker(User user, Sticker sticker);

    Cart findByIdAndUserId(int id, int userId);

    void mergeOrAddToCart(User user, Sticker sticker, int count);

    Cart createNewCart(User user, Sticker sticker, int count);

    void delete(Cart cart);

    void deleteAllByUserId(User user);
}