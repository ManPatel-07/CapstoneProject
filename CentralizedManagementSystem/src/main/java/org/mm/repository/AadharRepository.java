package org.mm.repository;

import java.util.Optional;

import org.mm.entity.AadharEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharRepository extends JpaRepository<AadharEntity, Long>
{

//	Optional<AadharEntity> findByProfileId(Long profileId);
	AadharEntity findByProfileId(Long profileId);

}
