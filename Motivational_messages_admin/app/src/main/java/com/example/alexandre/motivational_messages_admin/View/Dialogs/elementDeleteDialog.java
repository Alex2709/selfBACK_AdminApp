package com.example.alexandre.motivational_messages_admin.View.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.alexandre.motivational_messages_admin.Controller.InformationDAO;
import com.example.alexandre.motivational_messages_admin.Controller.MessageDAO;
import com.example.alexandre.motivational_messages_admin.Model.Information;
import com.example.alexandre.motivational_messages_admin.Model.Message;
import com.example.alexandre.motivational_messages_admin.R;

/**
 * Created by Alexandre on 16/05/2017.
 */

public class elementDeleteDialog extends DialogFragment{

    private Message message;
    private Information information;

    public static elementDeleteDialog newInstance(Object object){
        elementDeleteDialog mDD = new elementDeleteDialog();

        Bundle args = new Bundle();
        if(object instanceof Message)
            args.putParcelable("message", (Message)object);
        else{
            if(object instanceof Information){
                args.putParcelable("information", (Information)object);
            }
        }
        mDD.setArguments(args);

        return mDD;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        message = new Message();
        information = new Information();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.message_delete_dialog, null);

        if(bundle.containsKey("message")){
            message = bundle.getParcelable("message");

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            MessageDAO.getInstance().deleteMessage(message);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            elementDeleteDialog.this.getDialog().cancel();
                        }
                    });

            builder.setTitle("Delete message");
        }
        else
        {
            if(bundle.containsKey("information")){
                information = bundle.getParcelable("information");

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                InformationDAO.getInstance().deleteInformation(information);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                elementDeleteDialog.this.getDialog().cancel();
                            }
                        });

                builder.setTitle("Delete information message");
            }
        }


        return builder.create();
    }
}