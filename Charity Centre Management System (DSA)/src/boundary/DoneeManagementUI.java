/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author Ng Shen Zhi
 */
public class DoneeManagementUI {

    public String doneeManagementMenu() {
        return "\n+================================================+\n"
                + "|              Donee Management                  |\n"
                + "+================================================+\n"
                + "|        1. Add Donee                            |\n"
                + "|        2. Remove Donee                         |\n"
                + "|        3. Search Donee                         |\n"
                + "|        4. Update Donee                         |\n"
                + "|        5. List Donations By Donee              |\n"
                + "|        6. Filter Donee                         |\n"
                + "|        7. Generate Donees Summary Report       |\n"
                + "|        0. Exit                                 |\n"
                + "+================================================+\n";
    }

    public String addDoneeMenu() {
        return "\n+=====================================+\n"
                + "|            Add Donee                |\n"
                + "+=====================================+\n";
    }
    
    public String addDoneeTypeMenu() {
        return "\n+=====================================+\n"
                + "|            Add Donee                |\n"
                + "+=====================================+\n"
                + doneeTypeMenu();
    }
    
    public String updateDoneeTypeMenu() {
        return "\n+=====================================+\n"
                + "|          Update Donee Type          |\n"
                + "+=====================================+\n"
                + doneeTypeMenu();
    }
    
    public String addDoneeSuccessMsg() {
        return "+=======================================+\n"
                + "|       Donee add successfully          |\n"
                + "+=======================================+\n";
    }

    public String addDoneeUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|      Donee add unsuccessfully         |\n"
                + "+---------------------------------------+\n";
    }

    public String removeDoneeMenu() {
        return "\n+=====================================+\n"
                + "|           Remove Donee              |\n"
                + "+=====================================+\n"
                + "|      1. Remove specific donee       |\n"
                + "|      2. Remove all donee            |\n"
                + "|      0. Exit                        |\n"
                + "+=====================================+\n";
    }

    public String removeDoneeSuccessMsg() {
        return "+=======================================+\n"
                + "|      Donee remove successfully        |\n"
                + "+=======================================+\n";
    }

    public String removeDoneeUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|      Donee add unsuccessfully         |\n"
                + "+---------------------------------------+\n";
    }

    public String searchDoneeHeader() {
        return "\n+=====================================+\n"
                + "|           Search Donee              |\n"
                + "+=====================================+\n";
    }

    public String doneeNotFoundMsg() {
        return "+---------------------------------------+\n"
                + "|           Donee not found             |\n"
                + "+---------------------------------------+\n";
    }
    
    public String updateDoneeMenu() {
        return "\n+=====================================+\n"
                + "|            Update Donee             |\n"
                + "+=====================================+\n";
    }

    public String updateDoneeSuccessMsg() {
        return "+=======================================+\n"
                + "|        Donee update successfully      |\n"
                + "+=======================================+\n";
    }

    public String updateDoneeUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|       Donee update unsuccessfully     |\n"
                + "+---------------------------------------+\n";
    }
    
    public String listDonationByDoneeHeader() {
        return "\n+=====================================+\n"
                + "|       List Donation By Donee        |\n"
                + "+=====================================+\n";
    }

    public String filterDoneeMenu() {
        return "\n+=====================================+\n"
                + "|           Filter Donee              |\n"
                + "+=====================================+\n"
                + "|        1. By Registration Date      |\n"
                + "|        0. Exit                      |\n"
                + "+=====================================+\n";
    }

    public String filterDoneeByDate(String dateRange) {
        return "\n+=======================================================================================================+\n"
                + "|                                                Donee                                                  |\n"
                + "+=======================================================================================================+\n"
                + String.format("| Date    : %-92s|\n", dateRange)
                + header();
    }

    public String sortingMenu() {
        return "\n+=====================================+\n"
                + "|             Sort Donation           |\n"
                + "+=====================================+\n"
                + "|         1. Sort by donee name       |\n"
                + "|         2. Sort by donee type       |\n"
                + "|         0. Exit                     |\n"
                + "+=====================================+\n";
    }
    
    public String sortingActionMenu() {
        return "\n+=========================+\n"
                + "|          Action         |\n"
                + "+=========================+\n"
                + "|        1. Ascent        |\n"
                + "|        2. Descent       |\n"
                + "|        0. Exit          |\n"
                + "+=========================+\n";
    }

    public String allDoneeHeader() {
        return "\n+=======================================================================================================+\n"
                + "|                                                All Donee                                              |\n"
                + header();
    }
    
    public String doneeHeader() {
        return "\n+=======================================================================================================+\n"
                + "|                                                Donee                                                  |\n"
                + header();
    }
    
    public String summaryReportHeader() {
        return "\n+=========================================================================+\n"
                + "|                             Summary Report                              |\n"
                + "+=========================================================================+\n"
                + "| Donee Type            | Total Number of Donee   | Donee Percentage (%)  |\n"
                + "+=========================================================================+\n";
    }
    
    public String summaryReportDetails(int individualCount, int familyCount, int organisationCount, double individualPercentage, double familyPercentage, double organisationPercentage) {
        return String.format("| %-21s | %-23d | %-21.2f |\n", "Individual", individualCount, individualPercentage) +
               String.format("| %-21s | %-23d | %-21.2f |\n", "Family", familyCount, familyPercentage) +
               String.format("| %-21s | %-23d | %-21.2f |\n", "Organisation", organisationCount, organisationPercentage) +
               "+=========================================================================+\n";
    }
    
    public String enterAgainOrExitMenu() {
        return "\n+=======================================+\n"
                + "|             1. Enter again            |\n"
                + "|             0. Exit                   |\n"
                + "+=======================================+\n";
    }
    
    public String selectedFutureDateMsg() {
        return "+--------------------------------------------------+\n"
                + "|       Selected date cannot be in the future      |\n"
                + "+--------------------------------------------------+\n";
    }

    public String sleectedBeforeFromDateMsg() {
        return "+--------------------------------------------------+\n"
                + "|  Selected date cannot be before the \"From Date\"  |\n"
                + "+--------------------------------------------------+\n";
    }

    public String noDoneeForSpecifiedDateMsg() {
        return "+----------------------------------------------------------+\n"
                + "|         No donee found for the specified date            |\n"
                + "+----------------------------------------------------------+\n";
    }
    
    public String withouDoneeMsg() {
        return "+----------------------------------+\n"
                + "|     There is no donee yet        |\n"
                + "+----------------------------------+\n";
    }
  
    private String doneeTypeMenu() {
        return "|          1. Individual              |\n"
                + "|          2. Family                  |\n"
                + "|          3. Organisation            |\n"
                + "|          0. Exit                    |\n"
                + "+=====================================+\n";
    }
    
    private String header() {
        return "+=======================================================================================================+\n"
                + "| NO   | Registration Date   | Donee                | Donee Type        | ID       | Phone Number       |\n"
                + "+=======================================================================================================+\n";
    }
    
    public String footer() {
        return "+=======================================================================================================+\n";
    }
}   
