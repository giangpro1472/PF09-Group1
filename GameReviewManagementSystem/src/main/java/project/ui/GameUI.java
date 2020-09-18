package project.ui;

import java.util.Scanner;

import project.bl.GamesBL;
import project.bl.ReviewBL;
import project.persistance.Clrsrc;
import project.persistance.Games;
import project.persistance.Input;

public class GameUI {

    public static ReviewBL reviewBL = new ReviewBL();
    public static GamesBL gamesBL = new GamesBL();

    private static Scanner getScanner() {
        return new Scanner(System.in);
    };

    public static void rankingGames(String username) {
        Clrsrc.clrsrc();
        gamesBL.rankingGames(username);
        System.out.print("(*) Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void displayAllGames(String username) {
        gamesBL.displayDeleteGame(username);
        System.out.print("(*) Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void displayReviewInDay() {
        Clrsrc.clrsrc();
        Header.intro();
        reviewBL.displayReviewInDay();
        System.out.print("(*) Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void searchByGenre(String username) {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|             Search by Genre             |");
        System.out.println("+=========================================+");
        int id = 0;
        id = Input.genre();
        gamesBL.searchByGenre(username, id);
        System.out.print("(*) Press Enter to return to menu! ");
        getScanner().nextLine();
    }

    public static void searchByName(String username) {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|              Search by Name             |");
        System.out.println("+=========================================+");
        String gameName = "";
        gameName = Input.gameName(gameName);
        gamesBL.searchByName(username, gameName);
        System.out.print("(*) Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void gamesReviewManagementMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("+=========================================+");
            System.out.println("|    Games & Review's Management Menu     |");
            System.out.println("+=========================================+");
            System.out.println("| 1. Display all Review's in day.         |");
            System.out.println("| 2. Display all Games.                   |");
            System.out.println("| 3. Insert Games.                        |");
            System.out.println("| 0. Return to Main menu                  |");
            System.out.println("+=========================================+");
            choice = Input.choice(0, 3);
            switch (choice) {
                case 1:
                    displayReviewInDay();
                    break;
                case 2:
                    displayAllGames(username);
                    break;
                case 3:
                    postGame(username);
                    break;
                default:
                    break;
            }

        }
        System.out.print("(*) Return to Menu --> Press enter to continue :^) ");
        getScanner().nextLine();
    }

    public static void searchDisplayGames(String username) {
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("+=========================================+");
            System.out.println("|         Search & Display Games          |");
            System.out.println("+=========================================+");
            System.out.println("| 1. Ranking top 10 Games.                |");
            System.out.println("| 2. Search by Game's Name.               |");
            System.out.println("| 3. Search by Game's Genre.              |");
            System.out.println("| 0. Return to Main menu                  |");
            System.out.println("+=========================================+");
            choice = Input.choice(0, 3);
            switch (choice) {
                case 1:
                    rankingGames(username);
                    break;
                case 2:
                    searchByName(username);
                    break;
                case 3:
                    searchByGenre(username);
                    break;
                default:
                    break;
            }

        }
        System.out.print("(*) Return to Menu --> Press enter to continue :^) ");
        getScanner().nextLine();
    }

    public static void postGame(String username) {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|           Create New Account            |");
        System.out.println("+=========================================+");
        Games games = new Games();
        games.insertGame();
        gamesBL.insertGame(games, username);
        System.out.print("Press Enter to return to menu!");
        getScanner().nextLine();
    }
}
