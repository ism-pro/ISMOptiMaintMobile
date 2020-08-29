package org.ism.ismoptimaintmobile.ui.database;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import org.ism.ismoptimaintmobile.R;
import org.ism.ismoptimaintmobile.database.ConnectionClass;

public class DatabaseFragment extends PreferenceFragmentCompat {

    private ConnectionClass conn;

    private EditTextPreference prefIP ;
    private EditTextPreference prefInstance ;
    private EditTextPreference prefDBName ;

    private EditTextPreference prefUser ;
    private EditTextPreference prefPassword;

    private SwitchPreferenceCompat prefConnexion;





    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);


        prefIP = (EditTextPreference) findPreference("key_db_ip");
        prefInstance = (EditTextPreference) findPreference("key_db_instance");
        prefDBName = (EditTextPreference) findPreference("key_db_dbname");

        prefUser = (EditTextPreference) findPreference("key_db_user");
        prefPassword = (EditTextPreference) findPreference("key_db_password");

        conn = new ConnectionClass(prefIP.getText(), prefInstance.getText(), prefDBName.getText(), prefUser.getText(), prefPassword.getText());



        prefConnexion = (SwitchPreferenceCompat) findPreference("key_db_connect");
        prefConnexion.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                try{
                    if(prefConnexion.isChecked()){
                        Log.e("ASK", "On start Connexion is checked ! ");
                        if(!conn.isConnValid(1000)){
                            conn.open();
                        }
                    }else{
                        Log.e("ASK", "On start Connexion is not checked ! ");
                        conn.close();
                    }




                       /* Statement stmt = ConnectionClass.con.createStatement();
                        String sql = "select * from tabl";
                        ResultSet rs = stmt.executeQuery(sql);
                        Log.e(tag:"ASK", msg:"----------------------------");
                        while(rs.next()){
                            //Log.e(tag:"ASK", msg:rs.getString(columnLabel:"id")+ "     " + rs.getString())
                        }
                        */
                 }catch (Exception e){

                }
                return true;
            }
        });
    }





    public Preference getPrefIP() {
        return prefIP;
    }

    public void setPrefIP(EditTextPreference prefIP) {
        this.prefIP = prefIP;
    }

    public Preference getPrefInstance() {
        return prefInstance;
    }

    public void setPrefInstance(EditTextPreference prefInstance) {
        this.prefInstance = prefInstance;
    }

    public Preference getPrefDBName() {
        return prefDBName;
    }

    public void setPrefDBName(EditTextPreference prefDBName) {
        this.prefDBName = prefDBName;
    }

    public Preference getPrefUser() {
        return prefUser;
    }

    public void setPrefUser(EditTextPreference prefUser) {
        this.prefUser = prefUser;
    }

    public Preference getPrefPassword() {
        return prefPassword;
    }

    public void setPrefPassword(EditTextPreference prefPassword) {
        this.prefPassword = prefPassword;
    }

    public Preference getPrefConnexion() {
        return prefConnexion;
    }

    public void setPrefConnexion(SwitchPreferenceCompat prefConnexion) {
        this.prefConnexion = prefConnexion;
    }
}