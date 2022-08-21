package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;
import fr.fs.sdbm.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AjouterModifierController {


    @FXML
    private TextField nomLabel;

    @FXML
    private TextField prixLabel;

    @FXML
    private TextField stockLabel;

    @FXML
    private TextField titrageLabel;

    @FXML
    private ComboBox<String> volumLabel;
    @FXML
    private ComboBox<Marque> marqueLabel;
    @FXML
    private ComboBox<Couleur> couleurLabel;
    @FXML
    private ComboBox<TypeBiere> typeLabel;

    private ServiceArticle serviceArticle;
    private MenuApp menuApp;

    private Stage dialogStage;
    private boolean confirmed;


    private Article article;
    @FXML
    private Label titre;
    @FXML
    private Label nomAlert;
    private boolean nomCorrect;

    public void setMainApp(MenuApp menuApp) {
        this.menuApp = menuApp;

    }

    @FXML
    private void initialize() {
        serviceArticle = new ServiceArticle();

        marqueLabel.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        couleurLabel.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
        typeLabel.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBiereFiltre()));

        volumLabel.getItems().add(0, "Volume");
        volumLabel.getItems().add(1, "33");
        volumLabel.getItems().add(2, "75");
        volumLabel.getSelectionModel().select(0);
        volumLabel.valueProperty().addListener(observable -> filterArticle());


    }

    @FXML
    private void filterArticle() {
        ArticleSearch articleSearch = new ArticleSearch();

        if (volumLabel.getSelectionModel().getSelectedItem().equals("Volume"))
            articleSearch.setVolume(0);
        else articleSearch.setVolume(Integer.parseInt(volumLabel.getSelectionModel().getSelectedItem()));

    }

    @FXML
    public void annuler() {
        confirmed = false;
        dialogStage.close();

    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        confirmed = false;
    }


    public void modifierArticle(Article article) {
        this.article = article;
        if (article.getId() != null) {
            nomLabel.setText(article.getLibelle());
            titrageLabel.setText(String.valueOf(article.getTitrage().floatValue()));
            typeLabel.getSelectionModel().select(article.getTypeBiere());
            volumLabel.getSelectionModel().select(article.getVolume());
            marqueLabel.getSelectionModel().select(article.getMarque());
            prixLabel.setText(article.getPrixAchat().toString());
            couleurLabel.getSelectionModel().select(article.getCouleur());
            stockLabel.setText(String.valueOf(article.getStock()));
            isConfirmed();
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void validerArticle() {

        this.article.setLibelle(nomLabel.getText());
        this.article.setPrixAchat(Float.valueOf(prixLabel.getText()));
        this.article.setTitrage(Float.valueOf(titrageLabel.getText()));
        this.article.setVolume(Integer.valueOf(volumLabel.getSelectionModel().getSelectedItem()));
        this.article.setCouleur(couleurLabel.getSelectionModel().getSelectedItem());
        this.article.setMarque(marqueLabel.getSelectionModel().getSelectedItem());
        this.article.setTypeBiere(typeLabel.getSelectionModel().getSelectedItem());
        this.article.setStock(Integer.parseInt(stockLabel.getText()));

        if (dialogStage.getTitle().equals("Modifier article")) {
            serviceArticle.updateArticle(this.article);

        }
        if (dialogStage.getTitle().equals("Ajouter article")) {
            serviceArticle.insertArticle(this.article);
        }
        confirmed = true;
        dialogStage.close();
    }


    public void setTitle(String titre) {
        this.titre.setText(titre);
    }

   
}
