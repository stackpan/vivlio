package com.ivanzkyanto.vivlio.repository.gateway.impl;

import com.ivanzkyanto.vivlio.entity.UserEntity;
import com.ivanzkyanto.vivlio.exception.ResourceAlreadyExistsException;
import com.ivanzkyanto.vivlio.model.User;
import com.ivanzkyanto.vivlio.repository.UserRepository;
import com.ivanzkyanto.vivlio.repository.gateway.UserGatewayRepository;
import com.ivanzkyanto.vivlio.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserGatewayRepositoryImpl implements UserGatewayRepository {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<User> findByName(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByName(name);

        if (optionalUser.isEmpty())
            return Optional.empty();

        User user = userMapper.toModel(optionalUser.get());
        return Optional.of(user);
    }

    @Override
    public User create(User user) {
        userRepository.findByNameOrEmail(user.getName(), user.getEmail()).ifPresent(entity -> {
            throw new ResourceAlreadyExistsException(User.class.getSimpleName(), entity.getId().toString());
        });

        UserEntity entity = userMapper.toEntity(user);

        UserEntity saved = userRepository.save(entity);

        return userMapper.toModel(saved);
    }

}
