package androidproject.example.com.food.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import androidproject.example.com.food.Database.DatabaseHelper;
import androidproject.example.com.food.R;
import androidproject.example.com.food.UserProfile.User;

public class RegisterActivity extends AppCompatActivity {
    EditText reg_fullname, reg_email, reg_pass, reg_cpass, reg_mobile;
    Button btn_register;
    private DatabaseHelper databaseHelper;
    private User user;
    private final AppCompatActivity activity = RegisterActivity.this;
    private AwesomeValidation awesomeValidation;
    String enteredName,enteredEmail,enteredMobile,enteredPass,enteredCpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registration");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_fullname = (EditText) findViewById(R.id.reg_fullname);
        reg_pass = (EditText) findViewById(R.id.reg_pass);
        reg_cpass = (EditText) findViewById(R.id.reg_cpass);
        reg_mobile = (EditText) findViewById(R.id.reg_mobile);
        btn_register = (Button) findViewById(R.id.btn_register);

        databaseHelper = new DatabaseHelper(activity);
        user = new User();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredEmail=reg_email.getText().toString();
                enteredName=reg_fullname.getText().toString();
                enteredPass=reg_pass.getText().toString();
                enteredCpass=reg_cpass.getText().toString();
                enteredMobile=reg_mobile.getText().toString();

                if (enteredEmail.length()==0||enteredPass.length()==0||enteredCpass.length()==0||
                        enteredName.length()==0||enteredMobile.length()==0)
                {
                    Toast.makeText(activity, "Enter All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()) {
                    reg_email.setError("Enter Valid Email");
                    reg_email.requestFocus();
                    return;
                }
                    postDataToSQLite();
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }

        });
    }

    private void postDataToSQLite() {

        if (!databaseHelper.checkUser(reg_email.getText().toString().trim())) {

            user.setName(reg_fullname.getText().toString().trim());
            user.setEmail(reg_email.getText().toString().trim());
            user.setPassword(reg_pass.getText().toString().trim());

            databaseHelper.addUser(user);

            emptyInputEditText();
            Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();


        } else {
            reg_email.setError("Email Already Exists");
            reg_email.requestFocus();
        }


    }

    private void emptyInputEditText() {
        reg_fullname.setText(null);
        reg_email.setText(null);
        reg_pass.setText(null);
        reg_cpass.setText(null);
        reg_mobile.setText(null);
    }

}

