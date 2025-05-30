/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package absolutecinema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class User {
    private String username;
    private String password;
    private String fullName;
    private List<Purchase> purchases = new ArrayList<>();
    
public List<Purchase> getPurchases() {
    return purchases;
}

public void addPurchase(Purchase purchase) {
    purchases.add(purchase);
}

    public void setFullName(String fullName) {
    this.fullName = fullName;
}
    public void setPassword(String password) {
    this.password = password;
}

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
}


