package com.driver.io.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {


    public static OrderDto convertEntityToDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .cost(orderEntity.getCost())
                .items(orderEntity.getItems())
                .userId(orderEntity.getUserId())
                .status(orderEntity.isStatus())
                .build();
    }

    public static OrderDetailsResponse convertDtoToResponse(OrderDto orderDto) {
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(orderDto.isStatus())
                .build();
    }

    public static OrderDto convertRequestToDto(OrderDetailsRequestModel order) {
        return OrderDto.builder()
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId())
                .build();
    }

    public static OrderEntity convertDtoToEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(false)
                .build();
    }
}