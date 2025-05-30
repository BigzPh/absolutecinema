/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package absolutecinema;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;

/**
 *
 * @author carlo
 */
public class LockedSeats {
    
   // movieIndex -> time -> set of locked seat names
    private static Map<Integer, Map<String, Set<String>>> lockedSeatsMap = new HashMap<>();

    // Lock a seat (by seat name) for a specific movie and time
    public static void lockSeat(int movieIndex, String time, String seatName) {
        lockedSeatsMap
            .computeIfAbsent(movieIndex, k -> new HashMap<>())
            .computeIfAbsent(time, k -> new HashSet<>())
            .add(seatName);
    }

    // Check if a seat is locked for a movie and time
    public static boolean isSeatLocked(int movieIndex, String time, String seatName) {
        return lockedSeatsMap
            .getOrDefault(movieIndex, Collections.emptyMap())
            .getOrDefault(time, Collections.emptySet())
            .contains(seatName);
    }
/*
    // movieIndex -> time -> set of locked seat names (button.getText())
    private static Map<Integer, Map<String, Set<String>>> lockedSeatsMap = new HashMap<>();

    public static void lockSeat(int movieIndex, String time, String seatName) {
        lockedSeatsMap
            .computeIfAbsent(movieIndex, k -> new HashMap<>())
            .computeIfAbsent(time, k -> new HashSet<>())
            .add(seatName);
    }

    public static boolean isSeatLocked(int movieIndex, String time, String seatName) {
        return lockedSeatsMap
            .getOrDefault(movieIndex, Collections.emptyMap())
            .getOrDefault(time, Collections.emptySet())
            .contains(seatName);
    }
}*/

    /*
    private static ArrayList<JButton> button1_11AM = new ArrayList<>();
    private static ArrayList<JButton> button1_2PM = new ArrayList<>();
    private static ArrayList<JButton> button1_5PM = new ArrayList<>();
    

    public static ArrayList<JButton> getButtons(String time) {
    switch (time) {
        case "11AM":
            return button1_11AM;
        case "2PM":
            return button1_2PM;
        case "5PM":
            return button1_5PM;
        
    }
}

    
    public static void addButton(JButton button, String time) {
    switch(time) {
        case "11AM":
            button1_11AM.add(button);
            break;
        case "2PM":
            button1_2PM.add(button);
            break;
        case "5PM":
            button1_5PM.add(button);
            break;
    }*/  
}