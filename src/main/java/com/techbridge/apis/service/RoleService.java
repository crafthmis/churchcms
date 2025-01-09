package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Role;
import com.techbridge.apis.model.dto.RoleDto;
import com.techbridge.apis.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> getRoles() {
        List<RoleDto> RoleDtos =  roleRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new RoleDto(dto.getName(),dto.getDescription(),dto.getIsActive()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",RoleDtos);
    }

    public ResponseEntity<Object> getRole(Long id) {
        Optional<Role> Role = roleRepository.findById(id);
        if (Role.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such role with id: " + id);
        }
        RoleDto RoleDto = Role.map(dto -> new RoleDto(dto.getName(),dto.getDescription(),dto.getIsActive())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", RoleDto);
    }

    public ResponseEntity<Object> createNewRole(Role role) {

        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        if (optionalRole.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Role already exists");
        }
        if (null != role.getName() && !role.getName().isEmpty()) {
            role.setIsActive(1L);
            roleRepository.save(role);
            throw new CustomErrorException(HttpStatus.CREATED, "Role created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteRole(Long id) {
        boolean exists = roleRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        roleRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Role deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateRole(Long RoleId, RoleDto dto) {
        Role role = roleRepository.findById(RoleId).orElseThrow(() ->
                new IllegalArgumentException("No such Role with id: " + RoleId));
        if (!Objects.isNull(dto)) {
            role.setName(dto.getName());
            role.setDescription(dto.getDescription());
            role.setIsActive(dto.getIsActive());
            roleRepository.save(role);
            throw new CustomErrorException(HttpStatus.CREATED, "Role updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


