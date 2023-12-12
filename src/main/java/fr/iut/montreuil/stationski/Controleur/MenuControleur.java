package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Modele.Ambiance.SoundGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.ResourceBundle;
import fr.iut.montreuil.stationski.Main;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuControleur  implements Initializable {

    @FXML private HBox boxConnection;
    @FXML private HBox boxLogin;
    @FXML private HBox boxSignup;
    @FXML private HBox boxMenu;
    @FXML private TextField textfieldLogin;
    @FXML private TextField textfieldPwd;
    @FXML private TextField newLoginTF;
    @FXML private TextField newPwdTF;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SoundGame.playSoundEffect(3);
        SoundGame.loop();
    }

    @FXML
    private void playButton() {

/*
        CreationMap createur = new CreationMap();
        createur.start(new Stage());
*/

        if (SessionStart.sessionOn()) {
            SoundGame.playSoundEffect(1);
            try {

                Stage stage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game_menu.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
                stage.setTitle("MapMenu");
                stage.setScene(scene);
                stage.show();



            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.boxMenu.setVisible(false);
            boxConnection.setVisible(true);


            /*





            // Création de la grille pour organiser les composants
            GridPane grid = new GridPane();


            // Création de la scène
            Scene scene = new Scene(grid, 300, 200);


            Stage settingsStage = new Stage();
            settingsStage.initModality(Modality.APPLICATION_MODAL);

            TextField usernameField = new TextField();
            PasswordField passwordField = new PasswordField();

            // Labels pour les champs de texte
            Label usernameLabel = new Label("Nom d'utilisateur:");
            Label passwordLabel = new Label("Mot de passe:");

            // Bouton de connexion
            Button loginButton = new Button("Se Connecter");
            VBox settingsLayout = new VBox(10);

            grid.add(usernameField, 1, 0);
            grid.add(passwordLabel, 0, 1);
            grid.add(passwordField, 1, 1);
            grid.add(loginButton, 1, 2);
            settingsLayout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
            settingsLayout.setPadding(new Insets(20));



            Scene settingsScene = new Scene(settingsLayout, 720, 550);
            settingsStage.setScene(settingsScene);
            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if(UserConnect.connectUser(username, password)){
                    SessionStart.startSession(username);
                    settingsStage.close();

                }

            });

            settingsStage.showAndWait();
            */


            /*}*/
        }
    }

    @FXML
    private void settingsButton() {
        try {

            System.out.println("Les paramètres ont été ouverts !");

            Stage settingsStage = new Stage();
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            Label settingsLabel = new Label("Options de paramètres...");
            Button closeSettingsButton = new Button("Fermer les paramètres");
            closeSettingsButton.setOnMouseClicked(e -> settingsStage.close());

            VBox settingsLayout = new VBox(10);
            settingsLayout.getChildren().addAll(settingsLabel, closeSettingsButton);
            settingsLayout.setPadding(new Insets(20));

            Scene settingsScene = new Scene(settingsLayout, 720, 550);
            settingsStage.setScene(settingsScene);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
            settingsStage.setTitle("MapMenu");
            settingsStage.setScene(scene);
            settingsStage.showAndWait();

            SoundGame.playSoundEffect(1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void exitButton() {
        SoundGame.playSoundEffect(1);
        System.out.println("L'application a été fermée !");
        System.exit(0);

    }


    @FXML
    private void loginBouton() {
        this.boxConnection.setVisible(false);
        this.boxLogin.setVisible(true);

    }


    @FXML
    private void signupBouton() {
        this.boxConnection.setVisible(false);
        this.boxSignup.setVisible(true);

    }


    @FXML
    private void home() {
        this.boxMenu.setVisible(true);
        this.boxSignup.setVisible(false);
        this.boxLogin.setVisible(false);
        this.boxConnection.setVisible(false);
    }


    @FXML
    private void connect(){
        if(UserConnect.connectUser(this.textfieldLogin.getText(), this.textfieldPwd.getText())){
            this.boxMenu.setVisible(true);
            this.boxLogin.setVisible(false);
        }
    }

    @FXML
    private void signup(){//ne vérifie pas que l'identifiant est unique.
        if(this.newLoginTF.getText()!= null &&  this.newPwdTF.getText() !=null) {
            Object[] tab = {this.newLoginTF.getText(), this.newPwdTF.getText()};
            Connect.executeQuery("Insert into utilisateur (pseudo, identifiant, motdepasse, pointsExperience) value(null, ?, ?, 0);", tab);
            System.out.println("Inscription réussie");

            this.boxSignup.setVisible(false);
            this.boxMenu.setVisible(true);
        }
    }
}
