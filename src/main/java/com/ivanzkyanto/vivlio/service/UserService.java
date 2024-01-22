package com.ivanzkyanto.vivlio.service;

import com.ivanzkyanto.vivlio.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

}
