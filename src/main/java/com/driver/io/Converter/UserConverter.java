package com.driver.io.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class UserConverter {

    @Autowired
    RandomStringGenerator stringGenerator;

    public static UserDto convertEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userId(userEntity.getUserId())
                .build();
    }
    public static UserResponse convertDtoToResponse(UserDto userDto) {
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public static UserDto convertRequestToDto(UserDetailsRequestModel userDetails) {
        return UserDto.builder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .build();
    }

    public static UserEntity convertDtoToEntity(UserDto user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}