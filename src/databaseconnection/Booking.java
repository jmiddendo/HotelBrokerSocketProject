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
package databaseconnection;

import java.util.Date;
import java.util.ArrayList;


/**
 * This class represents a booking of a room in the hotel system.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 */
public class Booking {
    
    private Date beginDate;
    private String creditCard;
    private Date endDate;
    private String customerName;
    private static int referenceNumber = 0;
    private int requestedRoomNumber;
    private int thisRef;
    private double totalCost;
    
    /**
     * This is the default controller for the Booking class.
     */
    public Booking(){
        beginDate = null;
        creditCard = "XXXX";
        endDate = null;
        customerName = "John Doe";
        thisRef = ++referenceNumber;
        requestedRoomNumber = -1;
        totalCost = 0.0;
    }
    /**
     * This is the a controller to assign values to all of the members of the class.
     * 
     * @param newName This is the new name for the customer.
     * @param newBeg This is the beginning date for the booking.
     * @param newEnd This is the ending date for the booking.
     * @param newRoom This is the room number of the requested room.
     * @param newCost This is the cost of the booking.
     * @param newCard This is the number of the credit card number.
     */
    public Booking(String newName, Date newBeg, Date newEnd, int newRoom, double newCost, String newCard){
        customerName = newName;
        beginDate = newBeg;
        endDate = newEnd;
        requestedRoomNumber = newRoom;
        totalCost = newCost;
        creditCard = newCard;
        thisRef = ++referenceNumber;
    }
    
    /**
     * This returns an ArrayList containing first date and end date of the booking.
     * 
     * @return ArrayList This method returns an ArrayList containing the first date and the end date of the booking. 
     */
    public ArrayList<Date> cancelBooking(){
        
        ArrayList<Date> retValue = new ArrayList<>();
        retValue.add(beginDate);
        retValue.add(endDate);
        
        return retValue;
    }
    
    /**
     * This is the default accessor of the beginDate member.
     * 
     * @return Date This method returns the beginDate attribute.
     */
    public Date getBeginDate(){
        return beginDate;
    }
    
    /**
     * This is the default accessor of the creditCard member.
     * 
     * @return String This method returns the creditCard attribute.
     */
    public String getCreditCard(){
        return creditCard;
    }
    
    /**
     * This is the default accessor of the endDate data member.
     * 
     * @return Date This method returns the endDate attribute.
     */
    public Date getEndDate(){
        return endDate;
    }
    
    /**
     * This is the default accessor method of the customerName member.
     * 
     * @return String This method returns the customer name attribute.
     */
    public String getCustomerName(){
        return customerName;
    }
    
    /**
     * This method is the default accessor method of the requestedRoomNumber attribute.
     * 
     * @return int This method returns the requestedRoomNumber attribute.
     */
    public int getRequestedRoomNumber(){
        return requestedRoomNumber;
    }
    
    /**
     * This is the default accessor method of the reference member.
     * 
     * @return int This method returns the thisRef member.
     */
    public int getThisRef(){
        return thisRef;
    }
    
    /**
     * This method is the default accessor method of the totalCost attribute.
     * 
     * @return double This method returns the totalCost attribute. 
     */
    public double getTotalCost(){
        return totalCost;
    }
    
    /**
     * This method is the default mutator method for the beginDate attribute.
     * 
     * @param newBeg This is the new date for the beginDate attribute.
     */
    public void setBeginDate(Date newBeg){
        beginDate = newBeg;
    }
    
    /**
     * This method is the default mutator method for the creditCard attribute.
     * 
     * @param newNum This is the new value for the creditCard attribute.
     */
    public void setCreditCard(String newNum){
        creditCard = newNum;
    }
    
    /**
     * This method is the default mutator method for the endDate attribute.
     * 
     * @param newEnd This is the new value for the endDate attribute.
     */
    public void setEndDate(Date newEnd){
        endDate = newEnd;
    }
    
    /**
     * This method is the default mutator method for the customerName attribute.
     * 
     * @param newCust This is the new value for the customer attribute. 
     */
    public void setCustomerName(String newCust){
        customerName = newCust;
    }
   
    /**
     * This method is the default mutator for the referenceNumber attribute.
     * 
     * @param newNum This is the new value for the referenceNumber attribute. 
     */
    public void setReferenceNumber(int newNum){
        referenceNumber = newNum;
    }
    
    /**
     * This method is the default mutator for the requestedRoomNumber attribute.
     * 
     * @param newNum This is the new value for the requestedRoomNumber attribute.
     */
    public void setRequestedRoomNumber(int newNum){
        requestedRoomNumber = newNum;
    }
    
    /**
     * This method is the default mutator for the thisRef attribute.
     * 
     * @param newNum This is the new value for the thisRef attribute. 
     */
    public void setThisRef(int newNum){
        referenceNumber = newNum;
    }
    
    /**
     * This is the main mutator for the totalCost attribute.
     * 
     * @param newCost This is the new value for the total cost attribute.
     */
    public void setTotalCost(double newCost){
        totalCost = newCost;
    }
}   
