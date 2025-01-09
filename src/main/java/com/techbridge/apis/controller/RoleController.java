package com.techbridge.apis.controller;

import com.techbridge.apis.model.Role;
import com.techbridge.apis.model.dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoleController extends BaseController {

    @GetMapping("/roles")
    public ResponseEntity<Object> getRoles() {
        return roleService.getRoles();
    }


    @GetMapping("/role/{id}")
    public ResponseEntity<Object> getRole(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @PostMapping("/role/add")
    public ResponseEntity<Object> addNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable("id") Long id, @RequestParam(required = false) RoleDto dto) {
        return roleService.updateRole(id, dto);
    }
}
