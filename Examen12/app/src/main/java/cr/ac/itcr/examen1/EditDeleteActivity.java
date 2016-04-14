package cr.ac.itcr.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.Data.BirdRepository;
import cr.ac.itcr.examen1.Data.IRepository;

public class EditDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText nameBirdDeleteEdit =  (EditText) findViewById(R.id.txtNameBirdDeleteEdit);
        final EditText colorBirdDeleteEdit =  (EditText) findViewById(R.id.txtColorDeleteEdit);
        final EditText classificationBirdDeleteEdit =  (EditText) findViewById(R.id.txtClassificationDeleteEdit);
        final EditText locationBirdDeleteEdit =  (EditText) findViewById(R.id.txtLocationDeleteEdit);

        Intent i = getIntent();

        final int idBird = Integer.parseInt(i.getStringExtra("idBird"));

        nameBirdDeleteEdit.setText(i.getStringExtra("nameBird"));
        colorBirdDeleteEdit.setText(i.getStringExtra("colorBird"));
        classificationBirdDeleteEdit.setText(i.getStringExtra("classificationBird"));
        locationBirdDeleteEdit.setText(i.getStringExtra("locationBird"));

        Button btnDeleteBird = (Button) findViewById(R.id.btnDeleteBird);
        Button btnEditBird = (Button) findViewById(R.id.btnEditBird);
        
        btnDeleteBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bird bird = new Bird();

                bird.setName(nameBirdDeleteEdit.getText().toString());
                bird.setColor(colorBirdDeleteEdit.getText().toString());
                bird.setClassification(classificationBirdDeleteEdit.getText().toString());
                bird.setLocation(locationBirdDeleteEdit.getText().toString());
                bird.setId(idBird);

                IRepository repository = new BirdRepository(getApplicationContext());
                repository.Delete(bird);
                finish();
            }
        });

        btnEditBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bird bird = new Bird();

                bird.setName(nameBirdDeleteEdit.getText().toString());
                bird.setColor(colorBirdDeleteEdit.getText().toString());
                bird.setClassification(classificationBirdDeleteEdit.getText().toString());
                bird.setLocation(locationBirdDeleteEdit.getText().toString());
                bird.setId(idBird);

                IRepository repository = new BirdRepository(getApplicationContext());
                repository.Update(bird);
                finish();
            }
        });

    }

}
