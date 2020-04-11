package com.app;

import com.dao.AlbumController;
import com.dao.ArtistController;
import com.dao.ChartController;
import com.github.javafaker.Faker;
import com.util.Database;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws SQLException {

        Database.getConnection().setAutoCommit(false);
        Savepoint savepoint = Database.getConnection().setSavepoint();

        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        ChartController chartController = new ChartController();

//        useArtistController(artistController, albumController);
//        System.out.println();
//        useAlbumController(artistController, albumController);
//        System.out.println();
        insertData(artistController, albumController, chartController);
        displayRanking(artistController, albumController, chartController);
        generateHTMLReport(chartController.getChart());

        Database.getConnection().rollback(savepoint);
        Database.getConnection().close();

    }

    public static void insertData(ArtistController artistController, AlbumController albumController, ChartController chartController) {
        final int NO_OF_ARTISTS = 20;
        List<Integer> positions = IntStream.range(0, NO_OF_ARTISTS).boxed().collect(Collectors.toList());
        Faker faker = new Faker();
        String artistName, artistCountry, albumName;
        int artistID, albumID, releaseYear;

        for (int i = 0; i < NO_OF_ARTISTS; i++) {
            // insert artist
            artistName = faker.funnyName().name();
            artistCountry = faker.country().name();
            artistController.create(artistName, artistCountry);

            // insert album
            albumName = faker.music().genre() + " " + faker.music().instrument();
            artistID = artistController.findLastAddedArtist().getId();
            releaseYear = Integer.parseInt(Year.of(1970).plusYears(faker.number().numberBetween(0, 51)).toString());
            albumController.create(albumName, artistID, releaseYear);

            // insert chart entry
            albumID = albumController.findLastAddedAlbum().getId();
            chartController.create(artistID, albumID, 1 + positions.remove(faker.number().numberBetween(0, positions.size())));
        }
    }

    public static void displayRanking(ArtistController artistController, AlbumController albumController, ChartController chartController) {
        Chart chart = chartController.getChart();
        ChartEntry entry;
        for(int i = 0; i < chart.size(); i++) {
            entry = chart.getEntryByPosition(i);
            System.out.println("Entry #" + (i + 1));
            System.out.println(artistController.findByID(entry.getArtistID()));
            System.out.println(albumController.findByID(entry.getAlbumID()));
            System.out.println();
        }
    }

    public static void generateHTMLReport(Chart chart) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        try {
            configuration.setDirectoryForTemplateLoading(new File("./templates/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);

        try {
            Template template = configuration.getTemplate("html-template.ftlh");
            Writer writer = new FileWriter("html-report.html");
            template.process(chart, writer);
            writer.close();
        } catch(Exception e) {
            System.err.println("Error reading template.");
            e.printStackTrace();
        }
    }

    public static void useArtistController(ArtistController artistController, AlbumController albumController) {
        artistController.create("Tyler, The Creator", "United States");
        artistController.create("Kendrick Lamar", "United States");
        artistController.create("Drake", "Canada");

        List<Artist> artists = artistController.findByName("Kanye West");
        for (Artist artist : artists)
            System.out.println(artist);
    }

    public static void useAlbumController(ArtistController artistController, AlbumController albumController) {
        albumController.create("IGOR", artistController.findByName("Tyler, The Creator").get(0).getId(), 2019);
        albumController.create("Take Care", artistController.findByName("Drake").get(0).getId(), 2011);
        albumController.create("Views", artistController.findByName("Drake").get(0).getId(), 2016);

        List<Album> albums = albumController.findByArtist(artistController.findByName("Drake").get(0).getId());
        for (Album album : albums)
            System.out.println(album);
    }

}