package com.techbridge.apis.service;


import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.County;
import com.techbridge.apis.model.dto.CountyDto;
import com.techbridge.apis.repository.CountyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CountyService {
    private final CountyRepository countyRepository;

    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public ResponseEntity<Object> getCounties() {
        List<CountyDto> CountyDtos =  countyRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new CountyDto(dto.getName()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",CountyDtos);
    }

    public ResponseEntity<Object> getCounty(Long id) {
        Optional<County> County = countyRepository.findById(id);
        if (County.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such county with id: " + id);
        }
        CountyDto countyDto = County.map(dto -> new CountyDto(dto.getName())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", countyDto);
    }

    public ResponseEntity<Object> createNewCounty(County county) {

        Optional<County> optionalCounty = countyRepository.findByName(county.getName());
        if (optionalCounty.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "County already exists");
        }
        if (null != county.getName() && !county.getName().isEmpty()) {
            countyRepository.save(county);
            throw new CustomErrorException(HttpStatus.CREATED, "County created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteCounty(Long id) {
        boolean exists = countyRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        countyRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "County deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateCounty(Long CountyId, String name) {
        County county = countyRepository.findById(CountyId).orElseThrow(() ->
                new IllegalArgumentException("No such County with id: " + CountyId));
        if (name != null && !name.isEmpty() && !Objects.equals(county.getName(), name)) {
            county.setName(name);
            countyRepository.save(county);
            throw new CustomErrorException(HttpStatus.CREATED, "County updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}

