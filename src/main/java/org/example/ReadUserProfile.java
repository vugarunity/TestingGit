package org.example;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ID",
        "Name",
        "Full_name"
})

public class ReadUserProfile {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Full_Name")
    private String Full_name;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    @JsonProperty("Name")
    public void setName(String Name) {
        this.Name = Name;
    }

    @JsonProperty("Full_Name")
    public String getFull_name() {return Full_name;}

    @JsonProperty("Full_Name")
    public void setFull_name(String Full_Name) {this.Full_name = Full_Name;}

}
