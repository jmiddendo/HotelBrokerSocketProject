/*
 * Copyright (C) 2017 Jeffrey Middendorf
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package clientfrontend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * This class is the controller class for the socket to the Broker server.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 * 
 */
public class ClientController {
    
    ClientConnection sysModel;

    /**
     * Default Constructor for the ClientController class.
     * 
     * @throws UnknownHostException To handle a failed Connection to the server.
     * @throws IOException To handle a bad input or output.
     */
    ClientController() throws UnknownHostException, IOException{
        try{
            sysModel = new ClientConnection();
        }
        catch(UnknownHostException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }
    }
    
    /**
     * A constructor that sets a new model.
     * 
     * @param cliSock A new value for the client connection.
     */
    ClientController(ClientConnection cliSock){
        sysModel = cliSock;
    }
    
    /**
     * A method that fetches a list of room numbers that are available based on passed parameters.
     * 
     * @param hotelName The name of the hotel being searched.
     * @param rmAmt The amount being searched.
     * @param begDate The check-in date.
     * @param numDays The number of days.
     * @return ArrayList This method returns a list of room numbers that are available.
     */
    public ArrayList<String> getAvailableRooms(String hotelName, String rmAmt, String begDate, String numDays){
        return sysModel.getAvailableRooms(hotelName, rmAmt, begDate, numDays);
    }
    
    /**
     * A method that fetches a list of cities.
     * 
     * @return ArrayList This method returns a list of cities.
     */
    public ArrayList<String> getCities(){
        return sysModel.getCities();
    }
    
    /**
     * A method that fetches a the connection string.
     * 
     * @return String This method returns the connection string.
     */
    public String getConnectionDetails(){
        return sysModel.getConnectionAddress();
    }
    
    /**
     * A method that fetches a list of hotels based on city.
     * 
     * @param searchCity The city search criteria.
     * @return ArrayList This method returns a list of hotel names.
     */
    public ArrayList<String> getHotels(String searchCity){
        return sysModel.getHotels(searchCity);
    }
    
    /**
     * A method that fetches a list of rates for a particular hotel.
     * 
     * @param searchRates The hotel that is being search.
     * @return ArrayList This method returns a list of rates.
     */
    public ArrayList<String> getRates(String searchRates){
        return sysModel.getRates(searchRates);
    }
    
    /**
     * Creates a new BookingView Form, sets the controller for the form
     * and makes it visible.
     * 
     */
    public void initialize(){
        BookingView startUp = new BookingView();
        startUp.setController(this);
        startUp.setVisible(true);
    }
    
    /**
     * A method that fetches a list of reference numbers.
     * 
     * @param message A # delimited string that has the details for the new booking.
     * @return ArrayList This method returns a list of new reference numbers.
     */
    public ArrayList<String> postBooking(String message){
        return sysModel.postBooking(message);
    }
    
    /**
     * This method opens a new view and close the prior one to allow form navigation.
     * 
     * @param view The view that is being opened.
     * @param oldView The view that is being closed.
     */
    public void setView(View view, View oldView){
        view.setController(this);
        view.setVisible(true);
        oldView.setVisible(false);
    }
    
    /**
     * Displays a pop-up message to the user based on a passed string.
     * 
     * @param message The message that will be displayed.
     */
    public void showPopup(String message){
        JOptionPane.showMessageDialog(new JFrame(), message);
    }    
}
