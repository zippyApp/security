package com.zippy.security.repository;

import com.zippy.security.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    Optional<Credential> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Credential c WHERE c.id = :credentialId")
    void deleteByIdAndCascadePersonalInformation(@Param("credentialId") Long credentialId);

    @Query("SELECT c.personalInformationId FROM Credential c WHERE c.id = :credentialId")
    Long findPersonalInformationIdByCredentialId(@Param("credentialId") Long credentialId);
}
