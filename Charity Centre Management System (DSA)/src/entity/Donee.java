/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
/**
 *
 * @author USER
 */
public class Donee {
    private String doneeID;
    private String doneeName;
    private String doneeType;
    private String doneePhoneNum;
    private LocalDate registrationDate;
    private static int noID = 1;

    public Donee(String doneeName, String doneeType, String doneePhoneNum) {
        this.doneeID = "DNE" + noID;
        this.doneeName = doneeName;
        this.doneeType = doneeType;
        this.doneePhoneNum = doneePhoneNum;
        this.registrationDate = LocalDate.now();
        noID++;
    }
    
    public Donee() {
    }
    
    public String getDoneeID() {
        return doneeID;
    }

    public void setDoneeID(String doneeID) {
        this.doneeID = doneeID;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public String getDoneeType() {
        return doneeType;
    }

    public void setDoneeType(String doneeType) {
        this.doneeType = doneeType;
    }

    public String getDoneePhoneNum() {
        return doneePhoneNum;
    }

    public void setDoneePhoneNum(String doneePhoneNum) {
        this.doneePhoneNum = doneePhoneNum;
    }
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    @Override
    public String toString() {
        return String.format("| %-19s | %-20s | %-17s | %-8s | %-18s |", registrationDate, doneeName, doneeType, doneeID, doneePhoneNum);
    }
}
