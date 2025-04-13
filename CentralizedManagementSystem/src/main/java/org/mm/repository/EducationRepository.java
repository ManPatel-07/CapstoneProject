package org.mm.repository;

import org.mm.entity.AadharEntity;
import org.mm.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Long>
{
	EducationEntity findByProfileId(Long profileId);
}
