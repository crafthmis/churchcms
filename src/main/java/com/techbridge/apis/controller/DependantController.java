package com.techbridge.apis.controller;

import com.techbridge.apis.model.Dependant;
import com.techbridge.apis.model.dto.DependantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DependantController extends BaseController {

    @GetMapping("/dependants")
    public ResponseEntity<Object> getDependants() {
        return dependantService.getDependants();
    }


    @GetMapping("/dependant/{id}")
    public ResponseEntity<Object> getDependant(@PathVariable Long id) {
        return dependantService.getDependant(id);
    }

    @PostMapping("/dependant/add")
    public ResponseEntity<Object> addNewDependant(@RequestBody Dependant dependant) {
        return dependantService.createNewDependant(dependant);
    }

    @DeleteMapping("/dependant/{id}")
    public ResponseEntity<Object> deleteDependant(@PathVariable Long id) {
        return dependantService.deleteDependant(id);
    }

    @PutMapping("/dependant/{id}")
    public ResponseEntity<Object> updateDependant(@PathVariable("id") Long id, @RequestParam(required = false) DependantDto dto) {
        return dependantService.updateDependant(id, dto);
    }
}
