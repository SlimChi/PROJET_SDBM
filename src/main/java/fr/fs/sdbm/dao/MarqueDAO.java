package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.MarqueSearch;

import java.sql.*;
import java.util.ArrayList;


public class MarqueDAO extends DAO<Marque, Marque>
{
	public MarqueDAO(Connection connexion)
	{
		super(connexion);
	}

	public ArrayList<Marque> getAll()	{
		ArrayList<Marque> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()){


			// Determine the column set column

			String strCmd = "SELECT id_marque,nom_marque from marque as P order by id_marque";
			ResultSet rs = stmt.executeQuery(strCmd);

			while (rs.next()) {
				liste.add(new Marque(rs.getInt(1), rs.getString(2)));
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
	public ArrayList<Marque> getLike(Marque objet) {
		return null;
	}


	@Override
	public Marque getByID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Marque objet)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
