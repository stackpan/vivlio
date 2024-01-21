package com.ivanzkyanto.vivlio.util;

import com.ivanzkyanto.vivlio.controller.dto.UserRequest;
import com.ivanzkyanto.vivlio.controller.dto.UserResponse;
import com.ivanzkyanto.vivlio.entity.UserEntity;
import com.ivanzkyanto.vivlio.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toModel(UserEntity entity) {
        return mapper.map(entity, User.class);
    }

    public User toModel(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserEntity toEntity(User user) {
        return mapper.map(user, UserEntity.class);
    }

    public UserResponse toResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }

}
