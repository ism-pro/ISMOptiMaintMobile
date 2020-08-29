package org.ism.ismoptimaintmobile.database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private String ip = "192.168.1.29";
    private String instance = "ISMOPTIMAINT";
    private String user = "sa";
    private String password = "br@lic0";
    private String dbName = "OptiMaint";
    private Boolean connected = false;

    public static Connection con;


    public ConnectionClass() {
    }

    public ConnectionClass(String ip, String instance,  String dbName, String user, String password) {
        this.ip = ip;
        this.instance = instance;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }


    public void open(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String ConnURL = "jdbc:jtds:sqlserver://" + ip + ";instance=" + instance + ";user=" + user + ";password=" + password + ";databaseName=" + dbName + ";";
            //Log.e("ASK","SQL Conn URL >> " + ConnURL + " ???");

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(ConnURL, user, password);



            Log.e("ASK","Connection On !");
            connected = true;
        }catch (Exception e){
            connected = false;
            Log.e("ASK","SQL Conn URL >> Error connection !!!");
            Log.e("ASK","EXCEPTION" + e.getMessage());
        }
    }

    /**
     *
     */
    public void close(){
        try {
            con.close();
            Log.e("ASK","Connection Off !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isConnValid() throws SQLException {
        if(con == null) return false;
        return isConnValid(500);
    }

    public boolean isConnValid(int timeout) throws SQLException {
        if(con == null) return false;
        return con.isValid(500);
    }

    public boolean isConnected(){
        return connected;
    }

}
