
package org.runnerup.view;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.runnerup.R;

//
public class SearchingFragment extends Fragment {

    public SearchingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searching, container, false);
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= 23) {
            CircledImageView button = (CircledImageView) view.findViewById(R.id.icon_searching);
            AnimationDrawable frameAnimation = (AnimationDrawable) button.getForeground();
            frameAnimation.start();
        }

        return view;
    }

    //@Override
    //public void onAttach(Activity activity) {
    //    super.onAttach(activity);
    //    //MainActivity activity1 = (MainActivity) activity;
    //}
}
