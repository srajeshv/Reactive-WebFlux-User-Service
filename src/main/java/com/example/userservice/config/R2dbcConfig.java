/*
package com.example.userservice.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Override
    public ConnectionFactory connectionFactory() {
        return new MySqlConn(
                Mysql.builder()
                        .host("localhost")
                        .port(3306)
                        .database("your_database_name")
                        .username("your_username")
                        .password("your_password")
                        .build()
        );
    }
}
*/
