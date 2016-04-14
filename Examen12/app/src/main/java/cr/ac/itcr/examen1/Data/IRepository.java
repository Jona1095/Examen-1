package cr.ac.itcr.examen1.Data;

import java.util.ArrayList;

/**
 * Created by Jona_RV on 30/03/2016.
 */
public interface IRepository<Object> {

    public boolean Save(Object object);

    public boolean Delete(Object object);

    public boolean Update(Object object);

    public ArrayList<Object> GetAll();

    public ArrayList<Object> GetBy(Object object);
}
