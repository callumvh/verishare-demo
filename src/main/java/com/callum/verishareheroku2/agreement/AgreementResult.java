package com.callum.verishareheroku2.agreement;

public class AgreementResult {
    private final int successOrNot;
    private final String reasonIfUnsuccessful;
    private final double calculatedInterestAmount;

    public AgreementResult(int successOrNot, String reasonIfUnsuccessful, double calculatedInterestAmount) {
        this.successOrNot = successOrNot;
        this.reasonIfUnsuccessful = reasonIfUnsuccessful;
        this.calculatedInterestAmount = calculatedInterestAmount;
    }

    public int getSuccessOrNot() {
        return successOrNot;
    }

    public String getReasonIfUnsuccessful() {
        return reasonIfUnsuccessful;
    }

    public double getCalculatedInterestAmount() {
        return calculatedInterestAmount;
    }
}







