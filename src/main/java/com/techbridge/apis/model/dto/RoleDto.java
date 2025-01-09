package com.techbridge.apis.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RoleDto {
    private String  name;
    private String description;
    private Long isActive;
}
