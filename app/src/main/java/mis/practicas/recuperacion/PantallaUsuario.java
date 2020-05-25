package mis.practicas.recuperacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import mis.practicas.recuperacion.modelo.Provincia;

public class PantallaUsuario extends AppCompatActivity implements  View.OnClickListener
{
    
    public static final String DATO_TIPO_PROVINCIA="Provincia";
    
    private Spinner spProvincia;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_usuario);
  
        this.setTitle(R.string.txtTituloPantallaUsuario);
        
        //Cargar los datos en el Spinner
        ArrayAdapter<Provincia>adaptador=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.PROVINCIAS);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spProvincia=(Spinner)this.findViewById(R.id.spProvincia);
        this.spProvincia.setAdapter(adaptador);
        
        //establecer eventos de raton
        this.findViewById(R.id.btSalir).setOnClickListener(this);
        this.findViewById(R.id.btVerFaseProvincia).setOnClickListener(this);
        
    }
    
    
    @Override
    public void onClick(View v)
    {
        Intent i=null;
        switch(v.getId())
        {
            //Cargar otra activity con la imagen de la provincia, y la fase en la que se encuentra
            case R.id.btVerFaseProvincia:
                //Capturar la Provincia Seleccionada
                Provincia provSelec=(Provincia)spProvincia.getSelectedItem();
                //Toast.makeText(this, "Se ha seleccionado "+ provSelec.getNombre(), Toast.LENGTH_LONG).show();
                i=new Intent();
                i.setClassName(this, PantallaVerProvincia.class.getName());
                i.putExtra(PantallaUsuario.DATO_TIPO_PROVINCIA, provSelec);
                //Lanzamos el intent
                this.startActivity(i);
                break;
    
            //No se ha definido la accion a realizar: interpreto cerrar activity para volver a la anterior
            case R.id.btSalir:
                this.finish();
                break;
        }
        
    }
}
