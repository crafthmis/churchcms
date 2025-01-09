package com.techbridge.apis.controller;

import com.techbridge.apis.model.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class GenderController extends BaseController {

    @GetMapping("/genders")
    public ResponseEntity<Object> getCounties() {
        return genderService.getGenders();
    }


    @GetMapping("/gender/{id}")
    public ResponseEntity<Object> getGender(@PathVariable Long id) {
        return genderService.getGender(id);
    }

    @PostMapping("/gender/add")
    public ResponseEntity<Object> addNewGender(@RequestBody Gender gender) {
        return genderService.createNewGender(gender);
    }

    @DeleteMapping("/gender/{id}")
    public ResponseEntity<Object> deleteGender(@PathVariable Long id) {
        return genderService.deleteGender(id);
    }

    @PutMapping("/gender/{id}")
    public ResponseEntity<Object> updateGender(@PathVariable("id") Long id, @RequestParam(required = false) String name) {
        return genderService.updateGender(id, name);
    }
}
