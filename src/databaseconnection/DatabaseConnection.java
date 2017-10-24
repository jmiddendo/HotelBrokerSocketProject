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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is the controller class for the Hotel Booking System Database
 * Connection.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 * 
 */
public class DatabaseConnection {

    private HbsDbc sysDbc;
    
    /**
     * Default constructor for the Database Connection class.
     * 
     * @throws Exception To handle a failed database connection.
     */
    public DatabaseConnection() throws Exception{
        sysDbc = new HbsDbc();
    }
    
    /**
     * This method closes the open statement and connection.
     * 
     * @throws Exception To handle a failed connection closing.
     */
    public void closeConnection() throws Exception{
        sysDbc.shutdownDbc();
    }
    
    /**
     * This method is used to get a list of room that are available for a specified hotel,
     * amount, and date range.
     * 
     * @param hotelName This is the name of the hotel being searched.
     * @param rmAmt This is the amount the room costs.
     * @param begDate This is the check-in date.
     * @param numDays This is the number of day that booking is.
     * @return ArrayList This method returns a list of room numbers.
     * @throws SQLException To handle a failed SQL execution.
     * @throws ParseException To handle a failed calendar parse.
     * @throws NumberFormatException To handle a failed integer parse.
     */
    public ArrayList<String> getAvailability(String hotelName, String rmAmt, String begDate, String numDays) throws SQLException, ParseException, NumberFormatException{
        
        int count = 1, daysCount = Integer.parseInt(numDays);
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
        cal1.setTime(dt.parse(begDate));
        cal2.setTime(dt.parse(begDate));
        
        while(count < daysCount){
            cal2.add(Calendar.DATE, 1);
            count++;
        }
        
        String strSQL = "select distinct room_room_numeric from availability a, hotel h "
                + "where a.ROOM_HOTEL_HOTEL_ID = h.HOTEL_ID And h.HOTEL_NAME = '" + hotelName + "' "
                + "And availability_date Between '" + dt.format(cal1.getTime()) + "' And '" + dt.format(cal2.getTime()) + "' "
                + "GROUP BY room_room_numeric having count(*) = " + numDays;
        
        ArrayList<String> roomListing = new ArrayList<>();
        ResultSet rstQuery = sysDbc.getStmt().executeQuery(strSQL);
        int colCount = rstQuery.getMetaData().getColumnCount();
        
        while (rstQuery.next()){
            for (int i = 0; i < colCount; i++){
                roomListing.add(rstQuery.getString(i + 1));
            }
        }
        
        rstQuery.close();
        
        return roomListing;
        
    }
    
    /**
     * This is used to get a list of cities that is serviced by the system.
     * 
     * @return ArrayList This method returns a list of cities.
     * @throws SQLException To handle a failed SQL Execution.
     */
    public ArrayList<String> getCities() throws SQLException{
        
        ArrayList<String> cityListing = new ArrayList<>();
        ResultSet rstQuery = sysDbc.getStmt().executeQuery("Select Distinct hotel_city from hotel");
        int count = rstQuery.getMetaData().getColumnCount();
        
        while (rstQuery.next()){
            for (int i = 0; i < count; i++){
                cityListing.add(rstQuery.getString(i + 1));
            }
        }
        
        rstQuery.close();
        
        return cityListing;
    }
    
    /**
     * This method is used to get a list of hotels for a given city.
     * 
     * @param cityName This is the name of the city being search.
     * @return ArrayList This method returns a list of hotel names.
     * @throws SQLException To handle a failed SQL Execution.
     */
    public ArrayList<String> getHotels(String cityName) throws SQLException{
        
        ArrayList<String> hotelListing = new ArrayList<>();
        ResultSet rstQuery = sysDbc.getStmt().executeQuery("Select Distinct hotel_name from hotel where hotel_city = '" + cityName + "'");
        int count = rstQuery.getMetaData().getColumnCount();
        
        while (rstQuery.next()){
            for (int i = 0; i < count; i++){
                hotelListing.add(rstQuery.getString(i + 1));
            }
        }
        
        rstQuery.close();
        
        return hotelListing;
    }
    
    /**
     * This method is used to fetch a list of rates for a passed hotel.
     * 
     * @param hotelName This is the name of the hotel being searched.
     * @return ArrayList This method returns a list of rates.
     * @throws SQLException To handle a failed SQL Execution.
     */
    public ArrayList<String> getRates(String hotelName) throws SQLException{
        
        ArrayList<String> rateListing = new ArrayList<>();
        ResultSet rstQuery = sysDbc.getStmt().executeQuery("Select Distinct room_price from room r, hotel h where h.hotel_id = r.hotel_hotel_id And hotel_name = '" + hotelName + "'");
        int count = rstQuery.getMetaData().getColumnCount();
        
        while (rstQuery.next()){
            for (int i = 0; i < count; i++){
                rateListing.add(rstQuery.getString(i + 1));
            }
        }
        
        rstQuery.close();
        
        return rateListing;
    }
    
    /**
     * This method is used to insert a booking into the booking table of the database.
     * 
     * @param BookingDetails This is a # delimited string that contains the booking information.
     * @return ArrayList This method returns a list of newly created reference numbers.
     * @throws SQLException To handle a failed SQL Execution.
     * @throws ParseException To handle a failed calendar parse.
     * @throws NumberFormatException To handle a failed number parse.
     */
    public synchronized ArrayList<String> insertBooking(String BookingDetails) throws SQLException, ParseException, NumberFormatException{
        
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String hotel_id = "0";
        String[] values = BookingDetails.split("#");
        Double rate = Double.parseDouble(values[6]);
        int newReference = 0, count = 1, daysCount = Integer.parseInt(values[4]), totCost = daysCount * rate.intValue();
        boolean stillAvailable = true;
        ArrayList<String> newReferenceNumbers = new ArrayList<>();
        int bookingCount = 0;
                
        Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
        cal1.setTime(dt.parse(values[3]));
        cal2.setTime(dt.parse(values[3]));
        
        while(count < daysCount){
            cal2.add(Calendar.DATE, 1);
            count++;
        }
        
        ResultSet rset = sysDbc.getStmt().executeQuery("Select hotel_id from hotel where hotel_name = '" + values[1] + "'");
        
        while(rset.next()){
            hotel_id = rset.getString(1);
        }
        
        String strSQL = "select distinct room_room_numeric from availability a, hotel h "
                + "where a.ROOM_HOTEL_HOTEL_ID = h.HOTEL_ID And h.HOTEL_NAME = '" + values[1] + "' "
                + "And availability_date Between '" + dt.format(cal1.getTime()) + "' And '" + dt.format(cal2.getTime()) + "' "
                + "And a.room_room_numeric = " + values[0]
                + " GROUP BY room_room_numeric having count(*) = " + values[4];
        
        rset = sysDbc.getStmt().executeQuery(strSQL);
        
        if (!rset.next()){
            return newReferenceNumbers;
        }
        
        rset = sysDbc.getStmt().executeQuery("Select max(Booking_Reference) from booking");
        
        while(rset.next()){
            bookingCount = rset.getInt(1);
        }
        bookingCount++;
        
        strSQL = "Insert Into booking values(" + bookingCount + ", " + values[0].trim() + ", " + hotel_id + ", '" + values[2] + "', '" + dt.format(cal1.getTime()) + "', '" + dt.format(cal2.getTime()) + "', " + totCost + ", '" + values[5] + "')";
        
        sysDbc.getStmt().executeUpdate(strSQL);
        sysDbc.getStmt().executeUpdate("Delete from availability where Room_hotel_hotel_id = " + hotel_id + " And room_room_numeric = " + values[0] + " And Availability_date Between '" + dt.format(cal1.getTime()) + "' And '" + dt.format(cal2.getTime()) + "'");
        newReferenceNumbers.add(Integer.toString(bookingCount));
        
        return newReferenceNumbers;
    }
    
}
