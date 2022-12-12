package start.structure.metier.manager; //Votre package ici.

import start.structure.metier.entite.Score;
import start.structure.stockage.sql.StockageScoreDatabase;

import java.util.List;

public class ScoreManager {

    private static ScoreManager instance = null;
    private StockageScoreDatabase stockage = new StockageScoreDatabase();

    private ScoreManager() {}

    public static ScoreManager getInstance() {
        if (instance ==null) instance = new ScoreManager();
        return instance;
    }

    public void createScore(int score, String login) {
        Score s = new Score(score);
        s.setLogin(login);
        stockage.create(s);
    }

    public void updateScore(int id, int score) {
        Score s = stockage.getById(id);
        s.setScore(score);
        stockage.update(s);
    }

    public void deleteScoreById(int id) {
        stockage.deleteById(id);
    }

    public void deleteScoreByLogin(String login) {
        stockage.deleteByLogin(login);
    }

    public Score getHighScoreByLogin(String login) { return stockage.getHighScore(login); }

    //public List<Score> getHighScores() { return stockage.get10HighScore(); }

    public Score getScoreById(int id) {
        return stockage.getById(id);
    }

    public List<Score> getScoresHistoryByLogin(String login) {
        return stockage.getByLogin(login);
    }

    public List<Score> getScores() {
        return stockage.getAll();
    }
}