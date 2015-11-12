package taxiwebserviquibdo.elienco.com.funlamweb;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AdminSQLiteOpen2 extends SQLiteOpenHelper {
    private static int version = 2;
    private static String name = "Horariofunlamh" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public AdminSQLiteOpen2(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE Horariofunlamh(" +
                " idclase TEXT," +
                " cedula INTEGER," +
                " nombreclase TEXT, " +
                " bloque TEXT, " +
                " aula TEXT," +
                " fechainicio TEXT," +
                " hora TEXT," +
                " fechafinal TEXT)");
        db.execSQL("CREATE UNIQUE INDEX idclase ON Horariofunlamh (idclase ASC)");//
        Log.i(this.getClass().toString(), "Tabla Horariofunlamh creada");
        Log.i(this.getClass().toString(), "Base de datos creada"); }
    public AdminSQLiteOpen2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
}