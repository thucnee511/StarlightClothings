/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package starlight.model.user;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import starlight.entity.DBContext;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private static final String GET_GOOGLE_USER
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users] WHERE [Email] = ? OR [UserID] = ?";
    private static final String GET_USER_BY_ID
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users] WHERE [UserID] = ?";
    private static final String CREATE_USER
            = "INSERT INTO [dbo].[Users]([UserID],[Username],[Password],[Email],[Phone],[Address],[RoleID])"
            + " VALUES (?,?,?,?,?,?,1)";
    private static final String SYSTEM_LOGIN
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users] WHERE ([Username] = ? OR [Email] = ?) AND [Password] = ?";
    private static final String GET_USER_BY_NAME_OR_EMAIL
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users] WHERE ([Username] = ? OR [Email] = ?)";
    private static final String GET_ALL_USERS
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users]";
    private static final String UPDATE_USER
            = "UPDATE [dbo].[Users]"
            + "   SET [Username] = ?"
            + "      ,[Phone] = ?"
            + "      ,[Address] = ?"
            + "      ,[RoleID] = ?"
            + " WHERE [UserID] = ?";
    private static final String DELETE_USER
            = "DELETE FROM [dbo].[Users]"
            + " WHERE [UserID] = ?";
    private static final String SEARCH_USER
            = "SELECT [UserID],[Username],[Email],[Phone],[Address],[RoleID]"
            + " FROM [dbo].[Users] WHERE [Username] LIKE ?";

    public List<UserDTO> searchUser(String keyword)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(SEARCH_USER);
        stm.setString(1, "%" + keyword + "%");
        rs = stm.executeQuery();
        List<UserDTO> list = new ArrayList<>();
        while (rs.next()) {
            String userID = rs.getString("UserID");
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("roleID");
            UserDTO user = new UserDTO(userID, username, email, phone, address, roleID);
            list.add(user);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }

    public UserDTO createSystemUser(String username, String password,
            String email, String phone, String address)
            throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement stm;

        con = DBContext.getConnection();
        stm = con.prepareStatement(CREATE_USER);
        String userID = generateUserID();
        stm.setString(1, userID);
        stm.setString(2, username);
        stm.setString(3, password);
        stm.setString(4, email);
        stm.setString(5, phone);
        stm.setString(6, address);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            stm.close();
        }
        UserDTO user = getUser(userID);
        return user;
    }

    public UserDTO loginSystemUser(String _username, String password)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        stm = con.prepareStatement(SYSTEM_LOGIN);
        stm.setString(1, _username);
        stm.setString(2, _username);
        stm.setString(3, password);
        rs = stm.executeQuery();
        UserDTO user = null;
        if (rs.next()) {
            String userID = rs.getString("UserID");
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("RoleID");
            user = new UserDTO(userID, username, email, phone, address, roleID);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return user;
    }

    public UserDTO getUser(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_USER_BY_ID);
        stm.setString(1, userID);
        rs = stm.executeQuery();
        UserDTO user = null;
        if (rs.next()) {
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("RoleID");
            user = new UserDTO(userID, username, email, phone, address, roleID);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return user;
    }

    public UserDTO geGoogleUser(String email, String userID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_GOOGLE_USER);
        stm.setString(1, email);
        stm.setString(2, userID);
        rs = stm.executeQuery();
        UserDTO user = null;
        if (rs.next()) {
            String username = rs.getString("Username");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("RoleID");
            user = new UserDTO(userID, username, email, phone, address, roleID);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return user;
    }

    public UserDTO getUserByUsernameOrEmail(String _username, String _email)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_USER_BY_NAME_OR_EMAIL);
        stm.setString(1, _username);
        stm.setString(2, _email);
        rs = stm.executeQuery();
        UserDTO user = null;
        if (rs.next()) {
            String userID = rs.getString("Username");
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("RoleID");
            user = new UserDTO(userID, username, email, phone, address, roleID);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return user;
    }

    public UserDTO createUserFromGoogleLogin(GoogleUserDTO googleUser)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;

        con = DBContext.getConnection();
        stm = con.prepareStatement(CREATE_USER);
        stm.setString(1, googleUser.getId());
        stm.setString(2, generateUsernameFromEmail(googleUser.getEmail()));
        stm.setString(3, generatePassword(8));
        stm.setString(4, googleUser.getEmail());
        stm.setString(5, "N/A");
        stm.setString(6, "N/A");
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            stm.close();
        }

        UserDTO user = getUser(googleUser.getId());
        return user;
    }

    private String generateUserID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private String generateUsernameFromEmail(String email) {
        int index = email.indexOf('@');
        return email.substring(0, index);
    }

    private String generatePassword(int length) {
        String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
        String validCharacters = "";

        validCharacters += LOWERCASE;
        validCharacters += UPPERCASE;
        validCharacters += DIGITS;
        validCharacters += SPECIAL_CHARACTERS;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            password.append(validCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

    public List<UserDTO> getAllUsers()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_ALL_USERS);
        rs = stm.executeQuery();
        List<UserDTO> list = new ArrayList<>();
        while (rs.next()) {
            String userID = rs.getString("UserID");
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String phone = rs.getString("Phone");
            String address = rs.getString("Address");
            int roleID = rs.getInt("roleID");
            UserDTO user = new UserDTO(userID, username, email, phone, address, roleID);
            list.add(user);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }

    public boolean updateUser(String userID, String roleID, String username,
            String phone, String address)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;

        con = DBContext.getConnection();
        stm = con.prepareStatement(UPDATE_USER);
        stm.setString(1, username);
        stm.setString(2, phone);
        stm.setString(3, address);
        stm.setString(4, roleID);
        stm.setString(5, userID);
        rs = stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
        return rs > 0;
    }

    public boolean deleteUser(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;

        con = DBContext.getConnection();
        stm = con.prepareStatement(DELETE_USER);
        stm.setString(1, userID);
        rs = stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
        return rs > 0;
    }

    public static void main(String[] args) {
        try {
            UserDAO dao = new UserDAO();
            UserDTO userid = dao.loginSystemUser("ngocngocthuc@gmail.com", "05112003");
            System.out.println(userid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
