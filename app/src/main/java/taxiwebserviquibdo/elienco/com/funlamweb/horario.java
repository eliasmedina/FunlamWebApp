package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import taxiwebserviquibdo.elienco.com.funlamweb.horario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class horario extends Activity {
    String[] Arrayhistorial;
    ListView listah;
    lihistorial[] datosh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        cargarhistorial();
    }

    //
    public void cargarhistorial() {

        Arrayhistorial = consultarhistorial();
        try {
            int c = Arrayhistorial.length;
            datosh = new lihistorial[c];
            listah = (ListView) findViewById(R.id.listmaterias);
            Adaptertitulos adapterLS = new Adaptertitulos(horario.this);
            listah.setAdapter(adapterLS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // datos guardados en sqlite
    public String[] consultarhistorial() {
        String[] Arrayhistorial = new String[0];
        Activity context;
        String archivoinformacion = consultarArchivo("login");
        String informacion[] = archivoinformacion.split(",");
        String infor[] = informacion[1].split("/");
        try {
            AdminSQLiteOpen2 admin = new AdminSQLiteOpen2(this, "Horariofunlamh", null,2);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery("select * from Horariofunlamh where cedula = '"+infor[0]+"' ", null);
            ArrayList<taxiwebserviquibdo.elienco.com.funlamweb.lihistorial> historiaconsulta = new ArrayList<taxiwebserviquibdo.elienco.com.funlamweb.lihistorial>();
            Arrayhistorial = new String[fila.getCount()];
            int registros = fila.getCount();
            if (fila.moveToFirst()) {
                for (int i = 0; i < registros; i++) {
                    String IDclase = fila.getString(0);
                    String nombreclase = fila.getString(2);
                    String bloque = fila.getString(3);
                    String aula = fila.getString(4);
                    String fechainicio = fila.getString(5);
                    String hora = fila.getString(6);
                    String fechafinal = fila.getString(7);
                    String infServicio = IDclase + "," + nombreclase + "," + bloque + "," + aula + "," + fechainicio + "," +hora + "," +fechafinal;
                    Arrayhistorial[i] = infServicio;
                }
                return Arrayhistorial;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Arrayhistoria
    class Adaptertitulos extends ArrayAdapter<lihistorial> {
        Activity context;
        public Adaptertitulos(Activity context) {
            super(context, R.layout.limaterias, datosh);
            this.context = context;
        }

        public View getView(int posicion, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.limaterias, null);
            TextView codigo = (TextView)item.findViewById(R.id.lblcodigo);
            TextView clase = (TextView) item.findViewById(R.id.lblnombreclase);
            TextView bloque = (TextView) item.findViewById(R.id.lblbloque);
            TextView aula = (TextView)item.findViewById(R.id.lblaula);
            TextView hora = (TextView) item.findViewById(R.id.lblhora);
            TextView fechainicio = (TextView) item.findViewById(R.id.lblfechainicio);
            TextView fechafin = (TextView) item.findViewById(R.id.lblfechafinal);
            try {
                int h = Arrayhistorial.length;
                for (int i = 0; i < h; i++) {
                    String[] varSeparadas = Arrayhistorial[posicion].split(",");
                     String Arrcodigo = varSeparadas[0];
                     String Arrclase = varSeparadas[1];
                     String Arrbloque = varSeparadas[2];
                    String Arraula = varSeparadas[3];
                     String Arrhora= varSeparadas[4];
                    String Arrfechainicio = varSeparadas[5];
                    String Arrfechafinal = varSeparadas[6];
                    codigo.setText(Arrcodigo);
                    clase.setText(Arrclase);
                    bloque.setText(Arrbloque);
                    aula.setText(Arraula);
                    hora.setText(Arrhora);
                    fechainicio.setText(Arrfechainicio);
                    fechafin.setText(Arrfechafinal);

                }
            } catch (Exception s) {
                s.printStackTrace();
            }
            return item;
        }
    }
    //Consulta la infomacion almacenada en los archivos
    protected String consultarArchivo(String InfoArchivo) {
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

