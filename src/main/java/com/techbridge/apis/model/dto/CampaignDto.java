package com.techbridge.apis.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CampaignDto {
    private String name;
    private String description;
    private String message;
    private Date dispatchDate;
}
