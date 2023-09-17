package me.kyren223.samplebagrutapp;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        checkReceivePerm();
        checkSendPerm();

        Button sendButton = findViewById(R.id.smsSendButton);
        sendButton.setOnClickListener(this::sendMessage);
    }

    private void checkReceivePerm() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ((TextView)findViewById(R.id.smsReceivePerm)).setText("SMS Receive - No Permission");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECEIVE_SMS)) {
                ((TextView)findViewById(R.id.smsReceivePerm)).setText("SMS Receive - Permission Granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECEIVE_SMS}, 0);
            }
        }
        else
            ((TextView)findViewById(R.id.smsReceivePerm)).setText("SMS Receive - Permission Granted");
    }

    private void checkSendPerm() {
        ((TextView)findViewById(R.id.smsSendPerm)).setText("SMS Send - No Permission");
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECEIVE_SMS)) {
                ((TextView)findViewById(R.id.smsSendPerm)).setText("SMS Send - Permission Granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 0);
            }
        }
        else ((TextView)findViewById(R.id.smsSendPerm)).setText("SMS Send - Permission Granted");
    }

    public void sendMessage(View view) {
        String num = ((EditText)findViewById(R.id.smsNumber)).getText().toString();
        String body = ((EditText)findViewById(R.id.smsBody)).getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num, null, body, null, null);
            Toast.makeText(this,"Message Sent",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error Sending",Toast.LENGTH_LONG).show();
        }
    }

}