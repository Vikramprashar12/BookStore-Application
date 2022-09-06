/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Vikram Prashar
 */
public class User {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String username;
    public String password;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    
}
