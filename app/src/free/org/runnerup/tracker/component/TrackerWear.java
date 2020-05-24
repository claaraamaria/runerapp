package org.runnerup.tracker.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import org.runnerup.tracker.Tracker;
import org.runnerup.tracker.WorkoutObserver;
import org.runnerup.workout.WorkoutInfo;


public class TrackerWear extends DefaultTrackerComponent implements WorkoutObserver {

    public static final String NAME = "WEAR";

    public TrackerWear(Tracker tracker) {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public TrackerComponent.ResultCode onInit(final Callback callback, Context context) {
        return TrackerComponent.ResultCode.RESULT_NOT_SUPPORTED;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void workoutEvent(WorkoutInfo workoutInfo, int type) {
    }
}
