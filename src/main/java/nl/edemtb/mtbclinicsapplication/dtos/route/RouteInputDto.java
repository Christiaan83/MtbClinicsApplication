package nl.edemtb.mtbclinicsapplication.dtos.route;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;

public class RouteInputDto {
    @NotBlank
    @Size(min = 2, max = 30, message = "Name must be between 2-30 characters")
    private String name;
    @NotNull(message = "routeType is required")
    private RouteType routeType;
    @NotNull(message = "difficulty is required")
    private Difficulty difficulty;
    @NotBlank(message = "startingPoint is required")
    private String startingPoint;
    @NotBlank(message = "place is required")
    private String place;
    @NotBlank(message = "province is required")
    private String province;
    private String routeInformation;
    @NotNull
    private Double distance;
    private Boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRouteInformation() {
        return routeInformation;
    }

    public void setRouteInformation(String routeInformation) {
        this.routeInformation = routeInformation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
