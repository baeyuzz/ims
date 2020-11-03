package com.ssafy.IMS.repository;

import com.ssafy.IMS.model.Company;
import com.ssafy.IMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByName(@NotBlank String name);
    Optional<List<Company>> findByRank1AndRank2(@NotBlank String rank1, @NotBlank String rank2);
}
