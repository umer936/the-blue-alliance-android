/**
 * The Blue Alliance API
 * Access data about the FIRST Robotics Competition
 *
 * OpenAPI spec version: 2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.thebluealliance.api.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Nullable;


/**
 * Robot
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-08-08T22:10:21.357-04:00")
public class Robot   {
  @SerializedName("key")
  private String key = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("team_key")
  private String teamKey = null;

  @SerializedName("year")
  private Integer year = null;

  public Robot key(String key) {
    this.key = key;
    return this;
  }

   /**
   * A key identifying the robot object. Formed like [team_key]_[year]
   * @return key
  **/
  @ApiModelProperty(example = "null", value = "A key identifying the robot object. Formed like [team_key]_[year]")
  @Nullable
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Robot name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The robot name in this year
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "The robot name in this year")
  @Nullable
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Robot teamKey(String teamKey) {
    this.teamKey = teamKey;
    return this;
  }

   /**
   * The associated Team key
   * @return teamKey
  **/
  @ApiModelProperty(example = "null", value = "The associated Team key")
  @Nullable
  public String getTeamKey() {
    return teamKey;
  }

  public void setTeamKey(String teamKey) {
    this.teamKey = teamKey;
  }

  public Robot year(Integer year) {
    this.year = year;
    return this;
  }

   /**
   * The year this Robot model referes to
   * @return year
  **/
  @ApiModelProperty(example = "null", value = "The year this Robot model referes to")
  @Nullable
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Robot robot = (Robot) o;
    return Objects.equals(this.key, robot.key) &&
        Objects.equals(this.name, robot.name) &&
        Objects.equals(this.teamKey, robot.teamKey) &&
        Objects.equals(this.year, robot.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, name, teamKey, year);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Robot {\n");
    
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    teamKey: ").append(toIndentedString(teamKey)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

