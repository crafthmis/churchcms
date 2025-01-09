package com.techbridge.apis.controller;

import com.techbridge.apis.model.Campaign;
import com.techbridge.apis.model.dto.CampaignDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CampaignController extends BaseController {

    @GetMapping("/campaigns")
    public ResponseEntity<Object> getCampaigns() {
        return campaignService.getCampaigns();
    }


    @GetMapping("/campaign/{id}")
    public ResponseEntity<Object> getCampaign(@PathVariable Long id) {
        return campaignService.getCampaign(id);
    }

    @PostMapping("/campaign/add")
    public ResponseEntity<Object> addNewCampaign(@RequestBody Campaign campaign) {
        return campaignService.createNewCampaign(campaign);
    }

    @DeleteMapping("/campaign/{id}")
    public ResponseEntity<Object> deleteCampaign(@PathVariable Long id) {
        return campaignService.deleteCampaign(id);
    }

    @PutMapping("/campaign/{id}")
    public ResponseEntity<Object> updateCampaign(@PathVariable("id") Long id, @RequestParam(required = false) CampaignDto dto) {
        return campaignService.updateCampaign(id, dto);
    }
}
