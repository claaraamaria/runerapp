

package org.runnerup.workout.feedback;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import org.runnerup.util.Formatter;
import org.runnerup.workout.Dimension;
import org.runnerup.workout.Feedback;
import org.runnerup.workout.Scope;
import org.runnerup.workout.Workout;

import java.util.HashMap;


public class AudioCountdownFeedback extends Feedback {

    private Scope scope;
    private Dimension dimension;
    private RUTextToSpeech textToSpeech;
    private Formatter formatter;

    public AudioCountdownFeedback(Scope s, Dimension d) {
        this.scope = s;
        this.dimension = d;
    }

    @Override
    public void onBind(Workout s, HashMap<String, Object> bindValues) {
        super.onBind(s, bindValues);
        if (bindValues.containsKey(Workout.KEY_TTS))
            textToSpeech = (RUTextToSpeech) bindValues.get(Workout.KEY_TTS);
        if (bindValues.containsKey(Workout.KEY_FORMATTER))
            formatter = (Formatter) bindValues.get(Workout.KEY_FORMATTER);
    }

    @Override
    public boolean equals(Feedback _other) {
        if (!(_other instanceof AudioCountdownFeedback))
            return false;

        AudioCountdownFeedback other = (AudioCountdownFeedback) _other;

        return scope == other.scope && dimension == other.dimension;
    }

    @Override
    public void emit(Workout w, Context ctx) {
        double remaining = w.getRemaining(scope, dimension); // SI

        if (remaining > 0) {
            String msg = formatter.formatRemaining(Formatter.Format.CUE_SHORT, dimension, remaining);
            textToSpeech.speak(msg, TextToSpeech.QUEUE_ADD, null);
        }
    }
}
