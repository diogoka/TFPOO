package src;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private static Manager instance;

    public static Manager getInstance() {
        if (instance == null) instance = new Manager();
        return instance;
    }

    private StackPane gameStackPane;
    private BorderPane borderPane;
    private GridPane header;
    private BorderPane gameOverPane;
    private VBox configsPane;
    private Text scoreTxt;
    private Text lifesTxt;
    private Button configBtn;
    private Text configTitle;
    private ChoiceBox configDifficulty;
    private Text configDifficultyText;
    private Button configExit;
    private Text gameOverText;
    private Button gameOverPlayAgainBtn;
    private ObservableList<Score> scores;

    private double bounceAnimAux = 0;

    private Manager() {
        scores = FXCollections.observableList(new ArrayList<>());

        gameStackPane = new StackPane();
        borderPane = new BorderPane();

        // Header UI (score & lifes)

        header = new GridPane();
        header.setBackground(new Background(new BackgroundFill(Paint.valueOf("#121212"), null, null)));
        header.setPadding(new Insets(0, 30, 0, 30));

        scoreTxt = new Text(0, 10, "Score: 0");
        scoreTxt.setFill(Paint.valueOf("#dddddd"));
        scoreTxt.setFont(Font.font(30));

        lifesTxt = new Text(0, 10, "Lifes: 0");
        lifesTxt.setFill(Paint.valueOf("#dddddd"));
        lifesTxt.setFont(Font.font(30));

        configBtn = new Button("Configuração");
        configBtn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#232323"), null, null)));
        configBtn.setTextFill(Paint.valueOf("#dddddd"));
        configBtn.setPrefWidth(120);
        configBtn.setPrefHeight(30);
        configBtn.setFocusTraversable(false);
        configBtn.setOnMouseClicked(e -> {
            setConfigVisible(true);
            Game.getInstance().setPaused(true);
        });


        header.addColumn(0, lifesTxt);
        header.addColumn(1, scoreTxt);
        header.addColumn(2, configBtn);
        header.getColumnConstraints().addAll(
                new ColumnConstraints(50, 50, 50),
                new ColumnConstraints(-1, -1, -1, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(-1, -1, -1, Priority.ALWAYS, HPos.CENTER, true)
        );
        borderPane.setTop(header);

        // Game Over UI

        gameOverPane = new BorderPane();
        gameOverText = new Text(0, 0, "Game Over");
        gameOverText.setFill(Paint.valueOf("ff0000"));
        gameOverText.setFont(Font.font(48));
        gameOverText.setTextAlignment(TextAlignment.CENTER);

        TableView<Score> tableView = new TableView<>();
        TableColumn<Score, Integer> c1 = new TableColumn<>("Seus últimos scores:");
        c1.setCellValueFactory(
                new PropertyValueFactory<>("score"));
        c1.setPrefWidth(320);
        tableView.getColumns().add(c1);
        tableView.setItems(scores);
        tableView.setEditable(false);
        tableView.setFocusTraversable(false);
        tableView.setBackground(null);

        gameOverPlayAgainBtn = new Button("Jogar Novamente");
        gameOverPlayAgainBtn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#232323"), null, null)));
        gameOverPlayAgainBtn.setTextFill(Paint.valueOf("#dddddd"));
        gameOverPlayAgainBtn.setPrefWidth(120);
        gameOverPlayAgainBtn.setPrefHeight(30);
        gameOverPlayAgainBtn.setFocusTraversable(false);
        gameOverPlayAgainBtn.setTranslateY(10);
        gameOverPlayAgainBtn.setOnMouseClicked(e -> {
            setGameOverVisible(false);
            Game.getInstance().resetGame();
        });

        gameOverPane.setTop(gameOverText);
        gameOverPane.setCenter(tableView);
        gameOverPane.setBottom(gameOverPlayAgainBtn);
        gameOverPane.setVisible(false);
        gameOverPane.setBackground(null);
        BorderPane.setAlignment(gameOverPane.getTop(), Pos.CENTER);
        BorderPane.setAlignment(gameOverPane.getBottom(), Pos.CENTER);
        gameOverPane.setMaxWidth(320);
        gameOverPane.setMaxHeight(300);

        // Configuration UI

        configsPane = new VBox();
        configsPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#111111dd"), null, null)));
        configTitle = new Text("Configurações");
        configTitle.setFill(Paint.valueOf("#dddddd"));
        configTitle.setFont(Font.font(30));
        configExit = new Button("Voltar ao jogo");
        configExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("#232323"), null, null)));
        configExit.setTextFill(Paint.valueOf("#dddddd"));
        configExit.setPrefWidth(120);
        configExit.setPrefHeight(30);
        configExit.setFocusTraversable(false);
        configExit.setOnMouseClicked(e -> {
            setConfigVisible(false);
            Game.getInstance().setPaused(false);
        });
        configsPane.setVisible(false);
        configDifficultyText = new Text("Selecione a dificuldade");
        configDifficultyText.setFill(Paint.valueOf("#dddddd"));
        configDifficulty =  new ChoiceBox(FXCollections.observableArrayList(
                "Easy", "Normal", "Hard")
        );
        configDifficulty.setValue("Easy");
        configDifficulty.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    changeDificulty(new_val);
                });
        System.out.println(configDifficulty.getSelectionModel().getSelectedItem());
        configsPane.getChildren().add(configTitle);
        configsPane.getChildren().add(configDifficultyText);
        configsPane.getChildren().add(configDifficulty);
        configsPane.getChildren().add(configExit);
        configsPane.setAlignment(Pos.TOP_CENTER);
        configsPane.setSpacing(20);
        configsPane.setMaxWidth(320);
        configsPane.setMaxHeight(200);
        configsPane.setPadding(new Insets(30, 30, 30, 30));

        gameStackPane.getChildren().add(gameOverPane);
        gameStackPane.getChildren().add(configsPane);
        borderPane.setCenter(gameStackPane);

    }

    public void setScores(List<Score> scores) {
        this.scores.setAll(scores);
    }

    public void changeDificulty(Number  new_val) {
        switch (new_val.intValue()) {
            case 0:
                Game.getInstance().changeDificulty(new_val.intValue());
            case 1:
                Game.getInstance().changeDificulty(new_val.intValue());
            case 2:
                Game.getInstance().changeDificulty(new_val.intValue());
        }
    }

    public void Update(long currentNanoTime, long deltaTime) {
        scoreTxt.setText("Score: " + Game.getInstance().getScore());
        lifesTxt.setText("Lifes: " + Game.getInstance().getLifes());

        gameOverPlayAgainBtn.setScaleX(1d + Math.abs(Math.sin(bounceAnimAux)) / 5d);
        bounceAnimAux += 0.05;
        if (bounceAnimAux > 3.14) bounceAnimAux = 0;
    }

    public void setGameOverVisible(boolean visible) {
        gameOverPane.setVisible(visible);
    }

    public void setConfigVisible(boolean visible) {
        configsPane.setVisible(visible);
    }

    public void setRoot(Group root) {
        root.getChildren().add(borderPane);
    }

    public void setCanvas(Canvas canvas) {
        gameStackPane.getChildren().add(0, canvas);
    }
}
