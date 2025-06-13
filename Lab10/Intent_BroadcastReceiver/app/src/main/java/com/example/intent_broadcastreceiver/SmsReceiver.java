package com.example.intent_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context, intent);
    }

    public void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";

        if (extras != null) {
            Object[] smsExtra = (Object[]) extras.get("pdus");
            for (int i = 0; i < smsExtra.length; i++) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                body = sms.getMessageBody();
                address = sms.getOriginatingAddress();
                message += "Có 1 tin nhắn từ " + address + "\n" + body + " vừa gởi đến";
            }

            // Hiển thị Toast
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            // Cập nhật TextView nếu MainActivity đang mở
            if (MainActivity.tvSmsContent != null) {
                String finalMessage = message;
                MainActivity.tvSmsContent.post(() -> {
                    MainActivity.tvSmsContent.setText(finalMessage);
                });
            }
        }
    }
}