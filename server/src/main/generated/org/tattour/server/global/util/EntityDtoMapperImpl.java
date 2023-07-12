package org.tattour.server.global.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.service.dto.response.ProductLikedRes;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-12T01:06:13+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class EntityDtoMapperImpl implements EntityDtoMapper {

    @Override
    public GetUserProfileRes toGetUserProfileRes(User user) {
        if ( user == null ) {
            return null;
        }

        GetUserProfileRes.GetUserProfileResBuilder getUserProfileRes = GetUserProfileRes.builder();

        getUserProfileRes.name( user.getName() );
        getUserProfileRes.point( user.getPoint() );

        return getUserProfileRes.build();
    }

    @Override
    public ProductLikedRes toProductLikedRes(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }

        ProductLikedRes productLikedRes = new ProductLikedRes();

        productLikedRes.setUserId( productLikedUserId( productLiked ) );
        productLikedRes.setStickerId( productLikedStickerId( productLiked ) );
        productLikedRes.setId( productLiked.getId() );
        productLikedRes.setCreatedAt( productLiked.getCreatedAt() );

        return productLikedRes;
    }

    @Override
    public List<ProductLikedRes> toProductLikedResList(List<ProductLiked> productLikeds) {
        if ( productLikeds == null ) {
            return null;
        }

        List<ProductLikedRes> list = new ArrayList<ProductLikedRes>( productLikeds.size() );
        for ( ProductLiked productLiked : productLikeds ) {
            list.add( toProductLikedRes( productLiked ) );
        }

        return list;
    }

    private Integer productLikedUserId(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }
        User user = productLiked.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer productLikedStickerId(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }
        Sticker sticker = productLiked.getSticker();
        if ( sticker == null ) {
            return null;
        }
        Integer id = sticker.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
