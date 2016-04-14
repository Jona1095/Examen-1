package cr.ac.itcr.examen1.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jona_RV on 10/04/2016.
 */
public class Connexion extends SQLiteOpenHelper {

    private static final int VERSION_BDD = 1;
    private static final String NAME_BDD = "examen";

    public Connexion(Context context) {
        super(context, NAME_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();

            String sqlCreateUser = "CREATE TABLE IF NOT EXISTS user" +
                    "(name CHAR (50), telephone INTEGER, username CHAR(20) PRIMARY KEY, password CHAR(20))";

            sql.append(sqlCreateUser);
            db.execSQL(sql.toString());


            StringBuilder sql1 = new StringBuilder();

            String sqlCreateBird = "CREATE TABLE IF NOT EXISTS bird" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR (30), color CHAR (20), location CHAR(50), classification CHAR(50))";

            sql1.append(sqlCreateBird);
            db.execSQL(sql1.toString());

        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{

            StringBuilder sql = new StringBuilder();
            StringBuilder sql1 = new StringBuilder();

            for(int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
                int nextVersion = indiceVersion + 1;
                switch (nextVersion){

                    case 1:
                        String sqlDropUser = "DROP TABLE IF EXISTS user";
                        sql.append(sqlDropUser);
                        String sqlDropBird = "DROP TABLE IF EXISTS bird";
                        sql1.append(sqlDropBird);
                        break;

                    case 2:

                        break;

                    case 3:

                        break;
                }
            }
            db.execSQL(sql.toString());
            db.execSQL(sql1.toString());
        }
        catch (Exception error){

            Log.d("error", error.getMessage());
        }
    }
}
