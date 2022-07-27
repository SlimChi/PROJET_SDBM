package fr.fs.sdbm.metier;

public class TypeBiere
{
    private int id;
    private String libelle;
    
    public TypeBiere (int id, String libelle)
    {
	this.id = id;
	this.libelle = libelle;
    }
    public TypeBiere()
	{
		
	}
	public Integer getId()
    {
	return id;
    }
    @Override
    public String toString()
    {
	return libelle;
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
}