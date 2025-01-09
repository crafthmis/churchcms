package com.techbridge.apis.controller;

import com.techbridge.apis.model.Branch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BranchController extends BaseController {

    @GetMapping("/branches")
    public ResponseEntity<Object> getCounties() {
        return branchService.getBranches();
    }


    @GetMapping("/branch/{id}")
    public ResponseEntity<Object> getBranch(@PathVariable Long id) {
        return branchService.getBranch(id);
    }

    @PostMapping("/branch/add")
    public ResponseEntity<Object> addNewBranch(@RequestBody Branch branch) {
        return branchService.createNewBranch(branch);
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<Object> deleteBranch(@PathVariable Long id) {
        return branchService.deleteBranch(id);
    }

    @PutMapping("/branch/{id}")
    public ResponseEntity<Object> updateBranch(@PathVariable("id") Long id, @RequestParam(required = false) String name) {
        return branchService.updateBranch(id, name);
    }
}
