/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.courseworkdatastructures;

/**
 *
 * @author owenb
 */
import java.time.LocalDate;

public class Activity {

    private String activityId;
    private String activityName; // "AddToStock" or "RemoveFromStock"
    private int activityQuantity;
    private LocalDate activityDate;

    //Constructor
    public Activity(String activityId, String activityName, int activityQuantity) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityQuantity = activityQuantity;
        this.activityDate = LocalDate.now(); // automatically set date
    }

    // Getters & Setters
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getActivityQuantity() {
        return activityQuantity;
    }

    public void setActivityQuantity(int activityQuantity) {
        this.activityQuantity = activityQuantity;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }
}
