package fr.fs.sdbm.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marque
{

    private int id;
    private String libelle;
    private Pays pays;
    private Fabricant fabricant;

    public Marque(){

    }

    public Marque(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId()
    {
	return id;
    }

    public void setId(Integer id)
    {
	this.id=id;
    }

    public String getLibelle()
    {
	return libelle;
    }

    public void setLibelle(String libelle)
    {
	this.libelle=libelle;
    }

    public Pays getPays()
    {
	return pays;
    }

    public void setPays(Pays pays)
    {
	this.pays = pays;
    }

    public Fabricant getFabricant()
    {
	return fabricant;
    }

    public void setFabricant(Fabricant fabricant)
    {
	this.fabricant = fabricant;
    }

    @Override
    public String toString()
    {
	return libelle;
    }


}
