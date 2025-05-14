/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Loo Jie Qi
 */
public class InputUtility {
    
    private static Scanner input = new Scanner(System.in);
    private static ErrorMessage errorMessage = new ErrorMessage();
    
    public static String promptStringInput(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }
    
    public static int promptIntInput(String message) {
        int userInput = -1;
        boolean validInput = false;
        
        do {
            System.out.print(message);
            try {
                userInput = input.nextInt();
                input.nextLine();
                validInput = true;
            } catch (InputMismatchException ex) {
                input.nextLine();
                errorMessage.errorInputMsg();
            }
        } while (!validInput);
        
        return userInput;
    }
    
    public static double promptDoubleInput(String message) {
        double userInput = -1;
        boolean validInput = false;
        
        do {
            System.out.print(message);
            try {
                userInput = input.nextDouble();
                input.nextLine();
                validInput = true;
            } catch (InputMismatchException ex) {
                input.nextLine();
                errorMessage.errorInputMsg();
            }
        } while (!validInput);
        
        return userInput;
    }
}
