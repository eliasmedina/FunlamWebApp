package taxiwebserviquibdo.elienco.com.funlamweb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class registrohorario extends Activity {
    String a=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrohorario);
    }
    public void guardar(View view){
        try {
        //String archivoinformacion = consultarArchivo("login");
       // String info[] = archivoinformacion.split("/");
      //  a=info[1];
        EditText Idclase= (EditText) findViewById(R.id.txtidclase);
        EditText Nomclase= (EditText) findViewById(R.id.txtnombreclase);
        EditText Bloque= (EditText) findViewById(R.id.txtbloque);
        EditText Aula= (EditText) findViewById(R.id.txtaula);
        EditText Fechaini= (EditText) findViewById(R.id.txtfechainicio);
        EditText Fechafin= (EditText) findViewById(R.id.txtfechafinal);
        EditText Hora= (EditText) findViewById(R.id.txthora);
        EditText Cedula= (EditText) findViewById(R.id.txtcedula);
        String idclase=Idclase.getText().toString();
        String nomclase=Nomclase.getText().toString();
        String bloque=Bloque.getText().toString();
        String aula=Aula.getText().toString();
        String fechainicio=Fechaini.getText().toString();
        String fechafin=Fechafin.getText().toString();
        String hora=Hora.getText().toString();
        String cedula=Cedula.getText().toString();

            if(idclase.equals("")&&cedula.equals("")&&bloque.equals("")&&aula.equals("")&&nomclase.equals("")&&fechainicio.equals("")&&fechafin.equals("")&&hora.equals("")) {
                Toast.makeText(registrohorario.this, "Verifique los Campos", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    AdminSQLiteOpen2 dbHelper = new AdminSQLiteOpen2 (getBaseContext());
                   SQLiteDatabase db = dbHelper.getWritableDatabase();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                try {
                    AdminSQLiteOpen2 admin = new AdminSQLiteOpen2(registrohorario.this,"Horariofunlamh", null, 2);
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    String idclases =idclase;
                    String nombreclases =nomclase;
                    String bloques =bloque;
                    String aulas =aula;
                    String fechainicios =fechainicio;
                    String horas =hora;
                    String fechafinal =fechafin;
                    String cedulas =cedula;
                    ContentValues registro = new ContentValues();
                    registro.put("idclase",idclases );
                    registro.put("cedula", cedulas);
                    registro.put("nombreclase", nombreclases);
                    registro.put("bloque", bloques);
                    registro.put("aula", aulas);
                    registro.put("fechainicio", fechainicios);
                    registro.put("hora", horas);
                    registro.put("fechafinal", fechafinal);
                    bd.insert("Horariofunlamh", null, registro);
                    bd.close();
                    Toast.makeText(registrohorario.this, "Se guardo en historial",
                            Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(registrohorario.this, MainActivity.class);
            startActivity(nuevaAtivity);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
}
