

package org.runnerup.workout.feedback;

import android.content.Context;

import org.runnerup.workout.Feedback;
import org.runnerup.workout.Workout;


public class VibrationFeedback extends Feedback {

    @Override
    public boolean equals(Feedback _other) {
        return _other instanceof VibrationFeedback;
    }

    @Override
    public void emit(Workout s, Context ctx) {
        // TODO Auto-generated method stub

    }
}
