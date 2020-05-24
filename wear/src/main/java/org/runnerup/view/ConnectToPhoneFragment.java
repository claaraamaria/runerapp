package org.runnerup.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.runnerup.R;
import org.runnerup.common.tracker.TrackerState;
import org.runnerup.common.util.ValueModel;

/**
 * @todo make this fragment contact phone and start app
 */

public class ConnectToPhoneFragment extends Fragment implements ValueModel.ChangeListener<TrackerState> {

    private DelayedConfirmationView mButton;
    private MainActivity activity;
    public ConnectToPhoneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connect_to_phone, container, false);
        super.onViewCreated(view, savedInstanceState);

        mButton = (DelayedConfirmationView) view.findViewById(R.id.icon_open_on_phone);
        //TextView txt = (TextView) view.findViewById(R.id.txt_open_on_phone);

        mButton.setListener(mListener);

        return view;
    }

    private void updateView(TrackerState state) {
        if (state == null) {
            mButton.setStartTimeMs(0);
            mButton.setTotalTimeMs(5000);
            mButton.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.registerTrackerStateListener(this);
        updateView(this.activity.getTrackerState());
    }

    @Override
    public void onPause() {
        activity.unregisterTrackerStateListener(this);
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public void onValueChanged(ValueModel<TrackerState> obj,
                               TrackerState oldState, TrackerState newState) {
        if (isAdded())
            updateView(newState);
    }

    private final DelayedConfirmationView.DelayedConfirmationListener mListener = new DelayedConfirmationView.DelayedConfirmationListener() {
        @Override
        public void onTimerFinished(View view) {
            updateView(activity.getTrackerState());
        }

        @Override
        public void onTimerSelected(View view) {

        }
    };
}
