package com.example.medatlas.mapper;

import com.example.medatlas.dto.UserDTO;
import com.example.medatlas.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> userList);
}