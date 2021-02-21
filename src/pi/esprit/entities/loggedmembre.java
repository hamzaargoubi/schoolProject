/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.entities;

import java.util.Objects;

/**
 *
 * @author bureau
 */
public class loggedmembre {
   public static personnes p;

    public loggedmembre() {
    }

    public static personnes getP() {
        return p;
    }

    public static void setP(personnes p) {
        loggedmembre.p = p;
    }

    @Override
    public String toString() {
        return "loggedmembre{" + '}';
    }

}