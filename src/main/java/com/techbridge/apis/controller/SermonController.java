package com.techbridge.apis.controller;

import com.techbridge.apis.model.Sermon;
import com.techbridge.apis.model.dto.SermonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SermonController extends BaseController {

    @GetMapping("/sermons")
    public ResponseEntity<Object> getSermons() {
        return sermonService.getSermons();
    }


    @GetMapping("/sermon/{id}")
    public ResponseEntity<Object> getSermon(@PathVariable Long id) {
        return sermonService.getSermon(id);
    }

    @PostMapping("/sermon/add")
    public ResponseEntity<Object> addNewSermon(@RequestBody Sermon sermon) {
        return sermonService.createNewSermon(sermon);
    }

    @DeleteMapping("/sermon/{id}")
    public ResponseEntity<Object> deleteSermon(@PathVariable Long id) {
        return sermonService.deleteSermon(id);
    }

    @PutMapping("/sermon/{id}")
    public ResponseEntity<Object> updateSermon(@PathVariable("id") Long id, @RequestParam(required = false) SermonDto dto) {
        return sermonService.updateSermon(id, dto);
    }
}
