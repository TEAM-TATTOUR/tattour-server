package org.tattour.server.global.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.facade.dto.response.CreateCustomSummaryRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryRes;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.domain.OrderStatus;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.vo.PointChargeRequestInfo;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.vo.StickerLikedInfo;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.vo.HomeUserInfo;
import org.tattour.server.domain.user.provider.vo.UserContactInfo;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-14T23:31:38+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class EntityDtoMapperImpl implements EntityDtoMapper {

    @Override
    public HomeUserInfo toHomeUserInfo(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        Integer point = null;

        name = user.getName();
        point = user.getPoint();

        HomeUserInfo homeUserInfo = new HomeUserInfo( name, point );

        return homeUserInfo;
    }

    @Override
    public UserContactInfo toUserContactInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserContactInfo userContactInfo = new UserContactInfo();

        userContactInfo.setId( user.getId() );
        userContactInfo.setName( user.getName() );
        userContactInfo.setPhoneNumber( user.getPhoneNumber() );

        return userContactInfo;
    }

    @Override
    public UserProfileInfo toUserProfileInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserProfileInfo.UserProfileInfoBuilder userProfileInfo = UserProfileInfo.builder();

        if ( user.getId() != null ) {
            userProfileInfo.id( user.getId() );
        }
        userProfileInfo.name( user.getName() );
        userProfileInfo.phoneNumber( user.getPhoneNumber() );

        return userProfileInfo.build();
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
    public OrderHistoryInfo toOrderHistoryInfo(Order order) {
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
        orderHistoryInfo.setOrderStatus( orderOrderStatusValue( order ) );
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
    public List<OrderHistoryInfo> toOrderHistoryInfoPage(Page<Order> orderPage) {
        if ( orderPage == null ) {
            return null;
        }

        List<OrderHistoryInfo> list = new ArrayList<OrderHistoryInfo>();
        for ( Order order : orderPage ) {
            list.add( toOrderHistoryInfo( order ) );
        }

        return list;
    }

    @Override
    public PointChargeRequestInfo toGetPointChargeRequestRes(PointChargeRequest pointChargeRequest) {
        if ( pointChargeRequest == null ) {
            return null;
        }

        PointChargeRequestInfo.PointChargeRequestInfoBuilder pointChargeRequestInfo = PointChargeRequestInfo.builder();

        Integer id = pointChargeRequestUserId( pointChargeRequest );
        if ( id != null ) {
            pointChargeRequestInfo.userId( id );
        }
        if ( pointChargeRequest.getId() != null ) {
            pointChargeRequestInfo.id( pointChargeRequest.getId() );
        }
        pointChargeRequestInfo.chargeAmount( pointChargeRequest.getChargeAmount() );
        pointChargeRequestInfo.transferredAmount( pointChargeRequest.getTransferredAmount() );
        pointChargeRequestInfo.isDeposited( pointChargeRequest.getIsDeposited() );
        pointChargeRequestInfo.isAmountMatched( pointChargeRequest.getIsAmountMatched() );
        pointChargeRequestInfo.isApproved( pointChargeRequest.getIsApproved() );
        pointChargeRequestInfo.isCompleted( pointChargeRequest.getIsCompleted() );
        pointChargeRequestInfo.createdAt( pointChargeRequest.getCreatedAt() );
        pointChargeRequestInfo.lastUpdatedAt( pointChargeRequest.getLastUpdatedAt() );
        pointChargeRequestInfo.state( pointChargeRequest.getState() );

        return pointChargeRequestInfo.build();
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
    public CreateCustomSummaryRes toCustomApplySummaryInfo(Custom custom) {
        if ( custom == null ) {
            return null;
        }

        CreateCustomSummaryRes createCustomSummaryRes = new CreateCustomSummaryRes();

        createCustomSummaryRes.setId( custom.getId() );
        createCustomSummaryRes.setMainImageUrl( custom.getMainImageUrl() );
        createCustomSummaryRes.setName( custom.getName() );
        createCustomSummaryRes.setDescription( custom.getDescription() );
        createCustomSummaryRes.setCreatedAt( custom.getCreatedAt() );

        return createCustomSummaryRes;
    }

    @Override
    public List<CreateCustomSummaryRes> toCustomApplySummaryInfoList(List<Custom> customList) {
        if ( customList == null ) {
            return null;
        }

        List<CreateCustomSummaryRes> list = new ArrayList<CreateCustomSummaryRes>( customList.size() );
        for ( Custom custom : customList ) {
            list.add( toCustomApplySummaryInfo( custom ) );
        }

        return list;
    }

    @Override
    public ReadCustomSummaryRes toReadCustomSummaryRes(Custom custom) {
        if ( custom == null ) {
            return null;
        }

        ReadCustomSummaryRes.ReadCustomSummaryResBuilder readCustomSummaryRes = ReadCustomSummaryRes.builder();

        readCustomSummaryRes.imageUrl( custom.getMainImageUrl() );
        readCustomSummaryRes.id( custom.getId() );
        readCustomSummaryRes.name( custom.getName() );

        return readCustomSummaryRes.build();
    }

    @Override
    public List<ReadCustomSummaryRes> toReadCustomSummaryResList(List<Custom> customList) {
        if ( customList == null ) {
            return null;
        }

        List<ReadCustomSummaryRes> list = new ArrayList<ReadCustomSummaryRes>( customList.size() );
        for ( Custom custom : customList ) {
            list.add( toReadCustomSummaryRes( custom ) );
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

    private String orderOrderStatusValue(Order order) {
        if ( order == null ) {
            return null;
        }
        OrderStatus orderStatus = order.getOrderStatus();
        if ( orderStatus == null ) {
            return null;
        }
        String value = orderStatus.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
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
