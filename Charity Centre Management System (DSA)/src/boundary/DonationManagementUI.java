/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Loo Jie Qi
 */
public class DonationManagementUI {

    public String donationManagementMenu() {
        return "\n+================================================+\n"
                + "|              Donation Management               |\n"
                + "+================================================+\n"
                + "|        1. Add Donation                         |\n"
                + "|        2. Remove Donation                      |\n"
                + "|        3. Search Donation                      |\n"
                + "|        4. Amend Donation                       |\n"
                + "|        5. Track Items By Category              |\n"
                + "|        6. List Donations By Donor              |\n"
                + "|        7. List All Donations                   |\n"
                + "|        8. Filter Donation                      |\n"
                + "|        9. Generate Donations Summary Report    |\n"
                + "|        0. Exit                                 |\n"
                + "+================================================+\n";
    }

    public String addDonationMenu() {
        return "\n+=====================================+\n"
                + "|            Add Donation             |\n"
                + "+=====================================+\n"
                + categoryMenu();
    }

    public String addDonationSuccessMsg() {
        return "+=======================================+\n"
                + "|       Donation add successfully       |\n"
                + "+=======================================+\n";
    }

    public String addDonationUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|      Donation add unsuccessfully      |\n"
                + "+---------------------------------------+\n";
    }

    public String removeDonationMenu() {
        return "\n+=====================================+\n"
                + "|           Remove Donation           |\n"
                + "+=====================================+\n"
                + "|      1. Remove specific donation    |\n"
                + "|      2. Remove all donations        |\n"
                + "|      0. Exit                        |\n"
                + "+=====================================+\n";
    }

    public String removeDonationSuccessMsg() {
        return "+=======================================+\n"
                + "|      Donation remove successfully     |\n"
                + "+=======================================+\n";
    }

    public String removeDonationUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|      Donation add unsuccessfully      |\n"
                + "+---------------------------------------+\n";
    }

    public String searchDonationHeader() {
        return "\n+=====================================+\n"
                + "|           Search Donation           |\n"
                + "+=====================================+\n";
    }

    public String donationNotFoundMsg() {
        return "+---------------------------------------+\n"
                + "|           Donation not found          |\n"
                + "+---------------------------------------+\n";
    }

    public String amendDonationMenu() {
        return "\n+===============================================+\n"
                + "|                 Amend Donation                |\n"
                + "+===============================================+\n"
                + "|      1. Amend Donor                           |\n"
                + "|      2. Amend Donation Category and Type      |\n"
                + "|      3. Amend Donation Qtuantity and Value    |\n"
                + "|      4. Amend Donation Informations           |\n"
                + "|      0. Exit                                  |\n"
                + "+===============================================+\n";
    }
    
    public String categoryList() {
        return "\n+=====================================+\n"
                + "|          Category Donation          |\n"
                + "+=====================================+\n"
                + categoryMenu();
    }

    public String amendDonationSuccessMsg() {
        return "+=======================================+\n"
                + "|      Donation amend successfully      |\n"
                + "+=======================================+\n";
    }

    public String amendDonationUnsuccessMsg() {
        return "+---------------------------------------+\n"
                + "|     Donation amend unsuccessfully     |\n"
                + "+---------------------------------------+\n";
    }

    public String trackDonationsByCategoryMenu() {
        return "\n+=====================================+\n"
                + "|     Track Donations By Category     |\n"
                + "+=====================================+\n"
                + categoryMenu();
    }

    public String listDonationByDonorHeader() {
        return "\n+=====================================+\n"
                + "|       List Donation By Donor        |\n"
                + "+=====================================+\n";
    }
    
    public String printDonorDonationsHeader(String donorID, String donorName) {
        return  "\n+====================================================================================================================================================================================================+\n"
                + "|                                                                                        Donation                                                                                                    |\n"
                + "+====================================================================================================================================================================================================+\n"
                + String.format("| Donor    : %-6s %-127s                                                  |\n", donorID, donorName)
                + header();
    }

    public String totalDonationValueShort(double ttlValue) {
               return "+=========================================================================+==================================================+\n"

                + String.format("| %-47s %-23.2f |\n", "Total Donation Value :", ttlValue)
                                       + "+=========================================================================+\n";

    }

    public String totalDonationValueLong(double ttlValue) {
        return "+====================================================================================================================================================================================================+\n"
                + String.format("| %-172s %-21.2f |\n", "Total Donation Value :", ttlValue)
                + "+====================================================================================================================================================================================================+\n";
    }

    public String filterDonationMenu() {
        return "\n+=====================================+\n"
                + "|           Filter Donation           |\n"
                + "+=====================================+\n"
                + "|        1. By Donation Date          |\n"
                + "|        2. By Donation Value         |\n"
                + "|        0. Exit                      |\n"
                + "+=====================================+\n";
    }

    public String filterDonationByDate(String dateRange) {
        return "\n+====================================================================================================================================================================================================+\n"
                + "|                                                                                            Donation                                                                                                |\n"
                + "+====================================================================================================================================================================================================+\n"
                + String.format("| Date    : %-135s                                                  |\n", dateRange)
                + header();
    }
    
    public String filterDonationByValue(String valueRange) {
        return "\n+====================================================================================================================================================================================================+\n"
                + "|                                                                                            Donation                                                                                                |\n"
                + "+====================================================================================================================================================================================================+\n"
                + String.format("| Value   : %-135s                                                  |\n", valueRange)
                + header();
    }

    public String sortingMenu() {
        return "\n+=====================================+\n"
                + "|             Sort Donation           |\n"
                + "+=====================================+\n"
                + "|         1. Sort by value            |\n"
                + "|         2. Sort by quantity         |\n"
                + "|         3. Sort by category         |\n"
                + "|         4. Sort by donor name       |\n"
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

    public String allDonationHeader() {
        return " \n+====================================================================================================================================================================================================+\n"
                + "|                                                                                      All Donation                                                                                                  |\n"
                + header();
    }
    
    public String donationHeader() {
        return "\n+====================================================================================================================================================================================================+\n"
                + "|                                                                                      Donation                                                                                                      |\n"
                + header();
    }

        public String summaryReportHeader() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "\n=============================================================================================================================================================================================\n"
                + "                                                                              Charity Centre Management System                                                                              \n"
                + "                                                                                    Donation Subsystem                                                                                      \n\n"
                + "                                                                                  Donation Summary Report                                                                                   \n" 
                + "                                                                              -------------------------------                                                                               \n\n"
                + String.format("%s %s\n", "Generated at :", LocalDateTime.now().format(formatter))
                + "********************************************************************************************************************************************************************************************\n\n\n"
                + "+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"
                + "| Category                   | Type                                              |  Total Quantity        | Total Value               | Quantity Percentage (%)  |  Value Percentage (%)   |\n";
    }
        public String summaryTotalInfo(double ttlValue, int ttlQty, String highestValueCategory, String highestQtyCategory) {
        return "+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"
                + String.format("| %-26s %-154.2f |\n", "Total Donation Value       : ", ttlValue)
                + String.format("| %-26s %-154d |\n", "Total Donation Quantity    : ", ttlQty)
                + String.format("| %-26s %-154s |\n", "Highest Value              : ", highestValueCategory)
                + String.format("| %-26s %-154s |\n", "Highest Quantity           : ", highestQtyCategory)
                + "+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n\n\n";
    }
    public String summaryReportEnd() {
        return "********************************************************************************************************************************************************************************************\n"
                + "                                                                                     END OF THE REPORT                                                                                      \n"
                + "============================================================================================================================================================================================\n";
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

    public String noDonationForSpecifiedDateMsg() {
        return "+----------------------------------------------------------+\n"
                + "|         No donations found for the specified date        |\n"
                + "+----------------------------------------------------------+\n";
    }

    public String noDonationForSpecifiedValueMsg() {
        return "+----------------------------------------------------------+\n"
                + "|       No donations found for the specified value         |\n"
                + "+----------------------------------------------------------+\n";
    }
    
    public String noDonationForSpecifiedCategoryMsg() {
        return "+----------------------------------------------------+\n"
                + "|     There is no donation for this category yet     |\n"
                + "+----------------------------------------------------+\n";
    }

    public String noDonationForSpecifiedDonorMsg() {
        return "+----------------------------------------------------------+\n"
                + "|         No donations found for the specified donor       |\n"
                + "+----------------------------------------------------------+\n";
    }
                
    public String maxValueLessThanMinValue() {
        return "+----------------------------------------------------+\n"
                + "|       Max value cannot be less than min value      |\n"
                + "+----------------------------------------------------+\n";
    }
    
    public String withouDonationMsg() {
        return "+----------------------------------+\n"
                + "|     There is no donation yet     |\n"
                + "+----------------------------------+\n";
    }
    
    private String categoryMenu() {
        return "|          1. Cash                    |\n"
                + "|          2. Clothing                |\n"
                + "|          3. Food                    |\n"
                + "|          4. Water                   |\n"
                + "|          5. Furniture               |\n"
                + "|          6. Books                   |\n"
                + "|          7. Electronics             |\n"
                + "|          8. Toys                    |\n"
                + "|          9. Medical Supplies        |\n"
                + "|         10. Others                  |\n"
                + "|          0. Exit                    |\n"
                + "+=====================================+\n";
    }
  
    private String header() {
        return "+====================================================================================================================================================================================================+\n"
                + "| NO   | Date          | Donor                                                            | ID       | Category             | Type                         | Quantity        | Value                 |\n"
                + "+====================================================================================================================================================================================================+\n";
    }
}
