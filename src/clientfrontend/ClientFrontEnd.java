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

/**
 *  This class is the main driver class for the Client Front end GUI tier of the network.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 * 
 */
public class ClientFrontEnd {
    
    /**
     * This is the main driver method for the client GUI.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args){
        
        ClientConnection sysModel = null;
        ClientController sysController = null;
        
        try{
        if (args.length != 1) {
	    sysModel = new ClientConnection();
	}
        else{
            sysModel = new ClientConnection(args[0]);
        }
        
        sysController = new ClientController(sysModel);
        
        }
        catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(2);
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(3);
        }
        
        sysController.initialize();
    }
    
}
