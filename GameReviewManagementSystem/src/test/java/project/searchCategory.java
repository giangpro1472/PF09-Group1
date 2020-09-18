// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.bl.ReviewBL;
// import project.persistance.Model;

// public class searchCategory {
//     ReviewBL reviewBL = new ReviewBL();
//     @Test
//     public void idIsEmpty() {
//         int result = Model.category("");
//         int expected = 0;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void idIsNotEmpty() {
//         int result = Model.category("3");
//         int expected = 3;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void enterString() {
//         int result = Model.category("abc");
//         int expected = 0;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void enterNumber() {
//         int result = Model.category("3");
//         int expected = 3;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void inRange() {
//         int result = Model.category("3");
//         int expected = 3;
//         assertTrue(result == expected);
//     }

//     @Test
//     public void outRange() {
//         int result = Model.category("10");
//         int expected = 10;
//         assertTrue(result == expected);
//     }
    

//     @Test
//     public void categoryIsEmpty() {
//         final boolean result = reviewBL.search_category("abc", 7);
//         assertTrue(result);
//     }

//     @Test
//     public void categoryIsNotEmpty() {
//         final boolean result = reviewBL.search_category("abc", 4);
//         assertFalse(result);
//     }
// }
