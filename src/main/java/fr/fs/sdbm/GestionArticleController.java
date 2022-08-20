package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;
import fr.fs.sdbm.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.RangeSlider;

import java.util.Optional;


public class GestionArticleController {
    // Description de la table
    @FXML
    private AnchorPane detailShow;

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
    private Button reset;
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
    private ComboBox<String> toutContenance;

    @FXML
    private RangeSlider slider;

    @FXML
    private MenuApp menuApp;


    private ServiceArticle serviceArticle;


    @FXML
    private Label Volume_label;
    @FXML
    private Label continent;
    @FXML
    private Label couleur;
    @FXML
    private Label fabricant;
    @FXML
    private Label ID_label;
    @FXML
    private Label libelle;

    @FXML
    private Label marque;
    @FXML
    private Label pays;

    @FXML
    private Label prixAchat;
    @FXML
    private Label titrage;
    @FXML
    private Label type;

    private Article articleSelected;
    private Article article;
    @FXML
    private Button imprimer;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifer;
    @FXML
    private Button ajouter;

    public GestionArticleController() {
    }


    // Initialisation de la vue
    @FXML
    private void initialize() {

        serviceArticle = new ServiceArticle();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        titrageColumn.setCellValueFactory(cellData -> cellData.getValue().titrageProperty().asObject());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asObject());
        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceArticle.getContinentFiltre()));
        continentSearch.getItems().add(0, new Continent(0, "Continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        marqueSearch.getItems().add(0, new Marque(0, "Marque"));
        marqueSearch.valueProperty().addListener(observable -> filterArticle());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        //  paysSearch.getItems().add(0, new Pays(0, "Pays"));
        paysSearch.valueProperty().addListener(observable -> filterArticle());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.getItems().add(0, new Fabricant(0, "Fabricant"));
        fabricantSearch.valueProperty().addListener(observable -> filterArticle());

        couleurSearch.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
        couleurSearch.getItems().add(0, new Couleur(0, "Couleur"));
        couleurSearch.valueProperty().addListener(observable -> filterArticle());


        typeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBiereFiltre()));
        typeSearch.getItems().add(0, new TypeBiere(0, "Bière"));
        typeSearch.valueProperty().addListener(observable -> filterArticle());

        //toutContenance.setItems(FXCollections.observableArrayList(serviceArticle.getContenanceFiltre()));
        toutContenance.getItems().add(0, "Volume");
        toutContenance.getItems().add(1, "33");
        toutContenance.getItems().add(2, "75");
        toutContenance.getSelectionModel().select(0);
        toutContenance.valueProperty().addListener(observable -> filterArticle());

        slider.setMin(0);
        slider.setMax(26);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(2);
        slider.setHighValue(26);
        slider.setLowValue(0);

        slider.highValueProperty().addListener(observable -> filterArticle());
        slider.lowValueProperty().addListener(observable -> filterArticle());

        articleTable.getSelectionModel().selectedItemProperty().addListener((observale, oldValue, newValue) -> read(newValue));
        detailDisable(false);
    }


    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        }
        paysSearch.getItems().add(0, new Pays("", "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);
        filterArticle();
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
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
        if (toutContenance.getSelectionModel().getSelectedItem().equals("Volume"))
            articleSearch.setVolume(0);
        else articleSearch.setVolume(Integer.parseInt(toutContenance.getSelectionModel().getSelectedItem()));

        articleSearch.setTitrageMin(slider.getLowValue());
        articleSearch.setTitrageMax(slider.getHighValue());

        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles(articleSearch)));
    }


    private void detailDisable(boolean bool) {
        detailShow.setVisible(bool);

    }

    public void reset() {
        libelleSearch.setText("");
        marqueSearch.getSelectionModel().clearAndSelect(0);
        fabricantSearch.getSelectionModel().clearAndSelect(0);
        continentSearch.getSelectionModel().clearAndSelect(0);
        paysSearch.getSelectionModel().clearAndSelect(0);
        couleurSearch.getSelectionModel().clearAndSelect(0);
        typeSearch.getSelectionModel().clearAndSelect(0);
        toutContenance.getSelectionModel().clearAndSelect(0);
        detailDisable(false);

    }


    private void read(Article article) {
        if (article != null) {
            ID_label.setText(String.valueOf(article.getId()));
            libelle.setText(article.getLibelle());
            Volume_label.setText(String.valueOf(article.getVolume()));
            prixAchat.setText(String.valueOf(article.getPrixAchat()));
            pays.setText(article.getMarque().getPays().getLibelle());
            titrage.setText(String.valueOf(article.getTitrage()));
            marque.setText(article.getMarque().getLibelle());
            fabricant.setText(article.getMarque().getFabricant().getLibelle());
            couleur.setText(article.getCouleur().getLibelle());
            type.setText(String.valueOf(article.getTypeBiere()));
            continent.setText(article.getMarque().getPays().getContinent().getLibelle());
            detailDisable(true);
        }

    }

    @FXML
    public void update() {
        articleSelected = articleTable.getSelectionModel().getSelectedItem();
        menuApp.ajouterModifierArticle(articleSelected, "Modifier article");

        filterArticle();

    }

    @FXML
    public void create() {

        if (article == null) {
            Article article = new Article();
            menuApp.ajouterModifierArticle(article, "Ajouter article");

        }
        filterArticle();
    }

    @FXML
    public void delete() {
        articleSelected = articleTable.getSelectionModel().getSelectedItem();
        if (articleSelected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.getButtonTypes().clear();

            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);

            alert.setContentText("Voulez vous supprimer l'article ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                serviceArticle.deleteArticle(articleSelected);

            }

        }
        filterArticle();
    }


    @FXML
    public void imprimer() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        printerJob.showPrintDialog(menuApp.getPrimaryStage());
        boolean success = printerJob.printPage(articleTable);
        if (success) {
            printerJob.endJob();
        }
    }
}