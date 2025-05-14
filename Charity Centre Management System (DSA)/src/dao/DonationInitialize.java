/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Donation;
import entity.Donor;
import adt.*;
import control.DonorManagementControl;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Loo Jie Qi
 */
public class DonationInitialize {

    public static QueueInterface<Donation> donationList = new LinkedQueue<>();

    public DonationInitialize(QueueInterface<Donor> donorQueue) {
        DonorManagementControl donorManagementControl = new DonorManagementControl(donorQueue);

        Donor donor1 = donorManagementControl.getDonor(0);
        Donor donor2 = donorManagementControl.getDonor(1);
        Donor donor3 = donorManagementControl.getDonor(2);
        Donor donor4 = donorManagementControl.getDonor(3);
        Donor donor5 = donorManagementControl.getDonor(4);
        Donor donor6 = donorManagementControl.getDonor(5);
        Donor donor7 = donorManagementControl.getDonor(6);
        Donor donor8 = donorManagementControl.getDonor(7);
        Donor donor9 = donorManagementControl.getDonor(8);
        Donor donor10 = donorManagementControl.getDonor(9);
        Donor donor11 = donorManagementControl.getDonor(10);
        Donor donor12 = donorManagementControl.getDonor(11);
        Donor donor13 = donorManagementControl.getDonor(12);
        Donor donor14 = donorManagementControl.getDonor(13);
        Donor donor15 = donorManagementControl.getDonor(14);


        
        donationList.enqueue(new Donation("Clothing", "Clothes", 200, 5302.12, donor1, LocalDate.of(2024, Month.JANUARY, 29)));
        donationList.enqueue(new Donation("Food", "Rice(Pack)", 50, 1192.27, donor2, LocalDate.of(2022, Month.AUGUST, 3)));
        donationList.enqueue(new Donation("Others", "Wet Tissues(Pack)", 100, 1580.00, donor7, LocalDate.of(2024, Month.SEPTEMBER, 1)));
        donationList.enqueue(new Donation("Food", "Cookies (Box)", 50, 950.50, donor8, LocalDate.of(2024, Month.APRIL, 21)));
        donationList.enqueue(new Donation("Books", "Comics", 65, 3155.30, donor9, LocalDate.of(2024, Month.AUGUST, 1)));
        donationList.enqueue(new Donation("Others", "Tissues(Pack)", 100, 2522.00, donor3, LocalDate.of(2023, Month.FEBRUARY, 11)));
        donationList.enqueue(new Donation("Food", "Oil(barrel)", 20, 955.56, donor4, LocalDate.of(2024, Month.MARCH, 7)));
        donationList.enqueue(new Donation("Water", "Mineral water(1000ml)", 4000, 9214.77, donor1, LocalDate.of(2024, Month.MAY, 30)));
        donationList.enqueue(new Donation("Furniture", "Chair", 544, 10242.22, donor5, LocalDate.of(2024, Month.MAY, 19)));
        donationList.enqueue(new Donation("Furniture", "Table", 203, 14309.38, donor6, LocalDate.of(2024, Month.JANUARY, 5)));
        donationList.enqueue(new Donation("Food", "Meat(Chicken)(Pack)", 618, 13232.72, donor6, LocalDate.of(2024, Month.JULY, 1)));
        donationList.enqueue(new Donation("Books", "Novel", 70, 712.44, donor6, LocalDate.of(2023, Month.DECEMBER, 17)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 50000.00, donor13, LocalDate.of(2024, Month.JULY, 25)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 4500.00, donor11, LocalDate.of(2024, Month.JULY, 17)));
        donationList.enqueue(new Donation("Electronics", "Phone", 27, 8991.41, donor1, LocalDate.of(2024, Month.JUNE, 27)));
        donationList.enqueue(new Donation("Toys", "Doll", 120, 889.75, donor2, LocalDate.of(2023, Month.MARCH, 20)));
        donationList.enqueue(new Donation("Medical Supplies", "Plasters(Box)", 2300, 5492.66, donor2, LocalDate.of(2024, Month.MARCH, 14)));
        donationList.enqueue(new Donation("Medical Supplies", "First aid kit", 40, 2040.00, donor3, LocalDate.of(2024, Month.JULY, 12)));
        donationList.enqueue(new Donation("Food", "Vegetable(Broccoli)(Pack)", 300, 1000.70, donor3, LocalDate.of(2023, Month.AUGUST, 29)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 35000.00, donor15, LocalDate.of(2024, Month.JANUARY, 23)));
        donationList.enqueue(new Donation("Furniture", "Sofa", 10, 23574.22, donor10, LocalDate.of(2023, Month.MARCH, 5)));
        donationList.enqueue(new Donation("Water", "Mineral water(100ml)", 2500, 5550.70, donor11, LocalDate.of(2023, Month.DECEMBER, 4)));
        donationList.enqueue(new Donation("Medical Supplies", "Medicines", 100, 6089.56, donor12, LocalDate.of(2024, Month.JULY, 12)));
        donationList.enqueue(new Donation("Toys", "Puppets", 70, 618.75, donor14, LocalDate.of(2023, Month.MARCH, 20)));
        donationList.enqueue(new Donation("Food", "Meat(Pork)(Pack)", 3440, 7144.00, donor4, LocalDate.of(2024, Month.FEBRUARY, 6)));
        donationList.enqueue(new Donation("Electronics", "Induction Cooker", 130, 8912.88, donor5, LocalDate.of(2024, Month.JANUARY, 19)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 5000.00, donor5, LocalDate.of(2024, Month.MARCH, 13)));
        donationList.enqueue(new Donation("Water", "Mineral water(100ml)", 2000, 4300.00, donor5, LocalDate.of(2024, Month.FEBRUARY, 4)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 12000.00, donor4, LocalDate.of(2023, Month.JANUARY, 22)));
        donationList.enqueue(new Donation("Cash", "MYR", 0, 7000.00, donor14, LocalDate.of(2024, Month.JULY, 17)));

    }

    public static QueueInterface<Donation> getDonationList() {
        return donationList;
    }
}
