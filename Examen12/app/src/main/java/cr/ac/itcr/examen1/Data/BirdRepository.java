package cr.ac.itcr.examen1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Objects;

import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.Class.User;

/**
 * Created by Jona_RV on 10/04/2016.
 */
public class BirdRepository implements IRepository<Bird>{

    private Connexion connexion;

    public BirdRepository(Context context){

        connexion = new Connexion(context);
    }

    @Override
    public boolean Save(Bird bird) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                ContentValues newData = new ContentValues();
                newData.put("name", bird.getName());
                newData.put("color", bird.getColor());
                newData.put("location", bird.getLocation());
                newData.put("classification", bird.getClassification());

                db.insert("bird", null, newData);

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
    public boolean Delete(Bird bird) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null) {

                db.delete("bird", "id=?", new String[]{String.valueOf(bird.getId())});

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
    public boolean Update(Bird bird) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null) {

                ContentValues updateData = new ContentValues();
                updateData.put("name", bird.getName());
                updateData.put("color", bird.getColor());
                updateData.put("location", bird.getLocation());
                updateData.put("classification", bird.getClassification());

                db.update("bird", updateData, "id=?", new String[]{String.valueOf(bird.getId())});

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
    public ArrayList<Bird> GetAll() {

        ArrayList<Bird> listBird = new ArrayList<Bird>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                Cursor cursor = db.query("bird", new String[]{"id", "name", "color", "location", "classification"}, null,
                        null, null, null, "name desc", null);

                if(cursor.moveToFirst()){
                    do{

                        String id = cursor.getString(0);
                        String name = cursor.getString(1);
                        String color = cursor.getString(2);
                        String location = cursor.getString(3);
                        String clasification = cursor.getString(4);

                        Bird bird = new Bird();
                        bird.setId(Integer.parseInt(id));
                        bird.setName(name);
                        bird.setColor(color);
                        bird.setLocation(location);
                        bird.setClassification(clasification);

                        listBird.add(bird);

                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listBird;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listBird;
    }

    @Override
    public ArrayList<Bird> GetBy(Bird bird) {

        ArrayList<Bird> listBird = new ArrayList<Bird>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                String[] args = new String[]{String.valueOf(bird.getName())};
                Cursor cursor = db.query("bird", new String[]{"name", "color", "location", "classification"}, "name=?",
                        args, null, null, "name desc", null);

                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(0);
                        String color = cursor.getString(1);
                        String location = cursor.getString(2);
                        String classification = cursor.getString(3);

                        Bird birdTemp = new Bird();
                        birdTemp.setName(name);
                        birdTemp.setColor(color);
                        birdTemp.setLocation(location);
                        birdTemp.setClassification(classification);

                        listBird.add(birdTemp);

                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listBird;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listBird;
    }
}
