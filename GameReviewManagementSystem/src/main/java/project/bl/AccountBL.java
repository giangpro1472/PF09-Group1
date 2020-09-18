package project.bl;

import java.util.List;
import java.util.Scanner;

import project.dal.AccountDAL;
import project.persistance.Account;
import project.persistance.Input;
import project.ui.AccountUI;
import project.ui.Header;

public class AccountBL {
    private AccountDAL dal = new AccountDAL();

    public List<Account> getAllActiveAccounts() {
        return dal.getAllActiveUser();
    }

    public List<Account> getAllDisableAccounts() {
        return dal.getAllDisableUser();
    }

    private Scanner getScanner() {
        return new Scanner(System.in);
    };

    public void insert_account(Account account) {
        while (true) {
            System.out.print("(*) Do you want to create account?(Y/N): ");
            String choice = getScanner().nextLine();
            if (choice.isEmpty()) {
                System.out.println("(*) Please choose Y or N :( ");
                continue;
            } else if (choice.equalsIgnoreCase("y")) {
                dal.insertAccount(account);
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("(*) Cancel Register :( ");
                break;
            } else {
                System.out.println("(*) Please choose Y or N :( ");
                continue;
            }
        }
    }

    public void displayActiveAccounts() {
        while (true) {
            try {
                if (getAllActiveAccounts().isEmpty()) {
                    Header.intro();
                    System.out.println(" ");
                    System.out.println("+=========================================+");
                    System.out.println("|               ACTIVE ACCOUNT            |");
                    System.out.println("+=========================================+");
                    System.out.println("(*) No accounts yet registered :( ");
                    break;
                } else {
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.println(
                            "|                                                             ACTIVE ACCOUNT                                                              |");
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.printf("|%-20s|%-30s|%-15s|%-15s|%-12s|%-8s|%-7s|%-11s|%-11s|\n", "Username", "Email",
                            "Gender", "Address", "Phone", "Status", "Title", "Create Date", "Last Login");
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");

                    for (Account account : getAllActiveAccounts()) {
                        account.display();
                        System.out.println(
                                "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    }

                    System.out.println("");
                    System.out.println("+=========================================+");
                    System.out.println("| 1. Deactivate Account.                  |");
                    System.out.println("| 2. Return to menu.                      |");
                    System.out.println("+=========================================+");
                    System.out.print("Choose: ");
                    int choice = getScanner().nextInt();
                    if (choice == 1) {
                        disableAccount();
                        break;
                    } else if (choice < 1 || choice > 2) {
                        System.out.println("(*) Retype(1 or 2) >:( ");
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("(*) Retype(1 or 2) >:( ");
            }
        }
    }

    public void displayDisableAccounts() {
        while (true) {
            try {
                if (getAllDisableAccounts().isEmpty()) {
                    Header.intro();
                    System.out.println(" ");
                    System.out.println("+=========================================+");
                    System.out.println("|              DISABLE ACCOUNT            |");
                    System.out.println("+=========================================+");
                    System.out.println("(*) No accounts yet deactivated :( ");
                    break;
                } else {
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.println(
                            "|                                                            DISABLE ACCOUNT                                                              |");
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.printf("|%-20s|%-30s|%-15s|%-15s|%-12s|%-8s|%-7s|%-11s|%-11s|\n", "Username", "Email",
                            "Gender", "Address", "Phone", "Status", "Title", "Create Date", "Last Login");
                    System.out.println(
                            "+-----------------------------------------------------------------------------------------------------------------------------------------+");

                    for (Account account : getAllDisableAccounts()) {
                        account.display();
                        System.out.println(
                                "+-----------------------------------------------------------------------------------------------------------------------------------------+");
                    }

                    System.out.println("");
                    System.out.println("+=========================================+");
                    System.out.println("| 1. Unlock Account.                      |");
                    System.out.println("| 2. Return to menu.                      |");
                    System.out.println("+=========================================+");
                    System.out.print("Choose: ");
                    int choice = getScanner().nextInt();
                    if (choice == 1) {
                        unlockAccount();
                        break;
                    } else if (choice < 1 || choice > 2) {
                        System.out.println("(*) Retype(1 or 2) >:( ");
                    } else {
                        break;
                    }

                }
            } catch (Exception e) {
                System.out.println("(*) Retype(1 or 2) >:( ");
            }
        }
    }

    public void disableAccount() {
        String username = "";
        username = Input.loginUsername(username);
        if (dal.checkUsername(username)) {
            while (true) {
                System.out.print("Do you want to deactivate this account?(Y/N): ");
                String choice = getScanner().nextLine();
                if (choice.isEmpty()) {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                } else if (choice.equalsIgnoreCase("y")) {
                    dal.disableAccount(username);
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("(*) Cancel Deactivate :( ");
                    break;
                } else {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                }
            }
        } else {
            System.out.println("(*) Cannot find this account :( ");
        }

    }

    public void unlockAccount() {
        String username = "";
        username = Input.loginUsername(username);
        if (dal.checkUsername(username)) {
            while (true) {
                System.out.print("Do you want to unlock this account?(Y/N): ");
                String choice = getScanner().nextLine();
                if (choice.isEmpty()) {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                } else if (choice.equalsIgnoreCase("y")) {
                    dal.unlockAccount(username);
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("(*) Cancel Unlock :( ");
                    break;
                } else {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                }
            }
        } else {
            System.out.println("(*) Cannot find this account :( ");
        }

    }

    public void edit_profile(String username) {
        if (dal.checkUsername(username)) {
            String email = "";
            String gender = "";
            String address = "";
            String phone = "";
            email = Input.email(email);
            gender = Input.gender(gender);
            address = Input.address(address);
            phone = Input.phone(phone);
            while (true) {
                System.out.print("(*) Do you want to update profile?(Y/N): ");
                String choice = getScanner().nextLine();
                if (choice.isEmpty()) {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                } else if (choice.equalsIgnoreCase("y")) {
                    dal.editProfile(username, email, gender, address, phone);
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("(*) Cancle Update Profile :( ");
                    break;
                } else {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                }
            }

        } else {
            System.out.println("(*) Error somthing has gone wrong! Please try again :( ");
        }

    }

    public boolean login(String username, String password) {
        boolean result = false;
        if (dal.checkAccount(username, password)) {
            result = true;
            if (dal.checkStatus(username).trim().equals("Disable")) {
                System.out.println("(*) Your account has been deactivated :( ");
                System.out
                        .println("(*) Please contact the admin to re-activate your account at a reasonable price :) ");
                System.out.print("(*) Press Enter to continue! ");
                getScanner().nextLine();
            } else {
                dal.lastLogin(username);
                System.out.println("(*) Login Successfully! Welcome back " + username);
                System.out.print("(*) Press Enter to continue! ");
                getScanner().nextLine();
                if (dal.checkRole(username) == 1) {
                    AccountUI.adminMenu(username);
                } else {
                    AccountUI.memberMenu(username);

                }
            }
        } else {
            System.out.println("(*) You have entered wrong password or username!");
            System.out.println(
                    "(*) If you forgot your password or a new user please contact to admin to this number: 0352542276!");
            System.out.print("(*) Press Enter to continue! ");
            getScanner().nextLine();
        }
        return result;
    }

}
