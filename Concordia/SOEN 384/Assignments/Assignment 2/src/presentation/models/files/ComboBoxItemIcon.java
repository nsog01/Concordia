package presentation.models.files;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

public class ComboBoxItemIcon extends JFrame
{

	private static final long serialVersionUID = 432432147321L;

	public ComboBoxItemIcon()
    {
		
		String missingPaperWorkFlagText="Missing Paperwork";
		String informationFlagText="Information";
		String banFlagText="Ban";
		
        Vector<Item> model = new Vector<Item>();
        model.addElement( new Item(new ImageIcon("images/flag_green16.png"),missingPaperWorkFlagText  ) );
        model.addElement( new Item(new ImageIcon("images/flag_blue16.png"),informationFlagText) );
        model.addElement( new Item(new ImageIcon("images/flag_red16.png"),banFlagText) );

        JComboBox comboBox;

        comboBox = new JComboBox( model );
        comboBox.setRenderer( new ItemRenderer() );
        getContentPane().add(comboBox, BorderLayout.SOUTH );
    }

    class ItemRenderer extends BasicComboBoxRenderer
    {

		private static final long serialVersionUID = 4780234768234L;

		public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            Item item = (Item)value;

            if (index == -1)
            {
                setText( null );
                setIcon( item.getIcon() );
            }
            else
            {
                setText( item.getText() );
                setIcon( item.getIcon() );
            }

            return this;
        }
    }

    class Item
    {
        private Icon icon;
        private String text;

        public Item(Icon icon, String text)
        {
            this.icon = icon;
            this.text = text;
        }

        public Icon getIcon()
        {
            return icon;
        }

        public String getText()
        {
            return text;
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new ComboBoxItemIcon();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible( true );
     }

}