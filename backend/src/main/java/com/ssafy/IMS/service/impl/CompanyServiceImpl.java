package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.repository.CompanyRepository;
import com.ssafy.IMS.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
