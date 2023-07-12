package org.tattour.server.global.util;

import java.util.List;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.domain.UserShippingAddress;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.service.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedRes;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    GetUserProfileRes toGetUserProfileRes(User user);

    // ProductLiked
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    ProductLikedRes toProductLikedRes(ProductLiked productLiked);
    List<ProductLikedRes> toProductLikedResList(List<ProductLiked> productLikeds);
}
