/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.*;
import utility.*;
import boundary.DonationManagementUI;
import adt.*;
import dao.DonationInitialize;
import dao.DonorInitialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Loo Jie Qi
 */
public class DonationManagementControl {

    QueueInterface<Donation> donationQueue = new LinkedQueue<>();

    private DonationManagementUI donationManagementUI = new DonationManagementUI();
    private DonorManagementControl donorManagementControl = new DonorManagementControl(DonorInitialize.getDonorList());

    public DonationManagementControl(QueueInterface<Donation> donationQueue) {
        this.donationQueue = donationQueue;
    }
    
    public void donationManagement() {
        boolean selectDonationManagementAgain = true;

        do {
            System.out.print(donationManagementUI.donationManagementMenu());
            int donationManagementSelection = InputUtility.promptIntInput("Selection  -->  ");

            switch (donationManagementSelection) {
                case 1: // Add Donation
                    addDonation();
                    break;

                case 2: // Remove Donation
                    removeDonation();
                    break;

                case 3: // Search Donation
                    searchDonation();
                    break;

                case 4: // Amend Donation
                    amendDonation();
                    break;

                case 5: // Track Items By Category
                    trackDonationsByCategory();
                    break;

                case 6: // List Donations By Donor
                    listDonationsByDonor();
                    break;

                case 7: // List All Donations
                    listAllDonations();
                    break;

                case 8: // Filter Donation
                    filterDonations();
                    break;

                case 9: // Generate Donations Summary Report 
                    generateSummaryReport();
                    break;

                case 0: // Exit
                    selectDonationManagementAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (selectDonationManagementAgain);
    }

    public void addDonation() {
        boolean selectAgain = true;
        String category;
        do {
            System.out.print(donationManagementUI.addDonationMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");
            category = categorySelection(selection);
            if (!category.equals("Exit")) {
                String donorID = donorManagementControl.donorIDInput("Donor ID      : ");
                Donor donor = donorManagementControl.searchDonor(donorID);
                String type;
                int qty;
                if (donor != null) {
                    if (category.equals("Cash")) {
                        type = InputUtility.promptStringInput("Type (Only can input MYR.)   : ").toUpperCase();
                        qty = 0;
                    } else {
                        type = InputUtility.promptStringInput("Type (e.g. Rice(pack))   : ");
                        qty = InputUtility.promptIntInput("Quantity     : ");
                    }
                    double value = InputUtility.promptDoubleInput("Value        : ");
                    if (add(category, type, qty, value, donor)) {
                        System.out.print(donationManagementUI.addDonationSuccessMsg());
                    } else {
                        System.out.print(donationManagementUI.addDonationUnsuccessMsg());
                    }
                }
            } else {
                selectAgain = false;
            }
        } while (selectAgain);
    }

    public void removeDonation() {
        boolean removeDonationAgain = true;
        do {
            System.out.print(donationManagementUI.removeDonationMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: //Remove specific donation
                    String donationID = donationIDInput("Donation ID   : ");

                    if (remove(donationID)) {
                        System.out.print(donationManagementUI.removeDonationSuccessMsg());
                    } else {
                        System.out.print(donationManagementUI.removeDonationUnsuccessMsg());
                    }
                    break;

                case 2: //Remove all
                    if (clear()) {
                        System.out.print(donationManagementUI.removeDonationSuccessMsg());
                    } else {
                        System.out.print(donationManagementUI.removeDonationUnsuccessMsg());
                    }
                    break;

                case 0: //Exit
                    removeDonationAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (removeDonationAgain);
    }

    public void searchDonation() {
        System.out.print(donationManagementUI.searchDonationHeader());
        String donationID = donationIDInput("Donation ID   : ");
        Donation searchedDonation = search(donationID);
        if (searchedDonation != null) {
            System.out.print(donationManagementUI.donationHeader());
            System.out.printf("| %-4d %-115s\n", 1, searchedDonation.toString());
            System.out.println("+====================================================================================================================================================================================================+\n");
        } else {
            System.out.print(donationManagementUI.donationNotFoundMsg());
        }
    }

    public void amendDonation() {

        boolean amendDonationAgain = true;
        String donationID, category, donorID;
        int categortSelection;
        int qty = 0;
        double value = 0;
        Iterator<Donation> it = donationQueue.getIterator();

        do {
            System.out.print(donationManagementUI.amendDonationMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: // Amend Donor
                    donationID = donationIDInput("Donation ID   : ");
                    donorID = donorManagementControl.donorIDInput("Change to Donor ID    : ");
                    Donor donor = donorManagementControl.searchDonor(donorID);
                    while (it.hasNext()) {
                        Donation donation = it.next();
                        if (donation.getDonationID().equals(donationID)) {
                            donation.setDonor(donor);
                        }
                    }
                    System.out.print(donationManagementUI.amendDonationSuccessMsg());
                    break;

                case 2: // Amend Donation Category and Type
                    donationID = donationIDInput("Donation ID   : ");
                    System.out.println("\nSelect the donation category that you want to change");
                    System.out.print(donationManagementUI.categoryList());
                    categortSelection = InputUtility.promptIntInput("Category Selection  -->  ");
                    category = categorySelection(categortSelection);
                    String type = InputUtility.promptStringInput("Change to type  : ");

                    while (it.hasNext()) {
                        Donation donation = it.next();
                        if (donation.getDonationID().equals(donationID)) {
                            donation.setCategory(category);
                            donation.setType(type);
                        }
                    }
                    System.out.print(donationManagementUI.amendDonationSuccessMsg());
                    break;

                case 3: // Amend Donation Quantity and Value
                    donationID = donationIDInput("Donation ID   : ");
                    qty = InputUtility.promptIntInput("Change to quantity   : ");
                    value = InputUtility.promptDoubleInput("Change to value   : ");
                    while (it.hasNext()) {
                        Donation donation = it.next();
                        if (donation.getDonationID().equals(donationID)) {
                            donation.setQty(qty);
                            donation.setValue(value);
                        }
                    }
                    System.out.print(donationManagementUI.amendDonationSuccessMsg());
                    break;

                case 4: // Amend Donation Informations
                    donationID = donationIDInput("Donation ID   : ");
                    donorID = donorManagementControl.donorIDInput("Donor ID     : ");
                    Donor donor1 = donorManagementControl.searchDonor(donorID);
                    System.out.println("\nSelect the donation category that you want to change");
                    System.out.print(donationManagementUI.categoryList());
                    categortSelection = InputUtility.promptIntInput("Category Selection  -->  ");
                    category = categorySelection(categortSelection);
                    type = InputUtility.promptStringInput("Change to type  : ");
                    qty = InputUtility.promptIntInput("Change to quantity   : ");
                    value = InputUtility.promptDoubleInput("Change to value   : ");
                    while (it.hasNext()) {
                        Donation donation = it.next();
                        if (donation.getDonationID().equals(donationID)) {
                            donation.setDonor(donor1);
                            donation.setCategory(category);
                            donation.setType(type);
                            donation.setQty(qty);
                            donation.setValue(value);
                        }
                    }
                    System.out.print(donationManagementUI.amendDonationSuccessMsg());
                    break;

                case 0:
                    amendDonationAgain = false;
                    break;
            }
        } while (amendDonationAgain);
    }

    public void trackDonationsByCategory() {
        boolean trackItemsAgain = true;
        do {
            System.out.print(donationManagementUI.trackDonationsByCategoryMenu());
            int selection = InputUtility.promptIntInput("Category Selection  -->  ");
            String category = categorySelection(selection);

            if (category != null) {
                if (category.equals("Exit")) {
                    trackItemsAgain = false;
                    continue;
                }

                QueueInterface<Donation> trackedDonation = trackDonations(category);

                if (trackedDonation != null) {
                    Iterator<Donation> it = trackedDonation.getIterator();
                    System.out.print(donationManagementUI.donationHeader());
                    printDonationData(it, trackedDonation);
                } else {
                    System.out.print(donationManagementUI.noDonationForSpecifiedCategoryMsg());
                }

                QueueInterface<Donation> sortedQueue = new LinkedQueue<>();

                while (sortedQueue != null) {
                    sortedQueue = sorting(trackedDonation);
                    if (sortedQueue != null) {
                        Iterator<Donation> it = sortedQueue.getIterator();
                        System.out.print(donationManagementUI.donationHeader());
                        printDonationData(it, sortedQueue);
                    }
                }
            }
        } while (trackItemsAgain);
    }

    public void listDonationsByDonor() {
        System.out.print(donationManagementUI.listDonationByDonorHeader());
        String donorID = donorManagementControl.donorIDInput("Donor ID      : ");
        Donor donor = donorManagementControl.searchDonor(donorID);
        printDonations(donor);
    }

    public void listAllDonations() {
        printAllDonations();
    }

    public void filterDonations() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean filterDonationAgain = true;
        do {
            int year, month, day;
            String strDate;
            System.out.print(donationManagementUI.filterDonationMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: // By Donation Date
                    boolean inputDateAgain = true;
                    LocalDate fromDate = LocalDate.now();
                    ;
                    LocalDate toDate = LocalDate.now();
                    String formattedYear;
                    String formattedMonth;
                    String formattedDay;

                    do {
                        System.out.println("-------From Date-------");
                        year = InputUtility.promptIntInput("Year         : ");
                        formattedYear = String.format("%04d", year);
                        month = InputUtility.promptIntInput("Month (1-12) : ");
                        formattedMonth = String.format("%02d", month);
                        day = InputUtility.promptIntInput("Date         : ");
                        formattedDay = String.format("%02d", day);
                        strDate = formattedYear + "-" + formattedMonth + "-" + formattedDay;

                        if (DateValidation.isValidDate(strDate)) {
                            fromDate = LocalDate.parse(strDate, formatter);

                            if (!DateValidation.isFuture(fromDate)) {
                                inputDateAgain = false;
                            } else {
                                System.out.print(donationManagementUI.selectedFutureDateMsg());
                            }
                        } else {
                            ErrorMessage.invalidDateMsg();
                        }
                    } while (inputDateAgain);

                    do {
                        inputDateAgain = true;
                        System.out.println("\n--------To Date--------");
                        year = InputUtility.promptIntInput("Year         : ");
                        formattedYear = String.format("%04d", year);
                        month = InputUtility.promptIntInput("Month (1-12) : ");
                        formattedMonth = String.format("%02d", month);
                        day = InputUtility.promptIntInput("Date         : ");
                        formattedDay = String.format("%02d", day);
                        strDate = formattedYear + "-" + formattedMonth + "-" + formattedDay;

                        if (DateValidation.isValidDate(strDate)) {
                            toDate = LocalDate.parse(strDate, formatter);

                            if (!DateValidation.isFuture(toDate)) {
                                if (toDate.isBefore(fromDate)) {
                                    System.out.print(donationManagementUI.sleectedBeforeFromDateMsg());
                                } else {
                                    inputDateAgain = false;
                                }
                            } else {
                                System.out.print(donationManagementUI.selectedFutureDateMsg());
                            }
                        } else {
                            ErrorMessage.invalidDateMsg();
                        }
                    } while (inputDateAgain);

                    String dateRange = fromDate + "  ---  " + toDate;
                    QueueInterface<Donation> donationFilterByDate = filter(fromDate, toDate);

                    if (!donationFilterByDate.isEmpty()) {
                        Iterator<Donation> iteratorFilter = donationFilterByDate.getIterator();
                        System.out.print(donationManagementUI.filterDonationByDate(dateRange));
                        printDonationData(iteratorFilter, donationFilterByDate);

                        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();

                        while (sortedQueue != null) {
                            sortedQueue = sorting(donationFilterByDate);
                            if (sortedQueue != null) {
                                Iterator<Donation> itSortedQueue = sortedQueue.getIterator();
                                System.out.print(donationManagementUI.filterDonationByDate(dateRange));
                                printDonationData(itSortedQueue, sortedQueue);
                            }
                        }
                    } else {
                        System.out.print(donationManagementUI.noDonationForSpecifiedDateMsg());
                    }

                    break;

                case 2: //By Donation Value
                    boolean inputMaxValueAgain = true;
                    System.out.println("-------Min Value-------");
                    double minValue = InputUtility.promptDoubleInput("Value        : ");
                    double maxValue = 0;
                    do {
                        System.out.println("\n-------Max Value-------");
                        maxValue = InputUtility.promptDoubleInput("Value        : ");
                        if (maxValue >= minValue) {
                            inputMaxValueAgain = false;
                        } else {
                            System.out.print(donationManagementUI.maxValueLessThanMinValue());
                        }
                    } while (inputMaxValueAgain);
                    String valueRange = minValue + "  ---  " + maxValue;
                    QueueInterface<Donation> donationFilterByValue = filter(minValue, maxValue);

                    if (!donationFilterByValue.isEmpty()) {
                        Iterator<Donation> iteratorFilter = donationFilterByValue.getIterator();
                        System.out.print(donationManagementUI.filterDonationByValue(valueRange));
                        printDonationData(iteratorFilter, donationFilterByValue);

                        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();

                        while (sortedQueue != null) {
                            sortedQueue = sorting(donationFilterByValue);
                            if (sortedQueue != null) {
                                Iterator<Donation> itSortedQueue = sortedQueue.getIterator();
                                System.out.print(donationManagementUI.filterDonationByValue(valueRange));
                                printDonationData(itSortedQueue, sortedQueue);
                            }
                        }
                    } else {
                        System.out.print(donationManagementUI.noDonationForSpecifiedValueMsg());
                    }
                    break;

                case 0: //Exit
                    filterDonationAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (filterDonationAgain);
    }

    public void generateSummaryReport() {
        printSummaryReport();
    }

    private boolean add(String category, String type, int qty, double value, Donor donor) {
        if (donor != null) {
            Donation newDonation = new Donation(category, type, qty, value, donor);
            donationQueue.enqueue(newDonation);
            return true;
        } else {
            return false;
        }
    }

    private boolean remove(String donationID) {
        boolean isRemoved = false;
        QueueInterface<Donation> tempQueue = new LinkedQueue<>();

        while (!donationQueue.isEmpty()) {
            Donation donation = donationQueue.dequeue();
            if (donation.getDonationID().equals(donationID)) {
                isRemoved = true;
            } else {
                tempQueue.enqueue(donation);
            }
        }

        while (!tempQueue.isEmpty()) {
            donationQueue.enqueue(tempQueue.dequeue());
        }

        return isRemoved;
    }

    private boolean clear() {
        if (!donationQueue.isEmpty()) {
            donationQueue.clear();
            return true;
        } else {
            return false;
        }
    }

    private Donation search(String donationID) {
        Iterator<Donation> it = donationQueue.getIterator();
        while (it.hasNext()) {
            Donation donation = it.next();
            if (donation.getDonationID().equals(donationID)) {
                return donation;
            }
        }
        return null;
    }

    private boolean amend(String donationID, String newCategory, String newType, int newQty, double newValue, Donor newDonor) {
        Donation donation = search(donationID);
        if (donation != null) {
            donation.setCategory(newCategory);
            donation.setType(newType);
            donation.setQty(newQty);
            donation.setValue(newValue);
            donation.setDonor(newDonor);
            return true;
        }
        return false;
    }

    private QueueInterface<Donation> trackDonations(String category) {
        QueueInterface<Donation> allDonationTracked = new LinkedQueue<>();
        Iterator<Donation> it = donationQueue.getIterator();
        while (it.hasNext()) {
            Donation donation = it.next();
            if (donation.getCategory().equals(category)) {
                allDonationTracked.enqueue(donation);
            }
        }

        if (allDonationTracked.isEmpty()) {
            return null;
        }

        return allDonationTracked;
    }

    private void printDonations(Donor donor) {
        QueueInterface<Donation> listOutDonation = new LinkedQueue();
        Iterator<Donation> iterator = donationQueue.getIterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Donation donation = iterator.next();
            if (donation.getDonor().equals(donor)) {
                listOutDonation.enqueue(donation);
                found = true;
            }
        }

        if (found) {
            Iterator<Donation> it = listOutDonation.getIterator();
            int no = 1;
            System.out.print(donationManagementUI.printDonorDonationsHeader(donor.getDonorID(), donor.getDonorName()));
            printDonationData(it, listOutDonation);

            QueueInterface<Donation> sortedQueue = new LinkedQueue<>();

            while (sortedQueue != null) {
                sortedQueue = sorting(listOutDonation);
                if (sortedQueue != null) {
                    Iterator<Donation> itSortedQueue = sortedQueue.getIterator();
                    System.out.print(donationManagementUI.printDonorDonationsHeader(donor.getDonorID(), donor.getDonorName()));
                    printDonationData(itSortedQueue, sortedQueue);
                }
            }
        } else {
            System.out.println(donationManagementUI.noDonationForSpecifiedDonorMsg());
        }
    }

    private void printAllDonations() {
        Iterator<Donation> it = donationQueue.getIterator();
        if (it.hasNext()) {
            System.out.print(donationManagementUI.allDonationHeader());
            printDonationData(it, donationQueue);

            QueueInterface<Donation> sortedQueue = new LinkedQueue<>();

            while (sortedQueue != null) {
                sortedQueue = sorting(donationQueue);
                if (sortedQueue != null) {
                    Iterator<Donation> itSortedQueue = sortedQueue.getIterator();
                    System.out.print(donationManagementUI.allDonationHeader());
                    printDonationData(itSortedQueue, sortedQueue);
                }
            }
        } else {
            System.out.println(donationManagementUI.withouDonationMsg());
        }
    }

    private QueueInterface<Donation> filter(LocalDate startDate, LocalDate endDate) {
        QueueInterface<Donation> filterDonations = new LinkedQueue<>();
        Iterator<Donation> it = donationQueue.getIterator();

        while (it.hasNext()) {
            Donation donation = it.next();
            if ((donation.getDonateDate().isAfter(startDate) || donation.getDonateDate().equals(startDate))
                    && (donation.getDonateDate().isBefore(endDate) || donation.getDonateDate().equals(endDate))) {
                filterDonations.enqueue(donation);
            }
        }

        return filterDonations;
    }

    private QueueInterface<Donation> filter(double minValue, double maxValue) {
        QueueInterface<Donation> filterDonations = new LinkedQueue<>();
        Iterator<Donation> it = donationQueue.getIterator();

        while (it.hasNext()) {
            Donation donation = it.next();
            if (donation.getValue() >= minValue && donation.getValue() <= maxValue) {
                filterDonations.enqueue(donation);
            }
        }

        return filterDonations;
    }
    
 public void printSummaryReport() {
        Iterator<Donation> it = donationQueue.getIterator();
        if (it.hasNext()) {
            System.out.print(donationManagementUI.summaryReportHeader());
            QueueInterface<String> categories = new LinkedQueue<>();
            double ttlValue = 0.0, highestValue = 0.0;
            int ttlQty = 0, highestQty = 0;
            String highestValueCategory = "", highestQtyCategory = "";

            while (it.hasNext()) {
                Donation currentDonation = it.next();
                ttlValue += currentDonation.getValue();
                ttlQty += currentDonation.getQty();
            }

            //Reset Iterator
            it = donationQueue.getIterator();

            while (it.hasNext()) {
                Donation currentDonation = it.next();
                String category = currentDonation.getCategory();
                boolean alreadyProcessed = false;
                Iterator<String> processedIt = categories.getIterator();
                while (processedIt.hasNext()) {
                    if (processedIt.next().equals(category)) {
                        alreadyProcessed = true;
                        break;
                    }
                }

                if (!alreadyProcessed) {
                    int ttlCategoryQty = 0;
                    double ttlCategoryValue = 0.0;
                    QueueInterface<String> typeOutput = new LinkedQueue<>();

                    Iterator<Donation> innerIt = donationQueue.getIterator();
                    while (innerIt.hasNext()) {
                        Donation donation = innerIt.next();
                        if (donation.getCategory().equals(category)) {
                            ttlCategoryQty += donation.getQty();
                            ttlCategoryValue += donation.getValue();
                            typeOutput.enqueue(donation.getType());
                        }
                    }

                    if (highestQty < ttlCategoryQty) {
                        highestQty = ttlCategoryQty;
                        highestQtyCategory = category;
                    } else if (highestQty == ttlCategoryQty) {
                        highestQty = ttlCategoryQty;
                        highestQtyCategory += " and " + category;
                    }   

                    if (highestValue < ttlCategoryValue) {
                        highestValue = ttlCategoryValue;
                        highestValueCategory = category;
                    } else if (highestValue == ttlCategoryValue) {
                        highestValue = ttlCategoryValue;
                        highestValueCategory += " and " + category;
                    }

                    double valuePercentage = (ttlCategoryValue / ttlValue) * 100;
                    double qtyPercentage = ((double) ttlCategoryQty / ttlQty) * 100;

                    Iterator<String> itType = typeOutput.getIterator();
                    int times = 0;
                    while (itType.hasNext()) {
                        if (times == 0) {
                            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                            System.out.printf("| %-26s | %-49s | %-22d | %-25.2f | %-24.2f | %-23.2f |\n", category, itType.next(), ttlCategoryQty, ttlCategoryValue, qtyPercentage, valuePercentage);
                            times++;
                        } else {
                            System.out.printf("| %-26s | %-49s | %-22s | %-25s | %-24s | %-23s |\n", "", itType.next(), "", "", "", "");
                        }
                    }
                    categories.enqueue(category);
                }
            }
            System.out.println(donationManagementUI.summaryTotalInfo(ttlValue, ttlQty, highestValueCategory, highestQtyCategory));

            //Chart
            System.out.println("Value");
            int valueLevel = 20000;
            for (int i = 1; i <= 5; i++) { //Value Level
                String numLevel;
                numLevel = ">= " + valueLevel;
                System.out.printf("%-8s |", numLevel);
                for (int j = 1; j <= 10; j++) { // Category Type
                    int tempValue = 0;
                    Iterator<Donation> categoryForChart = donationQueue.getIterator();
                    while (categoryForChart.hasNext()) {
                        Donation tempDonation = categoryForChart.next();
                        if (tempDonation.getCategory().equals(categorySelection(j))) {
                            tempValue += (int)tempDonation.getValue();
                        }
                    }

                    if (tempValue >= valueLevel) {
                        System.out.print("       *        ");
                    } else {
                        System.out.print("                ");
                    }
                }
                valueLevel -= 5000;
                System.out.println();
            }
            
            System.out.println("---------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|--> Category");
            System.out.printf("            %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-14s%-15s   %-15s\n\n\n", categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue(), categories.dequeue());
            //--------
            
            System.out.println(donationManagementUI.summaryReportEnd());
        } else {
            System.out.println(donationManagementUI.withouDonationMsg());
        }
    }


    private QueueInterface<Donation> sorting(QueueInterface<Donation> sortQueue) {
        boolean sort = true;

        while (true) {
            String sortInput = InputUtility.promptStringInput("\nDo you want to sort the list? (y/n) : ").toLowerCase();

            if (sortInput.equals("y")) {
                break;
            } else if (sortInput.equals("n")) {
                sort = false;
                break;
            } else {
                ErrorMessage.errorInputMsg();
            }
        }

        if (sort) {
            boolean selectSortAgain = true;

            do {
                System.out.print(donationManagementUI.sortingMenu());
                int selection = InputUtility.promptIntInput("Selection  -->  ");
                String action;

                switch (selection) {
                    case 1: //Sort by value
                        action = sortingActionSelection();

                        if (!action.equals("Exit")) {
                            return sortValue(action, sortQueue);
                        }
                        break;

                    case 2: //Sort by quantity
                        action = sortingActionSelection();

                        if (!action.equals("Exit")) {
                            return sortQty(action, sortQueue);
                        }
                        break;

                    case 3: //Sort by category
                        return sortCategory(sortQueue);

                    case 4: //Sort by donor name
                        action = sortingActionSelection();

                        if (!action.equals("Exit")) {
                            return sortDonorName(action, sortQueue);
                        }
                        break;

                    case 0:
                        selectSortAgain = false;
                        break;

                    default:
                        ErrorMessage.errorInputMsg();
                }
            } while (selectSortAgain);

        }
        return null;
    }

    private QueueInterface<Donation> sortValue(String ascentOrDescent, QueueInterface<Donation> sortQueue) {
        QueueInterface<Donation> allItems = new LinkedQueue<>();

        Iterator<Donation> it = sortQueue.getIterator();
        while (it.hasNext()) {
            allItems.enqueue(it.next());
        }

        Donation[] itemArray = new Donation[allItems.size()];
        int index = 0;
        while (!allItems.isEmpty()) {
            itemArray[index++] = allItems.dequeue();
        }

        //using lambda expression to return the "Comparator<? super T> c"
        //Double.compare return that greater than = 1, equal to = 0, less than = -1
        //The function "mergeSort" in Arrays.sort() is using Dual Pivot QuickSort Alogorithms
        Arrays.sort(itemArray, (item1, item2) -> {
            if (ascentOrDescent.equals("Ascent")) {
                return Double.compare(item1.getValue(), item2.getValue());
            } else {
                return Double.compare(item2.getValue(), item1.getValue());
            }
        });

        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();
        for (Donation item : itemArray) {
            sortedQueue.enqueue(item);
        }

        return sortedQueue;
    }

    private QueueInterface<Donation> sortQty(String ascentOrDescent, QueueInterface<Donation> sortQueue) {

        QueueInterface<Donation> allItems = new LinkedQueue<>();

        Iterator<Donation> it = sortQueue.getIterator();
        while (it.hasNext()) {
            allItems.enqueue(it.next());
        }

        Donation[] itemArray = new Donation[allItems.size()];
        int index = 0;
        while (!allItems.isEmpty()) {
            itemArray[index++] = allItems.dequeue();
        }

        //using lambda expression to return the "Comparator<? super T> c"
        //Double.compare return that greater than = 1, equal to = 0, less than = -1
        //The function "mergeSort" in Arrays.sort() is using Dual Pivot QuickSort Alogorithms
        Arrays.sort(itemArray, (item1, item2) -> {
            if (ascentOrDescent.equals("Ascent")) {
                return Double.compare(item1.getQty(), item2.getQty());
            } else {
                return Double.compare(item2.getQty(), item1.getQty());
            }
        });

        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();
        for (Donation item : itemArray) {
            sortedQueue.enqueue(item);
        }

        return sortedQueue;
    }

    private QueueInterface<Donation> sortCategory(QueueInterface<Donation> sortQueue) {
        QueueInterface<Donation> cashQueue = new LinkedQueue<>();
        QueueInterface<Donation> clothingQueue = new LinkedQueue<>();
        QueueInterface<Donation> foodQueue = new LinkedQueue<>();
        QueueInterface<Donation> waterQueue = new LinkedQueue<>();
        QueueInterface<Donation> furnitureQueue = new LinkedQueue<>();
        QueueInterface<Donation> bookQueue = new LinkedQueue<>();
        QueueInterface<Donation> electronicQueue = new LinkedQueue<>();
        QueueInterface<Donation> toyQueue = new LinkedQueue<>();
        QueueInterface<Donation> medicalSuppliesQueue = new LinkedQueue<>();
        QueueInterface<Donation> otherQueue = new LinkedQueue<>();

        Iterator<Donation> it = sortQueue.getIterator();
        while (it.hasNext()) {
            Donation donation = it.next();
            switch (donation.getCategory()) {
                case "Cash":
                    cashQueue.enqueue(donation);
                    break;
                
                case "Clothing":
                    clothingQueue.enqueue(donation);
                    break;

                case "Food":
                    foodQueue.enqueue(donation);
                    break;

                case "Water":
                    waterQueue.enqueue(donation);
                    break;

                case "Furniture":
                    furnitureQueue.enqueue(donation);
                    break;

                case "Books":
                    bookQueue.enqueue(donation);
                    break;

                case "Electronics":
                    electronicQueue.enqueue(donation);
                    break;

                case "Toys":
                    toyQueue.enqueue(donation);
                    break;

                case "Medical Supplies":
                    medicalSuppliesQueue.enqueue(donation);
                    break;

                case "Others":
                    otherQueue.enqueue(donation);
                    break;

                default:
                    break;
            }
        }

        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();
        enqueueAll(sortedQueue, cashQueue);
        enqueueAll(sortedQueue, clothingQueue);
        enqueueAll(sortedQueue, foodQueue);
        enqueueAll(sortedQueue, waterQueue);
        enqueueAll(sortedQueue, furnitureQueue);
        enqueueAll(sortedQueue, bookQueue);
        enqueueAll(sortedQueue, electronicQueue);
        enqueueAll(sortedQueue, toyQueue);
        enqueueAll(sortedQueue, medicalSuppliesQueue);
        enqueueAll(sortedQueue, otherQueue);

        return sortedQueue;
    }

    private QueueInterface<Donation> sortDonorName(String ascentOrDescent, QueueInterface<Donation> sortQueue) {
        QueueInterface<Donation> sortedQueue = new LinkedQueue<>();
        Donation[] donationArray = new Donation[sortQueue.size()];
        Iterator<Donation> it = sortQueue.getIterator();

        int index = 0;
        while (it.hasNext()) {
            donationArray[index++] = it.next();
        }

        for (int i = 0; i < donationArray.length - 1; i++) {
            for (int j = 0; j < donationArray.length - i - 1; j++) {
                if ((ascentOrDescent.equals("Ascent")
                        && donationArray[j].getDonor().getDonorName().compareToIgnoreCase(donationArray[j + 1].getDonor().getDonorName()) > 0)
                        || (ascentOrDescent.equals("Descent")
                        && donationArray[j].getDonor().getDonorName().compareToIgnoreCase(donationArray[j + 1].getDonor().getDonorName()) < 0)) {

                    Donation temp = donationArray[j];
                    donationArray[j] = donationArray[j + 1];
                    donationArray[j + 1] = temp;
                }
            }
        }

        for (Donation donation : donationArray) {
            sortedQueue.enqueue(donation);
        }

        return sortedQueue;
    }

    private double calTotalValue(QueueInterface<Donation> donations) {
        double ttlValue = 0;
        Iterator<Donation> it = donations.getIterator();
        while (it.hasNext()) {
            Donation donation = it.next();
            ttlValue += donation.getValue();
        }
        return ttlValue;
    }

    private String categorySelection(int selection) {
        String category = "";
        switch (selection) {
            case 1:
                return "Cash";
            case 2:
                return "Clothing";
            case 3:
                return "Food";
            case 4:
                return "Water";
            case 5:
                return "Furniture";
            case 6:
                return "Books";
            case 7:
                return "Electronics";
            case 8:
                return "Toys";
            case 9:
                return "Medical Supplies";
            case 10:
                return "Others";
            case 0:
                return "Exit";
            default:
                ErrorMessage.errorInputMsg();
                return null;
        }
    }

    private String donationIDInput(String message) {
        boolean inputDonationIDAgain;
        String donationID = "";
        do {
            inputDonationIDAgain = true;
            donationID = InputUtility.promptStringInput(message);
            Donation donationSearch = search(donationID);
            if (donationSearch != null) {
                inputDonationIDAgain = false;
            } else {
                System.out.print(donationManagementUI.donationNotFoundMsg());
                boolean selectAgain;
                System.out.print(donationManagementUI.enterAgainOrExitMenu());
                do {
                    selectAgain = true;
                    int selection = InputUtility.promptIntInput("Selection  -->  ");

                    switch (selection) {
                        case 1:
                            selectAgain = false;
                            break;

                        case 0:
                            selectAgain = false;
                            inputDonationIDAgain = false;
                            break;

                        default:
                            ErrorMessage.errorInputMsg();
                    }
                } while (selectAgain);
            }
        } while (inputDonationIDAgain);
        return donationID;
    }

    private void enqueueAll(QueueInterface<Donation> targetQueue, QueueInterface<Donation> sourceQueue) {
        Iterator<Donation> it = sourceQueue.getIterator();
        while (it.hasNext()) {
            targetQueue.enqueue(it.next());
        }
    }

    private String sortingActionSelection() {

        while (true) {
            System.out.print(donationManagementUI.sortingActionMenu());
            int actionSelection = InputUtility.promptIntInput("Selection  -->  ");

            switch (actionSelection) {
                case 1:
                    return "Ascent";

                case 2:
                    return "Descent";

                case 0:
                    return "Exit";

                default:
                    ErrorMessage.errorInputMsg();
            }
        }
    }

    private void printDonationData(Iterator<Donation> it, QueueInterface<Donation> donationData) {
        int no = 1;
        while (it.hasNext()) {
            Donation donation = it.next();
            System.out.printf("| %-4d %-115s\n", no, donation.toString());
            no++;
        }
        System.out.print(donationManagementUI.totalDonationValueLong(calTotalValue(donationData)));
    }
}
