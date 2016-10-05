package com.example.root.appday.SendMail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.appday.R;

public class SendMailActivity extends AppCompatActivity {

    private Button sendEmailButton;
    private EditText emailAddress;
    private EditText emailSubject;
    private EditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        emailAddress = (EditText) findViewById(R.id.email);
        emailSubject = (EditText) findViewById(R.id.subject);
        message = (EditText) findViewById(R.id.message);
        sendEmailButton = (Button) findViewById(R.id.send_button);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toEmailAddress = emailAddress.getText().toString();
                String mSubject = emailSubject.getText().toString();
                String mMessage = message.getText().toString();

                Intent emailApp = new Intent(Intent.ACTION_SEND);
                emailApp.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmailAddress});
                emailApp.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                emailApp.putExtra(Intent.EXTRA_TEXT, mMessage);
                emailApp.setType("message/rfc822");
                startActivity(Intent.createChooser(emailApp, "Send Email Via"));
            }
        });
    }
}
