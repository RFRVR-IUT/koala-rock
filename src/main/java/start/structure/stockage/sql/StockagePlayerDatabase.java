package start.structure.stockage.sql; //Votre package ici.

import start.structure.metier.entite.AuthPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockagePlayerDatabase {

    public void create(AuthPlayer element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO JOUEURS(login, mdpHache, selHachage) VALUES (?, ?, ?)";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, element.getLogin());
            st.setString(2, element.getHashedPassword());
            st.setBytes(3, element.getSalt());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(AuthPlayer element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "UPDATE JOUEURS SET mdpHache = ?, selHachage = ? WHERE login = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(3, element.getLogin());
            st.setString(1, element.getHashedPassword());
            st.setBytes(2, element.getSalt());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLogin(String login) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM JOUEURS WHERE login = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AuthPlayer getByLogin(String login) {
        AuthPlayer player = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM JOUEURS WHERE login = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            try (ResultSet result = st.executeQuery();) {
                if (result.next()) {
                    String password = result.getString("mdpHache");
                    byte[] salt = result.getBytes("selHachage");
                    player = new AuthPlayer(login, password, salt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public List<AuthPlayer> getAll() {
        List<AuthPlayer> playerList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM JOUEURS";
        try (
                PreparedStatement st = connection.prepareStatement(req);
                ResultSet result = st.executeQuery();
        ) {
            while (result.next()) {
                String login = result.getString("login");
                String password = result.getString("mdpHache");
                byte[] salt = result.getBytes("selHachage");
                AuthPlayer player = new AuthPlayer(login, password, salt);
                playerList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }
}