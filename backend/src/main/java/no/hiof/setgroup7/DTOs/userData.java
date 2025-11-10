package no.hiof.setgroup7.DTOs;

public class userData {
    private String username;
    private String password;
    // Features for future implementation
    // private int favPos1;
    // private int favPos2;
    // private int favPos3;


    public userData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static userData DBConnector = new userData("gruppe7", "Summer26");

    public static userData getDBConnector() {
        return DBConnector;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    public int getFavPos1() {
        return favPos1;
    }

    public void setFavPos1(int favPos1) {
        this.favPos1 = favPos1;
    }

    public int getFavPos2() {
        return favPos2;
    }

    public void setFavPos2(int favPos2) {
        this.favPos2 = favPos2;
    }

    public int getFavPos3() {
        return favPos3;
    }

    public void setFavPos3(int favPos3) {
        this.favPos3 = favPos3;
    }

 */
}
