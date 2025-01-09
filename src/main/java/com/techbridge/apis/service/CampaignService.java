package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Campaign;
import com.techbridge.apis.model.dto.CampaignDto;
import com.techbridge.apis.repository.CampaignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public ResponseEntity<Object> getCampaigns() {
        List<CampaignDto> CampaignDtos =  campaignRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new CampaignDto(dto.getName(),dto.getDescription(),dto.getMessage(), dto.getDispatchDate()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",CampaignDtos);
    }

    public ResponseEntity<Object> getCampaign(Long id) {
        Optional<Campaign> Campaign = campaignRepository.findById(id);
        if (Campaign.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such campaign with id: " + id);
        }
        CampaignDto CampaignDto = Campaign.map(dto -> new CampaignDto(dto.getName(),dto.getDescription(),dto.getMessage(), dto.getDispatchDate())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", CampaignDto);
    }

    public ResponseEntity<Object> createNewCampaign(Campaign campaign) {

        Optional<Campaign> optionalCampaign = campaignRepository.findByName(campaign.getName());
        if (optionalCampaign.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Campaign already exists");
        }
        if (null != campaign.getName() && !campaign.getName().isEmpty()) {
            campaignRepository.save(campaign);
            throw new CustomErrorException(HttpStatus.CREATED, "Campaign created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteCampaign(Long id) {
        boolean exists = campaignRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        campaignRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Campaign deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateCampaign(Long CampaignId, CampaignDto dto) {
        Campaign campaign = campaignRepository.findById(CampaignId).orElseThrow(() ->
                new IllegalArgumentException("No such Campaign with id: " + CampaignId));
        if (!Objects.isNull(dto)) {
            campaign.setName(dto.getName());
            campaign.setMessage(dto.getMessage());
            campaign.setDescription(dto.getDescription());
            campaign.setDispatchDate(dto.getDispatchDate());
            campaignRepository.save(campaign);
            throw new CustomErrorException(HttpStatus.CREATED, "Campaign updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


