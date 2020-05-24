
package org.runnerup.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.runnerup.R;
import org.runnerup.common.tracker.TrackerState;
import org.runnerup.common.util.ValueModel;


public class StartFragment extends Fragment implements ValueModel.ChangeListener<TrackerState> {

    private TextView mTxt;
    private MainActivity activity;

    public StartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start, container, false);
        super.onViewCreated(view, savedInstanceState);

        CircledImageView button = (CircledImageView) view.findViewById(R.id.icon_start);
        button.setOnClickListener(startButtonClick);
        mTxt = (TextView) view.findViewById(R.id.txt_start);

        return view;
    }

    private void updateView(TrackerState state) {
        if (state == null)
            return;
        switch (state) {
            case INIT:
            case INITIALIZING:
            case INITIALIZED:
                mTxt.setText(getString(R.string.Start_GPS));
                break;
            case CONNECTING:
                break;
            case CONNECTED:
                mTxt.setText(getString(R.string.Start_activity));
                break;
            case STARTED:
                break;
            case PAUSED:
                break;
            case CLEANUP:
                break;
            case ERROR:
                break;
        }
    }

    private final View.OnClickListener startButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.getStateService().sendStart();
        }
    };

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
                               TrackerState oldValue, TrackerState newValue) {
        if (isAdded())
            updateView(newValue);
    }
}
