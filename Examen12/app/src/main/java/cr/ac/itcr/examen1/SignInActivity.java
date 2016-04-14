package cr.ac.itcr.examen1;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cr.ac.itcr.examen1.Class.User;
import cr.ac.itcr.examen1.Data.IRepository;
import cr.ac.itcr.examen1.Data.UserRepository;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signIn = (Button) findViewById(R.id.btnSignInActivity);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.txtNameUser);
                EditText telephone = (EditText) findViewById(R.id.txtTelephoneUser);
                EditText username = (EditText) findViewById(R.id.txtUsernameSignIn);
                EditText password = (EditText) findViewById(R.id.txtPasswordSignIn);

                if (name.getText().toString().equals("") && telephone.getText().toString().equals("")
                        && username.getText().toString().equals("") && password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Some fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    IRepository repository = new UserRepository(getApplicationContext());
                    User user = new User();
                    user.setName(name.getText().toString());
                    user.setTelepnone(Integer.valueOf(telephone.getText().toString()));
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    repository.Save(user);

                    name.setText("");
                    telephone.setText("");
                    username.setText("");
                    password.setText("");

                    Toast.makeText(getApplicationContext(), "Successful Insertion",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
