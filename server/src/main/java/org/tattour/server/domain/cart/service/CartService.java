package org.tattour.server.domain.cart.service;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

public interface CartService {
    void mergeOrAddToCart(User user, Sticker sticker, int count);

    Cart createNewCart(User user, Sticker sticker, int count);

    void save(Cart cart);

    Optional<Cart> findByUserAndSticker(User user, Sticker sticker);

    List<Cart> findByUser(User user);
}
