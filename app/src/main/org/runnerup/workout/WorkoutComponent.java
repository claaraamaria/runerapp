

package org.runnerup.workout;

import java.util.HashMap;

interface WorkoutComponent {
    /**
     * Called before workout begins
     */
    void onInit(Workout s);

    /**
     * Called at least once before onStart Can be called later if orientation
     * changes
     */
    void onBind(Workout s, HashMap<String, Object> bindValues);

    /**
     * Called before onStart
     */
    void onRepeat(int current, int limit);

    /**
     * Called when *what* starts
     */
    void onStart(Scope what, Workout s);

    /**
     * Called when user press PauseButton after this either onResume or
     * onComplete will be called
     */
    void onPause(Workout s);

    /**
     * Called when user press StopButton after this either onResume or
     * onComplete will be called
     */
    void onStop(Workout s);

    /**
     * Called when user press ResumeButton
     */
    void onResume(Workout s);

    /**
     * Called when *what* is completed
     */
    void onComplete(Scope what, Workout s);

    /**
     * Called after workout has ended
     */
    void onEnd(Workout s);
}
