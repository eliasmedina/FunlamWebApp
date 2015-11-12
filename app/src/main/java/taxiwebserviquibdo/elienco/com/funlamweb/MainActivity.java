package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    Intent callIntent;
    private WebView mWebView;
    private Intent intmenu= null;
    String nomarchivo = "login";
    public void btnperfil(View view){
        String archivoinformacion = consultarArchivo("login");
        String informacion[] = archivoinformacion.split(",");
        if(informacion[0].equals("OK")) {
            Intent inten = new Intent(MainActivity.this, perfil.class);
            startActivity(inten);
            finish();
        }else {
            Intent inten = new Intent(MainActivity.this, Login.class);
            startActivity(inten);
            finish();
        }
    }
    public void btnlogin(View view){
        Intent inten= new Intent(MainActivity.this,Login.class);
        startActivity(inten);
        finish();
    }
    public void btnsalir(View view){
        String archivoinformacion = consultarArchivo("login");
        String info[] = archivoinformacion.split("/");
        String conv="";
        File tarjeta = Environment.getExternalStorageDirectory();
        try {
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(conv);
            osw.flush();
            osw.close();
            File file2 = new File(tarjeta.getAbsolutePath(), info[1]);
            OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream(file2));
            osw2.write(conv);
            osw2.flush();
            osw2.close();
            Intent mainIntent = new Intent().setClass(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }catch (Exception e){

        }
    }
    public void btnhistorial(View view){
        Intent inten= new Intent(MainActivity.this,horario.class);
        startActivity(inten);
        finish();
    }
    public void btnllamar(View view){
        call();
    }

    public void btnregistro(View view){
        Intent inten= new Intent(MainActivity.this,registro.class);
        startActivity(inten);
        finish();
    }
    public void btnregistrohi(View view){
        Intent inten= new Intent(MainActivity.this,registrohorario.class);
        startActivity(inten);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webView);

        // Activo JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Cargamos la url que necesitamos
        mWebView.loadUrl("http://www.funlam.edu.co/");


        String archivoinformacion=consultarArchivo("login");
        String informacion[] =  archivoinformacion.split(",");
        try{
            if(informacion[0].equals("OK")){
                com.getbase.floatingactionbutton.FloatingActionButton registrarse = (FloatingActionButton) findViewById(R.id.btnregistro);
                registrarse.setVisibility(View.GONE);

                com.getbase.floatingactionbutton.FloatingActionButton login = (FloatingActionButton) findViewById(R.id.btnlogin);
                login.setVisibility(View.GONE);

                String infor[] = informacion[1].split("/");
                String corr=infor[3];
                if(corr.equals("a@a.com")) {
                    Toast.makeText(MainActivity.this, "ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                }else{
                    com.getbase.floatingactionbutton.FloatingActionButton registrarhorario = (FloatingActionButton) findViewById(R.id.btnregistrohi);
                    registrarhorario.setVisibility(View.GONE);
                }
            }
            else{
                com.getbase.floatingactionbutton.FloatingActionButton perfil = (FloatingActionButton) findViewById(R.id.btnperfil);
                perfil.setVisibility(View.GONE);
                com.getbase.floatingactionbutton.FloatingActionButton salir = (FloatingActionButton) findViewById(R.id.btnsalir);
                salir.setVisibility(View.GONE);
                com.getbase.floatingactionbutton.FloatingActionButton registrarhorario = (FloatingActionButton) findViewById(R.id.btnregistrohi);
                registrarhorario.setVisibility(View.GONE);
                com.getbase.floatingactionbutton.FloatingActionButton horario = (FloatingActionButton) findViewById(R.id.btnhistoria);
                horario.setVisibility(View.GONE);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //llamada
    private void call() {
        try {
            callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0344487666"));
            startActivity(callIntent);
            //finish();
        } catch (ActivityNotFoundException activityException) {
            Log.e("dialing-example", "Call failed", activityException);
        }
    }
    //boton atras
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.abc_dialog_material_background_dark)
                    .setTitle("Funlam App")
                    .setMessage("Desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)//sin listener
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            //Salir
                            MainActivity.this.finish();
                        }
                    }).show();
            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }
        public boolean onCreateOptionsMenu(Menu item) {

            MenuItem entrar= item.findItem(R.id.btnentrar);
            entrar.setVisible(true);
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


    //item menu
    public boolean onPrepareOptionsMenu(Menu item){
      //  String archivoinformacion=consultarArchivo("login");
       // String informacion[] =  archivoinformacion.split(",");
        try{
          //  if(informacion[0].equals("OK")){

                MenuItem entrar= item.findItem(R.id.btnentrar);
                entrar.setVisible(false);
            //}
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.btnentrar:
                intmenu = new Intent(MainActivity.this, Login.class);
                startActivityForResult(intmenu, 0);
                //finish();
                break;

        }
        return true;
    }

}