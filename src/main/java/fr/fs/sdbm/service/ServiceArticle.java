package fr.fs.sdbm.service;

import fr.fs.sdbm.dao.DaoFactory;
import fr.fs.sdbm.dao.TypeBiereDAO;
import fr.fs.sdbm.metier.*;

import java.util.ArrayList;

public class ServiceArticle {
    private ArrayList<Pays> paysFiltre;
    private ArrayList<Couleur> couleurFiltre;
    private ArrayList<TypeBiere> typeBiereFiltre;
    private ArrayList<Marque> marqueFiltre;
    private ArrayList<Continent> continentFiltre;
    private ArrayList<Fabricant> fabricantFiltre;
    public ServiceArticle()
    {
        paysFiltre = DaoFactory.getPaysDAO().getAll();
        continentFiltre = DaoFactory.getContinentDAO().getAll();
        fabricantFiltre = DaoFactory.getFabricantDAO().getAll();
        couleurFiltre =  DaoFactory.getCouleurDAO().getAll();
        typeBiereFiltre =  DaoFactory.getTypeBiereDAO().getAll();
        marqueFiltre =  DaoFactory.getMarqueDAO().getAll();
        Fabricant fabricant = new Fabricant();
        fabricant.setLibelle("Choisir un fabricant");
        fabricantFiltre.add(0, fabricant);
        Marque marque = new Marque();
        marque.setLibelle("Choisir un fabricant");
        marqueFiltre.add(0, marque);
    }


    public ArrayList<Pays> getPaysFiltre()
    {
        return paysFiltre;
    }
    public ArrayList<Continent> getContinentFiltre()
    {
        return continentFiltre;
    }
    public ArrayList<Fabricant> getFabricantFiltre()
    {
        return fabricantFiltre;
    }

    public ArrayList<Couleur> getCouleurFiltre(){return couleurFiltre;}

    public ArrayList<TypeBiere> getTypeBiereFiltre(){return typeBiereFiltre;}
    public ArrayList<Marque> getMarqueFiltre(){return marqueFiltre;}
    public ArrayList<Article> getFilteredArticles(ArticleSearch articleSearch)
    {
        return DaoFactory.getArticleDAO().getLike(articleSearch);
    }


}
