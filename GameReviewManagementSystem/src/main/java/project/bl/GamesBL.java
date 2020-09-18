package project.bl;

import java.util.List;
import java.util.Scanner;

import project.dal.GamesDAL;
import project.persistance.Clrsrc;
import project.persistance.Games;
import project.persistance.Input;
import project.ui.AccountUI;
import project.ui.Header;

public class GamesBL {
    public static GamesDAL dal = new GamesDAL();
    public ImagesBL bl = new ImagesBL();
    public ReviewBL reviewBL = new ReviewBL();

    public List<Games> getAllGames() {
        return dal.getAllGames();
    }

    public List<Games> gameNameResult(String gameName) {
        return dal.searchByName(gameName);
    }

    public List<Games> gameGenreResult(int genre) {
        return dal.searchByGenre(genre);
    }

    public List<Games> rankingGamesList() {
        return dal.rankingGames();
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    };

    public void displayDeleteGame(String username) {
        if (getAllGames().isEmpty()) {
            System.out.println("There are no games, please add more :(");
        } else {
            Clrsrc.clrsrc();
            displayResult();
            for (Games games : getAllGames()) {
                games.displayGame();
            }
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            System.out.println(" ");
            while (true) {
                try {
                    System.out.println("+=========================================+");
                    System.out.println("| 1. See game and review details.         |");
                    System.out.println("| 2. Delete games.                        |");
                    System.out.println("| 3. Return to menu.                      |");
                    System.out.println("+=========================================+");
                    System.out.print("Choose: ");
                    int choice = getScanner().nextInt();
                    if (choice == 1) {
                        int gameID = Input.gameID();
                        if (gameID == dal.checkGameID(gameID)) {
                            gameDetails(username, gameID);
                            break;
                        } else {
                            break;
                        }
                    } else if (choice == 2) {
                        deleteGame();
                        break;
                    } else if (choice < 1 || choice > 3) {
                        System.out.println("(*) Retype(1 or 3) >:(");
                    } else {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("(*) Retype(1 or 3) >:(");
                }
            }
        }
    }

    
    public void insertGame(Games games, String username) {
        while (true) {
            System.out.print("(*) Do you want to post this game?(Y/N): ");
            String choice = getScanner().nextLine();
            if (choice.isEmpty()) {
                System.out.println("(*) Please choose Y or N :( ");
                continue;
            } else if (choice.equalsIgnoreCase("y")) {
                boolean check = dal.insertGame(games);
                if (check) {
                    boolean continues = true;
                    while (continues) {
                        bl.insertImage(dal.getGameID());
                        System.out.print("(*) Do you want to add more image?(Y/N): ");
                        continues = getScanner().nextLine().equalsIgnoreCase("y");
                    }
                    System.out.println("");
                    System.out.println("(*) Post game successfully :> ");
                    System.out.println("(*) Now you need to review this game so everyone can see it :) ");
                    reviewBL.postReview(username, dal.getGameID());
                }
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("(*) Cancel Post Review >:( ");
                break;
            } else {
                System.out.println("(*) Please choose Y or N :( ");
                continue;
            }
        }
    }

    public void deleteGame() {
        int gameID = Input.gameID();
        if (gameID == dal.checkGameID(gameID)) {
            while (true) {
                System.out.print("(*) Do you want to delete this game?(Y/N): ");
                String choice = getScanner().nextLine();
                if (choice.isEmpty()) {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                } else if (choice.equalsIgnoreCase("y")) {
                    dal.deleteGame(gameID);
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("(*) Cancel Delete :( ");
                    break;
                } else {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                }
            }
        }
    }

    public void rankingGames(String username) {
        if (rankingGamesList().isEmpty()) {
            System.out.println("(*) We will rate it as soon as possible, please come back later :(");
        } else {
            Clrsrc.clrsrc();
            displayResult();
            for (Games games : rankingGamesList()) {
                games.displayGame();
            }
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            System.out.println(" ");
            viewGameAndReviews(username);
        }
    }

    public void searchByName(String username, String gameName) {
        if (gameNameResult(gameName).isEmpty()) {
            System.out
                    .println("(*) We found " + dal.getSearchNameResult(gameName) + " result based on your search :( ");
            System.out.println("(*) Sorry we cant not find this game name :( ");
        } else {
            System.out
                    .println("(*) We found " + dal.getSearchNameResult(gameName) + " result based on your search :) ");
            System.out.print("(*) Press enter to continue :)");
            getScanner().nextLine();
            Clrsrc.clrsrc();
            displayResult();
            for (Games games : gameNameResult(gameName)) {
                games.displayGame();
            }
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            System.out.println(" ");
            viewGameAndReviews(username);
        }
    }

    public void searchByGenre(String username, int genre) {
        if (gameGenreResult(genre).isEmpty()) {
            System.out.println("(*) There are no games in this genre lately! Please comeback later :(");
        } else {
            System.out.println("(*) We found " + dal.getGenreSearchResult(genre) + " result based on your search :) ");
            System.out.print("(*) Press enter to continue :)");
            getScanner().nextLine();
            Clrsrc.clrsrc();
            displayResult();
            for (Games games : gameGenreResult(genre)) {
                games.displayGame();
            }
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            System.out.println(" ");
            viewGameAndReviews(username);
        }

    }

    public void viewGameAndReviews(String username) {
        while (true) {
            try {
                System.out.println("+=========================================+");
                System.out.println("| 1. See game and review details.         |");
                System.out.println("| 2. Return to menu.                      |");
                System.out.println("+=========================================+");
                System.out.print("Choose: ");
                int choice = getScanner().nextInt();
                if (choice == 1) {
                    int gameID = Input.gameID();
                    if (gameID == dal.checkGameID(gameID)) {
                        gameDetails(username, gameID);
                        break;
                    } else {
                        break;
                    }

                } else if (choice < 1 || choice > 2) {
                    System.out.println("(*) Retype(1 or 2) >:(");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("(*) Retype(1 or 2) >:( ");
            }
        }
    }

    public void gameDetails(String username, int gameID) {
        while (true) {
            Clrsrc.clrsrc();
            Header.intro();
            gameSummary(gameID);
            userReviews(gameID);
            if (username.isEmpty()) {
                userNotLogin();
                break;
            } else {
                if (!reviewsGame(username, gameID)) {
                    break;
                }
            }

        }
    }

    public boolean reviewsGame(String username, int id) {
        boolean result = true;
        while (true) {
            try {
                System.out.println(" ");
                System.out.println("+=========================================+");
                System.out.println("| 1. Write a reviews.                     |");
                System.out.println("| 2. Return to menu.                      |");
                System.out.println("+=========================================+");
                System.out.print("Choose: ");
                int choice = getScanner().nextInt();
                if (choice == 1) {
                    reviewBL.postReview(username, id);
                    break;
                } else if (choice < 1 || choice > 2) {
                    System.out.println("(*) Retype(1 or 2) >:(");
                } else {
                    result = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("(*) Retype(1 or 2) >:( ");
            }
        }
        return result;
    }

    public void displayResult() {
        System.out.println(
                "+--------------------------------------------------------------------------------------------------+");
        System.out.println(
                "|                                        SEARCH RESULT                                             |");
        System.out.println(
                "+--------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-10s|%-25s|%-15s|%-20s|%-15s|%-8s|\n", "Game ID", "Game Name", "Genre", "Developers",
                "Release Date", "Score");
    }

    public void gameSummary(int gameID) {
        System.out.println("+----------------------------------------+");
        System.out.println("|                  Games                 |");
        System.out.println("+----------------------------------------+");
        dal.getGameByID(gameID).displayGameDetail();
        bl.displayImageByID(gameID);
        System.out.println("|________________________________________|");
    }

    public void userReviews(int gameID) {
        System.out.println("");
        System.out.println("__________________________________________");
        System.out.println("");
        System.out.println("+----------------------------------------+");
        System.out.println("|              User's Reviews            |");
        System.out.println("+----------------------------------------+");
        reviewBL.displayReviewByID(gameID);
    }

    public void userNotLogin() {
        while (true) {
            System.out.println("");
            System.out.println("__________________________________________");
            System.out.println("");
            System.out.println("  (*) Wait a minute, Who are you >:( (*)   ");
            System.out.println("You need to logged-in to rating this games!");
            System.out.println("Please loggin first >:(                    ");

            try {
                System.out.println("+=========================================+");
                System.out.println("| 1. Login.                               |");
                System.out.println("| 2. Return to menu.                      |");
                System.out.println("+=========================================+");
                System.out.print("Choose: ");
                int choice = getScanner().nextInt();
                if (choice == 1) {
                    AccountUI.login();
                    break;
                } else if (choice < 1 || choice > 2) {
                    System.out.println("(*) Retype(1 or 2) >:(");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("(*) Retype(1 or 2) >:(");
            }
        }
    }

}
