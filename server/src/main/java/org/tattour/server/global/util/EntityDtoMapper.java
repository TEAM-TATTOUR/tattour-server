package org.tattour.server.global.util;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.service.dto.response.GetUserRes;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    @Mapping(target = "userId", source = "user.id")
    GetUserRes toUserRes(User user);
}
