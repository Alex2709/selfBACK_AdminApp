package com.example.alexandre.motivational_messages_admin.Model;

/**
 * Created by Alexandre on 17/05/2017.
 */

public class ActivityToSteps {
    private String activityName;

    private float numberStepsPerMinute;

    public ActivityToSteps(String activityName, float numberStepsPerMinute) {
        this.activityName = activityName;
        this.numberStepsPerMinute = numberStepsPerMinute;
    }

    public ActivityToSteps() {
    }

    public float getSteps(int minuteNumber){
        return numberStepsPerMinute * minuteNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public float getNumberStepsPerMinute() {
        return numberStepsPerMinute;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setNumberStepsPerMinute(float numberStepsPerMinute) {
        this.numberStepsPerMinute = numberStepsPerMinute;
    }
}
