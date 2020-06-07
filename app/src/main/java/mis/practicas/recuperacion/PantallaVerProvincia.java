package mis.practicas.recuperacion;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import mis.practicas.recuperacion.modelo.Provincia;

public class PantallaVerProvincia extends AppCompatActivity
{
    private ListView lstCaractConfinamiento;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ver_provincia);
        
        //Captura y representacion de los datos de la provincia
        Provincia p=(Provincia)this.getIntent().getSerializableExtra(PantallaUsuario.DATO_TIPO_PROVINCIA);
        
        if(null==p)
            Toast.makeText(this, R.string.txtErrorPasoProvincia, Toast.LENGTH_LONG).show();
        else
            this.setTitle(this.getResources().getString(R.string.provincia, p.getNombre()) );
        
        ((ImageView)this.findViewById(R.id.imgListadoProvincia)).setImageResource(p.getIdImagen());
        ((TextView)this.findViewById(R.id.etqVPFaseConfinamiento)).setText(p.getFaseDesconfinamiento().toString());
        
        //Datos a cargar en la lista
        List<String> listaCaracteristicas=new ArrayList<String>();
        for(int x=0;x<p.getFaseDesconfinamiento().getNumeroCaracteristicas();x++)
            listaCaracteristicas.add(p.getFaseDesconfinamiento().getCaracteristica(x));
        
        this.lstCaractConfinamiento=(ListView)this.findViewById(R.id.lstCaractFaseConf);
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCaracteristicas);
        this.lstCaractConfinamiento.setAdapter(adaptador);
    }
}
