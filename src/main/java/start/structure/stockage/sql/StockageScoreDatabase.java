package start.structure.stockage.sql; //Votre package ici.

import start.structure.metier.entite.Score;

import java.sql.*;
import java.util.*;

public class StockageScoreDatabase {

    public void create(Score element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO SCORES(score, horodatage, codeJeu, login) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setInt(1, element.getScore());
            st.setTimestamp(2, element.getHorodatage());
            st.setString(3, Score.getGameCode());
            if (!element.getLogin().isEmpty()) st.setString(4, element.getLogin());
            else st.setNull(4, Types.VARCHAR);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Score element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "UPDATE SCORES SET score = ? WHERE codeScore = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setInt(1, element.getScore());
            st.setInt(3, element.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLogin(String login) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCode());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM SCORES WHERE codeScore = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Score getById(int id) {
        Score score = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE codeScore = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setInt(1, id);
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    int scoreValue = result.getInt("score");
                    Timestamp time = result.getTimestamp("horodatage");
                    String login = result.getString("login");
                    score = new Score(scoreValue, time);
                    score.setId(id);
                    score.setLogin(login);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public Score getHighScore(String login) {
        Score score = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ? ORDER BY score";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCode());
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    int scoreValue = result.getInt("score");
                    Timestamp time = result.getTimestamp("horodatage");
                    int id = result.getInt("codeScore");
                    score = new Score(scoreValue, time);
                    score.setId(id);
                    score.setLogin(login);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

//    public List<Score> get10HighScore() {
//        List<Score> scoreList = new ArrayList<>();
//        SQLUtils utils = SQLUtils.getInstance();
//        Connection connection = utils.getConnection();
//        String req = "SELECT * FROM SCORES WHERE codeJeu = ? ORDER BY score DESC LIMIT 10";
//        try (
//                PreparedStatement st = connection.prepareStatement(req);
//        ) {
//            st.setString(1, Score.getGameCode());
//            try (ResultSet result = st.executeQuery();) {
//                while (result.next()) {
//                    int id = result.getInt("codeScore");
//                    int scoreValue = result.getInt("score");
//                    Timestamp time = result.getTimestamp("horodatage");
//                    String login = result.getString("login");
//                    Score score = new Score(scoreValue, time);
//                    score.setId(id);
//                    score.setLogin(login);
//                    scoreList.add(score);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return scoreList;
//    }

    /**
     * Renvoie l'historique des scores sur votre jeu.
     *
     * @param login
     * @return
     */
    public List<Score> getByLogin(String login) {
        List<Score> scoresList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCode());
            try (ResultSet result = st.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("codeScore");
                    int scoreValue = result.getInt("score");
                    Timestamp time = result.getTimestamp("horodatage");
                    Score score = new Score(scoreValue, time);
                    score.setId(id);
                    score.setLogin(login);
                    scoresList.add(score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoresList;
    }

    /**
     * Renvoie tous les scores de votre jeu.
     *
     * @return
     */
    public List<Score> getAll() {
        List<Score> scoreList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE codeJeu = ? ORDER BY score DESC";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setString(1, Score.getGameCode());
            try (ResultSet result = st.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("codeScore");
                    int scoreValue = result.getInt("score");
                    Timestamp time = result.getTimestamp("horodatage");
                    String login = result.getString("login");
                    Score score = new Score(scoreValue, time);
                    score.setId(id);
                    score.setLogin(login);
                    scoreList.add(score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    public Map<Integer,Double> getAllTemps() {
        Map<Integer,Double> scoreList = new LinkedHashMap<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM scoreTemps ORDER BY temps ASC";
        try (PreparedStatement st = connection.prepareStatement(req)){
            try(ResultSet result = st.executeQuery()){
                while (result.next()){
                    System.out.println(result);
                    int id = result.getInt("codeScore");
                    double temps = result.getDouble("temps");
                    String login = result.getString("login");
                    scoreList.put(id,temps);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return scoreList;
    }

    public String getLoginTemps(int id){
        String res = "";
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM scoreTemps WHERE codeScore = ?";
        try (PreparedStatement st = connection.prepareStatement(req)){
            st.setInt(1,id);
            try(ResultSet result = st.executeQuery()){
                if (result.next()){
                    res = result.getString("login");
                }
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        return res;
    }

    public int getDernierCode(){
        int res = 0;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM scoreTemps ORDER BY codeScore DESC LIMIT 1";
        try (PreparedStatement st = connection.prepareStatement(req)){
            try(ResultSet result = st.executeQuery()){
                if (result.next()){
                    res = result.getInt("codeScore");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}