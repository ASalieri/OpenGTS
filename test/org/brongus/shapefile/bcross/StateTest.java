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
    public void testGetStateInfoForPoint() throws Exception {
        StateInfo stateInfo = state.getStateInfoForPoint(45.4812219, -104.5596687);
        Assert.assertEquals(stateInfo.stateName, "Hawaii");
        Assert.assertEquals(stateInfo.stateSubRegion, "Pacific");
        Assert.assertEquals(stateInfo.stateAbbreviation, "HI");
    }
}
