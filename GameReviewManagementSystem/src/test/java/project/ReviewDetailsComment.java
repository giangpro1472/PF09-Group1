// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.bl.ReviewBL;
// import project.dal.ReviewDAL;
// import project.persistance.Model;

// public class ReviewDetailsComment {
//     ReviewDAL dal = new ReviewDAL();
//     ReviewBL reviewBL = new ReviewBL();

//     @Test
//     public void reviewIDIsEmpty() {
//         int result = Model.reviewID("");
//         int expected = 0;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void reviewIDIsNotEmpty() {
//         int result = Model.reviewID("3");
//         int expected = 3;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void reviewIDIsExist() {
//         assertTrue(dal.checkReviewID(3));
//     }

//     @Test
//     public void reviewIDIsNotExist() {
//         assertFalse(dal.checkReviewID(40));
//     }

//     @Test
//     public void commentIsEmpty() {
//        String result = reviewBL.post_comment("abc", 1, "");
//        String expected = "";
//        assertTrue( result.equals(expected));
//     }

//     @Test
//     public void commentIsNotEmpty() {
//         String result = reviewBL.post_comment("abc", 1, "hihi");
//         String expected = "hihi";
//         assertTrue( result.equals(expected));
//     }

//     @Test
//     public void enterString() {
//         int result = Model.reviewID("abc");
//         int expected = 0;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void enterNumber() {
//         int result = Model.reviewID("3");
//         int expected = 3;
//         assertTrue(result == expected);
//     }
// }
