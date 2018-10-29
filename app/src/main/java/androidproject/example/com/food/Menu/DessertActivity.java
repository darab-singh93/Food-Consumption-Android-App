package androidproject.example.com.food.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidproject.example.com.food.Database.Tables.DessertHelper;
import androidproject.example.com.food.POJO.Dessert;
import androidproject.example.com.food.R;

public class DessertActivity extends AppCompatActivity {
    private final AppCompatActivity activity = DessertActivity.this;
    Button save_dessert,btnShare;
    EditText date_dessert,location_dessert,item1_dessert,item2_dessert,item3_dessert,edtShareEmail,edtSubject;
    TextView txt_dessert,txt_date,txt_location,txt_item1,txt_item2,txt_item3;
    private DessertHelper dessertHelper;
    private Dessert dessert;
    String date, location,item1,item2,item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Dessert");


        txt_dessert=(TextView)findViewById(R.id.txt_dessert);
        date_dessert=(EditText)findViewById(R.id.date_dessert);
        location_dessert=(EditText)findViewById(R.id.location_dessert);
        item1_dessert=(EditText)findViewById(R.id.item1_dessert);
        item2_dessert=(EditText)findViewById(R.id.item2_dessert);
        item3_dessert=(EditText)findViewById(R.id.item3_dessert);
        save_dessert=(Button)findViewById(R.id.save_dessert);

        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_item1=(TextView)findViewById(R.id.txt_item1);
        txt_item2=(TextView)findViewById(R.id.txt_item2);
        txt_item3=(TextView)findViewById(R.id.txt_item3);
        btnShare=(Button)findViewById(R.id.btnShare);

        save_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareInfo();
                postDataToSQLite();

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        initObjects();
    }


   // initialize objects

    private void initObjects() {

         dessertHelper= new DessertHelper(activity);
        dessert = new Dessert();
    }
    private void postDataToSQLite() {
        if (date.length()==0 || location.length()==0 || item1.length()==0 || item2.length()==0 || item3.length()==0)
        {
            Toast.makeText(activity, "Enter All Fields !", Toast.LENGTH_SHORT).show();
            return;
        }

        dessert.setDate(date_dessert.getText().toString().trim());
        dessert.setLocation(location_dessert.getText().toString().trim());
        dessert.setItem_1(item1_dessert.getText().toString().trim());
        dessert.setItem_2(item2_dessert.getText().toString().trim());
        dessert.setItem_3(item3_dessert.getText().toString().trim());


        dessertHelper.addDessert(dessert);
        Toast.makeText(activity, "Data Added. You can Share Now!!", Toast.LENGTH_SHORT).show();
        emptyInputEditText();
    }
    private void emptyInputEditText() {
        date_dessert.setText(null);
        location_dessert.setText(null);
        item1_dessert.setText(null);
        item2_dessert.setText(null);
        item3_dessert.setText(null);
    }
    private void shareInfo(){
        date=date_dessert.getText().toString();
        location=location_dessert.getText().toString();
        item1=item1_dessert.getText().toString();
        item2=item2_dessert.getText().toString();
        item3=item3_dessert.getText().toString();


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