package com.callum.verishareheroku2.agreement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository

public interface AgreementRepository
        extends JpaRepository<AgreementSubmit, Long> {

    @Query(value = "select calculatedInterest(:initialAmount,:agreementType, :startDate,:endDate)", nativeQuery = true)
    double calcInterest(
            @Param("initialAmount") double initialAmount,
            @Param("agreementType") String agreementType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}
