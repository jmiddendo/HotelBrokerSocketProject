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

import constants.Constants;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * This class creates the client socket and connection with the Broker server.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 */
public class ClientConnection{

    public static final int CLIENTPORT = 8189;
    private String connectionAddress;
    private Socket clientSocket;
    private InetAddress addr;
    private BufferedReader reader;
    private PrintStream writer;
    
    /**
     * This is the default constructor for the ClientConnection class
     * 
     * @throws UnknownHostException Handles an unknown host.
     * @throws IOException Handles bad input or output.
     */
    public ClientConnection() throws UnknownHostException, IOException{
        try {
            connectionAddress = "127.0.0.1";
            addr = InetAddress.getByName(connectionAddress);
            clientSocket = new Socket(addr, CLIENTPORT);
            System.out.println("Client Running v4");
            reader = new BufferedReader(new InputStreamReader(
            clientSocket.getInputStream()));
            writer = new PrintStream(clientSocket.getOutputStream());
        } 
        catch (UnknownHostException e) {
            throw e;
        } 
        catch (IOException e) {
            throw e;
        }
    }
     /**
      * This is the constructor that creates a connection with and address other than the home.
      * 
      * @param newAddy The address to connect.
     * @throws UnknownHostException Handles an unknown host.
     * @throws IOException Handles bad input or output.
      */
    public ClientConnection(String newAddy) throws UnknownHostException, IOException{
        try {
            connectionAddress = newAddy;
            addr = InetAddress.getByName(newAddy);
            clientSocket = new Socket(addr, CLIENTPORT);
            clientSocket.setSoTimeout(250000);
            System.out.println("Client Running v4");
            reader = new BufferedReader(new InputStreamReader(
            clientSocket.getInputStream()));
            writer = new PrintStream(clientSocket.getOutputStream());
        } 
        catch (UnknownHostException e) {
            throw e;
        } 
        catch (IOException e) {
            throw e;
        }
    }
    
    /**
     * Closes both the read and write into the server connection.
     */
    public void closeConnection(){
        writer.print(Constants.CLOSE_CONN + Constants.CR_LF);
        try{
            reader.close();
            writer.close();
            clientSocket.close();
        }
        catch(Exception e){
            System.exit(1);
        }
    }
    
    /**
     * This method gets a list of available rooms based on hotel name, room number, beginning date, and 
     * the number of days being requested.
     * 
     * @param hotelName The name of the hotel.
     * @param roomNumber The room number
     * @param begDate The check-in date in yyyy-MM-dd format.
     * @param numDays The number of days being requested.
     * @return ArrayList The method returns list of room numbers that are available.
     */
    public ArrayList<String> getAvailableRooms(String hotelName, String roomNumber, String begDate, String numDays){
        
        ArrayList<String> returnAvailableRooms = new ArrayList<>();
        
        writer.print(Constants.GET_DATE_VALIDATION + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(hotelName + Constants.CR_LF);
        writer.print(roomNumber + Constants.CR_LF);
        writer.print(begDate + Constants.CR_LF);
        writer.print(numDays + Constants.CR_LF);
        
	String line = "";
        
        try{
            while(true){		
		line = reader.readLine();
		if(line.equals(Constants.END_STREAM)){
                    break;
                }

                returnAvailableRooms.add(line);
                
            }
            return returnAvailableRooms;
	}
        catch(IOException e){
            e.printStackTrace();
            return null;
	}
    }
    
    /**
     * This method fetches a list of cities that are serviced by the system.
     * 
     * @return ArrayList This method returns a list of city names.
     */
    public ArrayList<String> getCities(){
        
        ArrayList<String> returnCities = new ArrayList<>();
        
        writer.print(Constants.GET_CITIES + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
	
	String line = "";
	try{
            while(true){		
                line = reader.readLine();
                
                if(line.equals(Constants.END_STREAM)){
                    break;
                }
                returnCities.add(line);
            }
            return returnCities;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * This method fetches the connection string.
     * 
     * @return String This method returns the connection string.
     */
    public String getConnectionAddress(){
        return connectionAddress;
    }
    
    /**
     * This method fetches a list of hotels for a given city.
     * 
     * @param citySearch The city that is being searched.
     * @return ArrayList This method returns a list of hotel names.
     */
    public ArrayList<String> getHotels(String citySearch){
        
        ArrayList<String> returnHotels = new ArrayList<>();
        
        writer.print(Constants.GET_HOTELS + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(citySearch + Constants.CR_LF);
        
	String line = "";
        
	try{
            while(true){		
		line = reader.readLine();
		if(line.equals(Constants.END_STREAM)){
                    break;
		}
		returnHotels.add(line);
            }
            return returnHotels;
	}
        catch(IOException e){
            e.printStackTrace();
            return null;
	}
    }
    
    /**
     * This method fetches the list of rates for a particular hotel.
     * 
     * @param rateSearch The hotel that is being searched.
     * @return ArrayList This method returns a list of rates.
     */
    public ArrayList<String> getRates(String rateSearch){
        
        ArrayList<String> returnRates = new ArrayList<>();
        
        writer.print(Constants.GET_RATES + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(rateSearch + Constants.CR_LF);
        
	String line = "";
	
        try{
            
            while(true){		
		line = reader.readLine();
		if(line.equals(Constants.END_STREAM)){
                    break;
		}
                
                try{
                    Double.parseDouble(line);
                    returnRates.add(line);
                }
                catch(Exception e){
                    System.out.println(line);
                }
            }
            return returnRates;
	}
        catch(IOException e){
            e.printStackTrace();
            return null;
	}
    }
    
    /**
     * This method submits the booking information for posting and
     * receive a list of the new booking numbers.
     * 
     * @param message A # separated string that contains the booking details.
     * @return ArrayList This method returns a list of booking reference numbers.
     */
    public ArrayList<String> postBooking(String message){
        
        ArrayList<String> returnBookings = new ArrayList<>();
        
        writer.print(Constants.POST_BOOKING + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(message + Constants.CR_LF);
        
	String line = "";
	
        try{
            
            line = reader.readLine();
            if(line.equals(Constants.NO_RESULTS)){
                return returnBookings;
            }
            while(true){		
		line = reader.readLine();
		if(line.equals(Constants.END_STREAM)){
                    break;
		}
                
                try{
                    Integer.parseInt(line);
                    returnBookings.add(line);
                }
                catch(Exception e){
                    System.out.println(line);
                }
            }
            return returnBookings;
	}
        catch(IOException e){
            e.printStackTrace();
            return null;
	}
    }
}

