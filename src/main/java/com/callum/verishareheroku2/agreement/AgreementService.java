package com.callum.verishareheroku2.agreement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AgreementService {
    private final AgreementRepository agreementRepository;


    @Autowired
    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;


    }

    public AgreementResult newPost(@RequestBody AgreementSubmit postData) throws SQLException {
        int binarySuccess;
        String possibleError = null;
        double calculatedAmount = 0;

        LocalDate stringStartDate = postData.getStartDate();
        LocalDate stringEndDate = postData.getEndDate();

        double initialAmount = postData.getInitialAmount();
        String agreementType = postData.getAgreementType();

        Date startDate=Date.valueOf(stringStartDate.toString());
        Date endDate=Date.valueOf(stringEndDate.toString());
        System.out.println(initialAmount);
        System.out.println(agreementType);
        System.out.println(startDate);
        System.out.println(endDate);
        try {

            calculatedAmount = agreementRepository.calcInterest(initialAmount,agreementType, startDate, endDate);
            binarySuccess =1;
            System.out.println(calculatedAmount);
        } catch (Exception e) {

            possibleError = e.toString();
            binarySuccess =0;
        }

        agreementRepository.save(postData);
        return new AgreementResult(binarySuccess, possibleError,calculatedAmount);
    }
}
