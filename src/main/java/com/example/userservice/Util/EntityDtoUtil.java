package com.example.userservice.Util;
import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.TransactionStatus;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.entity.UserTransactionEntity;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static java.lang.System.*;

public class EntityDtoUtil {
    public static UserDto toDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }
    public static UserEntity toEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return userEntity;
    }
    public static UserTransactionEntity toTransactionEntity(TransactionRequestDto transactionRequestDto){
        UserTransactionEntity userTransactionEntity = new UserTransactionEntity();
        userTransactionEntity.setUser_Id(transactionRequestDto.getUserId());
        userTransactionEntity.setAmount(transactionRequestDto.getAmount());
        userTransactionEntity.setTransaction_Date(OffsetDateTime.now(ZoneOffset.UTC));
        out.println(LocalDateTime.now());
        return userTransactionEntity;
    }
    public static TransactionResponseDto transactionResponseDto(TransactionRequestDto requestDto, TransactionStatus transactionStatus){
        TransactionResponseDto responseDto = new TransactionResponseDto();
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setStatus(transactionStatus);
        return responseDto;
    }
}
