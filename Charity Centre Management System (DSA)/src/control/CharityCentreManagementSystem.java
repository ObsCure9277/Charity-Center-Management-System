/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import utility.*;
import entity.*;
import adt.*;
import boundary.*;
import dao.*;

/**
 *
 * @author Loo Jie Qi
 */
public class CharityCentreManagementSystem {

    public static void main(String[] args) {
        
        DonorInitialize donorInitialize = new DonorInitialize();
        QueueInterface<Donor> donorQueue = donorInitialize.getDonorList();
        DonationInitialize donationInitializer = new DonationInitialize(donorQueue);
        QueueInterface<Donation> donationQueue = donationInitializer.getDonationList();
        DoneeInitialize doneeInitialize = new DoneeInitialize();
        QueueInterface<Donee> doneeQueue = doneeInitialize.getDoneeList();
        
        GeneralUI generalUI = new GeneralUI();

        boolean selectAgain = true;

        do {
            System.out.print(generalUI.charityCentreManagementMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: // Donor
                    DonorManagementControl donorManagementControl = new DonorManagementControl(donorQueue);
                    donorManagementControl.donorManagement();
                    break;

                case 2: // Donee
                    DoneeManagementControl doneeManagementControl = new DoneeManagementControl(doneeQueue);
                    doneeManagementControl.doneeManagement();
                    break;

                case 3: // Donation Management
                    DonationManagementControl donationManagementControl = new DonationManagementControl(donationQueue);
                    donationManagementControl.donationManagement();
                    break;

                case 0: // Exit
                    System.out.print(generalUI.systemExitMsg());
                    selectAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (selectAgain);
    }
}
