package com.example.userservice.service;

import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDto> findAll();
    Mono<UserDto> findById(Integer id);
    Mono<UserDto> saveUserDetails(Mono<UserDto>userDtoMono);
    Mono<UserDto> updateUserDetails(Integer id,Mono<UserDto>userDtoMono);
    Mono<Void> deleteUserDetail(Integer id);

    Mono<TransactionResponseDto>getUserTransactionDetails(TransactionRequestDto transactionRequestDtoMono);
}
