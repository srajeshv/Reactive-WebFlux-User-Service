package com.example.userservice.controller;


import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public Flux<UserDto>getAllDetails(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> findByIdUser(@PathVariable Integer id){
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<UserDto>> saveUserDetails(@RequestBody Mono<UserDto> userDtoMono){
        return userService.saveUserDetails(userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<UserDto>> updateUserDetails(@PathVariable Integer id, @RequestBody Mono<UserDto> userDtoMono){
        return userService.updateUserDetails(id,userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserDetails(@PathVariable Integer userId){
        return userService.deleteUserDetail(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("user/transaction")
    public Mono<TransactionResponseDto>getUserTransactionDetails(@RequestBody Mono<TransactionRequestDto>requestDtoMono){
        return requestDtoMono.flatMap(userService::getUserTransactionDetails);
    }




}
