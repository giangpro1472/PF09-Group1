// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.bl.AccountBL;
// import project.dal.AccountDAL;
// import project.persistance.Model;

// public class Update_DeleteAccount {
//     public AccountDAL dal = new AccountDAL();
//     public AccountBL bl = new AccountBL();

//     @Test
//     public void usernameIsEmpty() {
//         String result = Model.loginUsername("");
//         String expected = "";
//         assertTrue(result.equals(expected));
//     }

//     @Test
//     public void usernameIsNotEmpty() {
//         String result = Model.loginUsername("abc");
//         String expected = "abc";
//         assertTrue(result.equals(expected));
//     }

//     @Test
//     public void usernameIsExist() {
//         assertTrue(dal.checkUsername("abc"));

//     }

//     @Test
//     public void usernameIsNotExist() {
//         assertFalse(dal.checkUsername("huhu"));
//     }

//     @Test
//     public void newStatusIsEmpty() {
//         String result = bl.update_account("abc", "");
//         String expected = "";
//         assertTrue(result.equals(expected));
//     }

//     @Test
//     public void newStatusIsNotEmpty() {
//         String result = bl.update_account("abc", "xyz");
//         String expected = "xyz";
//         assertTrue(result.equals(expected));
//     }
// }
