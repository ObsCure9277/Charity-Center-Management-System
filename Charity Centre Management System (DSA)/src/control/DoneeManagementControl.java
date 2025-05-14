/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import entity.*;
import utility.*;
import boundary.*;
import dao.DoneeInitialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 *
 * @author Ng Shen Zhi
 */
public class DoneeManagementControl {
    
    QueueInterface<Donee> doneeQueue = new LinkedQueue<>();

    private DoneeManagementUI doneeManagementUI = new DoneeManagementUI();

    public DoneeManagementControl(QueueInterface<Donee> doneeQueue) {
        this.doneeQueue = doneeQueue;
    }

    public void doneeManagement() {
        boolean selectDoneeManagementAgain = true;

        do {
            System.out.print(doneeManagementUI.doneeManagementMenu());
            int doneeManagementSelection = InputUtility.promptIntInput("Selection  -->  ");

            switch (doneeManagementSelection) {
                case 1: // Add Donee
                    addDonee();
                    break;

                case 2: // Remove Donee
                    removeDonee();
                    break;

                case 3: // Search Donee
                    searchDonee();
                    break;

                case 4: // Update Donee
                    updateDonee();
                    break;

                case 5: // List Donations by Donee
                    listAllDonees();
                    break;

                case 6: // Filter Donee
                    filterDonees();
                    break;

                case 7: // Generate Donee Summary Report
                    generateSummaryReport();
                    break;

                case 0: // Exit
                    selectDoneeManagementAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }

        } while (selectDoneeManagementAgain);
    }

    public void addDonee() {
        boolean selectAgain = true;
        String doneeType;
        do {
            System.out.print(doneeManagementUI.addDoneeTypeMenu());
            int doneeTypeSelection = InputUtility.promptIntInput("Donee Type Selection  -->  ");
            doneeType = doneeTypeSelection(doneeTypeSelection);
            if (!doneeType.equals("Exit")) {
                String doneeName = InputUtility.promptStringInput("Donee Name     : ");
                String doneePhoneNum = InputUtility.promptStringInput("Phone Number     : ");
                if (add(doneeName, doneeType, doneePhoneNum)) { //check Donee
                    System.out.print(doneeManagementUI.addDoneeSuccessMsg());
                } else {
                    System.out.print(doneeManagementUI.addDoneeUnsuccessMsg());
                }
            } else {
                selectAgain = false;
            }
        } while (selectAgain);
    }

    public void removeDonee() {
        boolean removeDoneeAgain = true;
        do {
            System.out.print(doneeManagementUI.removeDoneeMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: //Remove specific donee
                    String doneeID = doneeIDInput("Donee ID   : ");

                    if (remove(doneeID)) {
                        System.out.print(doneeManagementUI.removeDoneeSuccessMsg());
                    } else {
                        System.out.print(doneeManagementUI.removeDoneeUnsuccessMsg());
                    }
                    break;

                case 2: //Remove all
                    if (clear()) {
                        System.out.print(doneeManagementUI.removeDoneeSuccessMsg());
                    } else {
                        System.out.print(doneeManagementUI.removeDoneeUnsuccessMsg());
                    }
                    break;

                case 0: //Exit
                    removeDoneeAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (removeDoneeAgain);
    }

    public void updateDonee() {
        boolean updateDoneeAgain = true;
        do {
            System.out.print(doneeManagementUI.updateDoneeMenu());
            String doneeID = doneeIDInput("Donee ID   : ");
            String newDoneeName = InputUtility.promptStringInput("New Donee Name   : ");
            String newDoneePhoneNum = InputUtility.promptStringInput("New Phone Number     : ");
            System.out.print(doneeManagementUI.updateDoneeTypeMenu());
            int doneeTypeSelection = InputUtility.promptIntInput("Donee Type Selection  -->  ");
            String newDoneeType = doneeTypeSelection(doneeTypeSelection);
            if (newDoneeType != null) {
                if (newDoneeType.equals("Exit")) {
                    updateDoneeAgain = false;
                    continue;
                }
                if (update(newDoneeName, newDoneeType, newDoneePhoneNum, doneeID)) {
                    System.out.print(doneeManagementUI.updateDoneeSuccessMsg());
                    int exitSelection = InputUtility.promptIntInput("Enter 0 to exit or any other number to update another donee: ");
                    if (exitSelection == 0) {
                        updateDoneeAgain = false;
                    }
                } else {
                    System.out.print(doneeManagementUI.updateDoneeUnsuccessMsg());
                }
            }
        } while (updateDoneeAgain);
    }

    public void searchDonee() {
        System.out.print(doneeManagementUI.searchDoneeHeader());
        String doneeID = doneeIDInput("Donee ID   : ");
        Donee searchedDonee = search(doneeID);
        if (searchedDonee != null) {
            System.out.print(doneeManagementUI.doneeHeader());
            System.out.printf("| %-4d %-115s\n", 1, searchedDonee.toString());
            System.out.println("+=======================================================================================================+\n");
        } else {
            System.out.print(doneeManagementUI.doneeNotFoundMsg());
        }
    }

    public void filterDonees() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean filterDoneeAgain = true;
        do {
            int year, month, day;
            String strDate;
            System.out.print(doneeManagementUI.filterDoneeMenu());
            int selection = InputUtility.promptIntInput("Selection  -->  ");

            switch (selection) {
                case 1: // By Registration Date
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
                                System.out.print(doneeManagementUI.selectedFutureDateMsg());
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
                                    System.out.print(doneeManagementUI.sleectedBeforeFromDateMsg());
                                } else {
                                    inputDateAgain = false;
                                }
                            } else {
                                System.out.print(doneeManagementUI.selectedFutureDateMsg());
                            }
                        } else {
                            ErrorMessage.invalidDateMsg();
                        }
                    } while (inputDateAgain);

                    String dateRange = fromDate + "  ---  " + toDate;
                    QueueInterface<Donee> doneeFilterByDate = filter(fromDate, toDate);

                    if (!doneeFilterByDate.isEmpty()) {
                        Iterator<Donee> iteratorFilter = doneeFilterByDate.getIterator();
                        System.out.print(doneeManagementUI.filterDoneeByDate(dateRange));
                        printDoneeData(iteratorFilter, doneeFilterByDate);

                        QueueInterface<Donee> sortedQueue = new LinkedQueue<>();

                        while (sortedQueue != null) {
                            sortedQueue = sorting(doneeFilterByDate);
                            if (sortedQueue != null) {
                                Iterator<Donee> itSortedQueue = sortedQueue.getIterator();
                                System.out.print(doneeManagementUI.filterDoneeByDate(dateRange));
                                printDoneeData(itSortedQueue, sortedQueue);
                            }
                        }
                    } else {
                        System.out.print(doneeManagementUI.noDoneeForSpecifiedDateMsg());
                    }

                    break;

                case 0: //Exit
                    filterDoneeAgain = false;
                    break;

                default:
                    ErrorMessage.errorInputMsg();
            }
        } while (filterDoneeAgain);
    }

    private QueueInterface<Donee> filter(LocalDate startDate, LocalDate endDate) {
        QueueInterface<Donee> filterDonees = new LinkedQueue<>();
        Iterator<Donee> it = doneeQueue.getIterator();

        while (it.hasNext()) {
            Donee donee = it.next();
            if ((donee.getRegistrationDate().isAfter(startDate) || donee.getRegistrationDate().equals(startDate))
                    && (donee.getRegistrationDate().isBefore(endDate) || donee.getRegistrationDate().equals(endDate))) {
                filterDonees.enqueue(donee);
            }
        }

        return filterDonees;
    }

    public void listAllDonees() {
        printAllDonees();
    }

    private void printAllDonees() {
        Iterator<Donee> it = doneeQueue.getIterator();
        if (it.hasNext()) {
            System.out.print(doneeManagementUI.allDoneeHeader());
            printDoneeData(it, doneeQueue);

            QueueInterface<Donee> sortedQueue = new LinkedQueue<>();

            while (sortedQueue != null) {
                sortedQueue = sorting(doneeQueue);
                if (sortedQueue != null) {
                    Iterator<Donee> itSortedQueue = sortedQueue.getIterator();
                    System.out.print(doneeManagementUI.allDoneeHeader());
                    printDoneeData(itSortedQueue, sortedQueue);
                }
            }
        } else {
            System.out.println(doneeManagementUI.withouDoneeMsg());
        }
    }

    private QueueInterface<Donee> sorting(QueueInterface<Donee> sortQueue) {
        boolean sort = true;

        while (true) {
            String sortInput = InputUtility.promptStringInput("\nDo you want to sort the list? (y/n) : ").toLowerCase();

            if (sortInput.equals("y")) {
                System.out.print(doneeManagementUI.sortingMenu());
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
                int selection = InputUtility.promptIntInput("Selection  -->  ");
                String action;

                switch (selection) {

                    case 1: //Sort by donee name
                        System.out.print(doneeManagementUI.sortingActionMenu());
                        action = sortingActionSelection();

                        if (!action.equals("Exit")) {
                            return sortDoneeName(action, sortQueue);
                        }
                        break;

                    case 2: //Sort by donee type
                        System.out.print(doneeManagementUI.sortingActionMenu());
                        action = sortingActionSelection();

                        if (!action.equals("Exit")) {
                            return sortDoneeType(action, sortQueue);
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

    private String sortingActionSelection() {

        while (true) {
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

    public void generateSummaryReport() {
        printSummaryReport();
    }

    private void printSummaryReport() {
        int totalDonees = doneeQueue.size();
        if (totalDonees == 0) {
            System.out.println("No donees available to generate the summary report.");
            return;
        }

        int individualCount = 0;
        int familyCount = 0;
        int organisationCount = 0;

        Iterator<Donee> it = doneeQueue.getIterator();
        while (it.hasNext()) {
            Donee donee = it.next();
            switch (donee.getDoneeType()) {
                case "Individual":
                    individualCount++;
                    break;
                case "Family":
                    familyCount++;
                    break;
                case "Organisation":
                    organisationCount++;
                    break;
            }
        }

        double individualPercentage = (individualCount / (double) totalDonees) * 100;
        double familyPercentage = (familyCount / (double) totalDonees) * 100;
        double organisationPercentage = (organisationCount / (double) totalDonees) * 100;

        System.out.print(doneeManagementUI.summaryReportHeader());
        System.out.print(doneeManagementUI.summaryReportDetails(individualCount, familyCount, organisationCount, individualPercentage, familyPercentage, organisationPercentage));
    }

    private boolean add(String doneeName, String doneeType, String doneePhoneNum) {
        Donee newDonee = new Donee(doneeName, doneeType, doneePhoneNum);
        doneeQueue.enqueue(newDonee);
        return true;
    }

    private boolean remove(String doneeID) {
        boolean isRemoved = false;
        QueueInterface<Donee> tempQueue = new LinkedQueue<>();

        while (!doneeQueue.isEmpty()) {
            Donee donee = doneeQueue.dequeue();
            if (donee.getDoneeID().equals(doneeID)) {
                isRemoved = true;
            } else {
                tempQueue.enqueue(donee);
            }
        }

        while (!tempQueue.isEmpty()) {
            doneeQueue.enqueue(tempQueue.dequeue());
        }

        return isRemoved;
    }

    private boolean clear() {
        if (!doneeQueue.isEmpty()) {
            doneeQueue.clear();
            return true;
        } else {
            return false;
        }
    }

    private Donee search(String doneeID) {
        Iterator<Donee> it = doneeQueue.getIterator();
        while (it.hasNext()) {
            Donee donee = it.next();
            if (donee.getDoneeID().equals(doneeID)) {
                return donee;
            }
        }
        return null;
    }

    private boolean update(String newDoneeName, String newDoneeType, String newDoneePhoneNum, String doneeID) {
        Donee donee = search(doneeID);
        if (donee != null) {
            donee.setDoneeName(newDoneeName);
            donee.setDoneePhoneNum(newDoneePhoneNum);
            donee.setDoneeType(newDoneeType);
            return true;
        }
        return false;
    }

    private String doneeIDInput(String message) {
        boolean inputDoneeIDAgain;
        String doneeID = "";
        do {
            inputDoneeIDAgain = true;
            doneeID = InputUtility.promptStringInput(message);
            Donee doneeSearch = search(doneeID);
            if (doneeSearch != null) {
                inputDoneeIDAgain = false;
            } else {
                System.out.print(doneeManagementUI.doneeNotFoundMsg());
                boolean selectAgain;
                System.out.print(doneeManagementUI.enterAgainOrExitMenu());
                do {
                    selectAgain = true;
                    int selection = InputUtility.promptIntInput("Selection  -->  ");

                    switch (selection) {
                        case 1:
                            selectAgain = false;
                            break;

                        case 0:
                            selectAgain = false;
                            inputDoneeIDAgain = false;
                            break;

                        default:
                            ErrorMessage.errorInputMsg();
                    }
                } while (selectAgain);
            }
        } while (inputDoneeIDAgain);
        return doneeID;
    }

    private String doneeTypeSelection(int doneeTypeSelection) {
        String doneeType = "";
        switch (doneeTypeSelection) {
            case 1:
                return "Individual";
            case 2:
                return "Family";
            case 3:
                return "Organisation";
            case 0:
                return "Exit";
            default:
                ErrorMessage.errorInputMsg();
                return null;
        }
    }

    private void enqueueAll(QueueInterface<Donee> targetQueue, QueueInterface<Donee> sourceQueue) {
        Iterator<Donee> it = sourceQueue.getIterator();
        while (it.hasNext()) {
            targetQueue.enqueue(it.next());
        }
    }

    private QueueInterface<Donee> sortDoneeName(String ascentOrDescent, QueueInterface<Donee> sortQueue) {
        QueueInterface<Donee> sortedQueue = new LinkedQueue<>();
        Donee[] doneeArray = new Donee[sortQueue.size()];
        Iterator<Donee> it = sortQueue.getIterator();

        int index = 0;
        while (it.hasNext()) {
            doneeArray[index++] = it.next();
        }

        for (int i = 0; i < doneeArray.length - 1; i++) {
            for (int j = 0; j < doneeArray.length - i - 1; j++) {
                if ((ascentOrDescent.equals("Ascent")
                        && doneeArray[j].getDoneeName().compareToIgnoreCase(doneeArray[j + 1].getDoneeName()) > 0)
                        || (ascentOrDescent.equals("Descent")
                        && doneeArray[j].getDoneeName().compareToIgnoreCase(doneeArray[j + 1].getDoneeName()) < 0)) {

                    Donee temp = doneeArray[j];
                    doneeArray[j] = doneeArray[j + 1];
                    doneeArray[j + 1] = temp;
                }
            }
        }

        for (Donee donee : doneeArray) {
            sortedQueue.enqueue(donee);
        }

        return sortedQueue;
    }

    private QueueInterface<Donee> sortDoneeType(String ascentOrDescent, QueueInterface<Donee> sortQueue) {
        // Convert QueueInterface to array
        Donee[] doneeArray = new Donee[sortQueue.size()];
        int index = 0;
        while (!sortQueue.isEmpty()) {
            doneeArray[index++] = sortQueue.dequeue();
        }

        // Sort the array based on doneeType
        for (int i = 0; i < doneeArray.length - 1; ++i) {
            for (int j = 0; j < doneeArray.length - i - 1; ++j) {
                boolean condition = ascentOrDescent.equals("Ascent")
                        ? doneeArray[j].getDoneeType().compareToIgnoreCase(doneeArray[j + 1].getDoneeType()) > 0
                        : doneeArray[j].getDoneeType().compareToIgnoreCase(doneeArray[j + 1].getDoneeType()) < 0;

                if (condition) {
                    Donee temp = doneeArray[j];
                    doneeArray[j] = doneeArray[j + 1];
                    doneeArray[j + 1] = temp;
                }
            }
        }

        // Enqueue the sorted array back into a QueueInterface
        QueueInterface<Donee> sortedQueue = new LinkedQueue<>() {
        };
        for (Donee donee : doneeArray) {
            sortedQueue.enqueue(donee);
        }

        return sortedQueue;
    }

    private void printDoneeData(Iterator<Donee> it, QueueInterface<Donee> doneeData) {
        int noID = 1;
        while (it.hasNext()) {
            Donee donee = it.next();
            System.out.printf("| %-4d %-115s\n", noID, donee.toString());
            noID++;
        }
        System.out.print(doneeManagementUI.footer());
    }
}
