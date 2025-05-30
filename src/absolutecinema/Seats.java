/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package absolutecinema;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author carlo
 */

public class Seats extends javax.swing.JFrame {
    //Map<JButton, Boolean> occupiedMap = new HashMap<>();
    Map<Integer, Map<String, Map<JButton, Boolean>>> occupiedSeatsByMovieAndTime = new HashMap<>();
    // Store locked seats as seat names or indexes, not JButton references
    Map<Integer, Map<String, Set<String>>> lockedSeatNamesByMovieAndTime = new HashMap<>();

    
    private void goToPaymentScreen() {
    List<String> selectedSeatNames = new ArrayList<>();
    for (Map.Entry<JButton, Boolean> entry : getCurrentSeatMap().entrySet()) {
        if (entry.getValue()) {
            selectedSeatNames.add(entry.getKey().getText()); // assuming buttons have seat names
        }
    }
    int total = selectedSeatNames.size() * 200;

    Payment paymentScreen = new Payment(selectedSeatNames, total);
    paymentScreen.setVisible(true);
    this.dispose(); // close current screen if desired
}
        
    public static int movie;
// Repeat for all buttons...

    /**
     * Creates new form Seats
     */
    int currentMovieIndex = movie;  // for example, movie 0 initially
    String currentTime = "11AM";
    private Map<JButton, Boolean> getCurrentSeatMap() {
    return occupiedSeatsByMovieAndTime.get(currentMovieIndex).get(currentTime);
}



    int count =0;
    private void updatePriceLabel(int count) {
    int newcount = count;
    for (Boolean value : getCurrentSeatMap().values()) {
        if (value) newcount++;
    }
    jLabel4.setText(newcount + "x - ₱" + String.format("%,d", newcount * 200));
}
    
 private void refreshUISeats() {
    Map<JButton, Boolean> seatMap = getCurrentSeatMap();
    for (Map.Entry<JButton, Boolean> entry : seatMap.entrySet()) {
        JButton button = entry.getKey();
        Boolean occupied = entry.getValue();
        if (LockedSeats.isSeatLocked(currentMovieIndex, currentTime, button.getText())) {
            lockSeat(button); // mark as red, unclickable
        } else if (occupied) {
            button.setBackground(new java.awt.Color(255, 255, 153)); // selected = yellow
            button.setEnabled(true);
        } else {
            button.setBackground(new java.awt.Color(255, 255, 254)); // default white
            button.setEnabled(true);
        }
    }

    updatePriceLabel(0); // Recalculate price display
}
   
    public Seats() {
    initComponents();
    
    String[] times = { "11AM", "2PM", "5PM" };
int totalMovies = 8;

for (int movieIndex = 0; movieIndex < totalMovies; movieIndex++) {
    Map<String, Map<JButton, Boolean>> timeMap = new HashMap<>();
    for (String time : times) {
        timeMap.put(time, createSeatMap());
    }
    occupiedSeatsByMovieAndTime.put(movieIndex, timeMap);
}

    switch(movie){
        case 0:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/gradosmall.jpg")));
            break;
        case 1:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/tinginsmall.jpg")));
            break;
        case 2:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/loveexesmall.jpg")));
            break;
        case 3:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/aninconvinientlovesmall.jpg")));
            break;
        case 4:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/compilingsmall.jpg")));
            break;
        case 5:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/linyasmall.jpg")));
            break;
        case 6:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/incentivessmall.jpg")));
            break;
        case 7:
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/icon/komsaysmall.jpg")));
            break;
            
    }
     
    jComboBox1.setSelectedItem("11AM"); //default
    
    jComboBox1.addActionListener(e -> {
        currentTime = jComboBox1.getSelectedItem().toString();
        // Refresh the map and UI to reflect correct time and locks
        Map<String, Map<JButton, Boolean>> timeMap = occupiedSeatsByMovieAndTime.get(currentMovieIndex);
        if (timeMap != null) {
            timeMap.put(currentTime, createSeatMap());
        }
        refreshUISeats(); // You need to implement this method (see below)
    });
    
    updatePriceLabel(0);
    
    List<String> selectedSeatNames = new ArrayList<>();
for (Map.Entry<JButton, Boolean> entry : getCurrentSeatMap().entrySet()) {
    if (entry.getValue()) {
        selectedSeatNames.add(entry.getKey().getText()); // assumes seat label is set
    }
}
int total = selectedSeatNames.size() * 200;




}
    
private String getImagePathForMovie(int num) {
         switch(num) {
        case 0:
            return "/icon/grado.jpg";       
        case 1:
            return "/icon/tingin.jpg";     
        case 2:
            return "/icon/loveexe.jpg";
        case 3:
            return "/icon/aninconvinientlove.jpg";  
        case 4:
            return"/icon/compiling.jpg";
        case 5:
            return "/icon/linya.jpg";
        case 6:
            return "/icon/incentives.jpg";
        case 7:
            return "/icon/komsay.jpg";
      } 
    return null;   
}  
private String getTitlePathForMovie(int title) {
         switch(title) {
        case 0:
            return "Grado";       
        case 1:
            return "Tingin";     
        case 2:
            return "Love.exe";
        case 3:
            return "An Inconvinient Love";  
        case 4:
            return"Compiling A CS Students Life";
        case 5:
            return "Linya-Linya.jpg";
        case 6:
            return "Incentives";
        case 7:
            return "Komsay";
      } 
    return null;   
}  
    private Map<JButton, Boolean> createSeatMap() {
    Map<JButton, Boolean> map = new HashMap<>();

    List<JButton> buttons = Arrays.asList(
        jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButton10,
        jButton12, jButton13, jButton14, jButton15, jButton16, jButton17, jButton18, jButton19, jButton20,
        jButton21, jButton22, jButton23, jButton24, jButton25, jButton26, jButton27, jButton28, jButton29,
        jButton30, jButton31, jButton32, jButton33, jButton34, jButton35, jButton36, jButton37, jButton38,
        jButton39, jButton40, jButton41, jButton42, jButton43, jButton44, jButton45, jButton46, jButton47,
        jButton48, jButton49, jButton50, jButton51, jButton52, jButton53, jButton54, jButton55, jButton56,
        jButton57, jButton58, jButton59, jButton60, jButton61, jButton62, jButton63, jButton64, jButton65,
        jButton66, jButton67, jButton68, jButton69, jButton70, jButton71
    );

    for (JButton button : buttons) {
        map.put(button, false);  // default: available
    }
   /*
    for (JButton locked : LockedSeats.getButtons("11AM")) {
        if (buttons.contains(locked)) {
            lockSeat(locked);
            map.put(locked, true);  // optionally mark as locked
        }
    }*/
    for (JButton button : buttons) {
         boolean locked = LockedSeats.isSeatLocked(currentMovieIndex, currentTime, button.getText());

        if (locked) {
            lockSeat(button);  // disables button and colors it red
              // seat is locked/occupied
        } else {
            button.setEnabled(true);
            button.setBackground(new java.awt.Color(255, 255, 254)); // reset color for available
            // seat is available
        }
    }

    return map;
}


private void lockSeat(JButton button) {
    button.setEnabled(false); // makes button unclickable
    button.setBackground(Color.RED); // optional: show it's unavailable
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton72 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlo\\OneDrive\\Documents\\NetBeansProjects\\AbsoluteCinema\\src\\icon\\temp.png")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11AM", "2PM", "5PM" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Not Available");

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Available");

        jLabel3.setText("SCREEN");
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 254));
        jButton1.setText("A2");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 254));
        jButton2.setText("A1");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 254));
        jButton3.setText("A3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 254));
        jButton4.setText("A4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 254));
        jButton5.setText("A5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 254));
        jButton6.setText("A6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 254));
        jButton7.setText("A7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 254));
        jButton8.setText("A8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 254));
        jButton9.setText("A9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 254));
        jButton10.setText("A10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 255, 254));
        jButton12.setText("B1");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 254));
        jButton13.setText("B2");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 255, 254));
        jButton14.setText("B3");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 254));
        jButton15.setText("B4");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 255, 254));
        jButton16.setText("B5");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 255, 254));
        jButton17.setText("B6");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 254));
        jButton18.setText("B7");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(255, 255, 254));
        jButton19.setText("B8");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(255, 255, 254));
        jButton20.setText("B9");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(255, 255, 254));
        jButton21.setText("B10");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(255, 255, 254));
        jButton22.setText("C1");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 254));
        jButton23.setText("C9");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(255, 255, 254));
        jButton24.setText("C10");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(255, 255, 254));
        jButton25.setText("C5");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(255, 255, 254));
        jButton26.setText("C6");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(255, 255, 254));
        jButton27.setText("C2");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(255, 255, 254));
        jButton28.setText("C8");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(255, 255, 254));
        jButton29.setText("C4");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(255, 255, 254));
        jButton30.setText("C7");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setBackground(new java.awt.Color(255, 255, 254));
        jButton31.setText("C3");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(255, 255, 254));
        jButton32.setText("D9");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 255, 254));
        jButton33.setText("D10");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(255, 255, 254));
        jButton34.setText("D5");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(255, 255, 254));
        jButton35.setText("D1");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(255, 255, 254));
        jButton36.setText("D2");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(255, 255, 254));
        jButton37.setText("D7");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setBackground(new java.awt.Color(255, 255, 254));
        jButton38.setText("D4");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(255, 255, 254));
        jButton39.setText("D8");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(255, 255, 254));
        jButton40.setText("D6");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(255, 255, 254));
        jButton41.setText("D3");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setBackground(new java.awt.Color(255, 255, 254));
        jButton42.setText("E2");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setBackground(new java.awt.Color(255, 255, 254));
        jButton43.setText("E4");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setBackground(new java.awt.Color(255, 255, 254));
        jButton44.setText("E3");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setBackground(new java.awt.Color(255, 255, 254));
        jButton45.setText("E6");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setBackground(new java.awt.Color(255, 255, 254));
        jButton46.setText("E1");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.setBackground(new java.awt.Color(255, 255, 254));
        jButton47.setText("E7");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setBackground(new java.awt.Color(255, 255, 254));
        jButton48.setText("E5");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setBackground(new java.awt.Color(255, 255, 254));
        jButton49.setText("E9");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setBackground(new java.awt.Color(255, 255, 254));
        jButton50.setText("E10");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setBackground(new java.awt.Color(255, 255, 254));
        jButton51.setText("E8");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setBackground(new java.awt.Color(255, 255, 254));
        jButton52.setText("F1");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setBackground(new java.awt.Color(255, 255, 254));
        jButton53.setText("F10");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        jButton54.setBackground(new java.awt.Color(255, 255, 254));
        jButton54.setText("F4");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jButton55.setBackground(new java.awt.Color(255, 255, 254));
        jButton55.setText("F5");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jButton56.setBackground(new java.awt.Color(255, 255, 254));
        jButton56.setText("F7");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton57.setBackground(new java.awt.Color(255, 255, 254));
        jButton57.setText("F3");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        jButton58.setBackground(new java.awt.Color(255, 255, 254));
        jButton58.setText("F2");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setBackground(new java.awt.Color(255, 255, 254));
        jButton59.setText("F6");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        jButton60.setBackground(new java.awt.Color(255, 255, 254));
        jButton60.setText("F9");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jButton61.setBackground(new java.awt.Color(255, 255, 254));
        jButton61.setText("F8");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton62.setBackground(new java.awt.Color(255, 255, 254));
        jButton62.setText("G6");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        jButton63.setBackground(new java.awt.Color(255, 255, 254));
        jButton63.setText("G3");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });

        jButton64.setBackground(new java.awt.Color(255, 255, 254));
        jButton64.setText("G2");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });

        jButton65.setBackground(new java.awt.Color(255, 255, 254));
        jButton65.setText("G9");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });

        jButton66.setBackground(new java.awt.Color(255, 255, 254));
        jButton66.setText("G8");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });

        jButton67.setBackground(new java.awt.Color(255, 255, 254));
        jButton67.setText("G5");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });

        jButton68.setBackground(new java.awt.Color(255, 255, 254));
        jButton68.setText("G10");
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });

        jButton69.setBackground(new java.awt.Color(255, 255, 254));
        jButton69.setText("G7");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jButton70.setBackground(new java.awt.Color(255, 255, 254));
        jButton70.setText("G1");
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });

        jButton71.setBackground(new java.awt.Color(255, 255, 254));
        jButton71.setText("G4");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(count + "x -  ₱" + String.format("%,d", count * 200));

        jButton11.setText("Continue");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selection");

        jButton72.setText("Back");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlo\\OneDrive\\Documents\\NetBeansProjects\\AbsoluteCinema\\src\\icon\\tempsmall.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(48, 48, 48)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton6)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton12)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jButton17)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton22)
                    .addComponent(jButton31)
                    .addComponent(jButton29)
                    .addComponent(jButton25)
                    .addComponent(jButton30)
                    .addComponent(jButton28)
                    .addComponent(jButton26)
                    .addComponent(jButton23)
                    .addComponent(jButton24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton36)
                    .addComponent(jButton35)
                    .addComponent(jButton41)
                    .addComponent(jButton38)
                    .addComponent(jButton34)
                    .addComponent(jButton37)
                    .addComponent(jButton39)
                    .addComponent(jButton40)
                    .addComponent(jButton32)
                    .addComponent(jButton33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton42)
                    .addComponent(jButton46)
                    .addComponent(jButton44)
                    .addComponent(jButton43)
                    .addComponent(jButton48)
                    .addComponent(jButton47)
                    .addComponent(jButton51)
                    .addComponent(jButton45)
                    .addComponent(jButton49)
                    .addComponent(jButton50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton58)
                    .addComponent(jButton52)
                    .addComponent(jButton57)
                    .addComponent(jButton54)
                    .addComponent(jButton55)
                    .addComponent(jButton56)
                    .addComponent(jButton61)
                    .addComponent(jButton59)
                    .addComponent(jButton60)
                    .addComponent(jButton53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton64)
                    .addComponent(jButton70)
                    .addComponent(jButton63)
                    .addComponent(jButton71)
                    .addComponent(jButton67)
                    .addComponent(jButton69)
                    .addComponent(jButton66)
                    .addComponent(jButton62)
                    .addComponent(jButton65)
                    .addComponent(jButton68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton11))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
        // TODO add your handling code here:
     Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton70);
    boolean updated = !current;
    currentMap.put(jButton70, updated);

    if (updated) {
        jButton70.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton70.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
        // TODO add your handling code here:
     Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton68);
    boolean updated = !current;
    currentMap.put(jButton68, updated);

    if (updated) {
        jButton68.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton68.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton68ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // TODO add your handling code here:
      Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton65);
    boolean updated = !current;
    currentMap.put(jButton65, updated);

    if (updated) {
        jButton65.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton65.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:
       Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton60);
    boolean updated = !current;
    currentMap.put(jButton60, updated);

    if (updated) {
        jButton60.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton60.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton53);
    boolean updated = !current;
    currentMap.put(jButton53, updated);

    if (updated) {
        jButton53.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton53.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton52);
    boolean updated = !current;
    currentMap.put(jButton52, updated);

    if (updated) {
        jButton52.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton52.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton50);
    boolean updated = !current;
    currentMap.put(jButton50, updated);

    if (updated) {
        jButton50.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton50.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton49);
    boolean updated = !current;
    currentMap.put(jButton49, updated);

    if (updated) {
        jButton49.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton49.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton46);
    boolean updated = !current;
    currentMap.put(jButton46, updated);

    if (updated) {
        jButton46.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton46.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton35);
    boolean updated = !current;
    currentMap.put(jButton35, updated);

    if (updated) {
        jButton35.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton35.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton33);
    boolean updated = !current;
    currentMap.put(jButton33, updated);

    if (updated) {
        jButton33.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton33.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton32);
    boolean updated = !current;
    currentMap.put(jButton32, updated);

    if (updated) {
        jButton32.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton32.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton24);
    boolean updated = !current;
    currentMap.put(jButton24, updated);

    if (updated) {
        jButton24.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton24.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton23);
    boolean updated = !current;
    currentMap.put(jButton23, updated);

    if (updated) {
        jButton23.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton23.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton22);
    boolean updated = !current;
    currentMap.put(jButton22, updated);

    if (updated) {
        jButton22.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton22.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton21);
    boolean updated = !current;
    currentMap.put(jButton21, updated);

    if (updated) {
        jButton21.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton21.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton20);
    boolean updated = !current;
    currentMap.put(jButton20, updated);

    if (updated) {
        jButton20.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton20.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton12);
    boolean updated = !current;
    currentMap.put(jButton12, updated);

    if (updated) {
        jButton12.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton12.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton10);
    boolean updated = !current;
    currentMap.put(jButton10, updated);

    if (updated) {
        jButton10.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton10.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton10ActionPerformed
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton9);
    boolean updated = !current;
    currentMap.put(jButton9, updated);

    if (updated) {
        jButton9.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton9.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton2);
    boolean updated = !current;
    currentMap.put(jButton2, updated);

    if (updated) {
        jButton2.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton2.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
            // TODO add your handling code here:  
        
        List<String> selectedSeatNames = new ArrayList<>();
    for (Map.Entry<JButton, Boolean> entry : getCurrentSeatMap().entrySet()) {
        if (entry.getValue()) {
            selectedSeatNames.add(entry.getKey().getText());
            
            
            
        }
    }
    int whatnum = Home.whatnum;
    User user = Identification.getUsers().get(whatnum);
List<String> seats = new ArrayList<>(selectedSeatNames);
String movieTitle = getTitlePathForMovie(currentMovieIndex); // get from JComboBox
String showtime = currentTime;         // get from JComboBox
String imagePath = getImagePathForMovie(currentMovieIndex); // see helper method below
int total = selectedSeatNames.size() * 200;
user.addPurchase(new Purchase(movieTitle, showtime, seats, "src"+imagePath,total));

    
for (Map.Entry<JButton, Boolean> entry : getCurrentSeatMap().entrySet()) {
    if (entry.getValue()) {
        String seatName = entry.getKey().getText();
        selectedSeatNames.add(seatName);

        // Lock the seat persistently
        LockedSeats.lockSeat(currentMovieIndex, currentTime, seatName);
    }
}

    /*
    for (Map.Entry<JButton, Boolean> entry : getCurrentSeatMap().entrySet()) {
        if(entry.getValue()){
            LockedSeats.addButton(entry.getKey(),"11AM");
    System.out.println(entry.getKey());
            }
        }*/
    
    
    // Compute total
    

    // Create and show the Payment screen
    Payment paymentFrame = new Payment(selectedSeatNames, total);
    paymentFrame.setVisible(true);
    paymentFrame.pack();
    paymentFrame.setLocationRelativeTo(null); 
    this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton1);
    boolean updated = !current;
    currentMap.put(jButton1, updated);

    if (updated) {
        jButton1.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton1.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton3);
    boolean updated = !current;
    currentMap.put(jButton3, updated);

    if (updated) {
        jButton3.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton3.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton4);
    boolean updated = !current;
    currentMap.put(jButton4, updated);

    if (updated) {
        jButton4.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton4.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
            Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton5);
    boolean updated = !current;
    currentMap.put(jButton5, updated);

    if (updated) {
        jButton5.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton5.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton13);
    boolean updated = !current;
    currentMap.put(jButton13, updated);

    if (updated) {
        jButton13.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton13.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton14);
    boolean updated = !current;
    currentMap.put(jButton14, updated);

    if (updated) {
        jButton14.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton14.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton15);
    boolean updated = !current;
    currentMap.put(jButton15, updated);

    if (updated) {
        jButton15.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton15.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton16);
    boolean updated = !current;
    currentMap.put(jButton16, updated);

    if (updated) {
        jButton16.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton16.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
            Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton27);
    boolean updated = !current;
    currentMap.put(jButton27, updated);

    if (updated) {
        jButton27.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton27.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton36);
    boolean updated = !current;
    currentMap.put(jButton36, updated);

    if (updated) {
        jButton36.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton36.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton42);
    boolean updated = !current;
    currentMap.put(jButton42, updated);

    if (updated) {
        jButton42.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton42.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton58);
    boolean updated = !current;
    currentMap.put(jButton58, updated);

    if (updated) {
        jButton58.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton58.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton64);
    boolean updated = !current;
    currentMap.put(jButton64, updated);

    if (updated) {
        jButton64.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton64.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton63);
    boolean updated = !current;
    currentMap.put(jButton63, updated);

    if (updated) {
        jButton63.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton63.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton57);
    boolean updated = !current;
    currentMap.put(jButton57, updated);

    if (updated) {
        jButton57.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton57.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton44);
    boolean updated = !current;
    currentMap.put(jButton44, updated);

    if (updated) {
        jButton44.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton44.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton41);
    boolean updated = !current;
    currentMap.put(jButton41, updated);

    if (updated) {
        jButton41.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton41.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton31);
    boolean updated = !current;
    currentMap.put(jButton31, updated);

    if (updated) {
        jButton31.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton31.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        // TODO add your handling code here:
       Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton71);
    boolean updated = !current;
    currentMap.put(jButton71, updated);

    if (updated) {
        jButton71.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton71.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton54);
    boolean updated = !current;
    currentMap.put(jButton54, updated);

    if (updated) {
        jButton54.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton54.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton43);
    boolean updated = !current;
    currentMap.put(jButton43, updated);

    if (updated) {
        jButton43.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton43.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton38);
    boolean updated = !current;
    currentMap.put(jButton38, updated);

    if (updated) {
        jButton38.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton38.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton29);
    boolean updated = !current;
    currentMap.put(jButton29, updated);

    if (updated) {
        jButton29.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton29.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton6);
    boolean updated = !current;
    currentMap.put(jButton6, updated);

    if (updated) {
        jButton6.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton6.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton25);
    boolean updated = !current;
    currentMap.put(jButton25, updated);

    if (updated) {
        jButton25.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton25.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton34);
    boolean updated = !current;
    currentMap.put(jButton34, updated);

    if (updated) {
        jButton34.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton34.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton48);
    boolean updated = !current;
    currentMap.put(jButton48, updated);

    if (updated) {
        jButton48.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton48.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton55);
    boolean updated = !current;
    currentMap.put(jButton55, updated);

    if (updated) {
        jButton55.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton55.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton67);
    boolean updated = !current;
    currentMap.put(jButton67, updated);

    if (updated) {
        jButton67.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton67.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton8);
    boolean updated = !current;
    currentMap.put(jButton8, updated);

    if (updated) {
        jButton8.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton8.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton19);
    boolean updated = !current;
    currentMap.put(jButton19, updated);

    if (updated) {
        jButton19.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton19.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
            Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton28);
    boolean updated = !current;
    currentMap.put(jButton28, updated);

    if (updated) {
        jButton28.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton28.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton39);
    boolean updated = !current;
    currentMap.put(jButton39, updated);

    if (updated) {
        jButton39.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton39.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton51);
    boolean updated = !current;
    currentMap.put(jButton51, updated);

    if (updated) {
        jButton51.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton51.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
            Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton61);
    boolean updated = !current;
    currentMap.put(jButton61, updated);

    if (updated) {
        jButton61.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton61.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton66);
    boolean updated = !current;
    currentMap.put(jButton66, updated);

    if (updated) {
        jButton66.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton66.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton69);
    boolean updated = !current;
    currentMap.put(jButton69, updated);

    if (updated) {
        jButton69.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton69.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton56);
    boolean updated = !current;
    currentMap.put(jButton56, updated);

    if (updated) {
        jButton56.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton56.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
           Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton47);
    boolean updated = !current;
    currentMap.put(jButton47, updated);

    if (updated) {
        jButton47.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton47.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton37);
    boolean updated = !current;
    currentMap.put(jButton37, updated);

    if (updated) {
        jButton37.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton37.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton30);
    boolean updated = !current;
    currentMap.put(jButton30, updated);

    if (updated) {
        jButton30.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton30.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton18);
    boolean updated = !current;
    currentMap.put(jButton18, updated);

    if (updated) {
        jButton18.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton18.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton7);
    boolean updated = !current;
    currentMap.put(jButton7, updated);

    if (updated) {
        jButton7.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton7.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton17);
    boolean updated = !current;
    currentMap.put(jButton17, updated);

    if (updated) {
        jButton17.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton17.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton26);
    boolean updated = !current;
    currentMap.put(jButton26, updated);

    if (updated) {
        jButton26.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton26.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
         Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton40);
    boolean updated = !current;
    currentMap.put(jButton40, updated);

    if (updated) {
        jButton40.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton40.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton45);
    boolean updated = !current;
    currentMap.put(jButton45, updated);

    if (updated) {
        jButton45.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton45.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here:
        Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton59);
    boolean updated = !current;
    currentMap.put(jButton59, updated);

    if (updated) {
        jButton59.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton59.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:
          Map<JButton, Boolean> currentMap = getCurrentSeatMap();

    boolean current = currentMap.get(jButton62);
    boolean updated = !current;
    currentMap.put(jButton62, updated);

    if (updated) {
        jButton62.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
    } else {
        jButton62.setBackground(new java.awt.Color(255, 255, 254)); // Off-white
    }

    updatePriceLabel(count);
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // TODO add your handling code here:
        Home HomeFrame = new Home();
        HomeFrame.setVisible(true);
        HomeFrame.pack();
        HomeFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_jButton72ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Seats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seats().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables

    Map<JButton, Boolean> currentSeatMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
