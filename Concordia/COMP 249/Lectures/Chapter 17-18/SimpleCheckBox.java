
import java.awt.*;
import javax.swing.*;

public class SimpleCheckBox
{
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Style Options");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      StyleOptionsPanel panel = new StyleOptionsPanel();
      frame.add (panel);

      frame.setSize(500,100);

      frame.setVisible(true);
   }
}

class StyleOptionsPanel extends JPanel{
   private JLabel sayingLabel;
   private JCheckBox boldButton, italicButton;

   public StyleOptionsPanel()
   {
      sayingLabel = new JLabel ("Say it with style!");
      sayingLabel.setFont (new Font ("Helvetica", Font.PLAIN, 36));

      boldButton = new JCheckBox ("Bold");
      boldButton.setBackground (Color.red);
      italicButton = new JCheckBox ("Italic");
      italicButton.setBackground (Color.red);

      add(sayingLabel);
      add(boldButton);
      add(italicButton);
   }
}