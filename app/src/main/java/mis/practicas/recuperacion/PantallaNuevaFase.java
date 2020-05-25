package mis.practicas.recuperacion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import mis.practicas.recuperacion.modelo.FaseDesconfinamiento;

public class PantallaNuevaFase extends AppCompatActivity implements View.OnClickListener
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_nueva_fase);
        
        this.setTitle(R.string.txtTituloPantallaCrearFase);
        
        
        /* Cargar los datos en el Spinner:
        Se permite generar hasta 10 niveles de  fase, siempre que no exista ya la fase.
        */
        List<Integer> valoresIdFase=new ArrayList<Integer>();
        //Aañdir el máximo de fases
        for(int i=0;i<MainActivity.MAX_FASES;i++)
            valoresIdFase.add(i);
        
        /* Quitar las ya asignadas:
        Convertimos a Integer, porque lo que queremos quitar es EL VALOR, no el indice
        */
        for(FaseDesconfinamiento fd:MainActivity.FASES_DESCONFINAMIENTO)
            valoresIdFase.remove( (Integer)fd.getIdFase());

        ArrayAdapter<Integer>adaptadorIDFase=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, valoresIdFase);
        adaptadorIDFase.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)this.findViewById(R.id.spNFFaseID)).setAdapter(adaptadorIDFase);
        
        /* Asignar acciones a los botones*/
        ((ImageButton)this.findViewById(R.id.btNFaddCaracteristica)).setOnClickListener(this);
        ((Button)this.findViewById(R.id.btNFRegistarFase)).setOnClickListener(this);

    }
    
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btNFaddCaracteristica:
            case R.id.btNFRegistarFase:
                //Creqcion de un dialoguillo
                AlertDialog.Builder bldDlg=new AlertDialog.Builder(this);
                bldDlg.setTitle(R.string.txtDlgNITitulo);
                bldDlg.setMessage(R.string.txtDlgNITexto);
                bldDlg.setIcon(android.R.drawable.ic_dialog_info);
                bldDlg.setPositiveButton(R.string.txtDlgNIBtAceptar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });
                bldDlg.create();
                bldDlg.show();
                break;
        }
    }
}
