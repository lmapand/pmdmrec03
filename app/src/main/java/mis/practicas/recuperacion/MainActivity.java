package mis.practicas.recuperacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TreeSet;

import mis.practicas.recuperacion.modelo.FaseDesconfinamiento;
import mis.practicas.recuperacion.modelo.Provincia;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{
    /** numero máximo de Fases, para este práctica*/
    public static final int MAX_FASES=10;

    /** Declaracion de las Provincias, a falta de almacenamiento permanente*/
    public static final Provincia[] PROVINCIAS={
                                                new Provincia("A Coruña",R.drawable.p_a_coruna ),
                                                new Provincia("Lugo", R.drawable.p_lugo),
                                                new Provincia("Ourense",R.drawable.p_ourense ),
                                                new Provincia("Pontevedra", R.drawable.p_pontevedra)
                                                };
    /** Declaracion de las FasesDesconfinamiento, a falta de almacenamiento permanente*/
    public static final TreeSet<FaseDesconfinamiento> FASES_DESCONFINAMIENTO =new TreeSet<>();
    
    private static final String USUARIO_LOGIN="admin";
    private static final String USUARIO_CONTRAS="abc123.";
    
    /** Creacion  de las fases de Desconfinamiento y asignacion a las provincias*/
    static
    {
        
        //Ceacion de las fases
        FASES_DESCONFINAMIENTO.add(new FaseDesconfinamiento(0));
        FASES_DESCONFINAMIENTO.add(new FaseDesconfinamiento(1));
        FASES_DESCONFINAMIENTO.add(new FaseDesconfinamiento(2));
        FASES_DESCONFINAMIENTO.add(new FaseDesconfinamiento(3));
        
        //Asignacion de la fase inicial a todas las provincias
        for(Provincia p:PROVINCIAS)
            p.setFaseDesconfinamiento(FASES_DESCONFINAMIENTO.first());
    }
    
    
    private EditText txtUsuario;
    private EditText txtContras;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        
        //Referencia a los elementos necesarios
        this.txtUsuario=this.findViewById(R.id.txtUsuario);
        this.txtContras=this.findViewById(R.id.txtPass);

        //Establecer los eventos de click
        this.findViewById(R.id.btUsuario).setOnClickListener(this);
        this.findViewById(R.id.btAdmin).setOnClickListener(this);
        
        this.setTitle("Desconfinador");
    }
    
    @Override
    public void onClick(View v)
    {
        Intent i=null;
        
        switch(v.getId())
        {
            //Gention del boton de Usuario
            case R.id.btUsuario:
                i=new Intent();
                i.setClassName(this, PantallaUsuario.class.getName());
                break;
    
            //Gention del boton de Administrador
            case R.id.btAdmin:
                if(!this.usuarioVerificado()) //Verificacion y salida de método si hay error
                    return;
                i=new Intent();
                i.setClassName(this, PantallaAdmin.class.getName());
                break;
        }

        //Inicio de Actividad si ha lugar
        if(null!=i)
            this.startActivity(i);
    }
    
    /** Comprueba que las credenciales son correctas.
     *
     * Adicionalmente, informa de la existencia de campos vacíos y de error en la verificacion
     *
     * @return boolean, true si la verificacion fue exitosa, false en caso conttrario
     */
    private boolean usuarioVerificado()
    {
        boolean verificado=true;
    
        //Verificar que existen los textos y en caso contrario, mostrar un error
        String usuario=this.txtUsuario.getText().toString();
        if(usuario.matches("^\\s*$"))
            this.txtUsuario.setError(this.getResources().getString(R.string.txtErrorCampoVacio));
         

        
        String contrasena=this.txtContras.getText().toString();
        if(contrasena.matches("^\\s*$"))
            this.txtContras.setError(this.getResources().getString(R.string.txtErrorCampoVacio));


        
        if(usuario.compareTo(MainActivity.USUARIO_LOGIN)!=0 || contrasena.compareTo(MainActivity.USUARIO_CONTRAS)!=0)
        {
            Toast.makeText(this, R.string.txtErrorFalloVerificacion, Toast.LENGTH_LONG).show();
            verificado = false;
        }
        return verificado;
    }
}
