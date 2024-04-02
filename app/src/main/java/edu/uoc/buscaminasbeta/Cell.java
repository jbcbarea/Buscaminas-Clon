package edu.uoc.buscaminasbeta;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.EventListener;

public class Cell  {
    //  if there is a bomb value will be -1 .
    //  Else it will represent number of the neighbours bombs.
    private int value = 0;
    private boolean isFlagged = false;
    private boolean isReveal = false;
    private boolean isClicked;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isReveal = true;

    }

    public Cell(int posX,int posY) {

    }

    public boolean isReveal() {
        return isReveal;
    }

    public boolean isBomb() {
        return value == -1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}


