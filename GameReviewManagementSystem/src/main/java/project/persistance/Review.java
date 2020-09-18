package project.persistance;

public class Review {
    private int reviewID;
    private String username;
    private String postDate;
    private String content;
    private int gameID;
    private int score;

    public void insert(String username, int id) {
        this.username = username;
        this.gameID = id;
        this.score = Input.score();
        this.content = Input.content(content);
    }

    public void displayReview() {
        System.out.println(" ________________________________________ ");
        System.out.printf("|                Score: %-17d|\n",score);
        System.out.printf("|Review-ID: %-29s|\n" ,reviewID);
        System.out.printf("|Username: %-30s|\n" ,username);
        System.out.printf("|Game-ID: %-31s|\n" ,gameID);
        System.out.printf("|Post Date: %-29s|\n",postDate);
        System.out.printf("|Content: %-31s|\n",content);
        System.out.println("|________________________________________|");
    }

    public void displayReviewInDay() {
        System.out.println(" ________________________________________ ");
        System.out.printf("|                Score: %-17d|\n",score);
        System.out.printf("|Username: %-30s|\n" ,username);
        System.out.printf("|Post Date: %-29s|\n",postDate);
        System.out.printf("|Content: %-31s|\n",content);
        System.out.println("|________________________________________|");
    }

    public int getReviewID() {
        return this.reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}