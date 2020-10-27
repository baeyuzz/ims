package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "회사에 관한 정보 처리")
@RequestMapping("/api/company")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;
}
