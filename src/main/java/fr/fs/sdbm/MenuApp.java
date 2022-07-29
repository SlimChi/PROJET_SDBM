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
    private Stage windowAjouter;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


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
        public void ajouterArticle() {

            try {
                windowAjouter=new Stage();
                FXMLLoader myFXMLloader = new FXMLLoader();
                myFXMLloader.setLocation(MenuApp.class.getResource("Modifer_ajouter_articles.fxml"));
                AnchorPane ajouterAncorpan=myFXMLloader.load();
                AjouterController ajouterController=myFXMLloader.getController();
                ajouterController.setMainApp(this);

                windowAjouter.initModality(Modality.WINDOW_MODAL);
                windowAjouter.initOwner(primaryStage);
                windowAjouter.setTitle("Ajouter un article");
                windowAjouter.setScene(new Scene(ajouterAncorpan));
                windowAjouter.showAndWait();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
