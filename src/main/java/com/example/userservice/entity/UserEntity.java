package com.example.userservice.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(value = "users")
public class UserEntity {

    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
