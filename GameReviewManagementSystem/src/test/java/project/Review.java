// package project;

// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.persistance.Model;

// public class Review {
//     @Test
//     public void reviewFieldIsEmpty() {
//         String result = Model.publisher("");
//         String expected = "";
//         assertTrue(result == expected);
//     }

//     @Test
//     public void reviewFieldIsNotEmpty() {
//         String result = Model.publisher("abc");
//         String expected = "abc";
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

// }
