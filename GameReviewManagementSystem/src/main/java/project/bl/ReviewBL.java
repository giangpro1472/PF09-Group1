package project.bl;

import java.util.List;
import java.util.Scanner;

import project.dal.ReviewDAL;
import project.persistance.Input;
import project.persistance.Review;

public class ReviewBL {
    public ReviewDAL dal = new ReviewDAL();

    public List<Review> getReviewsByID(int gameID) {
        return dal.getReviewsByID(gameID);
    }

    public List<Review> getReviewsInDay() {
        return dal.getReviewsInDay();
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    };

    public void postReview(String username, int id) {
        Review review = new Review();
        review.insert(username, id);
        dal.insertReview(review);
    }

    public void displayReviewByID(int gameID) {
        int count = 0;
        if (getReviewsByID(gameID).isEmpty()) {
            System.out.println("(*) Sorry we cannot display reviews :( ");
        } else {
            for (Review review : getReviewsByID(gameID)) {
                count++;
                review.displayReviewInDay();
                if (count == 3) {
                    System.out.println("");
                    System.out.print("(*) Do you want to see more reviews?(Y/N): ");
                    String choice = getScanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        continue;
                    } else if(choice.equalsIgnoreCase("n")) {
                        break;
                    }
                }
            }
        }
    }

    public void displayReviewInDay() {
        if (getReviewsInDay().isEmpty()) {
            System.out.println("(*) There are no reviews in today lately :) ");
        } else {
            System.out.println("+----------------------------------------+");
            System.out.println("|          User's Reviews In Day         |");
            System.out.println("+----------------------------------------+");
            for (Review review : getReviewsInDay()) {
                
                review.displayReview();
            }
            System.out.println("");
            while (true) {
                try {
                    System.out.println("+=========================================+");
                    System.out.println("| 1. Delete review.                       |");
                    System.out.println("| 2. Return to menu.                      |");
                    System.out.println("+=========================================+");
                    System.out.print("Choose: ");
                    int choice = getScanner().nextInt();
                    if (choice == 1) {
                        deleteReview();
                        break;
                    } else if (choice < 1 || choice > 2) {
                        System.out.println("(*) Retype(1 or 2) >:(");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("(*) Retype(1 or 3) >:(");
                }
            }
        }
    }

    public void deleteReview() {
        int reviewID = Input.reviewID();
        if (reviewID == dal.checkReviewID(reviewID)) {
            while (true) {
                System.out.print("(*) Do you want to delete this review?(Y/N): ");
                String choice = getScanner().nextLine();
                if (choice.isEmpty()) {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                } else if (choice.equalsIgnoreCase("y")) {
                    dal.deleteReview(reviewID);
                    break;
                } else if(choice.equalsIgnoreCase("n")){
                    System.out.println("(*) Cancel Delete :( ");
                    break;
                }else {
                    System.out.println("(*) Please choose Y or N :( ");
                    continue;
                }
            }
        }
    }
}
