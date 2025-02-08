package com.techbridge.apis.controller;



import com.techbridge.apis.service.*;
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
    public GenderService genderService;
    @Autowired
    public BiodataService biodataService;
    @Autowired
    public CampaignService campaignService;
    @Autowired
    public SermonService sermonService;
    @Autowired
    public RoleService roleService;
    @Autowired
    public DependantService dependantService;
    @Autowired
    public FamilyService familyService;
    @Autowired
    public EventService eventService;
    @Autowired
    public GroupService groupService;
    @Autowired
    public MessageService messageService;
    @Autowired
    public UserService userService;
    @Autowired
    protected PasswordEncoder passwordEncoder;
}
