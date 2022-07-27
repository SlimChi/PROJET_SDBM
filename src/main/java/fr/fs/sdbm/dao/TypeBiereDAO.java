package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.Couleur;
import fr.fs.sdbm.metier.TypeBiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeBiereDAO extends DAO <TypeBiere, TypeBiere> {

    public TypeBiereDAO (Connection connexion){
        super(connexion);
    }

    @Override
    public ArrayList<TypeBiere> getAll() {
        ArrayList<TypeBiere> list = new ArrayList<>();

        try (Statement stmt = connexion.createStatement()){
            String strCmd = "SELECT distinct T.id_type, nom_type from TYPEBIERE as T join article on T.id_type = article.ID_TYPE order by nom_type";
            ResultSet rs = stmt.executeQuery(strCmd);
            TypeBiere typebiereLu;
            while (rs.next())
            {
                typebiereLu = new TypeBiere(rs.getInt(1), rs.getString(2));
                list.add(typebiereLu);
            }
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
    @Override
    public TypeBiere getByID(int id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(TypeBiere objet)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(TypeBiere object)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(TypeBiere object)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<TypeBiere> getLike(TypeBiere objet)
    {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }
}