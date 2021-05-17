package com.callum.verishareheroku2.agreement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class AgreementController {
    private final AgreementService agreementService;

    @Autowired
    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "https://cal-pwa-firebase.web.app", "https://cal-pwa-firebase.firebaseapp.com"})
    @PostMapping("/agreement")
    public AgreementResult newPost(@RequestBody AgreementSubmit postData) throws SQLException {

        return agreementService.newPost(postData);
    }
}
