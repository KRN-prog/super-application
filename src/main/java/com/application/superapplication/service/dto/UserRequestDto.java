package com.application.superapplication.service.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDto {
    @NotEmpty(message = "Name must not be empty")
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;

    @NotNull
    @Max(value = 99, message = "Age must not exceed 99")
    @Min(1)
    private Integer age;

    @NotNull(message = "Height must not be null")
    @DecimalMax(value = "2.5", message = "Height must be less than 1 or equal to 2.5")
    @DecimalMin(value = "1.0", message = "Height must be less than 1 or equal to 2.5")
    private Integer height;
}
