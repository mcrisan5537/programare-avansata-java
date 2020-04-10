package com.app;

public class Album {

    private String name;
    private int artistID;
    private int releaseYear;

    public Album(String name, int artistID, int releaseYear) {
        this.name = name;
        this.artistID = artistID;
        this.releaseYear = releaseYear;
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

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artistID=" + artistID +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
