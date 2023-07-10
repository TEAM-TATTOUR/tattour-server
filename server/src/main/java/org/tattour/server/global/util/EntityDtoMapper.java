package org.tattour.server.global.util;

import java.util.List;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tattour.server.user.domain.ProductLiked;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.service.dto.response.GetUserProfileRes;
import org.tattour.server.user.service.dto.response.ProductLikedRes;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    @Mapping(target = "id", source = "user.id")
    GetUserProfileRes toGetUserProfileRes(User user);

    // ProductLiked
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    ProductLikedRes toProductLikedRes(ProductLiked productLiked);
    List<ProductLikedRes> toProductLikedResList(List<ProductLiked> productLikeds);
}
