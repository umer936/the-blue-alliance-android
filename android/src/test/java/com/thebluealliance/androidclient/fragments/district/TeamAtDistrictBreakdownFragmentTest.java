package com.thebluealliance.androidclient.fragments.district;

import com.thebluealliance.androidclient.IntegrationRobolectricRunner;
import com.thebluealliance.androidclient.R;
import com.thebluealliance.androidclient.fragments.framework.BaseFragmentTest;
import com.thebluealliance.androidclient.fragments.framework.FragmentTestDriver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(IntegrationRobolectricRunner.class)
public class TeamAtDistrictBreakdownFragmentTest extends BaseFragmentTest {

    TeamAtDistrictBreakdownFragment mFragment;

    @Before
    public void setUp() {
        mFragment = TeamAtDistrictBreakdownFragment.newInstance("frc1124", "2015ne");
    }

    @Test
    public void testLifecycle() {
        FragmentTestDriver.testLifecycle(mFragment);
    }

    @Test
    public void testNoDataBinding() {
        FragmentTestDriver.testNoDataBindings(mFragment, R.id.no_data);
    }
}