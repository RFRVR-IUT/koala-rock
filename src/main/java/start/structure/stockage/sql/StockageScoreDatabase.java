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

    public List<Double> getTempsByLogin(String login) {
        List<Double> tempsList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM scoreTemps WHERE login = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req)
        ) {
            st.setString(1, login);
            try (ResultSet result = st.executeQuery()) {
                while (result.next()) {
                    double temps = result.getInt("temps");
                    tempsList.add(temps);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempsList;
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

    public void addTemps(double temps, String login){
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO scoreTemps (codeScore,temps,login) VALUES (?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(req)){
            st.setInt(1,getDernierCode()+1);
            st.setDouble(2,temps);
            st.setString(3,login);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     *
     *
     *
     * TRON
     *
     *
     *
     *
     */
    public List<Score> getAllTRON() {
        List<Score> scoreList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE codeJeu = ? ORDER BY score DESC";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, Score.getGameCodeTRON());
            try (ResultSet result = st.executeQuery();) {
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

    public List<Score> getByLoginTRON(String login) {
        List<Score> scoresList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTRON());
            try (ResultSet result = st.executeQuery();) {
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

    public void createTRON(Score element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO SCORES(score, horodatage, codeJeu, login) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setInt(1, element.getScore());
            st.setTimestamp(2, element.getHorodatage());
            st.setString(3, Score.getGameCodeTRON());
            if (!element.getLogin().isEmpty()) st.setString(4, element.getLogin());
            else st.setNull(4, Types.VARCHAR);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLoginTRON(String login) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTRON());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Score getHighScoreTRON(String login) {
        Score score = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ? ORDER BY score";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTRON());
            try (ResultSet result = st.executeQuery();) {
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

    /**
     *
     *
     *
     *
     * CB
     *
     *
     *
     *
     */
    public List<Score> getAllCB() {
        List<Score> scoreList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE codeJeu = ? ORDER BY score DESC";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, Score.getGameCodeCB());
            try (ResultSet result = st.executeQuery();) {
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

    public List<Score> getByLoginCB(String login) {
        List<Score> scoresList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeCB());
            try (ResultSet result = st.executeQuery();) {
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

    public void createCB(Score element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO SCORES(score, horodatage, codeJeu, login) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setInt(1, element.getScore());
            st.setTimestamp(2, element.getHorodatage());
            st.setString(3, Score.getGameCodeCB());
            if (!element.getLogin().isEmpty()) st.setString(4, element.getLogin());
            else st.setNull(4, Types.VARCHAR);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLoginCB(String login) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeCB());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Score getHighScoreCB(String login) {
        Score score = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ? ORDER BY score";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeCB());
            try (ResultSet result = st.executeQuery();) {
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

    /**
     *
     *
     *
     *
     * TETRIS
     *
     *
     *
     *
     */
    public List<Score> getAllTETRIS() {
        List<Score> scoreList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE codeJeu = ? ORDER BY score DESC";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, Score.getGameCodeTETRIS());
            try (ResultSet result = st.executeQuery();) {
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

    public List<Score> getByLoginTETRIS(String login) {
        List<Score> scoresList = new ArrayList<>();
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTETRIS());
            try (ResultSet result = st.executeQuery();) {
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

    public void createTETRIS(Score element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "INSERT INTO SCORES(score, horodatage, codeJeu, login) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setInt(1, element.getScore());
            st.setTimestamp(2, element.getHorodatage());
            st.setString(3, Score.getGameCodeTETRIS());
            if (!element.getLogin().isEmpty()) st.setString(4, element.getLogin());
            else st.setNull(4, Types.VARCHAR);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLoginTETRIS(String login) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "DELETE FROM SCORES WHERE login = ? AND codeJeu = ?";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTETRIS());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Score getHighScoreTETRIS(String login) {
        Score score = null;
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String req = "SELECT * FROM SCORES WHERE login = ? AND codeJeu = ? ORDER BY score";
        try (
                PreparedStatement st = connection.prepareStatement(req);
        ) {
            st.setString(1, login);
            st.setString(2, Score.getGameCodeTETRIS());
            try (ResultSet result = st.executeQuery();) {
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

}