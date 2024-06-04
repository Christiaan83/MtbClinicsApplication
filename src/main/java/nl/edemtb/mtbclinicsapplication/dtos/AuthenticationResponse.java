package nl.edemtb.mtbclinicsapplication.dtos;

public class AuthenticationResponse {

    private final String accessToken;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJwt() {
        return accessToken;
    }

}
