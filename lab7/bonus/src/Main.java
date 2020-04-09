import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    public static void main(String[] args) throws Exception {

        Player p1 = new ManualPlayer("manual player");
        Player p2 = new RandomPlayer("random player 1");
        Player p3 = new RandomPlayer("random player 2");
        Player p4 = new SmartPlayer("smart player");
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Game.init(players, 40, 40, 3);

        launch(args);

    }

    static List<Label> playerNamesList = new ArrayList<>();
    static List<Label> playerPicksList = new ArrayList<>();
    static Label boardLabel, timeLeftLabel;
    static TextField inputTokenValueField;
    static Button pickButton;
    static AtomicLong secondsPassed;
    static Timer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // top
        boardLabel = new Label("board here");
        boardLabel.setFont(new Font(15.5));
        boardLabel.setText(Game.getBoard().toString());
        HBox timeHBox = new HBox();
        timeHBox.getChildren().add(new Label("Time elapsed since start of the game: "));
        timeLeftLabel = new Label("time left here");
        timer = new Timer();
        secondsPassed = new AtomicLong();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed.set(secondsPassed.get() + 1);
                Platform.runLater(Main::refreshData);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
        timeHBox.getChildren().add(timeLeftLabel);
        timeHBox.getChildren().add(new Label("s. Time Limit : " + Game.TIME_LIMIT + "s"));
        timeHBox.setSpacing(0);
        timeHBox.setAlignment(Pos.CENTER);
        VBox topVBox = new VBox();
        topVBox.getChildren().addAll(boardLabel, timeHBox);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setPadding(new Insets(10, 10, 10 ,10));
        topVBox.setSpacing(2);

        // bottom
        HBox bottomHBox = new HBox();
        inputTokenValueField = new TextField();
        inputTokenValueField.setPromptText("Token value");
        pickButton = new Button("PICK VALUE");
        bottomHBox.getChildren().addAll(inputTokenValueField, pickButton);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPadding(new Insets(10, 10, 10 ,10));
        bottomHBox.setSpacing(5);

        // center

        VBox playersVBox = new VBox();
        for(Player player : Game.getPlayers()) {
            Label playerName = new Label(player.getName());
            playerNamesList.add(playerName);
            Label playerPicks = new Label(player.getPicksString());
            playerPicksList.add(playerPicks);
            HBox playerHBox = new HBox(playerName, playerPicks);
            playerHBox.setAlignment(Pos.CENTER);
            playerHBox.setPadding(new Insets(10, 10, 10, 10));
            playerHBox.setSpacing(5);
            playersVBox.getChildren().add(playerHBox);
        }
        playersVBox.setAlignment(Pos.CENTER);

        // main border layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topVBox);
        mainLayout.setCenter(playersVBox);

        Scene scene = new Scene(mainLayout, 900, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Arithmetic progression game");
        primaryStage.setOnCloseRequest(Main::exitGame);
        primaryStage.show();

        new Thread(new Game()).start();

    }

    public static void refreshData() {
        boardLabel.setText(Game.boardAsString);
        timeLeftLabel.setText(String.valueOf(secondsPassed.get()));
        List<Player> players = Game.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            playerNamesList.get(i).setText(players.get(i).getName());
            playerPicksList.get(i).setText(players.get(i).getPicksString());
        }
        if(Game.hasFinished)
            timer.cancel();
    }

    public static void exitGame(WindowEvent e) {
        e.consume();
        if(displayConfirmBox("Confirm", "Are you sure you want to exit?" ) == true)
            System.exit(1);
    }

    public static boolean displayConfirmBox(String title, String message) {
        AtomicBoolean answer = new AtomicBoolean();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(message);
        Button yesButton = new Button("YES");
        yesButton.setOnAction(e -> { answer.set(true); stage.close();});
        Button noButton = new Button("NO");
        noButton.setOnAction(e -> { answer.set(false); stage.close();});
        HBox hBox = new HBox(yesButton, noButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5,5));
        hBox.setSpacing(10);

        VBox vBox = new VBox(label, hBox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 250, 100);
        stage.setScene(scene);
        stage.showAndWait();

        return answer.get();
    }

}

