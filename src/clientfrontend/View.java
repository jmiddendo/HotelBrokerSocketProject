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

/**
 * This class is the parent class to all child view.  It is used to share
 * a controller instance.
 * 
 * @author Jeffrey Middendorf
 * @version 1.0
 * @since 17-Apr-2017
 * 
 */
public abstract class View extends javax.swing.JFrame {
    
   ClientController controller;
   
   /**
    * The default assessor method for the controller attribute.
    * 
    * @return ClientController This method returns the controller attribute.
    */
   public ClientController getController(){
       return controller;
   }
   
   /**
    * This is the default mutator for the controller attribute.
    * 
    * @param newCont This is the new value for the controller.
    */
   public void setController(ClientController newCont){
       controller = newCont;
   }
    
}
