package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private Long brnId;
    private Long etyId;
    private Long isPledgeable;
    private String name;
    private String description;
    private Date fromDate;
    private Date toDate;
}
