package com.ssafy.IMS.service;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.CompanyRequest;

public interface CompanyService {
    Company getCompany(String name);

    Company addCompany(CompanyRequest companyRequest);

    Company updateCompany(CompanyRequest companyRequest);

    ApiResponse deleteCompany(int id);
}
