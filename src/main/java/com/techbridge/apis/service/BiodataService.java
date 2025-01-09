package com.techbridge.apis.service;


import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Biodata;
import com.techbridge.apis.model.dto.BiodataDto;
import com.techbridge.apis.repository.BiodataRepository;
import com.techbridge.apis.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class BiodataService {
    private final BiodataRepository biodataRepository;

    public BiodataService(BiodataRepository biodataRepository) {
        this.biodataRepository = biodataRepository;
    }

    public ResponseEntity<Object> getBiodatas() {
        List<BiodataDto> BiodataDtos =  biodataRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new BiodataDto(dto.getGndId(),dto.getFirstName(),dto.getMiddleName(),dto.getLastName(),dto.getIdNumber(),dto.getPhone1(),dto.getPhone2(),dto.getDob()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",BiodataDtos);
    }

    public ResponseEntity<Object> getBiodata(Long id) {
        Optional<Biodata> Biodata = biodataRepository.findById(id);
        if (Biodata.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such biodata with id: " + id);
        }
        BiodataDto biodataDto = Biodata.map(dto -> new BiodataDto(dto.getGndId(),dto.getFirstName(),dto.getMiddleName(),dto.getLastName(),dto.getIdNumber(),dto.getPhone1(),dto.getPhone2(),dto.getDob())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", biodataDto);
    }

    public ResponseEntity<Object> createNewBiodata(Biodata biodata) {

        Optional<Biodata> optionalBiodata = biodataRepository.findByIDNumber(biodata.getIdNumber());
        if (optionalBiodata.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Biodata already exists");
        }
        if (null != biodata.getIdNumber() && !biodata.getIdNumber().isEmpty()) {
            biodataRepository.save(biodata);
            throw new CustomErrorException(HttpStatus.CREATED, "Biodata created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() );

        }
    }

    public ResponseEntity<Object> deleteBiodata(Long id) {
        boolean exists = biodataRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        biodataRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Biodata deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateBiodata(Long BiodataId, BiodataDto dto) {
        Biodata biodata = biodataRepository.findById(BiodataId).orElseThrow(() ->
                new IllegalArgumentException("No such Biodata with id: " + BiodataId));
        if (!Objects.isNull(dto)) {
            biodata.setGndId(dto.getGndId());
            biodata.setFirstName(dto.getFirstName());
            biodata.setMiddleName(dto.getMiddleName());
            biodata.setIdNumber(dto.getIdNumber());
            biodata.setPhone1(dto.getPhone1());
            biodata.setPhone2(dto.getPhone2());
            biodata.setDob(dto.getDob());
            biodataRepository.save(biodata);
            throw new CustomErrorException(HttpStatus.CREATED, "Biodata updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}

