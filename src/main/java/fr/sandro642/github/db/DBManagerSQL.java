//MIT License
//
//Copyright (c) 2023 Sandro Soria
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

package fr.sandro642.github.db;

import fr.sandro642.github.MineWatch;

import java.sql.*;


/**
 * @Author: Sandro642
 * @Date: 2020/6/3 15:00
 * @File: MineWatch
 * @Version: 1.0
 * @Description: Main class of the plugin
 * @JDK: 1.8
 * @Software: IntelliJ IDEA
 * @Github: https://github.com/Sandro642/MineWatch
 * @Contact: https://sandro642.me/
 * @License: MIT License
 * @Project: MineWatch
 * @Server: Minecraft
 * @PluginTag: MineWatch
 * @PluginMainClass: fr.sandro642.github.MineWatch
 * @PluginVersion: 1.0.0
 * @PluginDescription: A plugin to watch your server
 */

public class DBManagerSQL {


    /**
     * var for the connection
     * @param DB_URL
     * @param USER
     * @param PASS
     * @param TABLE_NAME
     * @return
     */

    private static final String DB_URL = MineWatch.getInstance().getConfig().getString("MineWatch.DB_URI");
    private static final String USER = MineWatch.getInstance().getConfig().getString("MineWatch.USER");
    private static final String PASS = MineWatch.getInstance().getConfig().getString("MineWatch.PASS");
    private static final String TABLE_NAME = MineWatch.getInstance().getConfig().getString("MineWatch.TABLE");


    /**
     * Get the connection
     * @return
     * @throws SQLException
     */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    /**
     * Close the connection
     * @param conn
     * @param stmt
     * @param rs
     */

    public static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    /**
     * Create the table
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */

    public static void createTableClass() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Étape 1: Enregistrement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Étape 2: Ouverture de la connexion
            System.out.println("Connexion à la base de données...");
            conn = DBManagerSQL.getConnection();

            // Étape 3: Création de l'objet Statement pour envoyer des requêtes SQL à la base de données
            stmt = conn.createStatement();

            // Étape 4: Requête SQL de création de la table "ma_table"
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "World VARCHAR(255),"
                    + "Player VARCHAR(255),"
                    + "Action VARCHAR(255),"
                    + "Timestamp DATETIME"
                    + ")";

            // Étape 5: Exécuter la requête de création de la table
            stmt.executeUpdate(query);
            System.out.println("La table '" + TABLE_NAME + "' a été créée avec succès.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Étape 6: Fermer les ressources
            DBManagerSQL.closeResources(conn, stmt, null);
        }
    }


    /**
     * Init the connection
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */

    public static void initConnection() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Étape 1: Enregistrement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Étape 2: Ouverture de la connexion
            System.out.println("Connexion à la base de données...");
            conn = DBManagerSQL.getConnection();

            // Étape 3: Création de l'objet Statement pour envoyer des requêtes SQL à la base de données
            stmt = conn.createStatement();

            // Étape 4: Exécution d'une requête SQL (exemple: sélection de toutes les entrées de la table "table_joueurs")
            String sql = "SELECT * FROM " + TABLE_NAME;
            rs = stmt.executeQuery(sql);

            // Si la requête s'exécute sans erreur, cela signifie que la connexion est réussie.
            System.out.println("La connexion à la base de données est établie avec succès.");

        } catch (SQLException se) {
            // Gestion des erreurs JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Gestion des erreurs Class.forName
            e.printStackTrace();
        } finally {
            // Étape 6: Fermer les ressources
            DBManagerSQL.closeResources(conn, stmt, rs);
        }
        System.out.println("Connexion à la base de données terminée.");
    }


    /**
     * Add an element to the table
     * @param action
     * @param playerName
     * @param worldName
     * @param timestamp
     */

    public static void addElement(String action, String playerName, String worldName, long timestamp) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Établir la connexion
            conn = DBManagerSQL.getConnection();

            // Requête SQL d'insertion avec un ID auto-incrémenté
            String insertQuery = "INSERT INTO " + TABLE_NAME + " (Action, Player, World, Timestamp) VALUES (?, ?, ?, ?)";

            // Préparer la requête avec les paramètres
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, action);
            pstmt.setString(2, playerName);
            pstmt.setString(3, worldName);
            pstmt.setTimestamp(4, new Timestamp(timestamp));

            // Exécuter la requête d'insertion
            int rowsAffected = pstmt.executeUpdate();

            // Récupérer l'ID auto-incrémenté
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            DBManagerSQL.closeResources(conn, pstmt, null);
        }
    }

}
