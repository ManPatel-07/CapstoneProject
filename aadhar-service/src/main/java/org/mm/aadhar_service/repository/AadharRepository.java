package org.mm.aadhar_service.repository;

import org.mm.aadhar_service.entity.AadharEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharRepository extends JpaRepository<AadharEntity, Long>
{

}
