package PROJECTS.control_layer;

import PROJECTS.boundary_layer.adminWorkSpace;
import PROJECTS.boundary_layer.userWorkSpace;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static PROJECTS.entity_layer.adminDataManipulation.*;

public class systemAdmin extends User{
    public systemAdmin() {
    }


    public systemAdmin(String account, String password, String name, String profile) {
        super(account, password, name, profile);
    }

    public void UserLogin(){
        new adminWorkSpace(getName(), getProfile());
    }

    public boolean addVerification(String account, String password, String name, String profile) {
        // Check account, password, and name length requirements
        if (account.length() < 4 || password.length() < 6 || name.isEmpty()) {
            return false; // Return false if any of the conditions are not met
        }

        // Check profile value (1, 2, 3, or 4)
        int profileValue;
        try {
            profileValue = Integer.parseInt(profile);
            if (profileValue < 1 || profileValue > 4) {
                return false; // Return false if profile value is not within the allowed range
            }
        } catch (NumberFormatException e) {
            return false; // Return false if profile is not a valid integer
        }

        // If all conditions are met, return true
        Map<String, String> userAddInfo = new HashMap<>();
        userAddInfo.put("account",account);
        userAddInfo.put("password",password);
        userAddInfo.put("name",name);
        userAddInfo.put("profile",profile);

        return addUserToDatabase(userAddInfo);//connect to entity layer;
    }


    public Vector<String> searchUser(String account){
        Vector<String> newRowData = new Vector<>();
        if (account.length() < 4){
            return null;
        }else {
            return (newRowData=searchUserFromDatabase(account));
        }
    }

    public boolean updateVerification(String account, String password, String name, String profile){
        // Check account, password, and name length requirements
        if (account.length() < 4 || password.length() < 6 || name.isEmpty()) {
            return false; // Return false if any of the conditions are not met
        }

        // Check profile value (1, 2, 3, or 4)
        int profileValue;
        try {
            profileValue = Integer.parseInt(profile);
            if (profileValue < 1 || profileValue > 4) {
                return false; // Return false if profile value is not within the allowed range
            }
        } catch (NumberFormatException e) {
            return false; // Return false if profile is not a valid integer
        }

        // If all conditions are met, return true
        Map<String, String> userUpdateInfo = new HashMap<>();
        userUpdateInfo.put("account",account);
        userUpdateInfo.put("password",password);
        userUpdateInfo.put("name",name);
        userUpdateInfo.put("profile",profile);

        return updateUserToDatabase(userUpdateInfo);//connect to entity layer;
    }

    public boolean suspendAccount(String account){
        if (account.length() < 4){
            return false;
        }else {
            return (suspendAccountToDatabase(account));
        }
    }

}
