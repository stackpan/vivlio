package com.ivanzkyanto.vivlio.repository.gateway;

import com.ivanzkyanto.vivlio.model.User;

import java.util.Optional;

public interface UserGatewayRepository {

    Optional<User> findByName(String name);

    User create(User user);

}
