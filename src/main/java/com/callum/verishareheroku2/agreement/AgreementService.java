package com.callum.verishareheroku2.agreement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

// This Service handles the logic of the return object which goes back to the client, it also saves the user to the PostgreSQL DB
@Service
public class AgreementService {
    private final AgreementRepository agreementRepository;


    @Autowired
    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public AgreementResult newPost(@RequestBody AgreementSubmit postData) throws SQLException {
        // var for if the stored procedure worked
        int binarySuccess;
        // var for if the stored procdure did not work
        String possibleError = null;
        // var to return to the client
        double calculatedAmount = 0;

        LocalDate stringStartDate = postData.getStartDate();
        LocalDate stringEndDate = postData.getEndDate();

        double initialAmount = postData.getInitialAmount();
        String agreementType = postData.getAgreementType();

        // These two date instances use the SQL dates which are required for the parameters of the stored procedure
        Date startDate=Date.valueOf(stringStartDate.toString());
        Date endDate=Date.valueOf(stringEndDate.toString());
        
        try {
            // This variable receives hte amount from the stored procedure method called calcInterest
            calculatedAmount = agreementRepository.calcInterest(initialAmount,agreementType, startDate, endDate);
            binarySuccess =1;
        } catch (Exception e) {

            possibleError = e.toString();
            binarySuccess =0;
        }
        // Here is the save to the DB
        agreementRepository.save(postData);
        return new AgreementResult(binarySuccess, possibleError,(Math.round(calculatedAmount)*100)/100 );
    }
}
