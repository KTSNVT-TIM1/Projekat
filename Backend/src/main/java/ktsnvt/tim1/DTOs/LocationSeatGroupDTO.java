package ktsnvt.tim1.DTOs;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class LocationSeatGroupDTO {

    @NotNull(message = "Event's ID cannot be null")
    private Long eventID;

    @NotNull(message = "Location's ID cannot be null")
    private Long locationID;

    private String locationName;

    @Valid
    @NotEmpty
    private ArrayList<EventSeatGroupDTO> eventSeatGroups;

    public LocationSeatGroupDTO() {}

    public LocationSeatGroupDTO(Long eventID, Long locationID) {
        this.eventID = eventID;
        this.locationID = locationID;
        this.eventSeatGroups = new ArrayList<>();
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<EventSeatGroupDTO> getEventSeatGroups() {
        return eventSeatGroups;
    }

    public void setEventSeatGroups(ArrayList<EventSeatGroupDTO> eventSeatGroups) {
        this.eventSeatGroups = eventSeatGroups;
    }
}
