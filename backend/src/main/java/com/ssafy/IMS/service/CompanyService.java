package com.ssafy.IMS.service;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.CompanyRequest;

import java.util.List;

public interface CompanyService {
    Company getCompany(String name);

    Company addCompany(CompanyRequest companyRequest);

    Company updateCompany(CompanyRequest companyRequest);

    List<Company> getCompanyByRank(String rank1, String rank2);

    ApiResponse deleteCompany(int id);
}
