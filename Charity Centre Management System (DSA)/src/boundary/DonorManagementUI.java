/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.DonorManagementControl;
import entity.Donor;

/**
 *
 * @author Lim Chiew Ting
 */
public class DonorManagementUI {

    public String donorManagementMenu() {
        return "\n+================================================+\n"
                + "|                Donor Management                |\n"
                + "+================================================+\n"
                + "|        1. Add Donor                            |\n"
                + "|        2. Remove Donor                         |\n"
                + "|        3. Update Donor Details                 |\n"
                + "|        4. Search Donor Details                 |\n"
                + "|        5. List donors with all donations made  |\n"
                + "|        6. Filter donor                         |\n"
                + "|        7. Generate summary reports             |\n"
                + "|        8. Display Donor List                   |\n"
                + "|        0. Exit                                 |\n"
                + "+================================================+\n";
    }

    public String addDonorMenu() {
        return "+================================================+\n"
                + "|                  Add Donor                     |\n"
                + "+================================================+\n"
                + "|   Please select the number of representatives  |\n"
                + "|        1. One Person                           |\n"
                + "|        2. More than one person                 |\n"
                + "+================================================+\n";

    }

    public String addDonorSuccessfulMsg() {
        return "+================================================+\n"
                + "|          Donor was added successfully          |\n"
                + "+================================================+\n" + "\n";

    }

    public String addDonorFailedMsg() {
        return "+================================================+\n"
                + "|             Donor was failed to add            |\n"
                + "|         Please check the input values.         |\n"
                + "+================================================+\n"
                + "\n";

    }

    public String addDonorCancelMsg() {
        return "+================================================+\n"
                + "|          Donor addding process canceled!       |\n"
                + "+================================================+\n";
    }

    public String removeDonorMenu() {
        return "+================================================+\n"
                + "|                  Remove Donor                  |\n"
                + "+================================================+\n"
                + "|            1. Remove specific donor            |\n"
                + "|            2. Remove all donors                |\n"
                + "|            0. Exit                             |\n"
                + "+================================================+\n";
    }

    public String removeDonorSuccessfulMsg() {
        return "+================================================+\n"
                + "|         Donor was removed successfully         |\n"
                + "+================================================+\n";
    }

    public String removeDonorFailedMsg() {
        return "+------------------------------------------------+\n"
                + "|                 Donor not found                |\n"
                + "+------------------------------------------------+\n";
    }

    public String removeAllDonorSuccessfulMsg() {
        return "+================================================+\n"
                + "|            Donor list was cleared              |\n"
                + "+================================================+\n";
    }

    public String removeAllDonorFailedMsg() {
        return "+================================================+\n"
                + "|        Error! Unable to Clear donor list       |\n"
                + "+================================================+\n";
    }

    public String updateDonorSuccessfulMsg() {
        return "\n+================================================+\n"
                + "|                 Donor was updated              |\n"
                + "+================================================+\n";
    }

    public String updateDonorFailedMsg() {
        return  "+------------------------------------------------+\n"
              + "|              Donor was failed to update        |\n"
              + "|             Please check the input values.     |\n"
              + "+------------------------------------------------+\n"
                + "\n";

    }

    public String searchDonorHeader() {
        return   "\n+================================================+\n"
                 + "|              Search For Specific Donor         |"
               + "\n+================================================+\n";
    }

    public String searchSuccess() {
        return   "\n+================================================+\n"
                +  "|              Here the Donor's Details          |"
               + "\n+================================================+\n";
    }

    public String donorNotFoundMsg() {
        return    "+------------------------------------------------+\n"
                + "|                  Donor not found               |\n"
                + "+------------------------------------------------+\n";
    }

    public String listDonorDonationsMenu() {
        return "\n+========================================================================================+\n"
                + "|                                       Donation Listing                                 |\n"
                + "+========================================================================================+\n";

    }

    public String listDonorSuccessMsg() {
        return    "+------------------------------------------------+\n"
                + "|                  Record found                  |\n"
                + "+------------------------------------------------+\n";
    }

    public String listDonorFailedMsg() {
        return    "+------------------------------------------------+\n"
                + "|                 Record not found               |\n"
                + "+------------------------------------------------+\n";

    }

    public String matchFilterMsg() {
        return  "\n+================================================+\n"
                + "|                Filtered Donors                 |\n"
                + "+================================================+\n";
    }

    public String notMatchFilterMsg() {
        return    "+------------------------------------------------+\n"
                + "|        No donors found matching the criteria   |\n"
                + "+------------------------------------------------+\n";
    }

    public String reportHeader() {
        return  "\n+================================================+\n"
                + "|                Donor Summary Report            |\n"
                + "+================================================+\n"
                + "|  Type/Category    |  Count   | Percentage (%)  |\n"
                + "+================================================+\n";
    }

    public String summaryReportDetails(int governmentCount, int privateCount, int publicCount, int individualCount, int organizationCount, double governmentPercentage, double privatePercentage, double publicPercentage,
            double individualPercentage, double organizationPercentage) {
        return String.format("| %-17s | %-8d | %-14.2f  |\n", "Government", governmentCount, governmentPercentage)
                + String.format("| %-17s | %-8d | %-14.2f  |\n", "Private", privateCount, privatePercentage)
                + String.format("| %-17s | %-8d | %-14.2f  |\n", "Public", publicCount, publicPercentage)
                + "+================================================+\n"
                + String.format("| %-17s | %-8d | %-14.2f  |\n", "Individual", individualCount, individualPercentage)
                + String.format("| %-17s | %-8d | %-14.2f  |\n", "Organisation", organizationCount, organizationPercentage)
                + "+================================================+\n";

    }

    public String listDonorMenu() {
        return "\n+================================================+\n"
                + "|              List Donor with Donations         |\n"
                + "+================================================+\n"
                + "|            1. List specific donor              |\n"
                + "|            2. List all donors                  |\n"
                + "|            0. Exit                             |\n"
                + "+================================================+\n";
    }

    public String enterAgainOrExitMenu() {
        return "\n+=======================================+\n"
                + "|             1. Enter again            |\n"
                + "|             0. Exit                   |\n"
                + "+=======================================+\n";
    }
    

}
