package com.techbridge.apis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BranchDto {
    private Long ctyId;
    private String area;
    private String branchName;
    private String description;
}
