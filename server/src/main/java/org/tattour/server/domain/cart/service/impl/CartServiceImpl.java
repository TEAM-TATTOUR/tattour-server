package org.tattour.server.domain.cart.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.cart.repository.CartRepositoryImpl;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepositoryImpl cartRepositoryImpl;

    @Override
    public List<Cart> findByUserId(int userId) {
        return cartRepositoryImpl.findByUser_Id(userId);
    }

    @Override
    public List<Cart> findByUser(User user) {
        return cartRepositoryImpl.findByUser(user);
    }

    @Override
    public Optional<Cart> findByUserAndSticker(User user, Sticker sticker) {
        return cartRepositoryImpl.findCartByUserAndSticker(user, sticker);
    }

    @Override
    public Cart findByIdAndUserId(int userId, int id) {
        return cartRepositoryImpl.findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_CART_EXCEPTION));
    }

    @Override
    public void mergeOrAddToCart(User user, Sticker sticker, int count) {
        Cart cart = findByUserAndSticker(user, sticker)
                .map(cartExisting -> {
                    cartExisting.addCount(count);
                    return cartExisting;
                })
                .orElseGet(() -> createNewCart(user, sticker, count));
        cartRepositoryImpl.save(cart);
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
    @Transactional
    public void increaseCartCount(Cart cart) {
        cart.increaseCount();
        cartRepositoryImpl.save(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartRepositoryImpl.delete(cart);
    }

    @Override
    public void deleteAllByUserId(User user) {
        cartRepositoryImpl.deleteAllByUser(user);
    }
}