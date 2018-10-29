package androidproject.example.com.food.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidproject.example.com.food.Database.Tables.SnackHelper;
import androidproject.example.com.food.POJO.Snack;
import androidproject.example.com.food.R;

public class SnackActivity extends AppCompatActivity {
    private final AppCompatActivity activity = SnackActivity.this;
    private SnackHelper snackHelper;
    private Snack snack;
    Button save_snack,btnShare;
    EditText date_snack,location_snack,item1_snack,item2_snack,item3_snack;
    TextView txt_snack;
    TextView txt_date,txt_location,txt_item1,txt_item2,txt_item3;
    String date, location,item1,item2,item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Snack");

        txt_snack=(TextView)findViewById(R.id.txt_snack);
        date_snack=(EditText)findViewById(R.id.date_snack);
        location_snack=(EditText)findViewById(R.id.location_snack);
        item1_snack=(EditText)findViewById(R.id.item1_snack);
        item2_snack=(EditText)findViewById(R.id.item2_snack);
        item3_snack=(EditText)findViewById(R.id.item3_snack);
        save_snack=(Button)findViewById(R.id.save_snack);

        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_item1=(TextView)findViewById(R.id.txt_item1);
        txt_item2=(TextView)findViewById(R.id.txt_item2);
        txt_item3=(TextView)findViewById(R.id.txt_item3);
        btnShare=(Button)findViewById(R.id.btnShare);


        save_snack.setOnClickListener(new View.OnClickListener() {
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

    private void initObjects() {

        snackHelper = new SnackHelper(activity);
        snack = new Snack();
    }
    private void postDataToSQLite() {
        if (date.length()==0 || location.length()==0 || item1.length()==0 || item2.length()==0 || item3.length()==0)
        {
            Toast.makeText(activity, "Enter All Fields !", Toast.LENGTH_SHORT).show();
            return;
        }

        snack.setDate(date_snack.getText().toString().trim());
        snack.setLocation(location_snack.getText().toString().trim());
        snack.setItem_1(item1_snack.getText().toString().trim());
        snack.setItem_2(item2_snack.getText().toString().trim());
        snack.setItem_3(item3_snack.getText().toString().trim());


        snackHelper.addSnack(snack);
        Toast.makeText(activity, "Data Added. You can Share Now!!", Toast.LENGTH_SHORT).show();
        emptyInputEditText();
    }
    private void emptyInputEditText() {
        date_snack.setText(null);
        location_snack.setText(null);
        item1_snack.setText(null);
        item2_snack.setText(null);
        item3_snack.setText(null);
    }
    private void shareInfo(){
        date=date_snack.getText().toString();
        location=location_snack.getText().toString();
        item1=item1_snack.getText().toString();
        item2=item2_snack.getText().toString();
        item3=item3_snack.getText().toString();


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
