package project.persistance;

public class Images {
    private int gameID;
    private String imageName;

    public Images() {
        this.gameID = 0;
        this.imageName = "";
    }

    public void insertImages(int id) {
        this.gameID = id;
        this.imageName = Input.image();
    }

    public void displayImageByID() {
        System.out.printf("|Game's image: %-26s|\n",imageName);
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String gameName) {
        this.imageName = gameName;
    }

}
