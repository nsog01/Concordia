package presentation.views.files;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import domain.files.FileManager;
import domain.files.Flag;

import presentation.models.files.FlagComboBoxModel;
import presentation.models.files.FlagComboBoxModel.Item;

public class FlagComboBox extends JComboBox{

	private static final long serialVersionUID = 78493214231L;
	
	public FlagComboBox(){
		super(new FlagComboBoxModel());
		setRenderer(new ItemRenderer() );
		addActionListener(actionListener);
	}
	
	public FlagComboBoxModel getBoxModel(){
		return (FlagComboBoxModel) this.dataModel;
	}
	
	
    private class ItemRenderer extends BasicComboBoxRenderer
    {

		private static final long serialVersionUID = 4780234768234L;

		public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus){
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
            Item item = (Item)value;

            if (index == -1)
            {
                setText( null );
                setIcon( item.getIcon() );
                this.setPreferredSize(new Dimension(20, 20));
            }
            else
            {
                setText( item.getFlag().getFlag() );
                setIcon( item.getIcon() );
            }
            JScrollPane scroll = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, list);
            JPopupMenu pop = ((JPopupMenu)SwingUtilities.getAncestorOfClass(JPopupMenu.class, list));
            scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            int h = list.getHeight();
            if (h == 0){
            	pop.setPreferredSize(new Dimension(200, 63));
            } else {
            	pop.setPreferredSize(new Dimension(200, h+3));
            }
        	scroll.setSize(pop.getSize());
            scroll.setLocation(0, 0);
            scroll.revalidate();
            scroll.repaint();
            return this;
        }
    }
	
    ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (FileManager.getUniqueInstance().getActiveFile() == null){
				new AddFlagAlert();
			} else {
		        ItemSelectable is = (ItemSelectable)actionEvent.getSource();
		    	Object selected[] = is.getSelectedObjects();
		        Flag selectedFlag = ((Item)selected[0]).getFlag();
		        new AddFlagFrame(selectedFlag);
			}
		}
      };
      
}
