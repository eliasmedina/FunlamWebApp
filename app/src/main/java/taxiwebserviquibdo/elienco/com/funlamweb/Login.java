package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    View.OnClickListener btnlogin_Click = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
             usuario= (EditText) findViewById(R.id.txtusuario);
            login= (EditText) findViewById(R.id.txtlogin);
          if (usuario.toString()=="prueba"&&login.toString()=="prueba"){
              Intent intlogin = new Intent(Login.this, MainActivity.class);
              startActivityForResult(intlogin, 0);
              finish();
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
}
