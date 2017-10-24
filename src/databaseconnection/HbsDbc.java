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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * This class creates the connection into the database.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 * 
 */
public class HbsDbc {

    private static String dbURL = "jdbc:derby://localhost:1527/HotelBookingSystemAssignmentOne;create=true;user=S27338096;password=student";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    /**
     * Default constructor for database connector.
     * 
     * @throws Exception To handle a failed database connection
     */
    public HbsDbc() throws Exception{

        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        conn = DriverManager.getConnection(dbURL); 
        stmt = conn.createStatement();
 
    }
    
    /**
     * The default assessor method for the conn attribute.
     * 
     * @return Connection This method returns the conn attribute.
     */
    public Connection getConn(){
        return conn;
    }
    
    /**
     * The default assessor method for the stmt attribute.
     * 
     * @return Statement This method returns the stmt attribute.
     */
    public Statement getStmt(){
        return stmt;
    }
    
    /**
     * The default mutator method for the conn attribute.
     * 
     * @param newConn The new value for the conn attribute.
     */
    public void setConn(Connection newConn){
        conn = newConn;
    }
    
    /**
     * The default mutator method for the stmt attribute.
     * 
     * @param newStmt The new value for the stmt attribute.
     */
    public void setStmt(Statement newStmt){
        stmt = newStmt;
    }
    
    /**
     * This method closes the connection and statements that were opened.
     * 
     * @throws Exception To handle failed shutdowns.
     */
    public void shutdownDbc() throws Exception{
        stmt.close();
        conn.close();
    }
}
