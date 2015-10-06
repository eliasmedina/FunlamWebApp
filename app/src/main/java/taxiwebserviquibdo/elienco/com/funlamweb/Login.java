package taxiwebserviquibdo.elienco.com.funlamweb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    EditText usuario= null;
    EditText login= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login= (Button) findViewById(R.id.btnlogin);

        login.setOnClickListener(btnlogin_Click);

   