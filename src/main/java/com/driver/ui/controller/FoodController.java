package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.Converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto=foodService.getFoodById(id);
		return FoodConverter.convertDtoToResponse(foodDto);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) throws Exception {
		FoodDto foodDto= FoodConverter.convertRequestToDto(foodDetails);
		foodDto= foodService.createFood(foodDto);
		return FoodConverter.convertDtoToResponse(foodDto);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=FoodConverter.convertRequestToDto(foodDetails);
		foodDto=foodService.updateFoodDetails(id,foodDto);
		return FoodConverter.convertDtoToResponse(foodDto);

	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel op=OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.name())
				.operationResult(RequestOperationStatus.SUCCESS.name())
				.build();
		foodService.deleteFoodItem(id);
		return op;
	}

	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> foodList=new ArrayList<>();
		List<FoodDto> foodDto=foodService.getFoods();
		for (FoodDto food : foodDto) {
			FoodDetailsResponse foodDetailsResponse = FoodConverter.convertDtoToResponse(food);
			foodList.add(foodDetailsResponse);
		}
		return foodList;

	}
}
