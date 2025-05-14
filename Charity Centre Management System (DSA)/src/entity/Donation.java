/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author Kyle
 */
public class Donation {

    private String donationID;
    private String category;
    private String type;
    private int qty;
    private double value;
    private LocalDate donateDate;
    private Donor donor;
    private static int noDonationID = 1;

    public Donation(String category, String type, int qty, double value, Donor donor) {
        this.donationID = "DNT" + noDonationID++;
        this.category = category;
        this.type = type;
        this.qty = qty;
        this.value = value;
        this.donateDate = LocalDate.now();
        this.donor = donor;
    }

    public Donation(String category, String type, int qty, double value, Donor donor, LocalDate date) {
        this.donationID = "DNT" + noDonationID++;
        this.category = category;
        this.type = type;
        this.qty = qty;
        this.value = value;
        this.donateDate = date;
        this.donor = donor;
    }

    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDonateDate() {
        return donateDate;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public static void setNoDonationID(int noDonationID) {
        Donation.noDonationID = noDonationID;
    }

    @Override
    public String toString() {
        return String.format("| %-13s | %-64s | %-8s | %-21s| %-29s| %-15d | %-21.2f |", donateDate, donor.getDonorName(), donationID, category, type, qty, value);
    }
}
