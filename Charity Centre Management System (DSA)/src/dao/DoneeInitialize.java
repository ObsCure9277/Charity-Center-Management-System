package dao;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Donee;

/**
 *
 * @author Ng Shen Zhi
 */
public class DoneeInitialize {
    
    public static QueueInterface<Donee> doneeList = new LinkedQueue<>();
    
    public DoneeInitialize() {
        
        Donee donee1 = new Donee("Ng Shen Zhi", "Individual", "0189964840");
        Donee donee2 = new Donee("Kris Tan", "Individual", "0139932356");
        Donee donee3 = new Donee("Nick Lim", "Individual", "0176695949");
        Donee donee4 = new Donee("Lee Family", "Family", "0103345656");
        Donee donee5 = new Donee("ABC Charity", "Organisation", "0199939393");
        Donee donee6 = new Donee("Anya", "Individual", "0189932840");
        Donee donee7 = new Donee("May", "Individual", "0139932356");
        Donee donee8 = new Donee("Mei", "Individual", "0176123949");
        Donee donee9 = new Donee("Chua Family", "Family", "0103334656");
        Donee donee10 = new Donee("DNF Charity", "Organisation", "0122239393");
        Donee donee11 = new Donee("ZheQi", "Individual", "0122964840");
        Donee donee12 = new Donee("Lole", "Individual", "0139932346");
        Donee donee13 = new Donee("Zen", "Individual", "0176125949");
        Donee donee14 = new Donee("Ng Family", "Family", "0132345656");
        Donee donee15 = new Donee("Save Charity", "Organisation", "0199439393");
        Donee donee16 = new Donee("Eva", "Individual", "0189965430");
        Donee donee17 = new Donee("Khoo", "Individual", "013965456");
        Donee donee18 = new Donee("Ladey", "Individual", "0136695949");
        Donee donee19 = new Donee("Goh Family", "Family", "0167345656");
        Donee donee20 = new Donee("ZZZ Charity", "Organisation", "0109939393");
        
        doneeList.enqueue(donee1);
        doneeList.enqueue(donee2);
        doneeList.enqueue(donee3);
        doneeList.enqueue(donee4);
        doneeList.enqueue(donee5);
        doneeList.enqueue(donee6);
        doneeList.enqueue(donee7);
        doneeList.enqueue(donee8);
        doneeList.enqueue(donee9);
        doneeList.enqueue(donee10);
        doneeList.enqueue(donee11);
        doneeList.enqueue(donee12);
        doneeList.enqueue(donee13);
        doneeList.enqueue(donee14);
        doneeList.enqueue(donee15);
        doneeList.enqueue(donee16);
        doneeList.enqueue(donee17);
        doneeList.enqueue(donee18);
        doneeList.enqueue(donee19);
        doneeList.enqueue(donee20);
        
    }

    public static QueueInterface<Donee> getDoneeList() {
        return doneeList;
    }
    
    
}