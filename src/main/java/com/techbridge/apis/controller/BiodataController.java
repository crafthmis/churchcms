package com.techbridge.apis.controller;

import com.techbridge.apis.model.Biodata;
import com.techbridge.apis.model.dto.BiodataDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BiodataController extends BaseController {

    @GetMapping("/people")
    public ResponseEntity<Object> getBiodatas() {
        return biodataService.getBiodatas();
    }


    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getBiodata(@PathVariable Long id) {
        return biodataService.getBiodata(id);
    }

    @PostMapping("/person/add")
    public ResponseEntity<Object> addNewBiodata(@RequestBody Biodata biodata) {
        return biodataService.createNewBiodata(biodata);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Object> deleteBiodata(@PathVariable Long id) {
        return biodataService.deleteBiodata(id);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Object> updateBiodata(@PathVariable("id") Long id, @RequestParam(required = false) BiodataDto dto) {
        return biodataService.updateBiodata(id, dto);
    }
}
