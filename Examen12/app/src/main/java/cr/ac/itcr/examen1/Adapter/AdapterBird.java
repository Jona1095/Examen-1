package cr.ac.itcr.examen1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.R;

/**
 * Created by Efren on 15/3/2016.
 */
public class AdapterBird extends BaseAdapter {

    // Declare Variables
    Context context;

    LayoutInflater inflater;

    protected Activity activity;
    protected ArrayList<Bird> items;
    public AdapterBird(Context context, ArrayList<Bird> items) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {

        return items.size();
    }
    public void clear() {

        items.clear();
    }

    public void addAll(ArrayList<Bird> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listbird_view, parent, false);

        // Locate the TextViews in listview_item.xml
        TextView name = (TextView) itemView.findViewById(R.id.nameBirdView);
        TextView color = (TextView) itemView.findViewById(R.id.colorBirdView);
        TextView classification = (TextView) itemView.findViewById(R.id.classificationBirdView);
        TextView location = (TextView) itemView.findViewById(R.id.locationBirdView);
        TextView idBird = (TextView) itemView.findViewById(R.id.idBird);
        ImageView imgImg = (ImageView) itemView.findViewById(R.id.iconLista);

        // Capture position and set to the TextViews
        name.setText(items.get(position).getName());
        classification.setText(items.get(position).getClassification());
        color.setText(items.get(position).getColor());
        location.setText(items.get(position).getLocation());
        idBird.setText(String.valueOf(items.get(position).getId()));


        return itemView;
    }
}
