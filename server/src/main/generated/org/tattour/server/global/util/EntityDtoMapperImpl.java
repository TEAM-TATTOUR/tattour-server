package org.tattour.server.global.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfo;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.dto.response.GetOrderHistoryRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryRes;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestRes;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.dto.response.StickerLikedInfo;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T23:05:33+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
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
    public StickerLikedInfo toStickerLikedInfo(Sticker sticker) {
        if ( sticker == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        Integer price = null;
        String mainImageUrl = null;

        id = sticker.getId();
        name = sticker.getName();
        price = sticker.getPrice();
        mainImageUrl = sticker.getMainImageUrl();

        Integer discountPrice = sticker.getDiscount() != null ? sticker.getPrice() * (100 - sticker.getDiscount().getDiscountRate()) / 100 : null;
        Integer discountRate = sticker.getDiscount() != null ? sticker.getDiscount().getDiscountRate() : null;

        StickerLikedInfo stickerLikedInfo = new StickerLikedInfo( id, name, price, discountRate, discountPrice, mainImageUrl );

        return stickerLikedInfo;
    }

    @Override
    public List<StickerLikedInfo> toStickerLikedInfoList(List<Sticker> stickerList) {
        if ( stickerList == null ) {
            return null;
        }

        List<StickerLikedInfo> list = new ArrayList<StickerLikedInfo>( stickerList.size() );
        for ( Sticker sticker : stickerList ) {
            list.add( toStickerLikedInfo( sticker ) );
        }

        return list;
    }

    @Override
    public GetUserOrderHistoryRes toGetUserOrderHistoryRes(Order order) {
        if ( order == null ) {
            return null;
        }

        GetUserOrderHistoryRes getUserOrderHistoryRes = new GetUserOrderHistoryRes();

        Integer id = orderUserId( order );
        if ( id != null ) {
            getUserOrderHistoryRes.setUserId( id );
        }
        Integer id1 = orderStickerId( order );
        if ( id1 != null ) {
            getUserOrderHistoryRes.setStickerId( id1 );
        }
        if ( order.getId() != null ) {
            getUserOrderHistoryRes.setId( order.getId() );
        }
        getUserOrderHistoryRes.setProductName( order.getProductName() );
        getUserOrderHistoryRes.setProductSize( order.getProductSize() );
        getUserOrderHistoryRes.setProductImageUrl( order.getProductImageUrl() );
        if ( order.getProductAmount() != null ) {
            getUserOrderHistoryRes.setProductAmount( order.getProductAmount() );
        }
        if ( order.getProductCount() != null ) {
            getUserOrderHistoryRes.setProductCount( order.getProductCount() );
        }
        if ( order.getShippingFee() != null ) {
            getUserOrderHistoryRes.setShippingFee( order.getShippingFee() );
        }
        if ( order.getTotalAmount() != null ) {
            getUserOrderHistoryRes.setTotalAmount( order.getTotalAmount() );
        }
        getUserOrderHistoryRes.setRecipientName( order.getRecipientName() );
        getUserOrderHistoryRes.setContact( order.getContact() );
        getUserOrderHistoryRes.setMailingAddress( order.getMailingAddress() );
        getUserOrderHistoryRes.setBaseAddress( order.getBaseAddress() );
        getUserOrderHistoryRes.setDetailAddress( order.getDetailAddress() );
        getUserOrderHistoryRes.setCreatedAt( order.getCreatedAt() );
        getUserOrderHistoryRes.setLastUpdatedAt( order.getLastUpdatedAt() );
        if ( order.getState() != null ) {
            getUserOrderHistoryRes.setState( order.getState() );
        }

        return getUserOrderHistoryRes;
    }

    @Override
    public List<GetUserOrderHistoryRes> toGetUserOrderHistoryListRes(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<GetUserOrderHistoryRes> list = new ArrayList<GetUserOrderHistoryRes>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( toGetUserOrderHistoryRes( order ) );
        }

        return list;
    }

    @Override
    public GetOrderHistoryRes toGetOrderHistoryRes(Order order) {
        if ( order == null ) {
            return null;
        }

        GetOrderHistoryRes getOrderHistoryRes = new GetOrderHistoryRes();

        Integer id = orderUserId( order );
        if ( id != null ) {
            getOrderHistoryRes.setUserId( id );
        }
        Integer id1 = orderStickerId( order );
        if ( id1 != null ) {
            getOrderHistoryRes.setStickerId( id1 );
        }
        if ( order.getId() != null ) {
            getOrderHistoryRes.setId( order.getId() );
        }
        getOrderHistoryRes.setProductName( order.getProductName() );
        getOrderHistoryRes.setProductSize( order.getProductSize() );
        getOrderHistoryRes.setProductImageUrl( order.getProductImageUrl() );
        if ( order.getProductAmount() != null ) {
            getOrderHistoryRes.setProductAmount( order.getProductAmount() );
        }
        if ( order.getProductCount() != null ) {
            getOrderHistoryRes.setProductCount( order.getProductCount() );
        }
        if ( order.getShippingFee() != null ) {
            getOrderHistoryRes.setShippingFee( order.getShippingFee() );
        }
        if ( order.getTotalAmount() != null ) {
            getOrderHistoryRes.setTotalAmount( order.getTotalAmount() );
        }
        getOrderHistoryRes.setRecipientName( order.getRecipientName() );
        getOrderHistoryRes.setContact( order.getContact() );
        getOrderHistoryRes.setMailingAddress( order.getMailingAddress() );
        getOrderHistoryRes.setBaseAddress( order.getBaseAddress() );
        getOrderHistoryRes.setDetailAddress( order.getDetailAddress() );
        getOrderHistoryRes.setCreatedAt( order.getCreatedAt() );
        getOrderHistoryRes.setLastUpdatedAt( order.getLastUpdatedAt() );
        if ( order.getState() != null ) {
            getOrderHistoryRes.setState( order.getState() );
        }

        return getOrderHistoryRes;
    }

    @Override
    public List<GetOrderHistoryRes> toGetOrderHistoryListRes(Page<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<GetOrderHistoryRes> list = new ArrayList<GetOrderHistoryRes>();
        for ( Order order : orderList ) {
            list.add( toGetOrderHistoryRes( order ) );
        }

        return list;
    }

    @Override
    public GetPointChargeRequestRes toGetPointChargeRequestRes(PointChargeRequest pointChargeRequest) {
        if ( pointChargeRequest == null ) {
            return null;
        }

        GetPointChargeRequestRes.GetPointChargeRequestResBuilder getPointChargeRequestRes = GetPointChargeRequestRes.builder();

        Integer id = pointChargeRequestUserId( pointChargeRequest );
        if ( id != null ) {
            getPointChargeRequestRes.userId( id );
        }
        if ( pointChargeRequest.getId() != null ) {
            getPointChargeRequestRes.id( pointChargeRequest.getId() );
        }
        getPointChargeRequestRes.chargeAmount( pointChargeRequest.getChargeAmount() );
        getPointChargeRequestRes.transferredAmount( pointChargeRequest.getTransferredAmount() );
        getPointChargeRequestRes.isDeposited( pointChargeRequest.getIsDeposited() );
        getPointChargeRequestRes.isAmountMatched( pointChargeRequest.getIsAmountMatched() );
        getPointChargeRequestRes.isApproved( pointChargeRequest.getIsApproved() );
        getPointChargeRequestRes.isCompleted( pointChargeRequest.getIsCompleted() );
        getPointChargeRequestRes.createdAt( pointChargeRequest.getCreatedAt() );
        getPointChargeRequestRes.lastUpdatedAt( pointChargeRequest.getLastUpdatedAt() );
        if ( pointChargeRequest.getState() != null ) {
            getPointChargeRequestRes.state( pointChargeRequest.getState() );
        }

        return getPointChargeRequestRes.build();
    }

    @Override
    public List<GetPointChargeRequestRes> toGetPointChargeRequestResList(List<PointChargeRequest> pointChargeRequestList) {
        if ( pointChargeRequestList == null ) {
            return null;
        }

        List<GetPointChargeRequestRes> list = new ArrayList<GetPointChargeRequestRes>( pointChargeRequestList.size() );
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
