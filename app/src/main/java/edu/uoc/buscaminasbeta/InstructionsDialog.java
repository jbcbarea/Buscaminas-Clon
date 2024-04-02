package edu.uoc.buscaminasbeta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

    public class InstructionsDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.instruction_dialog, null));
            builder.setPositiveButton("Salir", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            return builder.create();
        }

    }



