package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.Converter.UserConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserServiceImpl userService;
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto=userService.getUserByUserId(id);
		return UserConverter.convertDtoToResponse(userDto);
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		UserDto userDto= UserConverter.convertRequestToDto(userDetails);

		userDto=userService.createUser(userDto);
		return UserConverter.convertDtoToResponse(userDto);
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto= UserConverter.convertRequestToDto(userDetails);
		userDto=userService.updateUser(id,userDto);
		return UserConverter.convertDtoToResponse(userDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		OperationStatusModel op=OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.name())
				.operationResult(RequestOperationStatus.SUCCESS.name())
				.build();
		userService.deleteUser(id);;
		return op;
	}

	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserResponse> userList=new ArrayList<>();
		List<UserDto> userDto=userService.getUsers();
		for (UserDto user : userDto) {
			UserResponse userResponse = UserConverter.convertDtoToResponse(user);
			userList.add(userResponse);
		}
		return userList;
	}

}