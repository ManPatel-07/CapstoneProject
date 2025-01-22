package org.mm.user_service.repository;

import java.util.Optional;

import org.mm.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> 
{
	Optional<UserEntity> findByEmail(String email);
}
