package mis.practicas.recuperacion;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mis.practicas.recuperacion.modelo.Provincia;

public class PantallaVerProvincia extends AppCompatActivity
{
    
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
        
        ((ImageView)this.findViewById(R.id.imgProvincia)).setImageResource(p.getIdImagen());
        ((TextView)this.findViewById(R.id.etqVPFaseConfinamiento)).setText(p.getFaseDesconfinamiento().toString());
    }
}
