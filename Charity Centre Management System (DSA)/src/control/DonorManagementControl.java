/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.*;
import utility.*;
import dao.DonationInitialize;
import boundary.DonorManagementUI;
import adt.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Lim Chiew Ting
 */
public class DonorManagementControl {

    QueueInterface<Donor> donorQueue = new LinkedQueue<>();

    public DonorManagementControl(QueueInterface<Donor> donorQueueu) {
        this.donorQueue = donorQueueu;
    }

    private DonorManagementUI donorManagementUI = new DonorManagementUI();

    public void donorManagement() {
        boolean selectDonorManagementAgain = true;

        do {
            System.out.print(donorManagementUI.donorManagementMenu());
            int donorManagementSelection = InputUtility.promptIntInput("Selection  -->  ");

            switch (donorManagementSelection) {
                case 1: // Add Donor
                    addDonor();
                    break;

                case 2: // Remove Donor
                    removeDonor();
                    break;

                case 3: // Update Donor Details
                    updateDonorDetails();
                    break;

                case 4: // Search Donor Details
                    search();
                    break;

                case 5: // List donors with all donations made
                    listDonorWithAllDonations();
                    break;

                case 6: // Filter donors
                    donorFilter();
                    break;

                case 7:  // Generate summary reports
                    generateSummaryReport();
                    break;

                case 8: // Display Donor List
                    displayCurrentDonors();
                    break;
                
                case 0: // Exit
                    selectDonorManagementAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }

        } while (selectDonorManagementAgain);
    }
    //add donor function

    public String[] categorizeDonors() {
        boolean categorizeAgain = true;
        String category = "";
        String type = "";

        do {
            System.out.print(donorManagementUI.addDonorMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: // Individual
                    category = "Individual";
                    boolean validIndividualType = false;
                    while (!validIndividualType) {
                        System.out.println("Are you a public or private individual?");
                        int individualType = InputUtility.promptIntInput("1. Public\n2. Private\nSelection --> ");
                        if (individualType == 1) {
                            type = "Public";
                            validIndividualType = true;
                        } else if (individualType == 2) {
                            type = "Private";
                            validIndividualType = true;
                        } else {
                            ErrorMessage.errorInputMsg();
                        }
                    }
                    categorizeAgain = false;
                    break;

                case 2: // Organisation
                    category = "Organisation";
                    boolean validOrganisationType = false;
                    while (!validOrganisationType) {
                        System.out.println("Please select your organisation type:");
                        int organisationType = InputUtility.promptIntInput("1. Public\n2. Private\n3. Government\nSelection --> ");
                        switch (organisationType) {
                            case 1:
                                type = "Public";
                                validOrganisationType = true;
                                break;
                            case 2:
                                type = "Private";
                                validOrganisationType = true;
                                break;
                            case 3:
                                type = "Government";
                                validOrganisationType = true;
                                break;
                            default:
                                ErrorMessage.errorInputMsg();
                        }
                    }
                    categorizeAgain = false;
                    break;

                case 0: // Exit
                    categorizeAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (categorizeAgain);

        String donorCategory = formatDonorType(category);
        String donorType = formatDonorType(type);
        return new String[]{donorCategory, donorType};
    }

    public void addDonor() {
        boolean selectAgain = true;
        Scanner input = new Scanner(System.in);

        while (selectAgain) {
            String[] donorInfo = categorizeDonors();
            String category = donorInfo[0];
            String type = donorInfo[1];

            String name;
            do {
                name = InputUtility.promptStringInput("Enter Donor Name: ");
                if (name.trim().isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid name! Please enter a valid name (only alphabets).\n");
                } else {
                    break;
                }
            } while (true);

            String phone;
            do {
                phone = InputUtility.promptStringInput("Enter Contact Number (format: xxx-xxxxxxx): ");
                phone = formatPhoneNum(phone);
                if (phone == null) {
                    // User chose to keep the current phone number or input was invalid
                    System.out.println("Phone number cannot be empty. Please try again.\n");
                    phone = InputUtility.promptStringInput("Enter Contact Number (format: xxx-xxxxxxx): ");
                } else {
                    break;
                }
            } while (true);

            String donorName = formatDonorName(name);
            System.out.print("\nConfirm to add this new donor? (1 = yes / 2 = no): ");
            int select = input.nextInt();
            if (select == 1) {
                donorQueue.enqueue(new Donor(donorName, type, category, phone));
                System.out.print(donorManagementUI.addDonorSuccessfulMsg());
                displayCurrentDonors();
                selectAgain = false;
            } else {
                System.out.print(donorManagementUI.addDonorCancelMsg());
                break;
            }
        }
    }

    //delete function
    public void removeDonor() {
        boolean removeDonorAgain = true;

        do {
            System.out.print(donorManagementUI.removeDonorMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1:
                    String donorIdNum = InputUtility.promptStringInput("Enter Donor ID to delete (e.g. D1): ");
                    boolean success = deleteDonorById(donorIdNum);
                    if (success) {
                        System.out.print(donorManagementUI.removeDonorSuccessfulMsg());
                    } else {
                        System.out.print(donorManagementUI.removeDonorFailedMsg());
                    }
                    break;

                case 2:
                    if (clearAllDonors()) {
                        System.out.print(donorManagementUI.removeAllDonorSuccessfulMsg());
                    } else {
                        System.out.print(donorManagementUI.removeAllDonorFailedMsg());
                    }
                    break;

                case 0:
                    removeDonorAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (removeDonorAgain);
    }

    //dlt All
    public boolean clearAllDonors() {
        if (!donorQueue.isEmpty()) {
            donorQueue = new LinkedQueue<>();
            return true;
        }
        return false;
    }

    //dlt specific donor and rearrange ID in the queue
    public boolean deleteDonorById(String donorIdNum) {

        QueueInterface<Donor> tempQueue = new LinkedQueue<>();
        boolean isDeleted = false;

        while (!donorQueue.isEmpty()) {
            Donor donor = donorQueue.dequeue();

            if (donor.getDonorID().equals(donorIdNum)) {
                isDeleted = true;

            } else {
                tempQueue.enqueue(donor);
            }
        }

        while (!tempQueue.isEmpty()) {
            donorQueue.enqueue(tempQueue.dequeue());
        }

        return isDeleted;

    }

    public void updateDonorDetails() {
        if (donorQueue.isEmpty()) {
            System.out.println("The donor queue is empty. No data can be updated. Please add donors data first.");
            return; // Exit the function since there's nothing to update
        }
        displayCurrentDonors();
        String donorID = InputUtility.promptStringInput("Enter Donor ID to update (e.g. D1): ");

        QueueInterface<Donor> tempQueue = new LinkedQueue<>();
        Donor targetDonor = null;
        while (!donorQueue.isEmpty()) {
            Donor currentDonor = donorQueue.dequeue();
            if (currentDonor.getDonorID().equals(donorID)) {
                targetDonor = currentDonor;
            }
            tempQueue.enqueue(currentDonor);
        }

        while (!tempQueue.isEmpty()) {
            donorQueue.enqueue(tempQueue.dequeue());
        }

        if (targetDonor != null) {

            String newName = InputUtility.promptStringInput("Enter new name (Just leave blank to keep current): ");

            String newType = "";
            if (targetDonor.getDCategory().equalsIgnoreCase("individual")) {
                System.out.println("Update type for Individual:");
                int individualType = InputUtility.promptIntInput("1. Public\n2. Private\nSelection --> ");
                newType = (individualType == 1) ? "Public" : "Private";
            } else if (targetDonor.getDCategory().equalsIgnoreCase("organisation")) {
                System.out.println("Update type for Organisation:");
                int organisationType = InputUtility.promptIntInput("1. Public\n2. Private\n3. Government\nSelection --> ");
                switch (organisationType) {
                    case 1:
                        newType = "Public";
                        break;
                    case 2:
                        newType = "Private";
                        break;
                    case 3:
                        newType = "Government";
                        break;
                    default:
                        System.out.println("Invalid selection. Keeping current type.");
                        newType = targetDonor.getDonorType(); // keep current if invalid input
                        break;
                }
            }
            String donorName = formatDonorName(newName);
            String newPhoneNum = InputUtility.promptStringInput("Enter new contact number (Just leave blank to keep current): ");
            if (!newPhoneNum.trim().isEmpty()) {
                newPhoneNum = formatPhoneNum(newPhoneNum);
                if (newPhoneNum == null) {
                    // User chose to keep the current phone number
                    newPhoneNum = targetDonor.getPhoneNum();
                }
            }

            if (!newName.trim().isEmpty()) {
                targetDonor.setDonorName(donorName);
            }
            if (!newType.trim().isEmpty()) {
                targetDonor.setDonorType(newType);
            }
            if (!newPhoneNum.trim().isEmpty()) {
                targetDonor.setPhoneNum(newPhoneNum);
            }

            System.out.print(donorManagementUI.updateDonorSuccessfulMsg());
        } else {
            System.out.println("Donor with ID " + donorID + " not found.");
        }
    }

    //search by call the byID function
    public void search() {
        if (donorQueue.isEmpty()) {
            System.out.println("The donor queue is empty. No data can be searched.Please add donors data first.");
            return; // Exit the function since there's nothing to update
        }
        System.out.print(donorManagementUI.searchDonorHeader());
        String donorID = InputUtility.promptStringInput("Enter Donor ID to update (e.g. D1): ");
        Donor foundDonor = searchDonorDetailsByID(donorID);

        if (foundDonor != null) {
            System.out.print(donorManagementUI.searchSuccess());
            displayDonorDetails(foundDonor);
        } else {
            System.out.print(donorManagementUI.donorNotFoundMsg());
        }
    }

    public void displayDonorDetails(Donor donor) {
        System.out.println("\n\n                                       Donor's Info                                      ");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Donor ID         : " + donor.getDonorID());
        System.out.println("Donor Name       : " + donor.getDonorName());
        System.out.println("Donor Type       : " + donor.getDonorType());
        System.out.println("Donor Category   : " + donor.getDCategory());
        System.out.println("Donor Contact No : " + donor.getPhoneNum());
        System.out.println("------------------------------------------------------------------------------------------");
    }

    //filter type/category, will only print matcthing results
    public void donorFilter() {
        QueueInterface<Donor> filteredDonors = donorQueue; // Start with all donors
        boolean continueFiltering = true;

        while (continueFiltering) {
            System.out.println("\nFilter by Type:");
            System.out.println("1.  Government");
            System.out.println("2.  Public");
            System.out.println("3.  Private");
            System.out.println("4.  Skip");
            int typeSelection = InputUtility.promptIntInput("Selection  -->  ");

            String type = "";
            switch (typeSelection) {
                case 1:
                    type = "government";
                    break;
                case 2:
                    type = "public";
                    break;
                case 3:
                    type = "private";
                    break;
                case 4:
                    type = "all"; // Skip filtering by type
                    break;
                default:
                    ErrorMessage.errorInputMsg();
                    continue;
            }

            System.out.println("\nFilter by Category:");
            System.out.println("1.  Individual");
            System.out.println("2.  Organisation");
            System.out.println("3.  Skip");
            int categorySelection = InputUtility.promptIntInput("Selection  -->  ");

            String category = "";
            switch (categorySelection) {
                case 1:
                    category = "individual";
                    break;
                case 2:
                    category = "organisation";
                    break;
                case 3:
                    category = "all"; // Skip filtering by category
                    break;
                default:
                    ErrorMessage.errorInputMsg();
                    continue;
            }

            filteredDonors = filterDonors(type, category, filteredDonors);

            if (filteredDonors.isEmpty()) {
                System.out.print(donorManagementUI.notMatchFilterMsg());
                return; // Exit if no matching donors are found
            } else {
                System.out.print(donorManagementUI.matchFilterMsg());
                System.out.printf("%-10s %-30s %-15s %-15s %-10s%n", "ID", "Name", "Type", "Category", "ContactNo.");
                System.out.println("--------------------------------------------------------------------------------------");
                QueueInterface<Donor> displayQueue = new LinkedQueue<>(); // Use a temporary queue to display
                while (!filteredDonors.isEmpty()) {
                    Donor donor = filteredDonors.dequeue();
                    displayQueue.enqueue(donor);
                    System.out.println(donor.toString());
                }
                filteredDonors = displayQueue; // Restore filtered donors for further filtering

                System.out.print("\nDo you want to continue filtering? (1 = Yes / 2 = No): \n");
                int continueSelection = InputUtility.promptIntInput("Selection  -->  ");
                if (continueSelection != 1) {
                    continueFiltering = false;
                }
            }
        }
    }

    public QueueInterface<Donor> filterDonors(String type, String category, QueueInterface<Donor> inputQueue) {
        QueueInterface<Donor> filteredDonors = new LinkedQueue<>();
        QueueInterface<Donor> tempQueue = new LinkedQueue<>();

        while (!inputQueue.isEmpty()) {
            Donor donor = inputQueue.dequeue();
            boolean match = true;

            if (!type.equalsIgnoreCase("all") && !donor.getDonorType().equalsIgnoreCase(type)) {
                match = false;
            }

            if (!category.equalsIgnoreCase("all") && !donor.getDCategory().equalsIgnoreCase(category)) {
                match = false;
            }

            if (match) {
                filteredDonors.enqueue(donor);
            }
            tempQueue.enqueue(donor);
        }

        while (!tempQueue.isEmpty()) {
            inputQueue.enqueue(tempQueue.dequeue());
        }

        return filteredDonors;
    }

    public void listDonorWithAllDonations() {
        boolean listDonorWithAllDonationsAgain = true;

        do {
            System.out.print(donorManagementUI.listDonorMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1:
                    String searchID = InputUtility.promptStringInput("Enter Donor ID to update (e.g. D1): ");

                    Donor foundDonor = displayDonorDetailsByID(searchID);
                    if (foundDonor != null) {
                        displayDonorDetails(foundDonor);
                        System.out.print(donorManagementUI.listDonorDonationsMenu());
                        System.out.printf("%-15s %-40s %-50s%n", "Date", "Category", "Type");
                        System.out.println("------------------------------------------------------------------------------------------");

                        Iterator<Donation> it = DonationInitialize.getDonationList().getIterator();
                        while (it.hasNext()) {
                            Donation itDonation = it.next();
                            if (foundDonor.getDonorID().equals(itDonation.getDonor().getDonorID())) {
                                System.out.printf("%-15s %-40s %-50s%n", itDonation.getDonateDate(), itDonation.getCategory(), itDonation.getType());
                            }
                        }

                    } else {
                        System.out.print(donorManagementUI.donorNotFoundMsg());
                    }
                    break;

                case 2:
                    QueueInterface<Donor> tempDonorQueue = new LinkedQueue<>();
                    System.out.printf("%-15s %-60s %-20s %-30s%-50s%n", "Donor ID", "Name", "Date", "Category", "Type");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    while (!donorQueue.isEmpty()) {
                        Donor foundAllDonor = donorQueue.dequeue();
                        tempDonorQueue.enqueue(foundAllDonor);

                        // Print the donor's ID and Name without a newline
                        System.out.printf("%-15s %-60s", foundAllDonor.getDonorID(), foundAllDonor.getDonorName());

                        // Track whether a donation is found for the current donor
                        boolean firstDonationPrinted = false;

                        // Get the iterator for the donation list
                        Iterator<Donation> it = DonationInitialize.getDonationList().getIterator();
                        while (it.hasNext()) {
                            Donation itDonation = it.next();
                            if (foundAllDonor.getDonorID().equals(itDonation.getDonor().getDonorID())) {
                                // If it's the first donation for the donor, print it on the same line
                                if (!firstDonationPrinted) {
                                    System.out.printf(" %-20s %-30s %-50s%n", itDonation.getDonateDate(), itDonation.getCategory(), itDonation.getType());
                                    firstDonationPrinted = true;
                                } else {
                                    // For subsequent donations, print them on new lines, aligned under the donor information
                                    System.out.printf("%-15s %-60s %-20s %-30s %-50s%n", "", "", itDonation.getDonateDate(), itDonation.getCategory(), itDonation.getType());
                                }
                            }
                        }

                        // If no donations were found, print a newline
                        if (!firstDonationPrinted) {
                            System.out.println();
                        }
                        System.out.println();
                    }
                    while (!tempDonorQueue.isEmpty()) {
                        donorQueue.enqueue(tempDonorQueue.dequeue());
                    }

                    break;

                case 0:
                    listDonorWithAllDonationsAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (listDonorWithAllDonationsAgain);
    }

    public Donor displayDonorDetailsByID(String donorID) {
        QueueInterface<Donor> tempQueue = new LinkedQueue<>();
        Donor foundDonor = null;

        while (!donorQueue.isEmpty()) {
            Donor currentDonor = donorQueue.dequeue();
            if (currentDonor.getDonorID().equals(donorID)) {
                foundDonor = currentDonor;
            }
            tempQueue.enqueue(currentDonor);
        }

        donorQueue = tempQueue;
        return foundDonor;
    }

public void generateSummaryReport() {
    int totalDonors = 0;
    int governmentCount = 0;
    int privateCount = 0;
    int publicCount = 0;
    int individualCount = 0;
    int organizationCount = 0;

    QueueInterface<Donor> tempQueue = new LinkedQueue<>();

    double highestMonthlyDonation = 0.0;
    double highestAnnualDonation = 0.0;
    Donor topMonthlyDonor = null;
    Donor topAnnualDonor = null;

    LocalDate currentDate = LocalDate.now();
    String currentMonthYear = currentDate.getMonth() + "-" + currentDate.getYear();
    String currentYear = Integer.toString(currentDate.getYear());

    while (!donorQueue.isEmpty()) {
        Donor donor = donorQueue.dequeue();
        totalDonors++;

        switch (donor.getDonorType().toLowerCase()) {
            case "government":
                governmentCount++;
                break;
            case "private":
                privateCount++;
                break;
            case "public":
                publicCount++;
                break;
        }

        switch (donor.getDCategory().toLowerCase()) {
            case "individual":
                individualCount++;
                break;
            case "organisation":
                organizationCount++;
                break;
        }

        tempQueue.enqueue(donor);
    }

    while (!tempQueue.isEmpty()) {
        donorQueue.enqueue(tempQueue.dequeue());
    }

    Iterator<Donation> it = DonationInitialize.getDonationList().getIterator();
    while (it.hasNext()) {
        Donation donation = it.next();
        Donor donor = donation.getDonor();
        LocalDate date = donation.getDonateDate();
        String donationMonthYear = date.getMonth() + "-" + date.getYear();
        String donationYear = Integer.toString(date.getYear());

        double monthlyDonation = 0.0;
        double annualDonation = 0.0;

        Iterator<Donation> innerIt = DonationInitialize.getDonationList().getIterator();
        while (innerIt.hasNext()) {
            Donation innerDonation = innerIt.next();
            if (innerDonation.getDonor().equals(donor)) {
                LocalDate innerDate = innerDonation.getDonateDate();
                String innerMonthYear = innerDate.getMonth() + "-" + innerDate.getYear();
                String innerYear = Integer.toString(innerDate.getYear());

                if (innerMonthYear.equals(currentMonthYear)) {
                    monthlyDonation += innerDonation.getValue();
                }

                if (innerYear.equals(currentYear)) {
                    annualDonation += innerDonation.getValue();
                }
            }
        }

        if (monthlyDonation > highestMonthlyDonation) {
            highestMonthlyDonation = monthlyDonation;
            topMonthlyDonor = donor;
        }

        if (annualDonation > highestAnnualDonation) {
            highestAnnualDonation = annualDonation;
            topAnnualDonor = donor;
        }
    }

    double governmentPercentage = ((double) governmentCount / totalDonors) * 100;
    double privatePercentage = ((double) privateCount / totalDonors) * 100;
    double publicPercentage = ((double) publicCount / totalDonors) * 100;
    double individualPercentage = ((double) individualCount / totalDonors) * 100;
    double organizationPercentage = ((double) organizationCount / totalDonors) * 100;

    System.out.print(donorManagementUI.reportHeader());
    System.out.print(donorManagementUI.summaryReportDetails(
            governmentCount, privateCount, publicCount, individualCount, organizationCount,
            governmentPercentage, privatePercentage, publicPercentage, individualPercentage, organizationPercentage));
    System.out.println("Total Donors            :" + totalDonors + "\n");
    
    if (topMonthlyDonor != null) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Top Donor of This Month : " + topMonthlyDonor.getDonorName() + " with a total donation value of " + highestMonthlyDonation);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    } else {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("There is no Top Donor of This Month as no donation record for this month.");
    }

    if (topAnnualDonor != null) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Top Donor of This Year  : " + topAnnualDonor.getDonorName() + " with a total donation value of " + highestAnnualDonation);
    } else {
        System.out.println("There is no donation record for this year.");
        
    }
    listDonorsAndTotalDonations();
}
public void listDonorsAndTotalDonations() {
    QueueInterface<Donor> tempQueue = new LinkedQueue<>();
    double totalDonationsForAllDonors = 0.0;
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    System.out.println("===========================================================================================================================");
    System.out.println("                                 Annual Report Donors' Donation Value                                                      ");
    System.out.println("===========================================================================================================================");
    System.out.printf("%-20s %-60s %-15s\n", "Donor ID", "Donor Name", "Total Donation (RM)");
    System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    while (!donorQueue.isEmpty()) {
        Donor donor = donorQueue.dequeue();
        double totalDonationForThisDonor = 0.0;

        Iterator<Donation> it = DonationInitialize.getDonationList().getIterator();
        while (it.hasNext()) {
            Donation donation = it.next();
            if (donation.getDonor().equals(donor)) {
                LocalDate donationDate = donation.getDonateDate();
                int donationYear = donationDate.getYear();
                
                if (donationYear == currentYear) {
                    totalDonationForThisDonor += donation.getValue();
                }
            }
        }

        totalDonationsForAllDonors += totalDonationForThisDonor;

        System.out.printf("%-20s %-60s %-15.2f\n", donor.getDonorID(), donor.getDonorName(), totalDonationForThisDonor);

        tempQueue.enqueue(donor);
    }

    while (!tempQueue.isEmpty()) {
        donorQueue.enqueue(tempQueue.dequeue());
    }

    System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-81s %-15.2f\n", "Total Donation Value for All Donors", totalDonationsForAllDonors);
    System.out.println("---------------------------------------------------------------------------------------------------------------------------");
}
 

    public void displayCurrentDonors() {
        System.out.println("\nCurrent Donors in the Queue:");
        if (donorQueue.isEmpty()) {
            System.out.println("No donors available.");
            return;
        }
        System.out.printf("%-10s %-60s %-15s %-15s %-10s%n", "Donor ID", "Name", "Type", "Category", "ContactNo.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        QueueInterface<Donor> tempQueue = new LinkedQueue<>();
        while (!donorQueue.isEmpty()) {
            Donor donor = donorQueue.dequeue();
            System.out.printf("%-10s %-60s %-15s %-15s %-10s%n", donor.getDonorID(), donor.getDonorName(), donor.getDonorType(), donor.getDCategory(), donor.getPhoneNum());
            tempQueue.enqueue(donor);
        }
        while (!tempQueue.isEmpty()) {
            donorQueue.enqueue(tempQueue.dequeue());
        }
        System.out.print("\n");
    }

    private static String formatDonorType(String donorType) {
        if (donorType == null || donorType.isEmpty()) {
            return donorType;
        }
        return donorType.substring(0, 1).toUpperCase() + donorType.substring(1).toLowerCase();
    }

    private static String formatDonorCategory(String donorCategory) {
        if (donorCategory == null || donorCategory.isEmpty()) {
            return donorCategory;
        }
        return donorCategory.substring(0, 1).toUpperCase() + donorCategory.substring(1).toLowerCase();
    }

    private static String formatDonorName(String donorName) {
        if (donorName == null || donorName.isEmpty()) {
            return donorName;
        }

        StringBuilder formattedName = new StringBuilder();
        boolean insideParentheses = false;

        for (int i = 0; i < donorName.length(); i++) {
            char currentChar = donorName.charAt(i);

            if (currentChar == '(') {
                insideParentheses = true;
                formattedName.append(currentChar);
            } else if (currentChar == ')') {
                insideParentheses = false;
                formattedName.append(currentChar);
            } else if (insideParentheses) {
                formattedName.append(Character.toUpperCase(currentChar));
            } else {
                if (i == 0 || donorName.charAt(i - 1) == ' ') {
                    formattedName.append(Character.toUpperCase(currentChar));
                } else {
                    formattedName.append(Character.toLowerCase(currentChar));
                }
            }
        }

        return formattedName.toString().trim();
    }

    private String formatPhoneNum(String phoneNum) {
        boolean isValidPhone = false;
        String formattedPhoneNum = phoneNum;

        while (!isValidPhone) {
            if (formattedPhoneNum.matches("011-\\d{8}")) {
                // Valid format for numbers starting with 011 and having 11 digits
                isValidPhone = true;
            } else if (formattedPhoneNum.matches("\\d{3}-\\d{7}") && !formattedPhoneNum.startsWith("011")) {
                // Valid format for numbers not starting with 011 and having 10 digits
                isValidPhone = true;
            } else {
                System.out.println("Invalid phone number! Please enter a valid phone number in the format xxx-xxxxxxx with 10 digits, or if starting with 011, 11 digits.\n");
                formattedPhoneNum = InputUtility.promptStringInput("Enter new contact number (Just leave blank to keep current): ");
                if (formattedPhoneNum.trim().isEmpty()) {
                    return null; // Return null if the user leaves the input blank
                }
            }
        }
        return formattedPhoneNum;
    }

    private Donor searchDonorDetailsByID(String donorID) {
        QueueInterface<Donor> tempQueue = new LinkedQueue<>();
        Donor foundDonor = null;
        while (!donorQueue.isEmpty()) {
            Donor donor = donorQueue.dequeue();
            if (donor.getDonorID().equalsIgnoreCase(donorID)) {
                foundDonor = donor;
            }
            tempQueue.enqueue(donor);
        }
        while (!tempQueue.isEmpty()) {
            donorQueue.enqueue(tempQueue.dequeue());
        }
        return foundDonor;
    }

    public Donor searchDonor(String donorID) {
        Iterator<Donor> it = donorQueue.getIterator();
        while (it.hasNext()) {
            Donor donor = it.next();
            if (donor.getDonorID().equals(donorID)) {
                return donor;
            }
        }
        return null;
    }

    public String donorIDInput(String message) {
        boolean inputDonorIDAgain;
        String donorID;
        do {
            inputDonorIDAgain = true;
            donorID = InputUtility.promptStringInput(message);
            Donor donorSearch = searchDonor(donorID);
            if (donorSearch != null) {
                inputDonorIDAgain = false;
            } else {
                System.out.print(donorManagementUI.removeDonorFailedMsg());
                boolean selectAgain;
                System.out.print(donorManagementUI.enterAgainOrExitMenu());
                do {
                    selectAgain = true;
                    int selection = InputUtility.promptIntInput("Selection  -->  ");

                    switch (selection) {
                        case 1:
                            selectAgain = false;
                            break;

                        case 0:
                            selectAgain = false;
                            inputDonorIDAgain = false;
                            break;

                        default:
                            ErrorMessage.errorInputMsg();
                    }
                } while (selectAgain);
            }
        } while (inputDonorIDAgain);
        return donorID;
    }

    public Donor getDonor(int index) {
        Iterator<Donor> it = donorQueue.getIterator();
        Donor donor = null;

        for (int i = 0; i <= index && it.hasNext(); i++) {
            donor = it.next();
        }

        return donor;
    }
}
