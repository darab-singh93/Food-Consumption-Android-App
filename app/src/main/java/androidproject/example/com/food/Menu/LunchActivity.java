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

import androidproject.example.com.food.Database.Tables.LunchHelper;
import androidproject.example.com.food.POJO.Lunch;
import androidproject.example.com.food.R;

public class LunchActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LunchActivity.this;
    private LunchHelper lunchHelper;
    private Lunch lunch;
    Button save_lunch,btnShare;
    EditText date_lunch,location_lunch,item1_lunch,item2_lunch,item3_lunch;
    TextView txt_lunch,txt_date,txt_location,txt_item1,txt_item2,txt_item3;;
    private AwesomeValidation awesomeValidation;
    String date, location,item1,item2,item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Lunch");

        txt_lunch=(TextView)findViewById(R.id.txt_lunch);
        date_lunch=(EditText)findViewById(R.id.date_lunch);
        location_lunch=(EditText)findViewById(R.id.location_lunch);
        item1_lunch=(EditText)findViewById(R.id.item1_lunch);
        item2_lunch=(EditText)findViewById(R.id.item2_lunch);
        item3_lunch=(EditText)findViewById(R.id.item3_lunch);
        save_lunch=(Button)findViewById(R.id.save_lunch);

        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_item1=(TextView)findViewById(R.id.txt_item1);
        txt_item2=(TextView)findViewById(R.id.txt_item2);
        txt_item3=(TextView)findViewById(R.id.txt_item3);
        btnShare=(Button)findViewById(R.id.btnShare);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        save_lunch.setOnClickListener(new View.OnClickListener() {
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

    /**
     *  to initialize objects
     */
    private void myObjects() {

        lunchHelper = new LunchHelper(activity);
        lunch= new Lunch();
    }
    private void sendDataToSQLite() {
        if (date.length()==0 || location.length()==0 || item1.length()==0 || item2.length()==0 || item3.length()==0)
        {
            Toast.makeText(activity, "Enter All Fields !", Toast.LENGTH_SHORT).show();
            return;
        }

        lunch.setDate(date_lunch.getText().toString().trim());
        lunch.setLocation(location_lunch.getText().toString().trim());
        lunch.setItem_1(item1_lunch.getText().toString().trim());
        lunch.setItem_2(item2_lunch.getText().toString().trim());
        lunch.setItem_3(item3_lunch.getText().toString().trim());


        lunchHelper.addLunch(lunch);
        Toast.makeText(activity, "Data Added. You can Share Now!!", Toast.LENGTH_SHORT).show();
        emptyInputEditText();
    }



    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate()) {
            sendDataToSQLite();
        }
    }
    private void emptyInputEditText() {
        date_lunch.setText(null);
        location_lunch.setText(null);
        item1_lunch.setText(null);
        item2_lunch.setText(null);
        item3_lunch.setText(null);
    }
    private void shareInfo(){
        date=date_lunch.getText().toString();
        location=location_lunch.getText().toString();
        item1=item1_lunch.getText().toString();
        item2=item2_lunch.getText().toString();
        item3=item3_lunch.getText().toString();


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
