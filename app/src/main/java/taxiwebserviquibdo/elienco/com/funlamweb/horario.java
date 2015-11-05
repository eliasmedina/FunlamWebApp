package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class horario extends Activity {

    TextView usuario= null;
    TextView correo= null;
    TextView celular= null;
    private Intent intmenu= null;
    //Intent intdireccionf= null;
    lihistorial[] datos = null;
    String[] Arraydirfavoritas;
    ListView listaDF;
    private Intent intConfirmar = null;
    String[] ArrayServicios;

    //Integer direccionfavorita;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

cargelistas();
    }

    public void cargelistas(){
        String archivoinformacion = consultarArchivo("login");
        String info[] = archivoinformacion.split("/");
        String alo=info[1];
        String archivoinformacion2 = consultarArchivo(alo);
        String informacion2[] = archivoinformacion2.split("/");
        int ultreg=informacion2.length;
        
        try{
           
            datos = new lihistorial[ultreg-1];
            ListView listaS = (ListView) findViewById(R.id.listmaterias);
            Adaptertitulos adapterLS = new Adaptertitulos(horario.this);
            listaS.setAdapter(adapterLS);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //cargar listas de servicios
		class Adaptertitulos extends ArrayAdapter<lihistorial>{
        String archivoinformacion = consultarArchivo("login");
        String info[] = archivoinformacion.split("/");
        String alo=info[1];
			Activity context;
			public Adaptertitulos(Activity context){
				super(context, R.layout.limaterias,datos);
				this.context =context;
			}
			public View getView(int posicion, View view, ViewGroup parent){
			LayoutInflater inflater=context.getLayoutInflater();
			View item=inflater.inflate(R.layout.limaterias, null);
			    TextView idclase=(TextView)item.findViewById(R.id.lblcodigo);
				TextView bloque=(TextView)item.findViewById(R.id.lblbloque);
				TextView aula=(TextView)item.findViewById(R.id.lblaula);
                TextView nombreclase=(TextView)item.findViewById(R.id.lblhora);
                TextView fecha=(TextView)item.findViewById(R.id.lblfecha);
				try{
                    String archivoinformacion2 = consultarArchivo(alo);
                    String informacion2[] = archivoinformacion2.split("/");
                    int ultreg=informacion2.length;
		for(int i =1; i < ultreg; i ++ )
			{
			    String list[] =informacion2[i].split(",");
				String ArrIdclase = list[1];
				String Arrbloque = list[2];
				String Arraula = list[3];
                String Arfecha = list[5];
                String Arhora = list[4];

                idclase.setText(ArrIdclase);
                bloque.setText(Arrbloque);
                aula.setText(Arraula);
                nombreclase.setText(Arfecha);
                fecha.setText(Arhora);

			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return item;
			}
		}

    //Consulta la infomacion almacenada en los archivos
    protected String consultarArchivo(String InfoArchivo)
    {
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), InfoArchivo);
        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "";
                linea = br.readLine();
            }
            br.close();
            archivo.close();

            return todo;
        } catch (IOException e) {
            return "Error";
        }
    }
    //boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(horario.this, MainActivity.class);
            startActivity(nuevaAtivity);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

