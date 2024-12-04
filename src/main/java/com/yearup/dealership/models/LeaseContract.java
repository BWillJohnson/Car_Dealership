package com.yearup.dealership.models;

import java.time.LocalDate;

public class LeaseContract {
    private int contractId;
    private String vin;
    private LocalDate leaseStart;
    private LocalDate leaseEnd;
    private double monthlyPayment;

    public LeaseContract(String vin, LocalDate leaseStart, LocalDate leaseEnd, double monthlyPayment) {
        this.vin = vin;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.monthlyPayment = monthlyPayment;
    }

    public LeaseContract(int contractId, String vin, LocalDate leaseStart, LocalDate leaseEnd, double monthlyPayment) {
        this.contractId = contractId;
        this.vin = vin;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.monthlyPayment = monthlyPayment;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LocalDate getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(LocalDate leaseStart) {
        this.leaseStart = leaseStart;
    }

    public LocalDate getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(LocalDate leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "contractId=" + contractId +
                ", vin='" + vin + '\'' +
                ", leaseStart=" + leaseStart +
                ", leaseEnd=" + leaseEnd +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }
}