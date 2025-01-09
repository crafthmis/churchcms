package com.techbridge.apis.controller;



import com.techbridge.apis.service.BranchService;
import com.techbridge.apis.service.CountyService;
import com.techbridge.apis.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BaseController {
    @Autowired
    protected Environment env;
    @Autowired
    public CountyService countyService;
    @Autowired
    public BranchService branchService;
    @Autowired
    protected PasswordEncoder passwordEncoder;
}
