package com.techbridge.apis.controller;

import com.techbridge.apis.model.Group;
import com.techbridge.apis.model.dto.GroupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class GroupController extends BaseController {

    @GetMapping("/groups")
    public ResponseEntity<Object> getCounties() {
        return groupService.getGroups();
    }


    @GetMapping("/group/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }

    @PostMapping("/group/add")
    public ResponseEntity<Object> addNewGroup(@RequestBody Group group) {
        return groupService.createNewGroup(group);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable Long id) {
        return groupService.deleteGroup(id);
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<Object> updateGroup(@PathVariable("id") Long id, @RequestParam(required = false) GroupDto dto) {
        return groupService.updateGroup(id, dto);
    }
}
