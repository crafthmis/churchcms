package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Sermon;
import com.techbridge.apis.model.dto.SermonDto;
import com.techbridge.apis.repository.SermonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class SermonService {
    private final SermonRepository sermonRepository;

    public SermonService(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    public ResponseEntity<Object> getSermons() {
        List<SermonDto> SermonDtos =  sermonRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new SermonDto(dto.getBrnId(),dto.getTitle(),dto.getDescription(), dto.getSermonDate()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",SermonDtos);
    }

    public ResponseEntity<Object> getSermon(Long id) {
        Optional<Sermon> Sermon = sermonRepository.findById(id);
        if (Sermon.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such sermon with id: " + id);
        }
        SermonDto SermonDto = Sermon.map(dto -> new SermonDto(dto.getBrnId(),dto.getTitle(),dto.getDescription(), dto.getSermonDate())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", SermonDto);
    }

    public ResponseEntity<Object> createNewSermon(Sermon sermon) {

        Optional<Sermon> optionalSermon = sermonRepository.findByTitle(sermon.getTitle());
        if (optionalSermon.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Sermon already exists");
        }
        if (null != sermon.getTitle() && !sermon.getTitle().isEmpty()) {
            sermonRepository.save(sermon);
            throw new CustomErrorException(HttpStatus.CREATED, "Sermon created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteSermon(Long id) {
        boolean exists = sermonRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        sermonRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Sermon deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateSermon(Long SermonId, SermonDto dto) {
        Sermon sermon = sermonRepository.findById(SermonId).orElseThrow(() ->
                new IllegalArgumentException("No such Sermon with id: " + SermonId));
        if (!Objects.isNull(dto)) {
            sermon.setBrnId(dto.getBrnId());
            sermon.setTitle(dto.getTitle());
            sermon.setDescription(dto.getDescription());
            sermon.setSermonDate(dto.getSermonDate());
            sermonRepository.save(sermon);
            throw new CustomErrorException(HttpStatus.CREATED, "Sermon updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


