package com.app;

import com.dao.AlbumController;
import com.dao.ArtistController;

public class ChartEntry {

    private int ID;
    private int artistID;
    private int albumID;
    private int position;

    public ChartEntry(int ID, int artistID, int albumID, int position) {
        this.ID = ID;
        this.artistID = artistID;
        this.albumID = albumID;
        this.position = position;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArtistID() {
        return artistID;
    }

    public Artist getArtist() {
        return new ArtistController().findByID(artistID);
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public Album getAlbum() {
        return new AlbumController().findByID(albumID);
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
