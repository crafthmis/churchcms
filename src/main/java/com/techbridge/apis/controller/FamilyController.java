package com.techbridge.apis.controller;

import com.techbridge.apis.model.Family;
import com.techbridge.apis.model.dto.FamilyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class FamilyController extends BaseController {

    @GetMapping("/families")
    public ResponseEntity<Object> getFamilies() {
        return familyService.getFamilies();
    }


    @GetMapping("/family/{id}")
    public ResponseEntity<Object> getFamily(@PathVariable Long id) {
        return familyService.getFamily(id);
    }

    @PostMapping("/family/add")
    public ResponseEntity<Object> addNewFamily(@RequestBody Family family) {
        return familyService.createNewFamily(family);
    }

    @DeleteMapping("/family/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable Long id) {
        return familyService.deleteFamily(id);
    }

    @PutMapping("/family/{id}")
    public ResponseEntity<Object> updateFamily(@PathVariable("id") Long id, @RequestParam(required = false) FamilyDto dto) {
        return familyService.updateFamily(id, dto);
    }
}
