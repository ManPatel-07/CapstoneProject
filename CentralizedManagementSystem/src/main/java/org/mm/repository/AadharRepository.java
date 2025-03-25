package org.mm.repository;

import org.mm.entity.AadharEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharRepository extends JpaRepository<AadharEntity, Long>
{
	AadharEntity findByProfileId(Long profileId);

}
