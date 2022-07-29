package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AjouterController {

    @FXML
    private ComboBox<Marque> marqueLabel;
    @FXML
    private ComboBox<Couleur> couleurLabel;
    @FXML
    private ComboBox<TypeBiere> typeLabel;
    private ServiceArticle serviceArticle;
    private MenuApp menuApp;
    public void setMainApp(MenuApp menuApp) {
        this.menuApp = menuApp;

    }

    @FXML
    private void initialize() {
        serviceArticle = new ServiceArticle();
        marqueLabel.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        couleurLabel.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
        typeLabel.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBiereFiltre()));
    }


}
