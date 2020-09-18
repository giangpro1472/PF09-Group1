// package project;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import project.bl.ReviewBL;
// import project.persistance.Model;

// public class SearchName {
//     ReviewBL reviewBL = new ReviewBL();
//     @Test
//     public void gameNameIsEmpty()
//     {
//         final String result = Model.gameName("");
//         final String expected = "";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void gameNameIsNotEmpty()
//     {
//         final String result = Model.gameName("Undertale");
//         final String expected = "Undertale";
//         assertTrue(result.trim().equals(expected));
//     }

//     @Test
//     public void gameNameIsExist()
//     {
//       boolean result = reviewBL.search_name("abc", "Undertale");  
//       assertTrue(result);
//     }

//     @Test
//     public void gameNameIsNotExist()
//     {
//         boolean result = reviewBL.search_name("abc", "xyz");  
//         assertFalse(result);
//     }

// }
