package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Dependant;
import com.techbridge.apis.model.dto.DependantDto;
import com.techbridge.apis.repository.DependantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class DependantService {
    private final DependantRepository dependantRepository;

    public DependantService(DependantRepository dependantRepository) {
        this.dependantRepository = dependantRepository;
    }

    public ResponseEntity<Object> getDependants() {
        List<DependantDto> DependantDtos =  dependantRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new DependantDto(dto.getName(),dto.getDescription()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",DependantDtos);
    }

    public ResponseEntity<Object> getDependant(Long id) {
        Optional<Dependant> Dependant = dependantRepository.findById(id);
        if (Dependant.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such dependant with id: " + id);
        }
        DependantDto DependantDto = Dependant.map(dto -> new DependantDto(dto.getName(),dto.getDescription())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", DependantDto);
    }

    public ResponseEntity<Object> createNewDependant(Dependant dependant) {

        Optional<Dependant> optionalDependant = dependantRepository.findByName(dependant.getName());
        if (optionalDependant.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Dependant already exists");
        }
        if (null != dependant.getName() && !dependant.getName().isEmpty()) {
            dependantRepository.save(dependant);
            throw new CustomErrorException(HttpStatus.CREATED, "Dependant created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteDependant(Long id) {
        boolean exists = dependantRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        dependantRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Dependant deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateDependant(Long DependantId, DependantDto dto) {
        Dependant dependant = dependantRepository.findById(DependantId).orElseThrow(() ->
                new IllegalArgumentException("No such Dependant with id: " + DependantId));
        if (!Objects.isNull(dto)) {
            dependant.setName(dto.getName());
            dependant.setDescription(dto.getDescription());
            dependantRepository.save(dependant);
            throw new CustomErrorException(HttpStatus.CREATED, "Dependant updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


