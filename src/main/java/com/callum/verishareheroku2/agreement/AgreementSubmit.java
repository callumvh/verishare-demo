package com.callum.verishareheroku2.agreement;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table

// This class represents the table in the database because of the @Table anotation, it contains three constructors, accessors and a toString method.
// Each record of the database represents a basic user, with a name, initial amount which interest will be calculated on, an agreement type, the start and end dates, the calculated amount from the stored procedure and the days between the two 
// dates which the stored procedure also calculates.
public class AgreementSubmit {
    @Id
    @SequenceGenerator(
            name = "agreement_sequence",
            sequenceName = "agreement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_sequence"
    )
    private Long id;
    private String name;
    private double initialAmount;
    private String agreementType;
    private LocalDate startDate;
    private LocalDate endDate;
    private double calculatedAmount;
    private int daysBetweenDates;



    public AgreementSubmit() {
    }

    public AgreementSubmit(String name, double initialAmount, String agreementType, LocalDate startDate, LocalDate endDate, double calculatedAmount, int daysBetweenDates) {
        this.name = name;
        this.initialAmount = initialAmount;
        this.agreementType = agreementType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.calculatedAmount = calculatedAmount;
        this.daysBetweenDates = daysBetweenDates;
    }

    public AgreementSubmit(Long id, String name, double initialAmount, String agreementType, LocalDate startDate, LocalDate endDate, double calculatedAmount, int daysBetweenDates) {
        this.id = id;
        this.name = name;
        this.initialAmount = initialAmount;
        this.agreementType = agreementType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.calculatedAmount = calculatedAmount;
        this.daysBetweenDates = daysBetweenDates;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(double calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    public int getDaysBetweenDates() {
        return daysBetweenDates;
    }

    public void setDaysBetweenDates(int daysBetweenDates) {
        this.daysBetweenDates = daysBetweenDates;
    }

    @Override
    public String toString() {
        return "AgreementSubmit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialAmount=" + initialAmount +
                ", agreementType='" + agreementType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", calculatedAmount=" + calculatedAmount +
                ", daysBetweenDates=" + daysBetweenDates +
                '}';
    }

}