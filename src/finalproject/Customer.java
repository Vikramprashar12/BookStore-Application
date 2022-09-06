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
public class Customer extends User {

    private int points;
    private int totalCost;
    private Status myStatus;

    public Customer(String username, String password, int points) {
        super(username, password);
        myStatus = new Silver();
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        statusUpdate();
    }

    public Status getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(Status myStatus) {
        this.myStatus = myStatus;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {

        //incase
        if (totalCost < 0) {
            this.totalCost = 0;
        } else {
            this.totalCost = totalCost;
        }
    }

    public void statusUpdate() {

        if (points >= 1000 && myStatus instanceof Silver) {
            setMyStatus(new Gold());
        } else if (points < 1000 && myStatus instanceof Gold) {
            setMyStatus(new Silver());
        }
    }

    public String myStatusName() {
        return (myStatus.myStatusName());
    }

}
