package mis.practicas.recuperacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mis.practicas.recuperacion.modelo.FaseDesconfinamiento;
import mis.practicas.recuperacion.modelo.Provincia;

public class PantallaAsignarFase extends AppCompatActivity implements Spinner.OnItemSelectedListener
{
    
    private Spinner spProvincia;
    private Spinner spFase;
    private TextView etqFaseProvSelec;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_asignar_fase);
    
        this.setTitle(R.string.txtTituloPantallaAsignarFase);
        
        /* Creacion de los modelos*/
        ArrayAdapter<Provincia>adaptadorProvincia=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.PROVINCIAS);
        adaptadorProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        FaseDesconfinamiento[] faseSpinner=new FaseDesconfinamiento[MainActivity.FASES_DESCONFINAMIENTO.size()];
        int i=0;
        for(FaseDesconfinamiento fd:MainActivity.FASES_DESCONFINAMIENTO)
            faseSpinner[i++] =fd;

        ArrayAdapter< FaseDesconfinamiento >adaptadorFase=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, faseSpinner);
        adaptadorFase.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //Establcer los spinners
        this.spProvincia=(Spinner)this.findViewById(R.id.spAFProvincia);
        spProvincia.setAdapter(adaptadorProvincia);
        spProvincia.setOnItemSelectedListener(this);
    
        this.spFase=(Spinner)this.findViewById(R.id.spAFFase);
        spFase.setAdapter(adaptadorFase);
        
        //Establecer el texto de la fae actual
        this.etqFaseProvSelec=(TextView)this.findViewById(R.id.etqAFFaseActual);
        Provincia pInicial=(Provincia)this.spProvincia.getSelectedItem();

        this.etqFaseProvSelec.setText(getResources().getString(R.string.fase_asignada, pInicial.getFaseDesconfinamiento().getIdFase() ));
        
        //Usamos clase anonim para evento clcik, porque no hay mas eventos de este tipo
        ((Button)this.findViewById(R.id.btAsignarFase)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Realiaar la asignacion
                Provincia pSelec=(Provincia)spProvincia.getSelectedItem();
                FaseDesconfinamiento fd=(FaseDesconfinamiento)spFase.getSelectedItem();
                pSelec.setFaseDesconfinamiento(fd);
                
                //Notificar
                Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.resultado_asignacion,
                                                        pSelec.getFaseDesconfinamiento().getIdFase(),
                                                        pSelec.getNombre()),
                                    Toast.LENGTH_LONG)
                                    .show();
                
                //Salir de la Activity
                finish();
            }
        });
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //No tenemso que hacer ninguna comprovacin del desencadenador, pues solo estamos escuchando a spProvincia.
        Provincia pSelec=(Provincia)this.spProvincia.getSelectedItem();
        this.etqFaseProvSelec.setText(getResources().getString(R.string.fase_asignada, pSelec.getFaseDesconfinamiento().getIdFase() ));
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
    
    }
}
