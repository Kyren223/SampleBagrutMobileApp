package me.kyren223.samplebagrutapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String body, phone;
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get("pdus");
        msgs = new SmsMessage[pdus.length];
        for (int i = 0; i < msgs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            }
            phone =  msgs[i].getOriginatingAddress();
            body = msgs[i].getMessageBody();
            sendData(context, phone, body);
        }
    }

    private void sendData(Context context, String phone, String body)
    {
        String data = phone+": "+ body;
        Toast.makeText(context, data, Toast.LENGTH_LONG).show();
    }

}