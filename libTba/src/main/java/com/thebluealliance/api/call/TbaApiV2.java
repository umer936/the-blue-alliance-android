package com.thebluealliance.api.call;



import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import com.thebluealliance.api.model.ApiStatus;
import com.thebluealliance.api.model.Event;
import com.thebluealliance.api.model.Team;
import com.thebluealliance.api.model.Award;
import com.thebluealliance.api.model.Match;
import com.thebluealliance.api.model.Media;
import com.thebluealliance.api.model.Robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TbaApiV2 {
  /**
   * API Status Request
   * Get various metadata about the TBA API
   * @return Call&lt;ApiStatus&gt;
   */
  
  @GET("api/v2/status")
  Call<ApiStatus> fetchApiStatus();
    

  /**
   * District Events Request
   * Fetch a list of events within a given district
   * @param districtShort Short string identifying a district (e.g. &#39;ne&#39;) (required)
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Event>&gt;
   */
  
  @GET("district/{district_short}/{year}/events")
  Call<List<Event>> fetchDistrictEvents(
    @Path("district_short") String districtShort, @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * District List Request
   * Fetch a list of active districts in the given year
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;String&gt;
   */
  
  @GET("districts/{year}")
  Call<String> fetchDistrictList(
    @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * District Rankings Reques
   * Fetch district rankings
   * @param districtShort Short string identifying a district (e.g. &#39;ne&#39;) (required)
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;String&gt;
   */
  
  @GET("district/{district_short}/{year}/rankings")
  Call<String> fetchDistrictRankings(
    @Path("district_short") String districtShort, @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * District Teams Request
   * Fetch a list of teams within a given district
   * @param districtShort Short string identifying a district (e.g. &#39;ne&#39;) (required)
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Team>&gt;
   */
  
  @GET("district/{district_short}/{year}/teams")
  Call<List<Team>> fetchDistrictTeamsInYear(
    @Path("district_short") String districtShort, @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Info Request
   * Fetch details for one event
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;Event&gt;
   */
  
  @GET("event/{event_key}")
  Call<Event> fetchEvent(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Awards Request
   * Fetch awards for the given event
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Award>&gt;
   */
  
  @GET("events/{event_key}/awards")
  Call<List<Award>> fetchEventAwards(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event District Points Request
   * Fetch district points for one event.
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;String&gt;
   */
  
  @GET("event/{event_key}/district_points")
  Call<String> fetchEventDistrictPoints(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Matches Request
   * Fetch matches for the given event
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Match>&gt;
   */
  
  @GET("events/{event_key}/matches")
  Call<List<Match>> fetchEventMatches(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Rankings Request
   * Fetch ranking details for one event.
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;String&gt;
   */
  
  @GET("event/{event_key}/rankings")
  Call<String> fetchEventRankings(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Stats Request
   * Fetch stats details for one event.
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;String&gt;
   */
  
  @GET("event/{event_key}/stats")
  Call<String> fetchEventStats(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event Teams Request
   * Fetch teams attending the given event
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Team>&gt;
   */
  
  @GET("events/{event_key}/teams")
  Call<List<Team>> fetchEventTeams(
    @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Event List Request
   * Fetch all events in a year
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Event>&gt;
   */
  
  @GET("events/{year}")
  Call<List<Event>> fetchEventsInYear(
    @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Match Request
   * Fetch details about a single match
   * @param matchKey Key identifying a single match, has format [event key]_[match id] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;Match&gt;
   */
  
  @GET("match/{match_key}")
  Call<Match> fetchMatch(
    @Path("match_key") String matchKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Single Team Request
   * This endpoit returns information about a single team
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;Team&gt;
   */
  
  @GET("team/{team_key}")
  Call<Team> fetchTeam(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team Event Awards Request
   * Fetch all awards won by a single team at an event
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Award>&gt;
   */
  
  @GET("team/{team_key}/event/{event_key}/awards")
  Call<List<Award>> fetchTeamAtEventAwards(
    @Path("team_key") String teamKey, @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team Event Matches Request
   * Fetch all matches for a single team at an event
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param eventKey Key identifying a single event, has format [year][event code] (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Match>&gt;
   */
  
  @GET("team/{team_key}/event/{event_key}/matches")
  Call<List<Match>> fetchTeamAtEventMatches(
    @Path("team_key") String teamKey, @Path("event_key") String eventKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team History Awards Request
   * Fetch all awards a team has won
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Award>&gt;
   */
  
  @GET("team/{team_key}/history/awards")
  Call<List<Award>> fetchTeamAwardHistory(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team History District Request
   * Fetch all district keys that a team has competed in
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<String>&gt;
   */
  
  @GET("team/{team_key}/history/districts")
  Call<List<String>> fetchTeamDistrictHistory(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team History Events Request
   * Fetch all events a team has event registered for
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Event>&gt;
   */
  
  @GET("team/{team_key}/history/events")
  Call<List<Event>> fetchTeamEventHistory(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team Events Request
   * Fetch all events for a given team in a given year
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Event>&gt;
   */
  
  @GET("team/{team_key}/{year}/events")
  Call<List<Event>> fetchTeamEvents(
    @Path("team_key") String teamKey, @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team Media Request
   * Fetch media associated with a team in a given year
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param year A specific year to request data for. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Media>&gt;
   */
  
  @GET("team/{team_key}/{year}/media")
  Call<List<Media>> fetchTeamMediaInYear(
    @Path("team_key") String teamKey, @Path("year") String year, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team List Request
   * Returns a page containing 500 teams
   * @param page A page of teams, zero-indexed. Each page consists of teams whose numbers start at start &#x3D; 500 * page_num and end at end &#x3D; start + 499, inclusive. (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Team>&gt;
   */
  
  @GET("teams/{page}")
  Call<List<Team>> fetchTeamPage(
    @Path("page") String page, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team History Robots Request
   * Fetch all robots a team has made since 2015. Robot names are scraped from TIMS.
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Robot>&gt;
   */
  
  @GET("team/{team_key}/history/robots")
  Call<List<Robot>> fetchTeamRobotHistory(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

  /**
   * Team Years Participated Request
   * Fetch the years for which the team was registered for an event
   * @param teamKey Key identifying a single team, has format frcXXXX, where XXXX is the team number (required)
   * @param xTBACache Special TBA App Internal Header to indicate caching strategy. (optional)
   * @return Call&lt;List<Integer>&gt;
   */
  
  @GET("team/{team_key}/years_participated")
  Call<List<Integer>> fetchTeamYearsParticipated(
    @Path("team_key") String teamKey, @Header("X-TBA-Cache") String xTBACache
  );

}
