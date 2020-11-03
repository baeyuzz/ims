package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.repository.CompanyRepository;
import com.ssafy.IMS.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "회사에 관한 정보 처리")
@RequestMapping("/api/company")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "회사 데이터 조회")
    @GetMapping("/getcompany")
    public ResponseEntity<Company> getCompany(@RequestParam(value = "name") String name) {
        Company getCompany = companyService.getCompany(name);
        return new ResponseEntity< >(getCompany, HttpStatus.OK);
    }
}
