package edu.uoc.buscaminasbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements LevelDialog.DialogResponse, SelectIconDialog.DialogResponseIcon {

    private Board board;
    private int level;
    private LinearLayout linearLayout;
    private MenuItem menuItem;
    private int image=R.drawable.bomb1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creo Tablero , calculando el tamaño de la pantalla
        board = new Board(getWindowManager().getDefaultDisplay(),level,image);
        board.createGrid(getApplicationContext());

        // Referencia al Layout(linear) Pintar Tablero.
        linearLayout = (LinearLayout) findViewById(R.id.main);
        linearLayout.addView(board.getMyGrid()); //inserta la gridLayout en el RelativeLayout padre
        board.printBoard();

    }

    //Creo el menu lo inflo del recurso que creee en menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itInstrucciones:
                //Dialogo instrucciones
                InstructionsDialog dialog = new InstructionsDialog();
                dialog.show(getFragmentManager(), "Instrucciones");
                return true;
            case R.id.itNuevo:
                //Crea tablero Nuevo, Partida nueva
                createNewBoard();
                return true;
            case R.id.itConfiguracion:
                //Seleccion dificultad
                LevelDialog dialog2 = new LevelDialog();
                dialog2.show(getSupportFragmentManager(), "Dificultad");
                return true;
            case R.id.itSeleccion:
                SelectIconDialog icon = new SelectIconDialog();
                icon.show(getSupportFragmentManager(), "Seleccion Personaje");
                menuItem =item;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNewBoard() {
        linearLayout.removeAllViews();
        board.getMyGrid().removeAllViewsInLayout();
        Board board = new Board(getWindowManager().getDefaultDisplay(),level,image);
        board.createGrid(getApplicationContext());
        linearLayout.addView(board.getMyGrid());
        board.printBoard();
    }

    public void SelectIconImage(int i){
        switch (i){
            case 0:
                image =R.drawable.bomb1;
                break;
            case 1:
                image =R.drawable.bomb2;
                break;
            case 2:
                image =R.drawable.bomb3;
                break;
        }
        menuItem.setIcon(image);
        createNewBoard();
    }

    @Override
    public void onResponse(int i) {
        switch (i) {
            case 0:
                level = 0;
                createNewBoard();
                break;
            case 1:
                level = 1;
                createNewBoard();
                break;
            case 2:
                level = 2;
                createNewBoard();
                break;
        }
    }
    @Override
    public void onREsponse2(int i) {
        SelectIconImage(i);
    }

}
