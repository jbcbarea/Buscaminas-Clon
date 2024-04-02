package edu.uoc.buscaminasbeta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LevelDialog extends DialogFragment {
    String[] level ={"Principiante","Ameteur","Experto"};
    DialogResponse response;

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        inflater.inflate(R.layout.configurate_dialog,null);
        builder.setTitle("Elige una dificultad:");
        builder.setSingleChoiceItems(level, R.id.radioGroup, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                response.onResponse(i);
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        return builder.create();
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        response =(DialogResponse)activity;
    }


    public interface DialogResponse {
        void onResponse(int i);
    }
    
}
