

package org.runnerup.workout.feedback;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import org.runnerup.R;
import org.runnerup.util.Formatter;
import org.runnerup.workout.Dimension;
import org.runnerup.workout.Feedback;
import org.runnerup.workout.Range;
import org.runnerup.workout.Scope;
import org.runnerup.workout.TargetTrigger;
import org.runnerup.workout.Workout;


public class CoachFeedback extends AudioFeedback {

    private int sign = 1;
    private Range range = null;
    private TargetTrigger trigger = null;

    public CoachFeedback(TargetTrigger trigger) {
        super(Scope.CURRENT, trigger.getDimension());
        this.range = trigger.getRange();
        this.trigger = trigger;

        if (dimension == Dimension.PACE) {
                sign = -1; // pace is "inverse"
        }
    }

    @Override
    public boolean equals(Feedback _other) {
        if (!(_other instanceof CoachFeedback))
            return false;

        CoachFeedback other = (CoachFeedback) _other;
        if (!range.contentEquals(other.range))
            return false;

        if (!dimension.equal(other.dimension))
            return false;

        return scope.equal(other.scope);
    }

    @Override
    public void emit(Workout s, Context ctx) {
        double val;
        if (trigger != null)
            val = trigger.getValue();
        else
            val = s.get(scope, dimension);

        int cmp = sign * range.compare(val);
        String msg = "";
        if (cmp < 0) {
            msg = " " + formatter.getCueString(R.string.cue_speedup);
        } else if (cmp > 0) {
            msg = " " + formatter.getCueString(R.string.cue_slowdown);
        }
        if (!"".contentEquals(msg) && textToSpeech != null) {
            textToSpeech.speak(formatter.getCueString(scope.getCueId())
                    + " "
                    + formatter.format(Formatter.Format.CUE_LONG, dimension, val)
                    + msg,
                    TextToSpeech.QUEUE_ADD, null);
        }
    }
}
