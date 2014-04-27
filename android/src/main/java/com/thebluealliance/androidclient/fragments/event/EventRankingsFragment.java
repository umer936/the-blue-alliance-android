package com.thebluealliance.androidclient.fragments.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thebluealliance.androidclient.R;
import com.thebluealliance.androidclient.background.PopulateEventRankings;

/**
 * File created by phil on 4/22/14.
 */
public class EventRankingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View results = inflater.inflate(R.layout.fragment_event_rankings, null);
        new PopulateEventRankings(getActivity(), results).execute("");
        return results;
    }
}
