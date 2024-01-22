package com.application.superapplication.sample;

import com.application.superapplication.service.dto.UserRequestDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserRequestSample {
    public static UserRequestDto oneUserRequestSample() {
        return UserRequestDto.builder()
                .name("julien")
                .age(26)
                .height(165)
                .build();
    }
}
