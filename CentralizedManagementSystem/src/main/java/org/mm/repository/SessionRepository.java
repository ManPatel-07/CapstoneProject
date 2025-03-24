package org.mm.repository;

import org.mm.entity.Session;
import org.mm.entity.UserProfileDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long>
{
	List<Session> findByUserProfile(UserProfileDetailsEntity userProfile);
	
	Optional<Session> findByRefreshToken(String refreshToken);
}
