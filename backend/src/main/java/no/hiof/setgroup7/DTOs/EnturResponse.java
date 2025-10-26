package no.hiof.setgroup7.DTOs;

public class EnturResponse {
    private DataWrapper data;

    public EnturResponse(DataWrapper data) {
        this.data = data;
    }

    public EnturResponse() {}

    public DataWrapper getData() {
        return data;
    }
}
