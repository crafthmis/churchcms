package com.techbridge.apis.service;


import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Gender;
import com.techbridge.apis.model.dto.GenderDto;
import com.techbridge.apis.repository.GenderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class GenderService {
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public ResponseEntity<Object> getGenders() {
        List<GenderDto> GenderDtos =  genderRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new GenderDto(dto.getName()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",GenderDtos);
    }

    public ResponseEntity<Object> getGender(Long id) {
        Optional<Gender> Gender = genderRepository.findById(id);
        if (Gender.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such gender with id: " + id);
        }
        GenderDto genderDto = Gender.map(dto -> new GenderDto(dto.getName())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", genderDto);
    }

    public ResponseEntity<Object> createNewGender(Gender gender) {

        Optional<Gender> optionalGender = genderRepository.findByName(gender.getName());
        if (optionalGender.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Gender already exists");
        }
        if (null != gender.getName() && !gender.getName().isEmpty()) {
            genderRepository.save(gender);
            throw new CustomErrorException(HttpStatus.CREATED, "Gender created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteGender(Long id) {
        boolean exists = genderRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        genderRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Gender deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateGender(Long GenderId, String name) {
        Gender gender = genderRepository.findById(GenderId).orElseThrow(() ->
                new IllegalArgumentException("No such Gender with id: " + GenderId));
        if (name != null && !name.isEmpty() && !Objects.equals(gender.getName(), name)) {
            gender.setName(name);
            genderRepository.save(gender);
            throw new CustomErrorException(HttpStatus.CREATED, "Gender updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}

