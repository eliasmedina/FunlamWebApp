package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Login extends Activity {
    String[] Arrayhistorial;
    ListView listah;
    lihistorial[] datosh = null;
 int r=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void btnregistro(View view){
        Intent inten= new Intent(Login.this,registro.class);
        startActivity(inten);
        finish();
    }
    public void btninicio(View view){
        String[] Arrayhistorial = new String[0];
        Activity context;
        try {
            EditText correoe = (EditText) findViewById(R.id.edtusuario);
            String correo = correoe.getText().toString();
            EditText contrasenae = (EditText) findViewById(R.id.contrasena);
            String contrasena = contrasenae.getText().toString();

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Horariofunlam", null, 2);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery("select * from Horariofunlam where correo = '" + correo + "'", null);
            ArrayList<taxiwebserviquibdo.elienco.com.funlamweb.liusuario> historiaconsulta = new ArrayList<taxiwebserviquibdo.elienco.com.funlamweb.liusuario>();
            Arrayhistorial = new String[fila.getCount()];
            int registros = fila.getCount();

            if (fila.moveToFirst()) {
                for (int i = 0; i < registros; i++) {
                    String var1 = fila.getString(0);
                    String var2 = fila.getString(1);
                    String var3 = fila.getString(2);
                    String var4 = fila.getString(3);
                    String var5 = fila.getString(4);
                    String var6 = fila.getString(5);
                    Arrayhistorial[0] =var1+","+var2+","+var3+","+var4+","+var5+","+var6+",";
                }
            }

            String usuario[] = Arrayhistorial[0].split(",");
            if (usuario[4].equals(contrasena)) {
                r = 1;
            }

            if (r==1) {
                String nomarchivo = "login";
                File tarjeta = Environment.getExternalStorageDirectory();
                File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file));
                osw.write("OK"+","+usuario[0]+"/"+usuario[1]+"/"+usuario[2]+"/"+usuario[3]+"/"+usuario[4]+"/"+usuario[5]);
                osw.flush();
                osw.close();
                Toast.makeText(Login.this, "Los datos fueron grabados correctamente",Toast.LENGTH_SHORT).show();
                Intent inten= new Intent(Login.this,MainActivity.class);
                startActivity(inten);
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
