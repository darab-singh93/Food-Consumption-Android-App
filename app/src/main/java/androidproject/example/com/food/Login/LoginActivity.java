package androidproject.example.com.food.Login;

import android.content.Intent;
import android.database.Cursor;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import androidproject.example.com.food.Database.DatabaseHelper;
import androidproject.example.com.food.MainActivity;
import androidproject.example.com.food.R;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText login_email, login_pass;
    Button btn_login, btn_signup;
    TextView txt_notaccount;
    private DatabaseHelper databaseHelper;
    private AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        txt_notaccount = (TextView) findViewById(R.id.txt_notaccount);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        login_email = (EditText) findViewById(R.id.login_email);
        login_pass = (EditText) findViewById(R.id.login_pass);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getEditableText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if(login_email.getText().toString().trim().length()==0){
                    if (!email.matches(emailPattern)) {
                        login_email.setError("Enter Valid Email");
                        login_email.requestFocus();
                    }
                }
                else if(login_pass.getText().toString().trim().length()==0){
                    login_pass.setError("Enter Valid Password");
                    login_pass.requestFocus();
                }
                else {
                    verifyFromSQLite();

                }
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


        initObjects();

    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);

    }


    private void verifyFromSQLite() {
        if (databaseHelper.checkUser(login_email.getText().toString().trim()
                , login_pass.getText().toString().trim())) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            emptyInputEditText();
            finish();
        }
    }
    private void emptyInputEditText() {
        login_email.setText(null);
        login_pass.setText(null);
    }
}