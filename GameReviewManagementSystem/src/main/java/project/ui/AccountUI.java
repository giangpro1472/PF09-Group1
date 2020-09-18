package project.ui;

import java.util.Scanner;

import project.bl.AccountBL;
import project.persistance.Account;
import project.persistance.Clrsrc;
import project.persistance.Input;

public class AccountUI {

    private static Scanner getScanner() {
        return new Scanner(System.in);
    };

    public static AccountBL accountBL = new AccountBL();
    
    public static void login() {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|                  Login                  |");
        System.out.println("+=========================================+");
        String username = "";
        String password = "";
        username = Input.loginUsername(username);
        password = Input.loginPassword(password);
        System.out.println("+=========================================+");
        System.out.println(" ");
        accountBL.login(username, password);

    }

    public static void register() {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|           Create New Account            |");
        System.out.println("+=========================================+");
        Account account = new Account();
        account.register();
        accountBL.insert_account(account);
        System.out.print("Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void displayActiveAccount() {
        Clrsrc.clrsrc();
        accountBL.displayActiveAccounts();
        System.out.print("Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void displayDisableAccount() {
        Clrsrc.clrsrc();
        accountBL.displayDisableAccounts();
        System.out.print("Press Enter to return to menu!");
        getScanner().nextLine();
    }

    public static void updateAccount(String username) {
        Clrsrc.clrsrc();
        Header.intro();
        System.out.println(" ");
        System.out.println("+=========================================+");
        System.out.println("|          Update User's Profile          |");
        System.out.println("+=========================================+");
        accountBL.edit_profile(username);
        System.out.print("Press Enter to return to menu! ");
        getScanner().nextLine();
    }

    public static void mainMenu() {
        String username = "";
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("+=========================================+");
            System.out.println("|      Game Review Management System      | ");
            System.out.println("+=========================================+ ");
            System.out.println("| 1. Log-in                               | ");
            System.out.println("| 2. Search and Display Game              | ");
            System.out.println("| 0. Exit                                 | ");
            System.out.println("+=========================================+ ");
            choice = Input.choice(0, 2);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    GameUI.searchDisplayGames(username);
                    break;
                default:
                    if (choice != 0 && choice > 2) {
                        System.err.println("(*) Please choice from (0-2) >:( ");
                    }
                    break;
            }
        }
        System.out.println("(*) Shut down Game Review Management System :( ");
        System.out.println("(*) Thanks for your contribution to the system <3 ");
        Header.footer();
    }

    public static void accountManagementMenu() {
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("+=========================================+");
            System.out.println("|        Account's Management Menu        |");
            System.out.println("+=========================================+");
            System.out.println("| 1. Display Active Accounts.             |");
            System.out.println("| 2. Display Disable Accounts.            |");
            System.out.println("| 3. Create new Account.                  |");
            System.out.println("| 0. Return to Main menu                  |");
            System.out.println("+=========================================+");
            choice = Input.choice(0, 3);
            switch (choice) {
                case 1:
                    displayActiveAccount();
                    break;
                case 2:
                    displayDisableAccount();
                    break;
                case 3:
                    register();
                    break;
                default:
                    if (choice != 0 && choice > 3) {
                        System.err.printf("Please choice from (0-3) >:( ");
                    }
                    break;
            }

        }
        System.out.printf("(*) Return to Menu --> Press enter to continue :^) ");
        getScanner().nextLine();
    }

    public static void adminMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("      --- Welcome: " + username + " ---");
            System.out.println("+=========================================+");
            System.out.println("|               Admin's Menu              |");
            System.out.println("+=========================================+");
            System.out.println("| 1. Account Management.                  |");
            System.out.println("| 2. Games & Reviews Management.          |");
            System.out.println("| 3. Search and Display Game              | ");
            System.out.println("| 4. Edit Profile.                        |");
            System.out.println("| 0. Log-out                              |");
            System.out.println("+=========================================+");
            choice = Input.choice(0, 4);
            switch (choice) {
                case 1:
                    accountManagementMenu();
                    break;
                case 2:
                    GameUI.gamesReviewManagementMenu(username);
                    break;
                case 3:
                    GameUI.searchDisplayGames(username);
                    break;
                case 4:
                    updateAccount(username);
                    break;
                default:
                    break;
            }
        }
        System.out.println("(*) Sayonara " + username + " :(");
        System.out.print("Press Enter to return to main menu! ");
        getScanner().nextLine();
    }

    public static void memberMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            Clrsrc.clrsrc();
            Header.intro();
            System.out.println(" ");
            System.out.println("      --- Welcome: " + username + " ---");
            System.out.println("+=========================================+");
            System.out.println("|              Member's Menu              |");
            System.out.println("+=========================================+");
            System.out.println("| 1. Search and Display Game              | ");
            System.out.println("| 2. Edit Profile.                        |");
            System.out.println("| 0. Log-out                              |");
            System.out.println("+=========================================+");
            choice = Input.choice(0, 2);
            switch (choice) {
                case 1:
                    GameUI.searchDisplayGames(username);
                    break;
                case 2:
                    updateAccount(username);
                    break;
                default:
                    break;
            }

        }
        System.out.println("(*) Sayonara " + username + " :( ");
        System.out.print("Press Enter to return to main menu! ");
        getScanner().nextLine();
    }

}
