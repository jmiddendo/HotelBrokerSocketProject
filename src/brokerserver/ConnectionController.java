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

import java.util.ArrayList;

/**
 * This class serves as the connection controller for the six connections into the hotel server.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 */
public class ConnectionController {
    
    public static final int ONE_PORT = 8700;
    public static final int TWO_PORT = 8701;
    public static final int THREE_PORT = 8702;
    public static final int FOUR_PORT = 8703;
    public static final int FIVE_PORT = 8704;
    public static final int SIX_PORT = 8705;
    
    private ArrayList<BrokerConnection> listOfServers;
    private ArrayList<String> listOfHotels;
    
    /**
     * The default constructor.
     * 
     * @throws Exception Handles any exceptions creating the hotel server connections.
     */
    public ConnectionController() throws Exception{
        listOfServers = new ArrayList<>();
        listOfServers.add(new BrokerConnection(ONE_PORT));
        listOfServers.add(new BrokerConnection(TWO_PORT));
        listOfServers.add(new BrokerConnection(THREE_PORT));
        listOfServers.add(new BrokerConnection(FOUR_PORT));
        listOfServers.add(new BrokerConnection(FIVE_PORT));
        listOfServers.add(new BrokerConnection(SIX_PORT));
        
        
        listOfHotels = new ArrayList<>();
        listOfHotels.add("HiltonS");
        listOfHotels.add("FourSeasons");
        listOfHotels.add("HiltonM");
        listOfHotels.add("DoubleTreeM");
        listOfHotels.add("HiltonP");
        listOfHotels.add("Crowne Plaza");
    }
    
    /**
     * A controller that establishes connection with hotel servers not on the home address.
     * 
     * @param Address The address of the hotel servers.
     * @throws Exception Handles any exception during the creation process.
     */
    public ConnectionController(String Address) throws Exception{
        listOfServers = new ArrayList<>();
        listOfServers.add(new BrokerConnection(Address,ONE_PORT));
        listOfServers.add(new BrokerConnection(Address,TWO_PORT));
        listOfServers.add(new BrokerConnection(Address,THREE_PORT));
        listOfServers.add(new BrokerConnection(Address,FOUR_PORT));
        listOfServers.add(new BrokerConnection(Address,FIVE_PORT));
        listOfServers.add(new BrokerConnection(Address,SIX_PORT));
        
        
        listOfHotels = new ArrayList<>();
        listOfHotels.add("HiltonS");
        listOfHotels.add("FourSeasons");
        listOfHotels.add("HiltonM");
        listOfHotels.add("DoubleTreeM");
        listOfHotels.add("HiltonP");
        listOfHotels.add("Crowne Plaza");
    }
    
    /**
     * This method returns the array index of a particular hotel given a hotel name.
     * 
     * @param hotelName The hotel name to be searched.
     * @return int This method returns the array index of a particular hotel.
     */
    public int getHotelIndex(String hotelName){
        
        int retValue = 0;
        for (int i = 0; i < listOfHotels.size(); i++){
            if(listOfHotels.get(i).equals(hotelName)){
                retValue = i;
            }
        }
        
        return retValue;
    }
    
    /**
     * This method is the default accessor method for the listOfHotels attribute.
     * 
     * @return ArrayList This method returns the listOfHotels attribute.
     */
    public ArrayList<String> getListOfHotels(){
        return listOfHotels;
    }
    
    /**
     * This method is the default accessor method for the listOfServers attribute.
     * 
     * @return ArrayList This method returns the listOfServers attribute.
     */
    public ArrayList<BrokerConnection> getListOfServers(){
        return listOfServers;
    }
    
    /**
     * This method fetches a single connection from the list of servers.
     * 
     * @param newName The hotel name of the connection that is being requested.
     * @return BrokerConnection This method returns a single connection from the list.
     */
    public BrokerConnection getSingleServer(String newName){
        return listOfServers.get(getHotelIndex(newName));
    }
    
    /**
     * This method is the default mutator method for the listOfHotels attribute.
     * 
     * @param newList The new value for the listOfHotels attribute.
     */
    public void setListOfHotels(ArrayList<String> newList){
        listOfHotels = new ArrayList<>(newList);
    }
    
    /**
     * This method is the default mutator method for the listOfServers attribute.
     * 
     * @param newList The new value for the listOfServers attribute.
     */
    public void setListOfServers(ArrayList<BrokerConnection> newList){
         listOfServers = new ArrayList<>(newList);
    }
}
