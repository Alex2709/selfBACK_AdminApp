package com.example.alexandre.motivational_messages_admin.View.Activities;

import android.app.DialogFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alexandre.motivational_messages_admin.Controller.ActivityConversionDAO;
import com.example.alexandre.motivational_messages_admin.Model.ActivityToSteps;
import com.example.alexandre.motivational_messages_admin.R;
import com.example.alexandre.motivational_messages_admin.View.Dialogs.datePickerDialog;
import com.example.alexandre.motivational_messages_admin.View.Dialogs.timePickerDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    private TaskCompletionSource<ArrayList<ActivityToSteps>> getConversionTask;
    private Task getConversionTaskWaiter;

    private ArrayList<ActivityToSteps> activityConversionList;
    private ArrayList<String> activityList;

    //public TextView tv

    private ArrayAdapter<String> activities_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        activityConversionList = new ArrayList<>();
        activityList = new ArrayList<>();
        activityList.add("Loading list...");

        Spinner sp_activityList = (Spinner)findViewById(R.id.sp_newActivity);
        NumberPicker np_hours = (NumberPicker)findViewById(R.id.np_hours);
        NumberPicker np_minutes = (NumberPicker)findViewById(R.id.np_minutes);
        Button bt_pickDate = (Button)findViewById(R.id.bt_datePicker);
        Button bt_pickTime = (Button)findViewById(R.id.bt_timePicker);
        Button bt_add = (Button)findViewById(R.id.bt_adNewActivity);


        getConversionTask = new TaskCompletionSource<>();
        getConversionTaskWaiter = getConversionTask.getTask();

        activities_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activityList);
        activities_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_activityList.setAdapter(activities_adapter);

        getConversionTaskWaiter.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    activityList.clear();

                    activityConversionList.addAll((ArrayList<ActivityToSteps>)task.getResult());

                    for(ActivityToSteps ats : activityConversionList){
                        activityList.add(ats.getActivityName());
                    }

                    activities_adapter.notifyDataSetChanged();
                }
                else{
                    Exception e = task.getException();
                    System.out.println(e.getMessage());
                }
            }
        });

        ActivityConversionDAO.getInstance().getConversion(getConversionTask);

        np_hours.setMinValue(0);
        np_hours.setMaxValue(24);

        np_minutes.setMinValue(0);
        np_minutes.setMaxValue(60);

        bt_pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new datePickerDialog();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        bt_pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new timePickerDialog();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new timePickerDialog();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

    }
}
