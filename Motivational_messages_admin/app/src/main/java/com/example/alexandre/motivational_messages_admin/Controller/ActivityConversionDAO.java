package com.example.alexandre.motivational_messages_admin.Controller;

import com.example.alexandre.motivational_messages_admin.Model.ActivityToSteps;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Alexandre on 16/05/2017.
 */

public class ActivityConversionDAO {

    protected static DatabaseReference mDatabase;
    protected static FirebaseDatabase mFirebaseInstance;

    private static ActivityConversionDAO INSTANCE = null;

    private ActivityConversionDAO() {
        mFirebaseInstance = FirebaseDatabase.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("activities conversion"); //gets the reference of the node 'quotes'
    }

    public final static ActivityConversionDAO getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ActivityConversionDAO();
        }
        return INSTANCE;
    }

    public void addActivityConversion(ActivityToSteps activityToSteps){
        if(activityToSteps != null){
            String activityName = activityToSteps.getActivityName();
            try{
                mDatabase.child(activityName).setValue(activityToSteps);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

}
