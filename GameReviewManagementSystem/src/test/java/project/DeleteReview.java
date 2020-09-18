// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.dal.ReviewDAL;
// import project.persistance.Model;

// public class DeleteReview {
//     public ReviewDAL dal = new ReviewDAL();

//     @Test
//     public void reviewIDIsEmpty() {
//         int result = Model.reviewID("");
//         int expected = 0;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void reviewIDIsNotEmpty() {
//         int result = Model.reviewID("2");
//         int expected = 2;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void reviewIDIsExist() {
//         assertTrue(dal.checkReviewID(1));
//     }

//     @Test
//     public void reviewIDIsNotExist() {
//         assertFalse(dal.checkReviewID(50));

//     }
// }
