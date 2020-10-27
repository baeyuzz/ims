package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;
}
