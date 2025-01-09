package com.techbridge.apis.service;


import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Branch;
import com.techbridge.apis.model.dto.BranchDto;
import com.techbridge.apis.repository.BranchRepository;
import com.techbridge.apis.repository.CountyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public ResponseEntity<Object> getBranches() {
        List<BranchDto> BranchDtos =  branchRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new BranchDto(dto.getCtyId(),dto.getArea(),dto.getBranchName(), dto.getDescription()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",BranchDtos);
    }

    public ResponseEntity<Object> getBranch(Long id) {
        Optional<Branch> Branch = branchRepository.findById(id);
        if (Branch.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such branch with id: " + id);
        }
        BranchDto BranchDto = Branch.map(dto -> new BranchDto(dto.getCtyId(),dto.getArea(),dto.getBranchName(), dto.getDescription())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", BranchDto);
    }

    public ResponseEntity<Object> createNewBranch(Branch branch) {

        Optional<Branch> optionalBranch = branchRepository.findByName(branch.getBranchName());
        if (optionalBranch.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Branch already exists");
        }
        if (null != branch.getBranchName() && !branch.getBranchName().isEmpty()) {
            branchRepository.save(branch);
            throw new CustomErrorException(HttpStatus.CREATED, "Branch created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteBranch(Long id) {
        boolean exists = branchRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        branchRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Branch deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateBranch(Long BranchId, String name) {
        Branch branch = branchRepository.findById(BranchId).orElseThrow(() ->
                new IllegalArgumentException("No such Branch with id: " + BranchId));
        if (name != null && !name.isEmpty() && !Objects.equals(branch.getBranchName(), name)) {
            branch.setBranchName(name);
            branchRepository.save(branch);
            throw new CustomErrorException(HttpStatus.CREATED, "Branch updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}

