package com.ssafy.IMS.repository;

import com.ssafy.IMS.model.Essay;
import com.ssafy.IMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Integer> {
    Optional<Essay> findByUser(@NotBlank User user);
    Optional<Essay> findByContent(@NotBlank String content);

}

