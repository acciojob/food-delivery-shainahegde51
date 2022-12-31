package com.driver.service.impl;

import com.driver.io.Converter.RandomStringGenerator;
import com.driver.io.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;

import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RandomStringGenerator stringGenerator;
    @Override
    public UserDto createUser(UserDto user) throws  Exception {
        //log.info("from service");
        UserEntity userExist=userRepository.findByUserId(user.getUserId());
        if(userExist!=null)
            throw new Exception("user  already exists...!!");

        UserEntity userEntity = UserConverter.convertDtoToEntity(user);
        userEntity.setUserId(stringGenerator.generateUserId(30));
        userEntity=userRepository.save(userEntity);
        return UserConverter.convertEntityToDto(userEntity);

    }


    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null)
            throw new Exception("user not found...!!");
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if (userEntity == null)
            throw new Exception("user not found...!!");
        UserDto userDto= UserConverter.convertEntityToDto(userEntity);
        userDto.setUserId(userEntity.getUserId());
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDetails) throws Exception {
        long id= userRepository.findByUserId(userId).getId();
        if (id<=0 ) {
            throw new Exception("user Not Found...!!");
        }
        UserEntity user= UserEntity.builder()
                .id(id)
                .userId(userDetails.getUserId())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .build();
        user=userRepository.save(user);
        return UserConverter.convertEntityToDto(user);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity user=userRepository.findByUserId(userId);
        if(user==null)
            throw new Exception ("user not found!....");
        long id=user.getId();
        userRepository.deleteById(id);

    }

    @Override
    public List<UserDto> getUsers() {

        List<UserDto> userList = new ArrayList<>();
        List<UserEntity> userlist;
        userlist = userRepository.findAll();

        for (UserEntity user : userlist) {
            UserDto userDto = UserConverter.convertEntityToDto(user);
            userList.add(userDto);
        }
        return userList;
    }

}