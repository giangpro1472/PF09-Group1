// package project;

// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.persistance.Model;

// public class UpdateProfile {
//     @Test
//     public void profileFieldIsEmpty() {
//         final String result = Model.email("");
//         final String expected = "";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void profileFieldIsNotEmpty() {
//         final String result = Model.email("abcxyz@gmail.com");
//         final String expected = "abcxyz@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void emailIsCorrect() {
//         final String result = Model.email("abcxyz@gmail.com");
//         final String expected = "abcxyz@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void emailIsIncorrect() {
//         final String result = Model.email("com");
//         final String expected = "com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void emailIsExist() {
//         final String result = Model.email("abc@gmail.com");
//         final String expected = "abc@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void emailIsNotExist() {
//         final String result = Model.email("abcxyz@gmail.com");
//         final String expected = "abcxyz@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }
// }
