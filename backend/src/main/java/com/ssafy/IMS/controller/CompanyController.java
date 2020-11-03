package com.ssafy.IMS.controller;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.CompanyRequest;
import com.ssafy.IMS.payload.EssayRequest;
import com.ssafy.IMS.payload.EssayUpdateRequest;
import com.ssafy.IMS.repository.CompanyRepository;
import com.ssafy.IMS.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    @GetMapping("/getCompany")
    public ResponseEntity<Company> getCompany(@RequestParam(value = "name") String name) {
        Company getCompany = companyService.getCompany(name);
        return new ResponseEntity< >(getCompany, HttpStatus.OK);
    }

    @ApiOperation(value = "회사정보 등록")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> registerCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        Company company = companyService.addCompany(companyRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/company/{companyId}")
                .buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "회사정보를 등록했습니다."));
    }

    @ApiOperation(value = "회사정보 업데이트")
    @PutMapping("/update")
    public ResponseEntity<Company> updateEssay(@Valid @RequestBody CompanyRequest companyRequest) {
        Company company = companyService.updateCompany(companyRequest);
        return new ResponseEntity< >(company, HttpStatus.CREATED);
    }

    @ApiOperation(value = "회사정보 삭제")
    @DeleteMapping("/{comapanyId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "companyId") int companyId) {
        ApiResponse apiResponse = companyService.deleteCompany(companyId);
        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }
}
