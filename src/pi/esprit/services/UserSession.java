/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import java.util.Set;

/**
 *
 * @author bureau
 */
public final class UserSession {
    private static UserSession instance;

    private int id_user;
    private int pwd;

    public UserSession(int id_user, int pwd) {
        this.id_user = id_user;
        this.pwd = pwd;
    }

   

    public static UserSession getInstace(int id_user, int pwd) {
        if(instance == null) {
            instance = new UserSession(id_user,pwd);
        }
        return instance;
}
}