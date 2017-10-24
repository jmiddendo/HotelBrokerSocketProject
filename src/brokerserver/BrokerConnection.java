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
package brokerserver;

import constants.Constants;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * This class is the client socket connection back into the hotel servers.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Aor-2017
 */
public class BrokerConnection{

    
    
    private String connectionAddress;
    private Socket clientSocket;
    private InetAddress addr;
    private BufferedReader reader;
    private PrintStream writer;
    
    /**
     * This is a constructor method for the BrokerConnection class.
     * 
     * @param port This is the port number of the hotel server.
     * @throws UnknownHostException Handles an unknown hotel server host.
     * @throws IOException Handles an input or output error.
     */
    public BrokerConnection(int port) throws UnknownHostException, IOException{
        try {
            connectionAddress = "127.0.0.1";
            addr = InetAddress.getByName(connectionAddress);
            clientSocket = new Socket(addr, port);
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
     * This is a constructor method for the BrokerConnection class.
     * 
     * @param newAddy This is the address of the hotel server.
     * @param port This is the port of the hotel server
     * @throws UnknownHostException Handles an unknown hotel server host.
     * @throws IOException Handles an input or output error.
     */
    public BrokerConnection(String newAddy, int port) throws UnknownHostException, IOException{
        try {
            connectionAddress = newAddy;
            addr = InetAddress.getByName(newAddy);
            clientSocket = new Socket(addr, port);
            clientSocket.setSoTimeout(25000);
            System.out.println("Client Running v1");
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
     * This method closes all of the open connection associated with this implementation.
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
     * This method fetches a list of available rooms based on the hotel name, the room number,
     * the check-in date, and the number of days requested.
     * 
     * @param hotelName The hotel being searched.
     * @param roomNumber The room that is being requested.
     * @param begDate The date that is being requested.
     * @param numOfDays The number of day that is being requested.
     * @return ArrayList This method returns a list of room numbers that are available.
     */
    public ArrayList<String> getAvailableRooms(String hotelName, String roomNumber, String begDate, String numOfDays){
        
        ArrayList<String> returnAvailableRooms = new ArrayList<>();
        
        writer.print(Constants.GET_DATE_VALIDATION + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(hotelName + Constants.CR_LF);
        writer.print(roomNumber + Constants.CR_LF);
        writer.print(begDate + Constants.CR_LF);
        writer.print(numOfDays + Constants.CR_LF);
        
	String line = "";
        
        try{
            
            line = reader.readLine();
            if(line.equals(Constants.NO_RESULTS)){
                return returnAvailableRooms;
            }
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
     * This method fetches the connection string.
     * 
     * @return String This method returns the connection string. 
     */
    public String getConnectionAddress(){
        return connectionAddress;
    }
    
    /**
     * This method fetches a list of rates for a specified hotel name.
     * 
     * @param rateSearch This is the name of the hotel being searched.
     * @return ArrayList This method returns a list of rates for a given hotel name.
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
     * This method submits the booking information to the hotel server and retrieves
     * a list of reference numbers.
     * 
     * @param message A # delimited string containing booking information.
     * @return ArrayList This method returns a list of reference numbers.
     */
    public ArrayList<String> postBooking(String message){
        
        ArrayList<String> returnBookings = new ArrayList<>();
        
        writer.print(Constants.POST_BOOKING + Constants.CR_LF);
	writer.print(Constants.START_STREAM + Constants.CR_LF);
        writer.print(message + Constants.CR_LF);
        
	String line = "";
	
        try{
            
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

