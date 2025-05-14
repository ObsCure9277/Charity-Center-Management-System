/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Loo Jie Qi
 */
public class DateValidation {
    public static boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    public static boolean isValidDate(String strDate) {
        try {
            LocalDate date = LocalDate.parse(strDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
