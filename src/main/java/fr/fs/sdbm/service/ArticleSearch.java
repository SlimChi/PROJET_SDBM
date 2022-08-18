package fr.fs.sdbm.service;
import fr.fs.sdbm.metier.*;

public class ArticleSearch {

    private int id;
    private String libelle;

    private double titrageMin;
    private double titrageMax;

    private float prixAchat;
    private int volume;

    private Pays pays;

    private Continent continent;


    private Fabricant fabricant;

     private float titrage;
    private Marque marque;
    private Couleur couleur;
    private TypeBiere typeBiere;

    public ArticleSearch()
    {
        marque = new Marque();
        couleur = new Couleur();
        typeBiere = new TypeBiere();
        fabricant = new Fabricant();
        pays = new Pays();
        continent = new Continent();
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public TypeBiere getTypeBiere() {
        return typeBiere;
    }

    public void setTypeBiere(TypeBiere typeBiere) {
        this.typeBiere = typeBiere;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
    public double getTitrageMin() {
        return titrageMin;
    }

    public void setTitrageMin(double titrageMin) {
        this.titrageMin = titrageMin;
    }

    public double getTitrageMax() {
        return titrageMax;
    }

    public void setTitrageMax(double titrageMax) {
        this.titrageMax = titrageMax;
    }
    public float getTitrage() {
        return titrage;
    }

    public void setTitrage(float titrage) {
        this.titrage = titrage;
    }
}