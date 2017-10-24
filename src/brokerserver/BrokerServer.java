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
import databaseconnection.DatabaseConnection;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * This class creates a Server socket and waits to accept incoming connections.
 * 
 * @author Jeffrey Middenodrf
 * @version 1.0
 * @since 17-Apr-2017
 */
public class BrokerServer {
    
    public static int BROKERPORT = 8189;
    private static DatabaseConnection connector;
    private static ConnectionController serverControl;
    
    
    /**
     * This is the main method of the Broker server tier of the network.
     * 
     * @param argv Command line arguments.
     */
    public static void main(String argv[]) {
	ServerSocket brokerServer = null;
	try {
	    brokerServer = new ServerSocket(BROKERPORT);
            connector = new DatabaseConnection();
            serverControl = new ConnectionController();
	} catch(IOException e) {
	    System.out.println(e);
	    System.exit(1);
	} catch(Exception e){
            System.out.println(e);
	    System.exit(4);
        }
	while (true) {
	    Socket incoming = null;
	    try {
		incoming = brokerServer.accept();
	    } catch(IOException e) {
		e.printStackTrace();
		continue;
	    }

	    new SocketHandler(incoming, connector, serverControl).start();

	}  
    }
}

/**
 * This class is the runnable thread that is used to create multiple threads
 * to handle the different connections.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 */
class SocketHandler extends Thread {

    protected Socket incoming;
    protected BufferedReader cin;
    protected PrintStream cout;
    protected DatabaseConnection connector;
    protected ConnectionController serverControl;

    /**
     * This is the constructor that initializes the Socket member, the HbsDbc connection, and the 
     * server controller.
     * 
     * @param incoming This is the server socket that the thread is serving.
     * @param newConn This is the threads version of the HbsDbc.
     * @param newServe This is the threads version of the server controller.
     */
    public SocketHandler(Socket incoming, DatabaseConnection newConn, ConnectionController newServe) {
	this.incoming = incoming;
        connector = newConn;
        serverControl = newServe;
    }

    /**
     * Closes all of the open connections within the thread.
     */
    public void exit() {
	try {
	    cout.print(Constants.CLOSE_CONN);
	    cin.close();
	    cout.close();
	    incoming.close();
            connector.closeConnection();
	} 
        catch(Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * This method runs and initiates the new thread.
     */
    public void run() {
        
        System.out.println("Broker: Connection is now established!");
        String inputRequest = "";
        
        InputStreamReader iStream = null;
        OutputStream oStream = null;
        
	try {
            
            iStream = new InputStreamReader(incoming.getInputStream());
            oStream = incoming.getOutputStream();
                
            cin = new BufferedReader(iStream);
            cout = new PrintStream(oStream);
    
	} 
        catch(IOException e) {
	    e.printStackTrace();
	}
        
        while(true){
            try{
                inputRequest = cin.readLine();
                System.out.println(inputRequest);
                if (inputRequest.equals(Constants.CLOSE_CONN)){
                    System.out.println("Broker: Client has terminated the connection!");
                    exit();
                    break;
                }              
            }
            catch(Exception e){
                e.printStackTrace();
                return;
            }
            try{
                if(inputRequest.equals(Constants.GET_CITIES)){
                    System.out.println("Broker: City listing requested!");
                    if(cin.readLine().equals(Constants.START_STREAM)){
                        ArrayList<String> cities = new ArrayList<String>(connector.getCities());
                        if (!cities.isEmpty()){
                            System.out.println("Broker: Transmitting city information");
                            for (String city : cities){
                                System.out.println("Broker: Sending " + city);
                                cout.print(city + Constants.CR_LF);
                            }
                        }
                        cout.print(Constants.END_STREAM + Constants.CR_LF);
                    }
                }
                else if(inputRequest.equals(Constants.GET_HOTELS)){
                    System.out.println("Broker: Hotel list requested!");
                    if(cin.readLine().equals(Constants.START_STREAM)){
                        System.out.println("Stream Start");
                        String cityName = cin.readLine();
                        ArrayList<String> hotels = new ArrayList<String>(connector.getHotels(cityName));
                        if(!hotels.isEmpty()){
                            System.out.println("Broker: Transmitting hotel information!");
                            for(String hotel : hotels){
                                System.out.println("Broker: Sending " + hotel);
                                cout.print(hotel + Constants.CR_LF);
                            }
                        }
                        cout.println(Constants.END_STREAM + Constants.CR_LF);
                    }
                }
                else if(inputRequest.equals(Constants.GET_RATES)){
                    System.out.println("Broker: Rate list requested!");
                    if(cin.readLine().equals(Constants.START_STREAM)){
                        System.out.println("Stream Start");
                        String hotelName = cin.readLine();
                        ArrayList<String> rates = new ArrayList<String>(serverControl.getSingleServer(hotelName).getRates(hotelName));
                        if (!rates.isEmpty()){
                            System.out.println("Broker: Transmitting rate information!");
                            for(String rate : rates){
                                System.out.println("Broker: Sending " + rate);
                                cout.print(rate + Constants.CR_LF);
                            }
                        }
                        cout.println(Constants.END_STREAM + Constants.CR_LF);
                    }
                }
                else if(inputRequest.equals(Constants.GET_DATE_VALIDATION)){
                    System.out.println("Broker: Room list requested!");
                    if(cin.readLine().equals(Constants.START_STREAM)){
                        System.out.println("Stream Start");
                        String hotelName = cin.readLine();
                        String roomPrice = cin.readLine();
                        String begDate = cin.readLine();
                        String numDays = cin.readLine();
                        ArrayList<String> availRooms = new ArrayList<String>(serverControl.getSingleServer(hotelName).getAvailableRooms(hotelName, roomPrice, begDate, numDays));
                        System.out.println(availRooms.size());
                        if (!availRooms.isEmpty()){
                            System.out.println("Broker: Transmitting hotel information!");
                            for(String room : availRooms){
                                System.out.println("Broker: Sending " + room);
                                cout.print(room + Constants.CR_LF);
                            }
                        }
                        cout.println(Constants.END_STREAM + Constants.CR_LF);
                    }
                }
                else if(inputRequest.equals(Constants.POST_BOOKING)){
                    System.out.println("Broker: Booking submission received!");
                    if(cin.readLine().equals(Constants.START_STREAM)){
                        System.out.println("Stream Start");
                        ArrayList<String> bookingsCreated = new ArrayList<>();
                        String bookingData = cin.readLine();
                        String[] hotelName = bookingData.split("#");
                        bookingsCreated.addAll(serverControl.getSingleServer(hotelName[1].trim()).postBooking(bookingData));
                        if (!bookingData.isEmpty()){
                            System.out.println("Broker: Transmitting Booking reference!");
                            for(String booking : bookingsCreated){
                                System.out.println("Broker: Sending " + booking);
                                cout.print(booking + Constants.CR_LF);
                            }
                        }
                        cout.println(Constants.END_STREAM + Constants.CR_LF);
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
                cout.println(Constants.END_STREAM + Constants.CR_LF);
            }
        }
    }
}
