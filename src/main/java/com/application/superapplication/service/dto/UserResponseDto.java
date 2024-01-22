package com.application.superapplication.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDto {
    private Integer id;
    private String name;
    private Integer age;
    private Integer height;
}
