// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.bl.AccountBL;
// import project.persistance.Model;

// public class Login {

//     AccountBL accountBL = new AccountBL();
//     @Test
//     public void loginValidate()
//     {
//         boolean result = accountBL.login("admin", "21232f297a57a5a743894a0e4a801fc3");
//         assertTrue(result);
//     }

//     @Test
//     public void loginNotValidate()
//     {
//         boolean result = accountBL.login("admin", "admin");
//         assertFalse(result);
//     }

//     @Test
//     public void usernameIsEmpty()
//     {
//         final String result = Model.loginUsername("");
//         final String expected = "";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void usernameIsNotEmpty()
//     {
//         final String result = Model.loginUsername("admin");
//         final String expected = "admin";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void passwordIsEmpty()
//     {
//         final String result = Model.loginPassword("");
//         final String expected = "";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void passwordIsNotEmpty()
//     {
//         final String result = Model.loginPassword("admin");
//         final String expected = "admin";
//         assertTrue(result.trim().equals(expected));
//     }
// }
