package com.driver.service.impl;

import com.driver.io.Converter.FoodConverter;

import com.driver.io.Converter.RandomStringGenerator;
import com.driver.io.entity.FoodEntity;

import com.driver.io.repository.FoodRepository;

import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RandomStringGenerator randomStringGenerator;

    @Override
    public FoodDto createFood(FoodDto food)  {
        FoodEntity foodPresent = foodRepository.findByFoodId(food.getFoodId());
        if (foodPresent != null) {
            throw new RuntimeException("Food already exists...!!");
        }
        FoodEntity foodEntity = FoodConverter.convertDtoToEntity(food);
        foodEntity.setFoodId(randomStringGenerator.generateFoodId(30));
        foodEntity = foodRepository.save(foodEntity);


        return FoodConverter.convertEntityToDto(foodEntity);

    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        if (food == null) {
            throw new Exception("Food not found...!!");
        }
        return FoodConverter.convertEntityToDto(food);

    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        long id = foodRepository.findByFoodId(foodId).getId();
        if (id<=0 ) {
            throw new Exception("food Not Found...!!");
        }
        FoodEntity food = FoodEntity.builder()
                .id(id)
                .foodName(foodDetails.getFoodName())
                .foodCategory(foodDetails.getFoodCategory())
                .foodPrice(foodDetails.getFoodPrice())
                .foodId(foodId)
                .build();
        food = foodRepository.save(food);
        return FoodConverter.convertEntityToDto(food);

    }

    @Override
    public void deleteFoodItem(String foodId) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        if (food == null)
            throw new Exception("food not found!....");
        long id = food.getId();
        foodRepository.deleteById(id);

    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodList = new ArrayList<>();
        List<FoodEntity> foodlist;
        foodlist = foodRepository.findAll();

        for (FoodEntity foodEntity : foodlist) {
            FoodDto foodDto = FoodConverter.convertEntityToDto(foodEntity);
            foodList.add(foodDto);
        }
        return foodList;
    }
}