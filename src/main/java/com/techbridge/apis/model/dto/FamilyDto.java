package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FamilyDto {
    private Long primaryMemId;
    private String name;
    private Long isActive;
}
