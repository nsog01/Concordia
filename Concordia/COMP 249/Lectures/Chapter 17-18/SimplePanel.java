
import javax.swing.*;

public class SimplePanel {
   public static void main (String[] args) {
        // Create the frame 
      JFrame myFrame = new JFrame("This is my JFrame");
      
        // Optional: specify what happens when the frame closes 
      myFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
        // Optional
      myFrame.setSize(300,100);

      myFrame.setVisible(true);
   }
}
