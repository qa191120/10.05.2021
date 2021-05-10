package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {

    private String m_conn;

    public CustomerDAO(String m_conn) {
        this.m_conn = m_conn;
    }

    public void insertCustomer(Customer c) {
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            if (conn != null) {

                String sql = String.format("INSERT INTO customer (name, stars, birth_year) " +
                        "VALUES ( '%s', %f, %d )", c.getName(), c.getStars(), c.getBirth_year());
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer(Customer c, int id) {
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            if (conn != null) {
                String sql = String.format(
                        "UPDATE customer set name = '%s', stars = %f, birth_year = %d " +
                                " WHERE id = %d"
                        , c.getName(), c.getStars(), c.getBirth_year(), id);
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            if (conn != null) {
                String sql = String.format("DELETE from customer where id = " + id);
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public ArrayList<Customer> getAllCustomers(){

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(m_conn)) {

            if (conn != null) {

                String sql = "SELECT * FROM Customer";

                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Customer e = new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getFloat("stars"),
                            rs.getInt("birth_year"));
                    customers.add(e);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  customers;
    }

    public Customer getCustomerById(int id){
        // try to connect to db
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            // check if connection succeed
            if (conn != null) {

                // prepare query string
                String sql = String.format("Select * from Customer where id = %d", id );

                // prepare statement
                Statement stmt = conn.createStatement();

                // fire query
                ResultSet rs = stmt.executeQuery(sql);

                // read results
                while (rs.next()) {
                    Customer e = new Customer(
                            rs.getInt("Id"),
                            rs.getString("Name"),
                            rs.getFloat("Stars"),
                            rs.getInt("birth_year"));
                    return e;
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

}