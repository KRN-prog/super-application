package com.application.superapplication.sample;

import com.application.superapplication.service.dto.UserResponseDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserResponseSample {
    public static UserResponseDto oneUserResponseSample() {
        return UserResponseDto.builder()
                .id(1)
                .name("jean")
                .age(5)
                .height(200)
                .build();
    }

    public static UserResponseDto oneOtherUserResponseSample() {
        return UserResponseDto.builder()
                .id(2)
                .name("jeanne")
                .age(15)
                .height(175)
                .build();
    }
}
