package com.cg.mts.controller;


import com.cg.mts.entities.Admin;
import com.cg.mts.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/add")
    public Admin add(@RequestBody Admin admin){
        Admin newAdmin = new Admin(admin.getUsername(),admin.getPassword(), admin.getMobileNumber(), admin.getEmail());
        admin = adminService.insertAdmin(newAdmin);
        return admin;
    }

}
