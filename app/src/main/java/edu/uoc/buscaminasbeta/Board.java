package edu.uoc.buscaminasbeta;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Board implements View.OnClickListener,View.OnLongClickListener {

    private GridLayout myGrid;
    private int height;
    private int width;
    public int rows;
    public int columns;
    public Cell[][] cellMatrix;
    public int numBombs;

    public void setCounterBombsForWin(int counterBombsForWin) {
        this.counterBombsForWin = counterBombsForWin;
    }
    private int counterBombsForWin = numBombs;
    private int image=R.drawable.bomb1;

    //Getters Setter rows colums, numBombs.....
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }

    // private Game game;

    public Board(Display display, int level,int image) {
        levelSelection(level);
        this.setNumBombs(numBombs);
        this.setColumns(columns);
        this.setCounterBombsForWin(numBombs);
        this.setRows(rows);
        this.setImage(image);
        cellMatrix=new Cell[rows][columns];
        levelSelection(level);
        calculateScreenSize(display);
        initializeMatrix();
        setBombs();
        fillMatrix();
    }

    public Board() {

    }


    private void calculateScreenSize(Display display) {
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y - 150;
    }

    public void createGrid(Context context) {
        myGrid = new GridLayout(context);
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;

       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width / columns, height / rows);
       layoutParams.setMargins(0, 0, 0, 0);

        myGrid.setRowCount(rows);
        myGrid.setColumnCount(columns);
        myGrid.setLayoutParams(param);

    }

    public GridLayout getMyGrid() {

        return myGrid;
    }

    public void initializeMatrix() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cellMatrix[row][column] = new Cell(column, row);
            }
        }
    }

    public void setBombs() {
       int counter=numBombs;
       while(counter!=0){
            int column = (int) (Math.random() * columns);
            int row = (int) (Math.random() * rows);
            //If there is no bomb on the cell , place a bomb
            if (cellMatrix[row][column].getValue() == 0) {
                cellMatrix[row][column].setValue(-1);
                counter--;
            }
        }
    }

    public void fillMatrix() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                calculateNeighbourBombs(row, column);
            }
        }
    }

    private void calculateNeighbourBombs(int row, int column) {
        if (cellMatrix[row][column].isBomb()) {
            return;
        }
        int bombsCounter = 0;
        // Check bombs in adjacent cells. Increment counter for each found bomb
        if (checkBombInCell(row - 1, column - 1)) bombsCounter++; //< Upper left
        if (checkBombInCell(row - 1, column)) bombsCounter++; //< Upper
        if (checkBombInCell(row - 1, column + 1)) bombsCounter++; //< Upper Right
        if (checkBombInCell(row, column - 1)) bombsCounter++; //< Left
        if (checkBombInCell(row, column + 1)) bombsCounter++; //< Right
        if (checkBombInCell(row + 1, column - 1)) bombsCounter++; //< Bottom left
        if (checkBombInCell(row + 1, column)) bombsCounter++; //< Bottom
        if (checkBombInCell(row + 1, column +1)) bombsCounter++; //< Bottom right

        cellMatrix[row][column].setValue(bombsCounter);
    }

    private boolean checkBombInCell(int row, int column) {
        if (isValidCell(row, column)) {
            if (cellMatrix[row][column].isBomb()) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCell(int row, int column) {
        return (column >= 0 && row >= 0 && column < columns && row < rows);
    }

    // Rename this method, which is responsible for creating Grid with its Buttons and ImageButtons
    public void printBoard() {
        View button;
        int buttonNumber = 0;


        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cellMatrix[row][column].isBomb()) {
                    button = new ImageButton(myGrid.getContext());
                    //button.setLayoutParams(new ViewGroup.LayoutParams((int) (width / 8), (int) (height / 8)));
                    //button.setPadding(20, 20, 20, 20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / columns, height / rows);
                    params.setMargins(0, 0, 0, 0);
                    button.setLayoutParams(params);
                    //layoutParams.setMargins(0,0,0,0);
                    //imageButton.setOnClickListener(cellMatrix[row][column]);
                    //imageButton.setOnLongClickListener(this);
                    //cellMatrix[row][column].addEventListener(imageButton.onClick())

                } else {
                    button = new Button(myGrid.getContext());
                    //button.setLayoutParams(new ViewGroup.LayoutParams((int) (width / 8), (int) (height/ 8)));
                    //button.setPadding(20, 20, 20, 20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / columns, height / rows);
                    params.setMargins(0, 0, 0, 0);
                    button.setLayoutParams(params);
                    //button.setOnClickListener(this);
                    //button.setOnLongClickListener(this);
                    // ((Button) button).setText(String.valueOf(cellMatrix[row][column].getValue()));

                    // cellMatrix[row][column].addEventListener(button.onClick())
                    //((Button) button).setTextColor(34);
                }
                myGrid.addView(button, buttonNumber);
                button.setId(buttonNumber++);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int buttonNumber = v.getId();
        int rowButton = buttonNumber / columns;
        int columnButton = buttonNumber % columns;
        if (v.getClass().getSimpleName().equals("Button")) {
              // ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                if (cellMatrix[rowButton][columnButton].getValue() == 1) {
                    ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                    ((Button) v).setTextColor(Color.GREEN);
                }
                if (cellMatrix[rowButton][columnButton].getValue() == 2) {
                    ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                    ((Button) v).setTextColor(Color.BLUE);
                }
                if (cellMatrix[rowButton][columnButton].getValue() == 3) {
                    ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                    ((Button) v).setTextColor(Color.BLACK);
                }
            if (cellMatrix[rowButton][columnButton].getValue() == 4) {
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                ((Button) v).setTextColor(Color.RED);
            }
            if (cellMatrix[rowButton][columnButton].getValue() == 5) {
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                ((Button) v).setTextColor(Color.RED);
            }
            if (cellMatrix[rowButton][columnButton].getValue() == 6) {
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                ((Button) v).setTextColor(Color.RED);
            }
            if (cellMatrix[rowButton][columnButton].getValue() == 7) {
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                ((Button) v).setTextColor(Color.RED);
            }
            if (cellMatrix[rowButton][columnButton].getValue() == 8) {
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));
                ((Button) v).setTextColor(Color.RED);
            }

                if (cellMatrix[rowButton][columnButton].getValue() == 0 && !cellMatrix[rowButton][columnButton].isClicked()) {
                    cellMatrix[rowButton][columnButton].setClicked();
                    ((Button) v).setTextColor(Color.BLACK);
                    ((Button) v).setText("x");
                    ((Button) v).setBackgroundColor(Color.LTGRAY);
                    performClickIfValidCell(rowButton - 1, columnButton - 1);
                    performClickIfValidCell(rowButton - 1, columnButton);
                    performClickIfValidCell(rowButton - 1, columnButton + 1);
                    performClickIfValidCell(rowButton, columnButton - 1);
                    performClickIfValidCell(rowButton, columnButton + 1);
                    performClickIfValidCell(rowButton + 1, columnButton - 1);
                    performClickIfValidCell(rowButton + 1, columnButton);
                    performClickIfValidCell(rowButton + 1, columnButton + 1);
                }
            }

        if (v.getClass().getSimpleName().equals("ImageButton")) {
            onGameLost(rowButton, columnButton, v);
        }
    }

    // Check if its alredy cliked or not
    private void performClickIfValidCell(int row, int column) {
        if (isValidCell(row, column) && cellMatrix[row][column].getValue() != -1) {
            int buttonNumber = row * columns + column;
            ((Button) myGrid.getChildAt(buttonNumber)).performClick();
        }
    }

    private void onGameLost(int row, int column, View v) {
        if (cellMatrix[row][column].isBomb()) {
            ((ImageButton) v).setImageResource(image);
            Toast.makeText(myGrid.getContext(), "Lo siento has desvelado la bomba, has perdido", Toast.LENGTH_SHORT).show();
            showAlltheBombs();
            disableAllCellsOnLost();
        }
    }

    private void showAlltheBombs() {
        for (int i = 0; i < myGrid.getChildCount(); i++) {
            View v = myGrid.getChildAt(i);
            if (v.getClass().getSimpleName().equals("ImageButton")) {
                ImageButton b3 = (ImageButton) v;
                b3.setImageResource(image);
                b3.setEnabled(false);
            }
        }

    }
    private void disableAllCellsOnLost() {
        for (int i = 0; i < myGrid.getChildCount(); i++) {
            View v = myGrid.getChildAt(i);
            if (v.getClass().getSimpleName().equals("Button") )  {
                Button b3 = (Button) v;
                b3.setEnabled(false);
            }
            if (v.getClass().getSimpleName().equals("ImageButton") )  {
                ImageButton b3 = (ImageButton) v;
                b3.setEnabled(false);
            }

        }
    }

    @Override
    public boolean onLongClick(View v) {

        int buttonNumber = v.getId();
        int rowButton = buttonNumber / columns;
        int columnButton = buttonNumber % columns;

        if (v.getClass().getSimpleName().equals("Button")) {
            if (cellMatrix[rowButton][columnButton].getValue() != -1) {
                ((Button) v).setTextColor(Color.MAGENTA);
                ((Button) v).setText(String.valueOf(cellMatrix[rowButton][columnButton].getValue()));

                Toast.makeText(myGrid.getContext(), "Lo siento no has encontrado una bomba, has perdido", Toast.LENGTH_SHORT).show();
                showAlltheBombs();
                disableAllCellsOnLost();
            }
        }
        if (v.getClass().getSimpleName().equals("ImageButton")) {
            if (cellMatrix[rowButton][columnButton].isBomb()) {


                ((ImageButton) v).setImageResource(R.drawable.flag);
                Toast.makeText(myGrid.getContext(), "Muy bien has encontrado una bomba....", Toast.LENGTH_SHORT).show();
                //cellMatrix[rowButton][columnButton].setFlagged();
                counterBombsForWin--;
            }
            WiningCondition(counterBombsForWin);
        }

        return false;
    }

    private void WiningCondition(int counterBombsforWin) {
        if (counterBombsforWin == 0) {
            Toast.makeText(myGrid.getContext(), "Muy bien has encontrado todas las bombas, has ganado el juego....", Toast.LENGTH_LONG).show();
            disableAllCellsOnLost();
        }
    }
    private void levelSelection(int index) {
        int id = index;
        switch (id) {
            case 0:
                rows = 5;
                columns = 5;
                numBombs = 5;
                break;
            case 1:
                rows =8;
                columns =8;
                numBombs =10;
                break;
            case 2:
                rows =12;
                columns =12;
                numBombs =30;
        }
        //No puse el tablero de 16*16 por que no se ven los numeros, funciona pero el boton es tan pequeño que no se ven los números

    }
}










