/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lim Chiew Ting
 */
public class Donor {

    private String donorID;
    private String donorName;
    private String donorType;
    private String donorCategory;
    private String donorPhoneNum;
    public static int no = 1;

    public Donor(String donorName, String donorType, String donorCategory, String donorPhoneNum) {
        this.donorID = "D" + no++;
        this.donorName = donorName;
        this.donorType = donorType;
        this.donorCategory = donorCategory;
        this.donorPhoneNum = donorPhoneNum;
    }

    public String getDonorID() {
        return donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDCategory() {
        return donorCategory;
    }

    public void setDCategory(String donorCategory) {
        this.donorCategory = donorCategory;
    }

    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public String getPhoneNum() {
        return donorPhoneNum;
    }

    public void setPhoneNum(String donorPhoneNum) {
        this.donorPhoneNum = donorPhoneNum;
    }
    
    @Override
    public String toString() {
        return String.format("%-10s %-30s %-15s %-15s %-10s",
                donorID, donorName, donorType, donorCategory, donorPhoneNum);
    }
}
