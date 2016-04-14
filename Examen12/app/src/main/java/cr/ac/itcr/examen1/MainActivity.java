package cr.ac.itcr.examen1;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.Class.User;
import cr.ac.itcr.examen1.Data.BirdRepository;
import cr.ac.itcr.examen1.Data.IRepository;
import cr.ac.itcr.examen1.Data.UserRepository;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSignIn(View view) {
        Intent i = new Intent(getApplicationContext(),SignInActivity.class);;
        startActivity(i);
    }

    public void onClickLogin(View view) {


        EditText username = (EditText) findViewById(R.id.txtUsername);
        EditText password = (EditText) findViewById(R.id.txtPassword);

        ArrayList<User> listUser;
        IRepository allUser = new UserRepository(getBaseContext().getApplicationContext());
        listUser = allUser.GetAll();

        if(username.getText().toString().equals("") && password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Some fields are empty",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            for(int i = 0; i < listUser.size(); i++)
            {
                if(listUser.get(i).getUsername().equals(username.getText().toString()) &&
                        listUser.get(i).getPassword().equals(password.getText().toString())) {
                    Intent x = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(x);
                    this.finish();
                    return;
                }
            }
            Toast.makeText(getApplicationContext(), "Invalid User",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
