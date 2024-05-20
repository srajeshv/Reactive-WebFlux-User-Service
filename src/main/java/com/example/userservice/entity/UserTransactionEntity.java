package com.example.userservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Table(value = "user_transactions")
public class UserTransactionEntity {
    @Id
    private Integer id;
    private Integer user_Id;
    private Integer amount;
    private OffsetDateTime transaction_Date;
}
