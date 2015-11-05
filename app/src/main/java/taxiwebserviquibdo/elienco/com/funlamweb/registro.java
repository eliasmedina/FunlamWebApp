package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
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


public class registro extends Activity {
    String nomarchivo = "FunlamWeb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }
    public void btnregistrar(View view){
        EditText correo = (EditText) findViewById(R.id.edtusuario);
        EditText usuario = (EditText) findViewById(R.id.edtnombre);
        EditText contrasena = (EditText) findViewById(R.id.edtcontrasena);
        EditText celular = (EditText) findViewById(R.id.edtcelular);
        EditText cedula = (EditText) findViewById(R.id.edtcedula);
        EditText direccion = (EditText) findViewById(R.id.edtcasa);
        String Correo= correo.getText().toString();
        String Usuario= usuario.getText().toString();
        String Contrasena= contrasena.getText().toString();
        String Celular= celular.getText().toString();
        String Cedula= cedula.getText().toString();
        String Direccion= direccion.getText().toString();
        if(((correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]")&&correo.length()> 0)||(correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]")&&correo.length()> 0)||(correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.co")&&correo.length()> 0)||(correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.co")&&correo.length()> 0)||(correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.es")&&correo.length()> 0) ))
        {
        if(Contrasena.equals("")&&Celular.equals("")&&Cedula.equals("")&&Direccion.equals("")&&Correo.equals("")) {
            Toast.makeText(registro.this, "Verifique los Datos", Toast.LENGTH_SHORT).show();
        }else{
            try {
                String archivoinformacion = consultarArchivo("FunlamWeb");
                String informacion[] = archivoinformacion.split("/");
                int ultreg=informacion.length;
                if (ultreg == 1){
                    try {
                        File tarjeta = Environment.getExternalStorageDirectory();
                        File filedir = new File(tarjeta.getAbsolutePath(), nomarchivo);
                        OutputStreamWriter oswdf = new OutputStreamWriter(new FileOutputStream(filedir));
                        oswdf.write("OK/"+"1,"+Correo+","+Contrasena+","+Usuario+","+Celular+","+Cedula+","+Direccion+"/");
                        oswdf.flush();
                        oswdf.close();
                        Toast.makeText(registro.this, "usuario agregado Correctamente", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int contador =0;
                    int reg=informacion.length;
                    for (int i = 1; i < reg; i++) {
                        String infor[] = informacion[i].split(",");
                        int ger= infor.length;
                    for(int j= 0; j<ger;j++){
                        if(infor[1].equals(Correo)){
                           contador=1;
                        }
                    }}
                    if(contador==0){
                    int a = 0;
                    String conv = "";
                    for (a = 0; a < ultreg; a++) {
                        conv += informacion[a] + "/";
                    }
                    try {
                        conv += (a) + "," + Correo + "," + Contrasena + "," + Usuario + "," + Celular + "," + Cedula + "," + Direccion + "/";
                        File tarjeta = Environment.getExternalStorageDirectory();
                        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
                        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
                        osw.write(conv);
                        osw.flush();
                        osw.close();
                        Toast.makeText(registro.this, "Direccion agregada Correctamente", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   }else{
                        Toast.makeText(registro.this, "Usuario existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            Intent inten = new Intent(registro.this, Login.class);
            startActivity(inten);
            finish();
        }}else {
            Toast.makeText(registro.this, "valide su Correo", Toast.LENGTH_SHORT).show();
        }
    }
    public void btninicio(View view){
        Intent inten= new Intent(registro.this,Login.class);
        startActivity(inten);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

               