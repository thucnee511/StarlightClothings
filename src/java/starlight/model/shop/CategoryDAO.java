/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.model.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import starlight.entity.DBContext;

/**
 *
 * @author Admin
 */
public class CategoryDAO {

    private static final String GET__ALL_CATEGORY
            = "SELECT [CateID],[CateName]"
            + " FROM [dbo].[Categories]";

    public List<CategoryDTO> getAllCates()
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        List<CategoryDTO> list = new ArrayList<>();
        con = DBContext.getConnection();
        stm = con.prepareStatement(GET__ALL_CATEGORY);
        rs = stm.executeQuery();
        while (rs.next()) {
            String cateID = rs.getString("CateID");
            String name = rs.getString("CateName");
            CategoryDTO p = new CategoryDTO(cateID, name);
            list.add(p);
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
}
