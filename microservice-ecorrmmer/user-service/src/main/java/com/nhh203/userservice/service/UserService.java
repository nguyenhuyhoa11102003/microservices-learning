package com.nhh203.userservice.service;

import com.nhh203.userservice.model.dto.request.ChangePasswordRequest;
import com.nhh203.userservice.model.dto.request.Login;
import com.nhh203.userservice.model.dto.request.SignUp;
import com.nhh203.userservice.model.dto.request.UserDto;
import com.nhh203.userservice.model.dto.response.JwtResponseMessage;
import com.nhh203.userservice.model.entity.User;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserService {

    Mono<User> register(SignUp signUp);

    Mono<JwtResponseMessage> login(Login signInForm);

    Mono<Void> logout();

    Mono<User> update(Long userId, SignUp update);

    Mono<String> changePassword(ChangePasswordRequest request);

    String delete(Long id);

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String userName);

    Page<UserDto> findAllUsers(int page, int size, String sortBy, String sortOrder);

}
