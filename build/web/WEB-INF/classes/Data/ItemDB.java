/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Item;
import java.sql.*;
import java.util.*;

/**
 *
 * @author regin
 */
public class ItemDB {

    public static void addItem(String ItemCode, String ItemName, String Category, String Rating, String description, String imageURL) {
        Item item = new Item();
        item.setItemCode(ItemCode);
        item.setName(ItemName);
        item.setCategory(Category);
        item.setRating(Rating);
        item.setDescription(description);
        item.setImageURL(imageURL);
        addItem(item);
    }

    public static int addItem(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Item (ItemCode, Name, Category, Description, Rating, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getItemCode());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getDescription());
            ps.setString(5, item.getRating());
            ps.setString(6, item.getImageURL());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Item> getAllItems() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Item";
        ArrayList<Item> items = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setCategory(rs.getString("Category"));
                item.setDescription(rs.getString("Description"));
                item.setName(rs.getString("Name"));
                item.setItemCode(rs.getString("ItemCode"));
                item.setImageURL(rs.getString("ImageURL"));
                item.setRating(rs.getString("Rating"));
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return items;
    }

    public static Item getItem(String ItemCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Item WHERE ItemCode = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ItemCode);
            rs = ps.executeQuery();
            Item item = new Item();
            if (rs.next()) {
                item.setCategory(rs.getString("Category"));
                item.setDescription(rs.getString("Description"));
                item.setImageURL(rs.getString("ImageURL"));
                item.setItemCode(rs.getString("ItemCode"));
                item.setName(rs.getString("Name"));
                item.setRating(rs.getString("Rating"));
            }
            return item;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
               DBUtil.closeResultSet(rs);
               DBUtil.closePreparedStatement(ps);
               pool.freeConnection(connection);
        }
    }

    public static ArrayList<String> getItemCodes() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Item";
        ArrayList<String> itemCodes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                itemCodes.add(rs.getString("ItemCode"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return itemCodes;

    }
}
