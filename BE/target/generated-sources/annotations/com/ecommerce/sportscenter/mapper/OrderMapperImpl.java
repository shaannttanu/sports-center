package com.ecommerce.sportscenter.mapper;

import com.ecommerce.sportscenter.entity.OrderAggregate.Order;
import com.ecommerce.sportscenter.entity.OrderAggregate.Order.OrderBuilder;
import com.ecommerce.sportscenter.entity.OrderAggregate.OrderStatus;
import com.ecommerce.sportscenter.model.OrderDto;
import com.ecommerce.sportscenter.model.OrderDto.OrderDtoBuilder;
import com.ecommerce.sportscenter.model.OrderResponse;
import com.ecommerce.sportscenter.model.OrderResponse.OrderResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-28T13:43:51+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse OrderToOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.basketId( order.getBasketId() );
        orderResponse.shippingAddress( order.getShippingAddress() );
        if ( order.getSubTotal() != null ) {
            orderResponse.subTotal( order.getSubTotal().longValue() );
        }
        orderResponse.deliveryFee( order.getDeliveryFee() );

        orderResponse.total( order.getSubTotal() + order.getDeliveryFee() );
        orderResponse.orderDate( java.time.LocalDateTime.now() );
        orderResponse.orderStatus( OrderStatus.Pending );

        return orderResponse.build();
    }

    @Override
    public Order orderResponseToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.basketId( orderDto.getBasketId() );
        order.shippingAddress( orderDto.getShippingAddress() );
        if ( orderDto.getSubTotal() != null ) {
            order.subTotal( orderDto.getSubTotal().doubleValue() );
        }
        order.deliveryFee( orderDto.getDeliveryFee() );

        order.orderDate( orderDto.getOrderDate() );
        order.orderStatus( OrderStatus.Pending );

        return order.build();
    }

    @Override
    public List<OrderDto> ordersToOrderResponses(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderDto( order ) );
        }

        return list;
    }

    @Override
    public void updateOrderFromOrderResponse(OrderDto orderDto, Order order) {
        if ( orderDto == null ) {
            return;
        }

        order.setBasketId( orderDto.getBasketId() );
        order.setShippingAddress( orderDto.getShippingAddress() );
        order.setOrderDate( orderDto.getOrderDate() );
        if ( orderDto.getSubTotal() != null ) {
            order.setSubTotal( orderDto.getSubTotal().doubleValue() );
        }
        else {
            order.setSubTotal( null );
        }
        order.setDeliveryFee( orderDto.getDeliveryFee() );
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.basketId( order.getBasketId() );
        orderDto.shippingAddress( order.getShippingAddress() );
        if ( order.getSubTotal() != null ) {
            orderDto.subTotal( order.getSubTotal().longValue() );
        }
        orderDto.deliveryFee( order.getDeliveryFee() );
        orderDto.orderDate( order.getOrderDate() );

        return orderDto.build();
    }
}
