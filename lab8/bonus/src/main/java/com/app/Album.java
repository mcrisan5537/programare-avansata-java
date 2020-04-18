package com.app;

public class Album {

    private int id;
    private String name;
    private int artistID;
    private int releaseYear;

    public Album(int id, String name, int artistID, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistID = artistID;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getReleaseYearString() {
        return String.valueOf(releaseYear);
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Album: " + name + " (" + releaseYear + ")";
    }
}
