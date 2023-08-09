package org.tattour.server.global.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfo;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.vo.PointChargeRequestInfo;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.vo.StickerLikedInfo;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-09T16:50:16+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 11.0.19 (Amazon.com Inc.)"
)
@Component
public class EntityDtoMapperImpl implements EntityDtoMapper {

    @Override
    public GetUserProfileRes toGetUserProfileRes(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        Integer point = null;

        name = user.getName();
        point = user.getPoint();

        GetUserProfileRes getUserProfileRes = new GetUserProfileRes( name, point );

        return getUserProfileRes;
    }

    @Override
    public GetUserInfoDto toGetUserInfoDto(User user) {
        if ( user == null ) {
            return null;
        }

        GetUserInfoDto getUserInfoDto = new GetUserInfoDto();

        getUserInfoDto.setId( user.getId() );
        getUserInfoDto.setName( user.getName() );
        getUserInfoDto.setPhoneNumber( user.getPhoneNumber() );

        return getUserInfoDto;
    }

    @Override
    public StickerLikedInfo toStickerLikedInfo(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }

        Integer stickerId = null;
        String name = null;
        Integer price = null;
        String mainImageUrl = null;
        Integer id = null;

        stickerId = productLikedStickerId( productLiked );
        name = productLikedStickerName( productLiked );
        price = productLikedStickerPrice( productLiked );
        mainImageUrl = productLikedStickerMainImageUrl( productLiked );
        id = productLiked.getId();

        Integer discountPrice = productLiked.getSticker().getDiscount() != null ? productLiked.getSticker().getPrice() * (100 - productLiked.getSticker().getDiscount().getDiscountRate()) / 100 : null;
        Integer discountRate = productLiked.getSticker().getDiscount() != null ? productLiked.getSticker().getDiscount().getDiscountRate() : null;

        StickerLikedInfo stickerLikedInfo = new StickerLikedInfo( id, stickerId, name, price, discountRate, discountPrice, mainImageUrl );

        return stickerLikedInfo;
    }

    @Override
    public List<StickerLikedInfo> toStickerLikedInfoList(List<ProductLiked> productLiked) {
        if ( productLiked == null ) {
            return null;
        }

        List<StickerLikedInfo> list = new ArrayList<StickerLikedInfo>( productLiked.size() );
        for ( ProductLiked productLiked1 : productLiked ) {
            list.add( toStickerLikedInfo( productLiked1 ) );
        }

        return list;
    }

    @Override
    public UserOrderHistoryInfo toGetUserOrderHistoryRes(Order order) {
        if ( order == null ) {
            return null;
        }

        UserOrderHistoryInfo userOrderHistoryInfo = new UserOrderHistoryInfo();

        Integer id = orderUserId( order );
        if ( id != null ) {
            userOrderHistoryInfo.setUserId( id );
        }
        Integer id1 = orderStickerId( order );
        if ( id1 != null ) {
            userOrderHistoryInfo.setStickerId( id1 );
        }
        if ( order.getId() != null ) {
            userOrderHistoryInfo.setId( order.getId() );
        }
        userOrderHistoryInfo.setProductName( order.getProductName() );
        userOrderHistoryInfo.setProductSize( order.getProductSize() );
        userOrderHistoryInfo.setProductImageUrl( order.getProductImageUrl() );
        if ( order.getProductAmount() != null ) {
            userOrderHistoryInfo.setProductAmount( order.getProductAmount() );
        }
        if ( order.getProductCount() != null ) {
            userOrderHistoryInfo.setProductCount( order.getProductCount() );
        }
        if ( order.getShippingFee() != null ) {
            userOrderHistoryInfo.setShippingFee( order.getShippingFee() );
        }
        if ( order.getTotalAmount() != null ) {
            userOrderHistoryInfo.setTotalAmount( order.getTotalAmount() );
        }
        userOrderHistoryInfo.setRecipientName( order.getRecipientName() );
        userOrderHistoryInfo.setContact( order.getContact() );
        userOrderHistoryInfo.setMailingAddress( order.getMailingAddress() );
        userOrderHistoryInfo.setBaseAddress( order.getBaseAddress() );
        userOrderHistoryInfo.setDetailAddress( order.getDetailAddress() );
        userOrderHistoryInfo.setCreatedAt( order.getCreatedAt() );
        userOrderHistoryInfo.setLastUpdatedAt( order.getLastUpdatedAt() );
        userOrderHistoryInfo.setState( order.getState() );

        return userOrderHistoryInfo;
    }

    @Override
    public List<UserOrderHistoryInfo> toGetUserOrderHistoryListRes(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<UserOrderHistoryInfo> list = new ArrayList<UserOrderHistoryInfo>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( toGetUserOrderHistoryRes( order ) );
        }

        return list;
    }

    @Override
    public OrderHistoryInfo toGetOrderHistoryRes(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderHistoryInfo orderHistoryInfo = new OrderHistoryInfo();

        Integer id = orderUserId( order );
        if ( id != null ) {
            orderHistoryInfo.setUserId( id );
        }
        Integer id1 = orderStickerId( order );
        if ( id1 != null ) {
            orderHistoryInfo.setStickerId( id1 );
        }
        if ( order.getId() != null ) {
            orderHistoryInfo.setId( order.getId() );
        }
        orderHistoryInfo.setProductName( order.getProductName() );
        orderHistoryInfo.setProductSize( order.getProductSize() );
        orderHistoryInfo.setProductImageUrl( order.getProductImageUrl() );
        if ( order.getProductAmount() != null ) {
            orderHistoryInfo.setProductAmount( order.getProductAmount() );
        }
        if ( order.getProductCount() != null ) {
            orderHistoryInfo.setProductCount( order.getProductCount() );
        }
        if ( order.getShippingFee() != null ) {
            orderHistoryInfo.setShippingFee( order.getShippingFee() );
        }
        if ( order.getTotalAmount() != null ) {
            orderHistoryInfo.setTotalAmount( order.getTotalAmount() );
        }
        orderHistoryInfo.setRecipientName( order.getRecipientName() );
        orderHistoryInfo.setContact( order.getContact() );
        orderHistoryInfo.setMailingAddress( order.getMailingAddress() );
        orderHistoryInfo.setBaseAddress( order.getBaseAddress() );
        orderHistoryInfo.setDetailAddress( order.getDetailAddress() );
        orderHistoryInfo.setCreatedAt( order.getCreatedAt() );
        orderHistoryInfo.setLastUpdatedAt( order.getLastUpdatedAt() );
        orderHistoryInfo.setState( order.getState() );

        return orderHistoryInfo;
    }

    @Override
    public List<OrderHistoryInfo> toGetOrderHistoryListRes(Page<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderHistoryInfo> list = new ArrayList<OrderHistoryInfo>();
        for ( Order order : orderList ) {
            list.add( toGetOrderHistoryRes( order ) );
        }

        return list;
    }

    @Override
    public PointChargeRequestInfo toGetPointChargeRequestRes(PointChargeRequest pointChargeRequest) {
        if ( pointChargeRequest == null ) {
            return null;
        }

        PointChargeRequestInfo.ReadPointChargeRequestResBuilder readPointChargeRequestRes = PointChargeRequestInfo.builder();

        Integer id = pointChargeRequestUserId( pointChargeRequest );
        if ( id != null ) {
            readPointChargeRequestRes.userId( id );
        }
        if ( pointChargeRequest.getId() != null ) {
            readPointChargeRequestRes.id( pointChargeRequest.getId() );
        }
        readPointChargeRequestRes.chargeAmount( pointChargeRequest.getChargeAmount() );
        readPointChargeRequestRes.transferredAmount( pointChargeRequest.getTransferredAmount() );
        readPointChargeRequestRes.isDeposited( pointChargeRequest.getIsDeposited() );
        readPointChargeRequestRes.isAmountMatched( pointChargeRequest.getIsAmountMatched() );
        readPointChargeRequestRes.isApproved( pointChargeRequest.getIsApproved() );
        readPointChargeRequestRes.isCompleted( pointChargeRequest.getIsCompleted() );
        readPointChargeRequestRes.createdAt( pointChargeRequest.getCreatedAt() );
        readPointChargeRequestRes.lastUpdatedAt( pointChargeRequest.getLastUpdatedAt() );
        readPointChargeRequestRes.state( pointChargeRequest.getState() );

        return readPointChargeRequestRes.build();
    }

    @Override
    public List<PointChargeRequestInfo> toGetPointChargeRequestResList(List<PointChargeRequest> pointChargeRequestList) {
        if ( pointChargeRequestList == null ) {
            return null;
        }

        List<PointChargeRequestInfo> list = new ArrayList<PointChargeRequestInfo>( pointChargeRequestList.size() );
        for ( PointChargeRequest pointChargeRequest : pointChargeRequestList ) {
            list.add( toGetPointChargeRequestRes( pointChargeRequest ) );
        }

        return list;
    }

    @Override
    public CustomApplySummaryInfo toCustomApplySummaryInfo(Custom custom) {
        if ( custom == null ) {
            return null;
        }

        CustomApplySummaryInfo customApplySummaryInfo = new CustomApplySummaryInfo();

        customApplySummaryInfo.setId( custom.getId() );
        customApplySummaryInfo.setMainImageUrl( custom.getMainImageUrl() );
        customApplySummaryInfo.setName( custom.getName() );
        customApplySummaryInfo.setDescription( custom.getDescription() );
        customApplySummaryInfo.setCreatedAt( custom.getCreatedAt() );

        return customApplySummaryInfo;
    }

    @Override
    public List<CustomApplySummaryInfo> toCustomApplySummaryInfoList(List<Custom> customList) {
        if ( customList == null ) {
            return null;
        }

        List<CustomApplySummaryInfo> list = new ArrayList<CustomApplySummaryInfo>( customList.size() );
        for ( Custom custom : customList ) {
            list.add( toCustomApplySummaryInfo( custom ) );
        }

        return list;
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

    private String productLikedStickerName(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }
        Sticker sticker = productLiked.getSticker();
        if ( sticker == null ) {
            return null;
        }
        String name = sticker.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer productLikedStickerPrice(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }
        Sticker sticker = productLiked.getSticker();
        if ( sticker == null ) {
            return null;
        }
        Integer price = sticker.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }

    private String productLikedStickerMainImageUrl(ProductLiked productLiked) {
        if ( productLiked == null ) {
            return null;
        }
        Sticker sticker = productLiked.getSticker();
        if ( sticker == null ) {
            return null;
        }
        String mainImageUrl = sticker.getMainImageUrl();
        if ( mainImageUrl == null ) {
            return null;
        }
        return mainImageUrl;
    }

    private Integer orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer orderStickerId(Order order) {
        if ( order == null ) {
            return null;
        }
        Sticker sticker = order.getSticker();
        if ( sticker == null ) {
            return null;
        }
        Integer id = sticker.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer pointChargeRequestUserId(PointChargeRequest pointChargeRequest) {
        if ( pointChargeRequest == null ) {
            return null;
        }
        User user = pointChargeRequest.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
