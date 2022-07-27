package fr.fs.sdbm.dao;


import fr.fs.sdbm.metier.Couleur;
import fr.fs.sdbm.metier.Fabricant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CouleurDAO extends DAO<Couleur, Couleur> {
    public CouleurDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Couleur> getAll() {
        ArrayList<Couleur> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {


            // Determine the column set column

            String strCmd = "SELECT id_couleur,nom_couleur from couleur order by nom_couleur";
            ResultSet rs = stmt.executeQuery(strCmd);

            while (rs.next()) {
                Couleur couleur = new Couleur();
                couleur.setId(rs.getInt(1));
                couleur.setLibelle( rs.getString(2));
                liste.add(couleur);
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public Couleur getByID(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(Couleur objet) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Couleur object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Couleur object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Couleur> getLike(Couleur objet) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }
}