package org.tattour.server.domain.sticker.provider;

import java.util.List;
import org.tattour.server.domain.cart.model.Cart;
import org.tattour.server.domain.sticker.model.Sticker;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;

public interface StickerProvider {

    Sticker getById(Integer id);

    StickerOrderInfo getStickerOrderInfoFromOrder(int stickerId, int count);

    StickerOrderInfo getStickerOrderInfoFromCart(List<Cart> carts);

    List<Sticker> getAllCustomStickerOrderByOrder();

    List<Sticker> getAllByThemeAndStyleOrderByOrder(String theme, String style);

    List<Sticker> getAllByThemeAndStyleOrderByPrice(String theme, String style);

    List<Sticker> getAllByThemeAndStyleOrderByPriceDesc(String theme, String style);

    List<Sticker> getAllSameThemeOrStyleById(Integer id);

    List<Sticker> getAllByThemeOrStyleOrNameLike(String word);

}
