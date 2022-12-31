package com.driver.io.Converter;


import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

public class FoodConverter {
    public static FoodDto convertEntityToDto(FoodEntity food) {
        return FoodDto.builder()
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory())
                .build();
    }

    public static FoodDetailsResponse convertDtoToResponse(FoodDto food) {
        return FoodDetailsResponse.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory())
                .build();
    }

    public static FoodDto convertRequestToDto(FoodDetailsRequestModel food) {
        return FoodDto.builder()
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .build();
    }

    public static FoodEntity convertDtoToEntity(FoodDto food) {
        return FoodEntity.builder()
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory())
                .build();
    }
}

