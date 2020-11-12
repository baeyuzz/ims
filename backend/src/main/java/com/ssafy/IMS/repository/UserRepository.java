package com.ssafy.IMS.repository;


import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(@NotBlank String username);
	Optional<User> findByEmail(@NotBlank String email);
	Boolean existsByName(@NotBlank String name);
	Boolean existsByEmail(@NotBlank String email);
	default User getUserByEmail(String email) {
		return findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
}
