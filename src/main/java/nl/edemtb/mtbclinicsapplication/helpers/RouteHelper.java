package nl.edemtb.mtbclinicsapplication.helpers;

import nl.edemtb.mtbclinicsapplication.exceptions.RouteNotAvailableException;

public class RouteHelper {

    private RouteHelper() {

    }

    public static void checkRouteAvailability(boolean available) {
        if (!available) {
            throw new RouteNotAvailableException("Helaas is de route gesloten, probeer een ander route.");
        }
    }
}