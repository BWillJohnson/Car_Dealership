package com.yearup.dealership.models;

import java.time.LocalDate;

public class SalesContract {
    private int contractId;
    private String vin;
    private LocalDate saleDate;
    private double price;

    public SalesContract(String vin, LocalDate saleDate, double price) {
        this.vin = vin;
        this.saleDate = saleDate;
        this.price = price;
    }

    public SalesContract(int contractId, String vin, LocalDate saleDate, double price) {
        this.contractId = contractId;
        this.vin = vin;
        this.saleDate = saleDate;
        this.price = price;
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

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "contractId=" + contractId +
                ", vin='" + vin + '\'' +
                ", saleDate=" + saleDate +
                ", price=" + price +
                '}';
    }
}