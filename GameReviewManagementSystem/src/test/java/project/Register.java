// package project;

// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.persistance.Model;

// public class Register {
//     @Test
//     public void registerFieldIsEmpty() {
//         final String result = Model.email("");
//         final String expected = "";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void registerFieldIsNotEmpty() {
//         final String result = Model.email("abcxyz@gmail.com");
//         final String expected = "abcxyz@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void email_PasswordIsCorrect() {
//         final String result = Model.email("abcxyz@gmail.com");
//         final String expected = "abcxyz@gmail.com";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void email_PasswordIsIncorrect() {
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
