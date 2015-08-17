package com.thebluealliance.androidclient.subscribers;

import com.google.gson.Gson;
import com.thebluealliance.androidclient.datafeed.framework.DatafeedTestDriver;
import com.thebluealliance.androidclient.datafeed.framework.ModelMaker;
import com.thebluealliance.androidclient.eventbus.ActionBarTitleEvent;
import com.thebluealliance.androidclient.listitems.ImageListElement;
import com.thebluealliance.androidclient.listitems.ListItem;
import com.thebluealliance.androidclient.listitems.MatchListElement;
import com.thebluealliance.androidclient.models.BasicModel;
import com.thebluealliance.androidclient.models.Event;
import com.thebluealliance.androidclient.models.Match;
import com.thebluealliance.androidclient.models.Media;
import com.thebluealliance.androidclient.modules.DatafeedModule;
import com.thebluealliance.androidclient.subscribers.MatchInfoSubscriber.Model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import de.greenrobot.event.EventBus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class MatchInfoSubscriberTest {

    @Mock EventBus mEventBus;

    MatchInfoSubscriber mSubscriber;
    Gson mGson;
    Model mData;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mGson = DatafeedModule.getGson();
        mSubscriber = new MatchInfoSubscriber(mGson, mEventBus);
        mData = new Model(
          ModelMaker.getModel(Match.class, "2015necmp_f1m1"),
          ModelMaker.getModel(Event.class, "2015necmp"));
    }

    @Test
    public void testParseNullData() throws BasicModel.FieldNotDefinedException {
        DatafeedTestDriver.parseNullData(mSubscriber);
    }

    @Test
    public void testSimpleParsing() throws BasicModel.FieldNotDefinedException {
        DatafeedTestDriver.testSimpleParsing(mSubscriber, mData);
        verify(mEventBus).post(any(ActionBarTitleEvent.class));
    }

    @Test
    public void testParsedData() throws BasicModel.FieldNotDefinedException {
        List<ListItem> data = DatafeedTestDriver.getParsedData(mSubscriber, mData);

        assertEquals(2, data.size());
        assertTrue(data.get(0) instanceof MatchListElement);
        assertTrue(data.get(1) instanceof ImageListElement);

        MatchListElement match = (MatchListElement) data.get(0);
        Media videoItem = mGson.fromJson(mData.match.getVideos().get(0), Media.class);
        ImageListElement video = (ImageListElement) data.get(1);

        assertEquals(mData.match.render(false, true, false, false), match);
        assertEquals(videoItem.render(), video);
    }
}