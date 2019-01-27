package presentation.models.files;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ListDataListener;

import domain.files.FileManager;
import domain.files.Flag;
import domain.files.FlagList;

public class FlagComboBoxModel implements ComboBoxModel{

	Vector<Item> flags;
	private Item selectedItem;
	private String[] icons = {"images/flag_green16.png",
				"images/flag_red16.png",
				"images/flag_yellow16.png",
				"images/flag_blue16.png",
				"images/flag_black16.png"};
	
	public FlagComboBoxModel(){
		FlagList flagList = FileManager.getUniqueInstance().getAllFlags();
		flags = new Vector<Item>();
		int i = 0;
		for (Flag f : flagList){
			flags.add(new Item(new ImageIcon(icons[i]), f ) );
			if (i != icons.length){
				i++;
			} else {
				i = 0;
			}
		}
		this.selectedItem = flags.get(0);
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getElementAt(int index) {
		return flags.get(index);
	}

	@Override
	public int getSize() {
		return flags.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = (Item) anItem;
	}

	public class Item
    {
        private Icon icon;
        private Flag flag;

        public Item(Icon icon, Flag flag)
        {
            this.icon = icon;
            this.flag = flag;
        }

        public Icon getIcon()
        {
            return icon;
        }

        public Flag getFlag()
        {
            return flag;
        }
    }
}
