package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BiodataDto {
    private Long bdtId;
    private Long gndId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private String phone1;
    private String phone2;
    private LocalDate dob;
}
