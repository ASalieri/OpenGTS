package org.brongus.shapefile.bcross;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lazar on 3/22/14.
 */
@RunWith(JUnit4.class)
public class StateTest {

    State state;

    @Before
    public void setUp() throws Exception {
        state = new State();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestPointsFarFromStateBorders() throws Exception {
        StateInfo stateInfo;

        stateInfo = state.getStateInfoForPoint(-88.9910706846, 40.4054969251);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-91.0562008603, 40.1146335201);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-95.3609792547, 37.7586399394);
        Assert.assertEquals("Kansas", stateInfo.stateName);
        Assert.assertEquals("West North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("KS", stateInfo.stateAbbreviation);
    }

    @Test
    public void TestPointsNearStateBorder() throws Exception {
        StateInfo stateInfo;

        stateInfo = state.getStateInfoForPoint(-91.4052369464, 40.1000903499);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-87.7258148728, 42.0343319933);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-90.9543986686, 41.3071734807);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-89.1801318979, 37.0060308789);
        Assert.assertEquals("Illinois", stateInfo.stateName);
        Assert.assertEquals("East North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("IL", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-89.2701177638, 37.0660214562);
        Assert.assertEquals("Missouri", stateInfo.stateName);
        Assert.assertEquals("West North Central", stateInfo.stateSubRegion);
        Assert.assertEquals("MO", stateInfo.stateAbbreviation);

        stateInfo = state.getStateInfoForPoint(-155.490085919, 19.6262039082);
        Assert.assertEquals("Hawaii", stateInfo.stateName);
        Assert.assertEquals("Pacific", stateInfo.stateSubRegion);
        Assert.assertEquals("HI", stateInfo.stateAbbreviation);

    }
}
