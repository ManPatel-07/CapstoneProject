package org.mm.repository;

import java.util.Optional;

import org.mm.entity.UserProfileDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository  extends JpaRepository<UserProfileDetailsEntity, Long>{

	Optional<UserProfileDetailsEntity> findByEmail(String email);
}
