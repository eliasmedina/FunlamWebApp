package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Login extends Activity {
    EditText usuario= null;
    EditText login= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login= (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(btnlogin_Click);

    }
    //boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(Login.this, MainActivity.class);
            startActivity(nuevaAtivity);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void btnregistro(View view){
        Intent inten= new Intent(Login.this,registro.class);
        startActivity(inten);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    View.OnClickListener btnlogin_Click = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
             usuario = (EditText) findViewById(R.id.edtusuario);
            login = (EditText) findViewById(R.id.contrasena);
            String a=usuario.getText().toString();
            String b=login.getText().toString();
            if (a.equals("")||b.equals("")) {
                Toast.makeText(Login.this, "Campos incompletos", Toast.LENGTH_SHORT).show();
            }else {
                if(((usuario.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]")&&usuario.length()> 0)||(usuario.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]")&&usuario.length()> 0)||(usuario.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.co")&&usuario.length()> 0)||(usuario.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.co")&&usuario.length()> 0)||(usuario.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.es")&&usuario.length()> 0) ))
                    {
                        String archivoinformacion = consultarArchivo("FunlamWeb");
                        String informacion[] = archivoinformacion.split("/");
                        int reg=informacion.length;
                        for (int i = 1; i < reg; i++) {
                            String infor[] = informacion[i].split(",");
                            int ger= infor.length;
                            for(int j= 0; j<ger;j++){
                                if(infor[1].equals(a)){
                                    if(infor[2].equals((b))){
                                        //guarda archivo en microsd
                                        try {
                                            String nomarchivo = "login";
                                            File tarjeta = Environment.getExternalStorageDirectory();
                                            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
                                            OutputStreamWriter osw = new OutputStreamWriter(
                                                    new FileOutputStream(file));
                                            osw.write("OK,"+infor[0]+"/"+infor[1]+"/"+infor[2]+"/"+infor[3]+"/"+infor[4]+"/"+infor[5]+"/"+infor[6]);
                                            osw.flush();
                                            osw.close();
                                            j=ger+1;
                                            i=reg+1;
                                            Toast.makeText(Login.this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                                            Intent inten = new Intent(Login.this, MainActivity.class);
                                            startActivity(inten);
                                            finish();
                                        }catch (Exception lol){
                                            lol.printStackTrace();
                                        }
                                    }
                                }else{
                                    Toast.makeText(Login.this, "Usuario no existe o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }

                }else {
                    Toast.makeText(Login.this, "valide su Correo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
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
