package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
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
            Toast.makeText(registro.this, "Verifique los Campos", Toast.LENGTH_SHORT).show();
        }else{
            try {
                AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper (getBaseContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(registro.this,"Horariofunlam", null, 2);
                SQLiteDatabase bd = admin.getWritableDatabase();
                int cedulas =Integer.parseInt(Cedula);
                String nombres =Usuario;
                String direccions =Direccion;
                String correos =Correo;
                String contrasenas =Contrasena;
                String telefonos =Celular;
                ContentValues registro = new ContentValues();
                registro.put("cedula",cedulas );
                registro.put("nombre", nombres);
                registro.put("Direccion", direccions);
                registro.put("correo", correos);
                registro.put("contrasena", contrasenas);
                registro.put("telefono", telefonos);
                bd.insert("Horariofunlam", null, registro);
                bd.close();
                Toast.makeText(registro.this, "Se guardo en historial",
                        Toast.LENGTH_SHORT).show();
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
    //boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(registro.this, MainActivity.class);
            startActivity(nuevaAtivity);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
