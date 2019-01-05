package org.techtown.mysmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    private static final String Tag = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Tag,"onReceive() 호출됨.");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            Log.d(Tag,"sender : " + sender);

            String contents = messages[0].getMessageBody().toString();
            Log.d(Tag,"contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(Tag, "received date : " + receivedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i = 0 ; i < objs.length; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);

            }
        }

        return messages;
    }
}
