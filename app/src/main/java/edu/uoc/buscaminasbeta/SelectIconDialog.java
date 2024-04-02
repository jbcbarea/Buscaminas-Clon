package edu.uoc.buscaminasbeta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class SelectIconDialog extends DialogFragment {

    private DialogResponseIcon response2;

    String[] icons = {"Blue Bomb", "Yellow Bomb", "Money Bomb"};
    int images[] = {R.drawable.bomb1,
            R.drawable.bomb2,
            R.drawable.bomb3};



    public class Adapter extends ArrayAdapter<String> {

        public Adapter(Context context, int txtViewResourceId, String[] objects) {
            super(context, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return CustomRow(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return CustomRow(pos, cnvtView, prnt);
        }

        public View CustomRow(int position,
                              View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View miFila = inflater.inflate(R.layout.select_icon_doalog, parent, false);
            TextView nombre = (TextView) miFila.findViewById(R.id.descripcion);
            nombre.setText(icons[position]);
            ImageView imagen = (ImageView) miFila.findViewById(R.id.imagenpersonaje);
            imagen.setImageResource(images[position]);
            return miFila;
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Adapter a = new Adapter(getActivity(), R.layout.select_icon_doalog, icons);
        builder.setSingleChoiceItems(a,R.id.spinner, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                response2.onREsponse2(i);

            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        response2 =(DialogResponseIcon)activity;
    }

    public interface DialogResponseIcon {
        public void onREsponse2(int i);
    }


}
