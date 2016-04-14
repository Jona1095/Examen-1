package cr.ac.itcr.examen1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.ArrayList;

import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.Class.User;

/**
 * Created by Jona_RV on 30/03/2016.
 */
public class UserRepository implements IRepository<User>{

    private Connexion connexion;

    public UserRepository(Context context){

        connexion = new Connexion(context);
    }

    @Override
    public boolean Save(User user) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                ContentValues newData = new ContentValues();
                newData.put("name", user.getName());
                newData.put("telephone", user.getTelepnone());
                newData.put("username", user.getUsername());
                newData.put("password", user.getPassword());

                db.insert("user", null, newData);

                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return true;
    }

    @Override
    public boolean Update(User user) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null) {

                ContentValues updateData = new ContentValues();
                updateData.put("name", user.getName());
                updateData.put("telephone", user.getTelepnone());
                updateData.put("username", user.getUsername());
                updateData.put("password", user.getPassword());

                db.update("user", updateData, "username=?", new String[]{String.valueOf(user.getUsername())});

                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return true;
    }

    @Override
    public boolean Delete(User user) {

        return true;
    }

    @Override
    public ArrayList<User> GetAll() {

        ArrayList<User> listUser = new ArrayList<User>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                Cursor cursor = db.query("user", new String[]{"name", "telephone", "username", "password"}, null,
                        null, null, null, "name desc", null);

                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(0);
                        int telephone = cursor.getInt(1);
                        String username = cursor.getString(2);
                        String password = cursor.getString(3);

                        User user = new User();
                        user.setName(name);
                        user.setTelepnone(telephone);
                        user.setUsername(username);
                        user.setPassword(password);

                        listUser.add(user);

                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listUser;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listUser;
    }

    @Override
    public ArrayList<User> GetBy(User user) {

        ArrayList<User> listUser = new ArrayList<User>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                String[] args = new String[]{String.valueOf(user.getUsername())};
                Cursor cursor = db.query("user", new String[]{"name", "telephone", "username", "password"}, "username=?",
                        args, null, null, "name desc", null);

                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(0);
                        int telephone = cursor.getInt(1);
                        String username = cursor.getString(2);
                        String password = cursor.getString(3);

                        User userTemp = new User();
                        userTemp.setName(name);
                        userTemp.setTelepnone(telephone);
                        userTemp.setUsername(username);
                        userTemp.setPassword(password);

                        listUser.add(userTemp);

                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listUser;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listUser;
    }
}
