package com.example.userservice.service.Impl;

import com.example.userservice.Util.EntityDtoUtil;
import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.TransactionStatus;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.UserTransactionRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserTransactionRepository userTransactionRepository;

    @Override
    public Flux<UserDto> findAll() {
        return userRepository.findAll().map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<UserDto> findById(Integer id) {
        return userRepository.findById(id).map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<UserDto> saveUserDetails(Mono<UserDto> userDtoMono) {
        return userDtoMono.map(EntityDtoUtil::toEntity)
                .map(userRepository::save)
                .flatMap(m->m.map(EntityDtoUtil::toDto));
    }

    @Override
    public Mono<UserDto> updateUserDetails(Integer id, Mono<UserDto> userDtoMono) {
        return userRepository.findById(id)
                .flatMap(i->userDtoMono.map(EntityDtoUtil::toEntity))
                .doOnNext(m->m.setId(id))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<Void> deleteUserDetail(Integer id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionResponseDto> getUserTransactionDetails(TransactionRequestDto transactionRequestDtoMono) {
        return userRepository.updateUserDetails(transactionRequestDtoMono.getUserId(),transactionRequestDtoMono.getAmount())
                .map(Boolean::booleanValue)
                .map(m->EntityDtoUtil.toTransactionEntity(transactionRequestDtoMono))
                .flatMap(userTransactionRepository::save)
                .map(m-> EntityDtoUtil.transactionResponseDto(transactionRequestDtoMono, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.transactionResponseDto(transactionRequestDtoMono,TransactionStatus.DECLIEND));
    }
}
