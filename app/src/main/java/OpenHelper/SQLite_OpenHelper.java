package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creacion de Tabla
        String  query="create table usuarios(" +
                "_Id integer primary key autoincrement," +
                "Nombre text," +
                "Distrito text," +
                "Correo text," +
                "Password text); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Metodo que permite abrir la base de datos
    public void abrirdb(){
        this.getWritableDatabase();
    }

    //Metodo que permite cerrar la base de datos
    public void cerrardb(){
        this.close();
    }

    //Metodo que permite INSERTAR REGISTROS en tabla usuarios
    public void insertarReg(String nom, String dis, String cor, String pass){
        ContentValues valores = new ContentValues();

        //Almacenando datos en una variable tipo ContentValues
        valores.put("Nombre",nom);
        valores.put("Distrito",dis);
        valores.put("Correo",cor);
        valores.put("Password",pass);
        //grabar datos en base de datos
        this.getWritableDatabase().insert("usuarios",null, valores);
        }

        //METODO QUEPERMITE VALIDAR SI EL USUARIO EXISTE
        public Cursor ConsultarUsuarioPas (String email, String pas)throws SQLException{
            Cursor mcursor=null;
            mcursor=this.getReadableDatabase().query("usuarios",new String[]{"_Id",
                    "Nombre","Distrito","Correo","Passsword"},"Correo like '"+email+"' " +
                    "and Password like '"+pas+"'",null,null,null,null);
            return mcursor;
        }

}
