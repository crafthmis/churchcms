package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Family;
import com.techbridge.apis.model.dto.FamilyDto;
import com.techbridge.apis.repository.FamilyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class FamilyService {
    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public ResponseEntity<Object> getFamilies() {
        List<FamilyDto> FamilyDtos =  familyRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new FamilyDto(dto.getPrimaryMemId(),dto.getName(), dto.getIsActive()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",FamilyDtos);
    }

    public ResponseEntity<Object> getFamily(Long id) {
        Optional<Family> Family = familyRepository.findById(id);
        if (Family.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such family with id: " + id);
        }
        FamilyDto FamilyDto = Family.map(dto -> new FamilyDto(dto.getPrimaryMemId(),dto.getName(), dto.getIsActive())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", FamilyDto);
    }

    public ResponseEntity<Object> createNewFamily(Family family) {

        Optional<Family> optionalFamily = familyRepository.findByMemberId(String.valueOf(family.getPrimaryMemId()));
        if (optionalFamily.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Family already exists");
        }
        if (null != family.getName() && !family.getName().isEmpty()) {
            family.setIsActive(1L);
            familyRepository.save(family);
            throw new CustomErrorException(HttpStatus.CREATED, "Family created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteFamily(Long id) {
        boolean exists = familyRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        familyRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Family deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateFamily(Long FamilyId, FamilyDto dto) {
        Family family = familyRepository.findById(FamilyId).orElseThrow(() ->
                new IllegalArgumentException("No such Family with id: " + FamilyId));
        if (!Objects.isNull(dto)) {
            family.setName(dto.getName());
            familyRepository.save(family);
            throw new CustomErrorException(HttpStatus.CREATED, "Family updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


