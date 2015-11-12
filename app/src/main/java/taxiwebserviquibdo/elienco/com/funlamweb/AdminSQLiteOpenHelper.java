package taxiwebserviquibdo.elienco.com.funlamweb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static int version = 2;
    private static String name = "Horariofunlam" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public AdminSQLiteOpenHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE Horariofunlam(" +
                " cedula INTEGER PRIMARY KEY," +
                " nombre TEXT, " +
                " Direccion TEXT, " +
                " correo TEXT," +
                " contrasena TEXT," +
                " telefono TEXT)");
        db.execSQL("CREATE UNIQUE INDEX cedula ON Horariofunlam (cedula ASC)");//
        Log.i(this.getClass().toString(), "Tabla Horariofunlam creada");
        Log.i(this.getClass().toString(), "Base de datos creada"); }
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
}