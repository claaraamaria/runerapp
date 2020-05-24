package org.runnerup.view;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import org.runnerup.common.util.Constants;
import org.runnerup.tracker.component.HeadsetButtonReceiver;


public class StartActivityHeadsetButtonReceiver extends HeadsetButtonReceiver {

    public static void registerHeadsetListener(Context context) {
        registerHeadsetListener(context, StartActivityHeadsetButtonReceiver.class);
    }

    public static void unregisterHeadsetListener(Context context) {
        unregisterHeadsetListener(context, StartActivityHeadsetButtonReceiver.class);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent event = intent
                    .getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (KeyEvent.ACTION_DOWN == event.getAction()) {
                Intent startBroadcastIntent = new Intent()
                        .setAction(Constants.Intents.START_ACTIVITY);
                context.sendBroadcast(startBroadcastIntent);
            }
        }
    }
}
