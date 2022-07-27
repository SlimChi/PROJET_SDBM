package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;
import fr.fs.sdbm.service.MarqueSearch;
import fr.fs.sdbm.service.ServiceArticle;
import fr.fs.sdbm.service.ServiceMarque;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class GestionArticleController {
    // Description de la table
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, Integer> idColumn;
    @FXML
    private TableColumn<Article, String> nomColumn;
    @FXML
    private TableColumn<Article, Float> titrageColumn;
    @FXML
    private TableColumn<Article, Integer> volumeColumn;

    // description des champs de recherche
    @FXML
    private TextField libelleSearch;
    @FXML
    private ComboBox<Marque> marqueSearch;
    @FXML
    private ComboBox<Fabricant> fabricantSearch;
    @FXML
    private ComboBox<Pays> paysSearch;
    @FXML
    private ComboBox<Continent> continentSearch;
    @FXML
    private ComboBox<Couleur> couleurSearch;
    @FXML
    private ComboBox<TypeBiere> typeSearch;
    @FXML
    private MenuApp menuApp;

    private ServiceMarque serviceMarque;
    private ServiceArticle serviceArticle;

    // Initialisation de la vue
    @FXML
    private void initialize() {
        //serviceMarque = new ServiceMarque();
        serviceArticle = new ServiceArticle();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        titrageColumn.setCellValueFactory(cellData -> cellData.getValue().titrageProperty().asObject());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asObject());
        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceArticle.getContinentFiltre()));
        continentSearch.getItems().add(0,new Continent(0, "Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        paysSearch.valueProperty().addListener(observable -> filterArticle());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.valueProperty().addListener(observable -> filterArticle());

        marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        marqueSearch.valueProperty().addListener(observable -> filterArticle());

        couleurSearch.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
        couleurSearch.valueProperty().addListener(observable -> filterArticle());

        typeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBiereFiltre()));
        typeSearch.valueProperty().addListener(observable -> filterArticle());

    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        filterArticle();
    }


    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays("", "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);
        filterArticle();
    }

    @FXML
    private void filterArticle() {
        ArticleSearch articleSearch = new ArticleSearch();
        articleSearch.setLibelle(libelleSearch.getText());

        if (paysSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());
        if (continentSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
        if (fabricantSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setFabricant(fabricantSearch.getSelectionModel().getSelectedItem());
        if (marqueSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setMarque(marqueSearch.getSelectionModel().getSelectedItem());
        if (couleurSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setCouleur(couleurSearch.getSelectionModel().getSelectedItem());
        if (typeSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setTypeBiere(typeSearch.getSelectionModel().getSelectedItem());
        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles(articleSearch)));
    }
}