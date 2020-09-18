package project.persistance;



public class Clrsrc {
    public static void clrsrc() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}