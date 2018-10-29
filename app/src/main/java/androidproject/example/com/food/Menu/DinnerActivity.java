package androidproject.example.com.food.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import androidproject.example.com.food.Database.Tables.DinnerHelper;
import androidproject.example.com.food.POJO.Dinner;
import androidproject.example.com.food.R;

public class DinnerActivity extends AppCompatActivity {
    private final AppCompatActivity activity = DinnerActivity.this;
    private DinnerHelper dinnerHelper;
    private Dinner dinner;
    private AwesomeValidation awesomeValidation;
    Button save_dinner,btnShare;
    EditText date_dinner,location_dinner,item1_dinner,item2_dinner,item3_dinner;
    TextView txt_dinner;
    TextView txt_date,txt_location,txt_item1,txt_item2,txt_item3;
    String date, location,item1,item2,item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Dinner");
        save_dinner=(Button)findViewById(R.id.save_dinner);
        txt_dinner=(TextView)findViewById(R.id.txt_dinner);
        date_dinner=(EditText)findViewById(R.id.date_dinner);
        location_dinner=(EditText)findViewById(R.id.location_dinner);
        item1_dinner=(EditText)findViewById(R.id.item1_dinner);
        item2_dinner=(EditText)findViewById(R.id.item2_dinner);
        item3_dinner=(EditText)findViewById(R.id.item3_dinner);

        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_item1=(TextView)findViewById(R.id.txt_item1);
        txt_item2=(TextView)findViewById(R.id.txt_item2);
        txt_item3=(TextView)findViewById(R.id.txt_item3);
        btnShare=(Button)findViewById(R.id.btnShare);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        save_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareInfo();
                submitForm();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        myObjects();

    }
    //initialize objects
    private void myObjects() {

        dinnerHelper = new DinnerHelper(activity);
        dinner = new Dinner();
    }
    private void sendDataToSQLite() {
        if (date.length()==0 || location.length()==0 || item1.length()==0 || item2.length()==0 || item3.length()==0)
        {
            Toast.makeText(activity, "Enter All Fields !", Toast.LENGTH_SHORT).show();
            return;
        }

        dinner.setDate(date_dinner.getText().toString().trim());
        dinner.setLocation(location_dinner.getText().toString().trim());
        dinner.setItem_1(item1_dinner.getText().toString().trim());
        dinner.setItem_2(item2_dinner.getText().toString().trim());
        dinner.setItem_3(item3_dinner.getText().toString().trim());


        dinnerHelper.addDinner(dinner);
        Toast.makeText(activity, "Data Added. You can Share Now!!", Toast.LENGTH_SHORT).show();
        emptyInputEditText();
    }
    private void emptyInputEditText() {
        date_dinner.setText(null);
        location_dinner.setText(null);
        item1_dinner.setText(null);
        item2_dinner.setText(null);
        item3_dinner.setText(null);
    }


    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate()) {
            sendDataToSQLite();
        }
    }
    private void shareInfo(){
        date=date_dinner.getText().toString();
        location=location_dinner.getText().toString();
        item1=item1_dinner.getText().toString();
        item2=item2_dinner.getText().toString();
        item3=item3_dinner.getText().toString();


        txt_date.setTextKeepState("Date: "+date);
        txt_location.setTextKeepState("Location: "+location);
        txt_item1.setTextKeepState("Item1: "+item1);
        txt_item2.setTextKeepState("Item2: "+item2);
        txt_item3.setTextKeepState("Item3: "+item3);
    }
    private void sendEmail() {
        /**
         String shareEmail = edtShareEmail.getText().toString();
         String[] recipients = shareEmail.split(",");

         String subject = edtSubject.getText().toString();
         **/

        String msg1 = txt_date.getText().toString();
        String msg2 = txt_location.getText().toString();
        String msg3 = txt_item1.getText().toString();
        String msg4 = txt_item2.getText().toString();
        String msg5 = txt_item3.getText().toString();


        String message = msg1 + "\n\n" + msg2 + "\n\n" + msg3 + "\n\n" + msg4 + "\n\n" + msg5;

        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email or Google Drive"));
    }

}
