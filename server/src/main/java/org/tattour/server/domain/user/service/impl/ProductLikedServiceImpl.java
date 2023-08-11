package org.tattour.server.domain.user.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.ProductLikedService;

@Service
@RequiredArgsConstructor
public class ProductLikedServiceImpl implements ProductLikedService {

	private final ProductLikedRepositoryImpl productLikedRepository;
	private final ProductLikedProviderImpl productLikedProvider;
	private final UserProviderImpl userProvider;
	private final StickerProvider stickerProvider;

	@Override
	public void saveProductLiked(int userId, int stickerId) {
		User user = userProvider.readUserById(userId);
		Sticker sticker = stickerProvider.getById(stickerId);
		productLikedRepository.save(ProductLiked.of(user, sticker));
	}

	@Override
	public void removeProductLiked(int userId, int stickerId) {
		ProductLiked productLiked = productLikedProvider.readProductLikedByUserIdAndStickerId(userId, stickerId);

		productLikedRepository.delete(productLiked);
	}

	@Override
	public Boolean checkProductLikedExists(Integer userId, Integer stickerId) {
		Optional<ProductLiked> productLiked = productLikedRepository.findProductLikedByUser_IdAndSticker_Id(
				userId, stickerId
		);
		return productLiked.isPresent();
	}
}
