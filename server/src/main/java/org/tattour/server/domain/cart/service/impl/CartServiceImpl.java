package org.tattour.server.domain.cart.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.cart.repository.CartRepository;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void mergeOrAddToCart(User user, Sticker sticker, int count) {
        Cart cart = findByUserAndSticker(user, sticker)
                .map(cartExisting -> {
                    cartExisting.addCount(count);
                    return cartExisting;
                })
                .orElseGet(() -> createNewCart(user, sticker, count));
        save(cart);
    }

    @Override
    public Optional<Cart> findByUserAndSticker(User user, Sticker sticker) {
        return cartRepository.findCartByUserAndSticker(user, sticker);
    }

    @Override
    public Cart createNewCart(User user, Sticker sticker, int count) {
        return Cart.builder()
                .count(count)
                .user(user)
                .sticker(sticker)
                .build();
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
