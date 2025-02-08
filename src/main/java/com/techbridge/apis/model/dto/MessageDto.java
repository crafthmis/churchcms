package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDto {
    private Long msgId;
    private Long cmpId;
    private Long usrId;
    private String phone;
    private String msg;
}
