package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Group;
import com.techbridge.apis.model.dto.GroupDto;
import com.techbridge.apis.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public ResponseEntity<Object> getGroups() {
        List<GroupDto> GroupDtos =  groupRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new GroupDto(dto.getBrnId(),dto.getEmail(),dto.getName(),dto.getDescription(), dto.getIsActive()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",GroupDtos);
    }

    public ResponseEntity<Object> getGroup(Long id) {
        Optional<Group> Group = groupRepository.findById(id);
        if (Group.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such group with id: " + id);
        }
        GroupDto GroupDto = Group.map(dto -> new GroupDto(dto.getBrnId(),dto.getEmail(),dto.getName(),dto.getDescription(), dto.getIsActive())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", GroupDto);
    }

    public ResponseEntity<Object> createNewGroup(Group group) {

        Optional<Group> optionalGroup = groupRepository.findByName(group.getName());
        if (optionalGroup.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Group already exists");
        }
        if (null != group.getEmail() && !group.getEmail().isEmpty()) {
            groupRepository.save(group);
            throw new CustomErrorException(HttpStatus.CREATED, "Group created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteGroup(Long id) {
        boolean exists = groupRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        groupRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Group deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateGroup(Long GroupId, GroupDto dto) {
        Group group = groupRepository.findById(GroupId).orElseThrow(() ->
                new IllegalArgumentException("No such Group with id: " + GroupId));
        if (!Objects.isNull(dto)) {
            group.setBrnId(dto.getBrnId());
            group.setEmail(dto.getEmail());
            group.setDescription(dto.getDescription());
            group.setIsActive(dto.getIsActive());
            groupRepository.save(group);
            throw new CustomErrorException(HttpStatus.CREATED, "Group updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


