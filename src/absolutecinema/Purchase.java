/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package absolutecinema;

import java.util.List;

/**
 *
 * @author carlo
 */
public class Purchase {
    private String movieTitle;
    private String showtime;
    private List<String> seats;
    private String imagePath;
    private int totalPrice;

    public Purchase(String movieTitle, String showtime, List<String> seats, String imagePath,int total) {
        this.movieTitle = movieTitle;
        this.showtime = showtime;
        this.seats = seats;
        this.imagePath = imagePath;
        this.totalPrice = total;
    }
    public int getTotalPrice() { return totalPrice; }
    public String getMovieTitle() { return movieTitle; }
    public String getShowtime() { return showtime; }
    public List<String> getSeats() { return seats; }
    public String getImagePath() { return imagePath; }
}

