package nl.edemtb.mtbclinicsapplication.dtos.route;

import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.models.Picture;

public class RouteDto {

    private Long id;
    private String name;
    private RouteType routeType;
    private Difficulty difficulty;
    private String startingPoint;
    private String place;
    private String province;
    private String routeInformation;
    private Double distance;
    private Boolean available;
    private Picture picture;

    public RouteDto() {
    }

    public RouteDto(Long id, String name, RouteType routeType, Difficulty difficulty, String startingPoint, String place, String province, String routeInformation, Double distance, Boolean available, Picture picture) {
        this.id = id;
        this.name = name;
        this.routeType = routeType;
        this.difficulty = difficulty;
        this.startingPoint = startingPoint;
        this.place = place;
        this.province = province;
        this.routeInformation = routeInformation;
        this.distance = distance;
        this.available = available;
        this.picture = picture;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
