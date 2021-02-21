/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import java.sql.Connection;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class Gestioncategoris {
     Connection cnx;

    public Gestioncategoris() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    
}
