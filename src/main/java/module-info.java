module fr.fs.sdbm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;

    opens fr.fs.sdbm to javafx.fxml;
    exports fr.fs.sdbm;
}