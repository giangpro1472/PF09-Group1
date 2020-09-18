package project.persistance;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

import project.dal.AccountDAL;
import project.dal.GamesDAL;

public class Input {

    private static Scanner getScanner() {
        return new Scanner(System.in);
    };

    public static AccountDAL dal = new AccountDAL();
    public static GamesDAL gameDAL = new GamesDAL();

    private static Boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    private static Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})",
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(password).matches();
    }

    private static Boolean validateReleaseDate(String date) {
        Pattern pattern = Pattern.compile("^\\d{4}/\\d{2}/\\d{2}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(date).matches();
    }

    private static Boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(phone).matches();
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String username(String username) {
        while (true) {
            System.out.print("- Enter username: ");
            username = getScanner().nextLine();
            if (username.isEmpty()) {
                System.out.println("(*) Please enter username >:( ");
                continue;
            } else if (!dal.checkExistUsername(username)) {
                continue;
            }
            break;
        }
        return username;
    }

    public static String password(String password) {
        while (true) {
            password = PasswordField.readPassword("- Enter password: ");
            boolean validatePassword = validatePassword(password);
            if (password.isEmpty()) {
                System.out.println("(*) Please enter password >:( ");
                continue;
            } else if (!validatePassword) {
                System.out.println("(*) Password must be more than 8 characters!");
                System.out.println("(*) Contain at least one digit!");
                System.out.println("(*) Contain at least one lower case character!");
                System.out.println("(*) Contain at least one upper case character!");
                System.out.println("(*) Contain at least on special character from [ @ # $ % ! . ]!");
                continue;
            }
            password = getMd5(password);
            break;
        }
        return password;
    }

    public static String email(String email) {
        while (true) {
            System.out.print("- Enter email: ");
            email = getScanner().nextLine();
            boolean validateEmail = validateEmail(email);
            if (email.isEmpty()) {
                System.out.println("(*) Please enter email >:( ");
                continue;
            } else if (!validateEmail) {
                System.out.println("(*) Please enter correct email's form >:( ");
                continue;
            } else if (!dal.checkExistEmail(email)) {
                continue;
            }
            break;
        }
        return email;
    }

    public static String gender(String gender) {
        while (true) {
            System.out.print("- Enter gender (Male/Female): ");
            gender = getScanner().nextLine();
            if (gender.isEmpty()) {
                System.out.println("(*) Please enter gender >:( ");
            } else if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                break;
            } else {
                System.out.println("Please retype (Male/Female) >:( ");
            }
        }
        return gender;
    }

    public static String address(String address) {
        while (true) {
            System.out.print("- Enter address: ");
            address = getScanner().nextLine();
            if (address.isEmpty()) {
                System.out.println("(*) Please enter address >:( ");
                continue;
            }
            break;
        }
        return address;
    }

    public static String phone(String phone) {
        while (true) {
            System.out.print("- Enter phone-number (xxx-xxx-xxxx): ");
            phone = getScanner().nextLine();
            boolean validatePhone = validatePhone(phone);
            if (phone.isEmpty()) {
                System.out.println("(*) Please enter phone-number >:( ");
                continue;
            } else if (!validatePhone) {
                System.out.println("(*) Please enter correct format >:( (xxx-xxx-xxxx) !");
                continue;
            } else if (!dal.checkExistPhone(phone)) {
                continue;
            }
            break;
        }
        return phone;
    }

    public static int role_id() {
        int role_id = 0;
        while (true) {
            try {
                System.out.print("- Enter user's role (1.Admin) or (2.User): ");
                String str = getScanner().nextLine();
                role_id = Integer.parseInt(str);
                if (str.isEmpty()) {
                    System.out.println("(*) Please choose user's role(1 or 2) >:( ");
                    continue;
                } else if (role_id >= 1 && role_id <= 2) {
                    break;
                } else {
                    System.out.println("(*) Please choose user's role(1 or 2) >:( ");
                }

            } catch (Exception e) {
                System.out.println("(*) Please choose user's role(1 or 2) >:( ");
            }
        }
        return role_id;
    }

    public static String loginUsername(String username) {
        while (true) {
            System.out.printf("(*) Username: ");
            username = getScanner().nextLine();
            if (username.isEmpty()) {
                System.out.println("(*) Please enter username >:( ");
                continue;
            }
            break;
        }
        return username;
    }

    public static String loginPassword(String password) {
        while (true) {
            password = PasswordField.readPassword("(*) Password: ");
            if (password.isEmpty()) {
                System.out.println("(*) Please enter password >:( ");

                continue;
            }
            password = Input.getMd5(password);
            break;
        }
        return password;
    }

    public static String content(String content) {
        while (true) {
            System.out.print("- Enter Reviews's Content: ");
            content = getScanner().nextLine();
            if (content.isEmpty()) {
                System.out.println("(*) Please enter content >:( ");
                continue;
            }
            break;
        }
        return content;
    }

    public static int score() {
        int score = 0;
        while (true) {
            try {
                System.out.print("- Your score: ");
                String str = getScanner().nextLine();
                score = Integer.parseInt(str);
                if (str.isEmpty()) {
                    System.out.println("(*) Please score 0 - 10 >:( ");
                    continue;
                } else if (score <= 10 && score >= 0) {
                    break;
                } else {
                    System.out.println("(*) Please score 0 - 10 >:( ");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("(*) Please score 0 - 10 >:( ");
            }
        }
        return score;
    }

    public static int gameID() {
        int gameID = 0;
        while (true) {
            try {
                System.out.print("- Enter Game-ID: ");
                String check = getScanner().nextLine();
                if (check.isEmpty()) {
                    System.out.println("(*) Please enter Game-ID >:( ");
                    continue;
                } else {
                    gameID = Integer.parseInt(check);
                    break;
                }
            } catch (Exception e) {
                System.out.println("(*) Please enter Game-ID >:( ");
            }
        }
        return gameID;
    }

    public static int reviewID() {
        int reviewID = 0;
        while (true) {
            try {
                System.out.print("- Enter Review-ID: ");
                String check = getScanner().nextLine();
                if (check.isEmpty()) {
                    System.out.println("(*) Please enter Review-ID >:( ");
                    continue;
                } else {
                    reviewID = Integer.parseInt(check);
                    break;
                }

            } catch (Exception e) {
                System.out.println("(*) Please enter Review-ID >:( ");
            }
        }
        return reviewID;
    }

    public static String gameName(String gameName) {
        while (true) {
            System.out.print("- Enter Game's Name: ");
            gameName = getScanner().nextLine();
            if (gameName.isEmpty()) {
                System.out.println("(*) Please enter game's name >:( ");
                continue;
            } else if (!gameDAL.checkExistGameName(gameName)) {
                continue;
            }
            break;
        }
        return gameName;
    }

    public static int genre() {
        int genre = 0;
        while (true) {
            System.out.println("(1.Action),(2.Horror),(3.Simulation)");
            System.out.println("(4.Puzzle),(5.Indie),(6.Stratedy),(7.Survival)");
            System.out.println("(8.Moba),(9.RPG),(10.Role-play)");
            try {
                System.out.print("- Choose a genre: ");
                String genreID = getScanner().nextLine();
                genre = Integer.parseInt(genreID);
                if (genreID.isEmpty()) {
                    System.out.println("(*) Please choose a genre >:( ");
                    continue;

                } else if (genre <= 10 && genre >= 1) {
                    break;
                } else {
                    System.out.println("(*) Please choose a genre(1-10) >:( ");

                    continue;
                }
            } catch (Exception e) {
                System.out.println("(*) Please choose a genre(1-10) >:( ");

            }
        }
        return genre;
    }

    public static String developers() {
        String dev = "";
        while (true) {
            System.out.print("- Enter Game's Developers: ");
            dev = getScanner().nextLine();
            if (dev.isEmpty()) {
                System.out.println("(*) Please enter game's developers >:( ");

                continue;
            }
            break;
        }
        return dev;
    }

    public static String release() {
        String date = "";
        while (true) {
            System.out.print("- Enter Game's Release date (yyyy/mm/dd): ");
            date = getScanner().nextLine();
            boolean validateReleaseDate = validateReleaseDate(date);
            if (date.isEmpty()) {
                System.out.println("(*) Please enter game's Release date >:( ");
                continue;
            } else if (!validateReleaseDate) {
                System.out.println("(*) Please enter correct format >:( (yyyy/mm/dd)!");
                continue;
            }
            break;
        }
        return date;
    }

    public static String image() {
        String image = "";
        while (true) {
            System.out.print("- Import Images: ");
            image = getScanner().nextLine();
            if (image.isEmpty()) {
                System.out.println("(*) Please import image >:( ");
                continue;
            }
            break;
        }
        return image;
    }

    public static int choice(int from, int to) {
        int choice = 0;
        while (true) {
            try {
                System.out.print("#Choose: ");
                String check = getScanner().nextLine();
                choice = Integer.parseInt(check);
                if (check.isEmpty()) {
                    System.out.println("(*) Please enter your choice >:( ");
                    continue;
                } else if (choice >= from && choice <= to) {
                    break;
                } else {
                    System.out.println("(*) Please enter your choice from: " + from + " to " + to + " >:(");
                }
            } catch (Exception e) {
                System.out.println("(*) Please enter your choice from: " + from + " to " + to + " >:(");
            }
        }
        return choice;
    }
}