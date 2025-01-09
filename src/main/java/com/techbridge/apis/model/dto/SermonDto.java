package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SermonDto {
    private Long brnId;
    private String title;
    private String description;
    private LocalDate sermonDate;
}
