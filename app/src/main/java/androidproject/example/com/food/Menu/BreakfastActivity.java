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
import androidproject.example.com.food.Database.Tables.BreakHelper;
import androidproject.example.com.food.POJO.Break;
import androidproject.example.com.food.R;

public class BreakfastActivity extends AppCompatActivity {
    private final AppCompatActivity activity = BreakfastActivity.this;
    private BreakHelper breakHelper;
    private Break break1;
    private AwesomeValidation awesomeValidation;
    Button save_break,btnShare;
    EditText date_break,location_break,item1_break,item2_break,item3_break,edtShareEmail,edtSubject;
    TextView txt_break,txt_date,txt_location,txt_item1,txt_item2,txt_item3;
    String date, location,item1,item2,item3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Breakfast");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        save_break=(Button)findViewById(R.id.save_break);
        txt_break=(TextView)findViewById(R.id.txt_break);
        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_item1=(TextView)findViewById(R.id.txt_item1);
        txt_item2=(TextView)findViewById(R.id.txt_item2);
        txt_item3=(TextView)findViewById(R.id.txt_item3);
        btnShare=(Button)findViewById(R.id.btnShare);

        date_break=(EditText)findViewById(R.id.date_break);
        location_break=(EditText)findViewById(R.id.location_break);
        item1_break=(EditText)findViewById(R.id.item1_break);
        item2_break=(EditText)findViewById(R.id.item2_break);
        item3_break=(EditText)findViewById(R.id.item3_break);
        save_break.setOnClickListener(new View.OnClickListener() {
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
    //Initialize Objects

    private void myObjects() {

        breakHelper = new BreakHelper(activity);
        break1 = new Break();
    }
    private void sendDataToSQLite() {
        if (date.length()==0 || location.length()==0 || item1.length()==0 || item2.length()==0 || item3.length()==0)
        {
            Toast.makeText(activity, "Enter All Fields !", Toast.LENGTH_SHORT).show();
            return;
        }
            break1.setDate(date_break.getText().toString().trim());
            break1.setLocation(location_break.getText().toString().trim());
            break1.setItem_1(item1_break.getText().toString().trim());
            break1.setItem_2(item2_break.getText().toString().trim());
            break1.setItem_3(item3_break.getText().toString().trim());


            breakHelper.addBeneficiary(break1);
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
        date_break.setText(null);
        location_break.setText(null);
        item1_break.setText(null);
        item2_break.setText(null);
        item3_break.setText(null);
    }

    private void shareInfo(){
        date=date_break.getText().toString();
        location=location_break.getText().toString();
        item1=item1_break.getText().toString();
        item2=item2_break.getText().toString();
        item3=item3_break.getText().toString();

        txt_date.setTextKeepState("Date: "+date);
        txt_location.setTextKeepState("Location: "+location);
        txt_item1.setTextKeepState("Item1: "+item1);
        txt_item2.setTextKeepState("Item2: "+item2);
        txt_item3.setTextKeepState("Item3: "+item3);
    }
    private void sendEmail()
    {

        String msg1=txt_date.getText().toString();
        String msg2=txt_location.getText().toString();
        String msg3=txt_item1.getText().toString();
        String msg4=txt_item2.getText().toString();
        String msg5=txt_item3.getText().toString();


        String message = msg1+"\n\n"+msg2+"\n\n"+msg3+"\n\n"+msg4+"\n\n"+msg5;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email or Google Drive"));

    }
}
