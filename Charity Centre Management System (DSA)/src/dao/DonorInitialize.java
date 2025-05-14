/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Donor;
import adt.*;

/**
 *
 * @author USER
 */
public class DonorInitialize {
    
    public static QueueInterface<Donor> donorList = new LinkedQueue<>();
    
    public DonorInitialize() {
        donorList.enqueue(new Donor("Kyle", "Private", "Individual", "012-2251478"));
        donorList.enqueue(new Donor("Lee Chong Wei", "Public", "Individual", "017-3451234"));
        donorList.enqueue(new Donor("Ng Healthy World Lifestyle Sdn. Bhd.", "Private", "Organisation", "016-3881951"));
        donorList.enqueue(new Donor("Raden", "Private", "Individual", "013-6621778"));
        donorList.enqueue(new Donor("Ministry of Transport of Malaysia(MOT)", "Government", "Organisation", "012-3451234"));
        donorList.enqueue(new Donor("MERCY Humanitarian Fund", "Public", "Organisation", "016-7174228"));
        donorList.enqueue(new Donor("Yuna Ting", "Private", "Individual", "016-2396647"));
        donorList.enqueue(new Donor("Sultan Ibrahim Ismail of Johor", "Public", "Individual", "013-3361289"));
        donorList.enqueue(new Donor("Malaysia Red Cresent Society", "Public", "Organisation", "016-7174228"));
        donorList.enqueue(new Donor("Sime Darby Foundation", "Private", "Organisation", "014-3425599"));
        donorList.enqueue(new Donor("Ministry of Health Malaysia(MOH)", "Government", "Organisation", "012-3347895"));
        donorList.enqueue(new Donor("Tengku Zafrul Aziz", "Public", "Individual", "012-7304228"));
        donorList.enqueue(new Donor("Maybank Investment Bank Bhd", "Government", "Organisation", "011-36991154"));
        donorList.enqueue(new Donor("The Pure Life Society", "Public", "Organisation", "016-2237166"));
        donorList.enqueue(new Donor("Petronas", "Private", "Organisation", "016-1197553"));

    }

    public static QueueInterface<Donor> getDonorList() {
        return donorList;
    }

}
