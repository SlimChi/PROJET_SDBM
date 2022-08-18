package fr.fs.sdbm;


import fr.fs.sdbm.metier.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {
    private Stage primaryStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    private Stage dialogStage;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Article articleSelected;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion des Articles");
        showArticle();
    }

    private void showArticle() {
        try {
            // Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionArticle.fxml"));
            AnchorPane menuLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            GestionArticleController controller = loader.getController();
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterModifierArticle(Article articleSelected, String titre) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("Modifer_ajouter_articles.fxml"));
            AnchorPane ajouterModifierOverview = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(ajouterModifierOverview);
            AjouterModifierController ajouterModifierController = loader.getController();
            ajouterModifierController.setDialogStage(dialogStage);
            ajouterModifierController.modifierArticle(articleSelected);
            ajouterModifierController.setTitle(titre);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
