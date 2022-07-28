package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;
import fr.fs.sdbm.service.MarqueSearch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, ArticleSearch> {
    public ArticleDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Article> getLike(ArticleSearch articleSearch) {
        ResultSet rs;
        ArrayList<Article> liste = new ArrayList<>();
        String procedureStockee = "{call SP_Vue_Article(?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {



            cStmt.setString(1, articleSearch.getLibelle());
            cStmt.setInt(2, articleSearch.getVolume());
            cStmt.setInt(3, articleSearch.getMarque().getId());

            //cStmt.setFloat(3, 0);
            //cStmt.setFloat(4, 9);

            cStmt.setInt(4, articleSearch.getCouleur().getId());
            cStmt.setInt(5, articleSearch.getTypeBiere().getId());
            cStmt.setInt(6, articleSearch.getFabricant().getId());
            cStmt.setString(7, articleSearch.getPays().getId());
            cStmt.setInt(8, articleSearch.getContinent().getId());
            cStmt.setDouble(9, articleSearch.getTitrageMin());
            cStmt.setDouble(10,articleSearch.getTitrageMax());

            //cStmt.setNull(5, Types.INTEGER);
            //cStmt.setNull(6, Types.INTEGER);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                // création d'un nouvel article à partir d'une ligne du resultset
                Article newArticle = new Article();
                newArticle.setId(rs.getInt(3));
                newArticle.setLibelle(rs.getString(4));
                newArticle.setVolume(rs.getInt(6));
                newArticle.setTitrage(rs.getFloat(7));
                newArticle.setPrixAchat(rs.getFloat(5));
                newArticle.setCouleur(new Couleur(rs.getInt(8),rs.getString(9)));
                newArticle.setTypeBiere(new TypeBiere(rs.getInt(1),rs.getString(2)));

                Marque newMarque = new Marque();
                newMarque.setPays(new Pays(rs.getString(10), rs.getString(11), new Continent(rs.getInt(12), rs.getString(13))));
                newMarque.setId(rs.getInt(15));
                newMarque.setLibelle(rs.getString(14));
                newMarque.setFabricant(new Fabricant(rs.getInt(16),rs.getString(17)));

                //  System.out.println(rs.getString(11),);
                newArticle.setMarque(newMarque);
                liste.add(newArticle);
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
    public ArrayList<Article> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article getByID(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(Article objet) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Article object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Article object) {
        // TODO Auto-generated method stub
        return false;
    }
}
