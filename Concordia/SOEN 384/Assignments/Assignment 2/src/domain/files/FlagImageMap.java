package domain.files;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class FlagImageMap {

	private static Map<Integer, ImageIcon> flagIcons;
	private static FileManager fileManager;
	private static String[] icons = {"images/flag_green.png",
			"images/flag_red.png",
			"images/flag_yellow.png",
			"images/flag_blue.png",
			"images/flag_black.png"};
	
	static{
		fileManager = FileManager.getUniqueInstance();
		flagIcons = new HashMap<Integer, ImageIcon>();
		
		FlagList flags = fileManager.getAllFlags();
		int i = 0;
		for (Flag f : flags){
			flagIcons.put(f.getId(), new ImageIcon(icons[i]));
			if (i != icons.length){
				i++;
			} else {
				i = 0;
			}
		}
	}
	
	public static ImageIcon getFlagImage(Flag flag){
		return flagIcons.get(flag.getFlagType());
	}
}
