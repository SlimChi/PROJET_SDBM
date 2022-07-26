package fr.fs.sdbm.dao;

import java.sql.Connection;


public class DaoFactory {

    private DaoFactory() {

    }

    private static final Connection connexion = SDBMConnect2.getInstance();

    public static ContinentDAO2 getContinentDAO() {
        return new ContinentDAO2(connexion);
    }

    public static PaysDAO getPaysDAO() {
        return new PaysDAO(connexion);
    }

    public static FabricantDAO getFabricantDAO() {
        return new FabricantDAO(connexion);
    }

    public static MarqueDAO getMarqueDAO() {
        return new MarqueDAO(connexion);
    }
}
