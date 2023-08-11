package org.tattour.server.domain.user.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.domain.user.provider.dto.request.SaveProductLikedReq;
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
	public void saveProductLiked(SaveProductLikedReq req) {
		User user = userProvider.getUserById(req.getUserId());
		Sticker sticker = stickerProvider.getById(req.getStickerId());
		productLikedRepository.save(ProductLiked.of(user, sticker));
	}

	@Override
	public void deleteProductLiked(Integer userId, Integer stickerId) {
		ProductLiked productLiked = productLikedProvider.getProductLikedByUserIdAndStickerId(userId, stickerId);

		productLikedRepository.delete(productLiked);
	}

	@Override
	public Boolean getProductLiked(Integer userId, Integer stickerId) {
		Optional<ProductLiked> productLiked = productLikedRepository.findProductLikedByUser_IdAndSticker_Id(
				userId, stickerId
		);
		return productLiked.isPresent();
	}
}
