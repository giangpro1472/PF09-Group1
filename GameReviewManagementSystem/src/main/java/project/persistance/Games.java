package project.persistance;

public class Games {
    private int gameID;
    private String gameName;
    private String developers;
    private String releaseDate;
    private int genreID;
    private String genre;
    private double score;


    public Games() {
        this.gameID = 0;
        this.gameName = "";
        this.developers = "";
        this.releaseDate = "";
        this.genreID = 0;
        this.genre = "";
        this.score = 0.0;
    }


    public void insertGame() {
        gameName = Input.gameName(gameName);
        genreID = Input.genre();
        developers = Input.developers();
        releaseDate = Input.release();
    }

    public void displayGame() {
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-10s|%-25s|%-15s|%-20s|%-15s|%-5.1f/10|\n", 
        gameID, gameName, genre, developers, releaseDate, score);
    }

    public void displayGameDetail() {
        System.out.println(" ________________________________________");
        System.out.printf("|           User's Score: %-14.1f |\n", score);
        System.out.printf("|Game's Name: %-27s|\n",gameName);
        System.out.printf("|Genre:  %-32s|\n" ,genre);
        System.out.printf("|Developers: %-28s|\n",developers);
        System.out.printf("|Release Date: %-26s|\n",releaseDate);
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getGenreID() {
        return this.genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
