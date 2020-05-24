package org.runnerup.tracker.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;

import org.runnerup.R;
import org.runnerup.workout.Workout;
import org.runnerup.workout.feedback.RUTextToSpeech;

import java.util.HashMap;


public class TrackerTTS extends DefaultTrackerComponent {

    private TextToSpeech tts;
    private Context context;

    private static final String NAME = "TTS";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ResultCode onInit(final Callback callback, final Context context) {
        this.context = context;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    callback.run(TrackerTTS.this, ResultCode.RESULT_OK);
                }
                else {
                    callback.run(TrackerTTS.this, ResultCode.RESULT_ERROR);
                }
            }
        });
        return ResultCode.RESULT_PENDING;
    }

    @Override
    public void onBind(HashMap<String, Object> bindValues) {
        Context ctx = (Context) bindValues.get(TrackerComponent.KEY_CONTEXT);
        Boolean mute = (Boolean) bindValues.get(Workout.KEY_MUTE);
        bindValues.put(Workout.KEY_TTS, new RUTextToSpeech(tts, mute, ctx));
    }

    @Override
    public ResultCode onEnd(Callback callback, Context context) {
        if (tts != null) {
            tts.shutdown();
            tts = null;
        }
        return ResultCode.RESULT_OK;
    }
}
