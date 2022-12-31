package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.Converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto=orderService.getOrderById(id);
		return OrderConverter.convertDtoToResponse(orderDto);
	}

	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) throws Exception {
		OrderDto orderDto = OrderConverter.convertRequestToDto(order);
		orderDto=orderService.createOrder(orderDto);
		return OrderConverter.convertDtoToResponse(orderDto);

	}

	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = OrderConverter.convertRequestToDto(order);
		orderDto=orderService.updateOrderDetails(id,orderDto);
		return OrderConverter.convertDtoToResponse(orderDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel op=OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.name())
				.operationResult(RequestOperationStatus.SUCCESS.name())
				.build();
		orderService.deleteOrder(id);
		return op;
	}

	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> orderList=new ArrayList<>();
		List<OrderDto> orderDto=orderService.getOrders();
		for (OrderDto order : orderDto) {
			OrderDetailsResponse orderResponse = OrderConverter.convertDtoToResponse(order);
			orderList.add(orderResponse);
		}
		return orderList;
	}
}