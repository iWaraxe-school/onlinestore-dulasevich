package by.issoft.domain;

import com.github.javafaker.Faker;

import java.sql.*;

public class DB {

    public static int categoryCount;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        return con;
    }

    public static void createCategoryTable() throws SQLException, ClassNotFoundException {
        Statement createCategoryTable = getConnection().createStatement();
        createCategoryTable.execute("CREATE TABLE CATEGORY (ID INT, NAME VARCHAR(255), PRIMARY KEY (ID))");
        getConnection().close();
    }

    public static void createProductTable() throws SQLException, ClassNotFoundException {
        Statement createProductTable = getConnection().createStatement();
        createProductTable.execute("CREATE TABLE PRODUCTS (RATE DOUBLE, PRICE DOUBLE, NAME VARCHAR(30), CATEGORY INT)");
        getConnection().close();
    }

    public static void deleteCategoryTable() throws SQLException, ClassNotFoundException {
        Statement createCategoryTable = getConnection().createStatement();
        createCategoryTable.execute("DROP TABLE IF EXISTS CATEGORY");
        getConnection().close();
    }

    public static void deleteProductsTable() throws SQLException, ClassNotFoundException {
        Statement createCategoryTable = getConnection().createStatement();
        createCategoryTable.execute("DROP TABLE IF EXISTS PRODUCTS");
        getConnection().close();
    }

    public static void addProducts(int productsNumber) throws SQLException, ClassNotFoundException {

        Faker faker = new Faker();

        String SQL = "INSERT INTO PRODUCTS (name, rate, price, category) VALUES (?, ?, ?, ?)";

        PreparedStatement p = getConnection().prepareStatement(SQL);
        for(int i=0; i<productsNumber; i++){
            p.setString(1, faker.app().name());
            p.setDouble(2, faker.number().randomDouble(2, 1, 5));
            p.setDouble(3, faker.number().randomDouble(2, 0, 1000));
            p.setInt(4, faker.number().numberBetween(1, 4));
            p.execute();
        }
    }

    public static void addCategory(int id, String category) throws SQLException, ClassNotFoundException {

        String SQL = "INSERT INTO CATEGORY (id, name) VALUES (?, ?)";

        PreparedStatement p = getConnection().prepareStatement(SQL);
        p.setInt(1, id);
        p.setString(2, category);
        p.execute();
        categoryCount++;
    }

    public static void top5() throws SQLException, ClassNotFoundException {

        Statement st = getConnection().createStatement();
        String sql = ("SELECT * FROM PRODUCTS ORDER BY PRICE DESC LIMIT 5;");
        ResultSet rs = st.executeQuery(sql);
        System.out.println("Top 5 most expensive products are: ");
        while(rs.next()) {
            String name = rs.getString("name");
            double rate = rs.getDouble("rate");
            double price = rs.getDouble("price");
            System.out.println("Name - " + name + "," + " Rate - " + rate + "," + " Price - " + price);
        }
        getConnection().close();
    }

    public static String getCategoryName(int id) throws SQLException, ClassNotFoundException {
        String categoryname = null;
        String SQL = "Select * from CATEGORY where id=" + id;
        Statement st2 = getConnection().createStatement();
        ResultSet rs2 = st2.executeQuery(SQL);
        if(id == 1){
            categoryname = "Book";
        } else if (id == 2){
            categoryname = "Milk";
        } else if (id == 3){
            categoryname = "Phone";
        }
        return categoryname;
    }

    public static void sort(String sortOption) throws SQLException, ClassNotFoundException {

        if(sortOption.equals("name") || sortOption.equals("price")){
        for(int i=1; i<categoryCount+1; i++){
            String SQL = "Select * from PRODUCTS where category=" + i + "order by " + sortOption;
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()) {
                String name = rs.getString("name");
                double rate = rs.getDouble("rate");
                double price = rs.getDouble("price");
                String cat = getCategoryName(i);
                System.out.println("Category " + cat + "," + " Name - " + name + "," +
                            " Rate - " + rate + "," + " Price - " + price);
                }
            }
        }
        else {
            for(int i=1; i<categoryCount+1; i++){
                String SQL = "Select * from PRODUCTS where category=" + i + "order by " + sortOption + " DESC";
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(SQL);
                while(rs.next()) {
                    String name = rs.getString("name");
                    double rate = rs.getDouble("rate");
                    double price = rs.getDouble("price");
                    String cat = getCategoryName(i);
                    System.out.println("Category " + cat + "," + " Name - " + name + "," +
                            " Rate - " + rate + "," + " Price - " + price);
                }
            }
        }
        getConnection().close();
    }
}
