package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.CompanyRequest;
import com.ssafy.IMS.repository.CompanyRepository;
import com.ssafy.IMS.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Company getCompany(String name) {
        Company data =companyRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "name", name));
        return data;
    }

    @Override
    public Company addCompany(CompanyRequest companyRequest) {
        Company newCompany = new Company();
        newCompany.setName(companyRequest.getName());
        newCompany.setResult1(companyRequest.getResult1());
        newCompany.setResult2(companyRequest.getResult2());
        newCompany.setResult3(companyRequest.getResult3());
        newCompany.setResult4(companyRequest.getResult4());
        newCompany.setResult5(companyRequest.getResult5());
        newCompany.setResult6(companyRequest.getResult6());
        newCompany.setResult7(companyRequest.getResult7());
        newCompany.setResult8(companyRequest.getResult8());

        return companyRepository.save(newCompany);
    }

    @Override
    public Company updateCompany(CompanyRequest companyRequest) {
        Optional<Company> oldCompany = companyRepository.findByName(companyRequest.getName());
        oldCompany.ifPresent(newCompany ->{
            newCompany.setName(companyRequest.getName());
            newCompany.setResult1(companyRequest.getResult1());
            newCompany.setResult2(companyRequest.getResult2());
            newCompany.setResult3(companyRequest.getResult3());
            newCompany.setResult4(companyRequest.getResult4());
            newCompany.setResult5(companyRequest.getResult5());
            newCompany.setResult6(companyRequest.getResult6());
            newCompany.setResult7(companyRequest.getResult7());
            newCompany.setResult8(companyRequest.getResult8());
            companyRepository.save(newCompany);
        });

        return companyRepository.findByName(companyRequest.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Company", "name", companyRequest.getName()));
    }

    @Override
    public ApiResponse deleteCompany(int id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("company", "id", id));
        companyRepository.deleteById(id);
        return new ApiResponse(Boolean.TRUE,  "회사정보가 삭제되었습니다..");
    }


}
