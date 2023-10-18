package PROJECTS.entity_layer;

import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class adminDataManipulation {

    /**
     * This method is suitable for the administrator workSpace,
     * and this function is to return information about all user's information in the database
     * @return DefaultTableModel : Write all users information into the tableModel and return it directly
     */
    public static DefaultTableModel viewFromDatabase() {
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT accountNo, password, name, description,status FROM t_user_account JOIN t_user_profile ON t_user_account.t_u_pro_no = t_user_profile.pro_no;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Account", "Password", "Name", "Description","Status"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String accountNo = resultSet.getString("accountNo");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String status = resultSet.getBoolean("status")==true?"active":"inactive";

                // Add fetched data to the table model
                Object[] rowData = {accountNo, password, name, description,status};
                myModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return myModel;
    }


    /**
     * This method is used to add new users to the database
     * @param userAddInfo a hashmap data type ,It contains information about the user to be added to the database
     * @return Return true if adding succeeds otherwise false
     */
    public static boolean addUserToDatabase(Map<String, String> userAddInfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_user_account (accountNo, password, name, t_u_pro_no) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAddInfo.get("account"));
            preparedStatement.setString(2, userAddInfo.get("password"));
            preparedStatement.setString(3, userAddInfo.get("name"));
            preparedStatement.setString(4, userAddInfo.get("profile"));

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }



    /**
     * This method is used by administrators to find users
     *
     * @param account account information
     * @return Returns a Vector containing user information including "Account No", "Password", "Name", "Description","Status"
     */
    public static Vector<String> searchUserFromDatabase(String account){
        Vector<String> newRowData = new Vector<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT accountNo, password, name, description,status FROM t_user_account JOIN t_user_profile ON t_user_account.t_u_pro_no = t_user_profile.pro_no where accountNo = ?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Account", "Password", "Name", "Description","Status"};

            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                newRowData.addElement(resultSet.getString("accountNo"));
                newRowData.addElement(resultSet.getString("password"));
                newRowData.addElement(resultSet.getString("name"));
                newRowData.addElement(resultSet.getString("description"));
                newRowData.addElement(resultSet.getBoolean("status")==true?"active":"inactive");
                return newRowData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }

        return null;
    }




    /**
     * This method is used by administrators to update user information
     *
     * @param updateAddInfo a Map<String, String>  contain new user information
     * @return boolean : if count=1 then return true(database return)
     */
    public static boolean updateUserToDatabase(Map<String, String> updateAddInfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set password = ?,name =?,t_u_pro_no = ? where accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(4, updateAddInfo.get("account"));
            preparedStatement.setString(1, updateAddInfo.get("password"));
            preparedStatement.setString(2, updateAddInfo.get("name"));
            preparedStatement.setString(3, updateAddInfo.get("profile"));

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }


    /**
     * This method is used by the administrator to deactivate（suspend） the account
     *
     * @param account account information
     * @return if suspend success return true
     */
    public static boolean suspendAccountToDatabase(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set status = false where accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account);

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DButil.clos(connection, preparedStatement, resultSet);
            return false; //可以改到下面
        }
    }
}
