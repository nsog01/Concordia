package presentation.views.files;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import domain.files.Client;
import domain.files.FileManager;

public class ClientImagePanel extends JPanel {

	private static final long serialVersionUID = 785493253789L;
	private Image image;
	private JPanel panel;
	private boolean imageChanged;
	private Client client;
	private FileManager fileManager = FileManager.getUniqueInstance();

	public ClientImagePanel(Client client) {
		this.client = client;
		if (client != null && client.getPhoto() != null) {
			image = client.getPhoto();
		} else {
			try {
				this.image = ImageIO.read(new File("images/default.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Dimension panelSize = new Dimension(200, 280);
		this.setPreferredSize(panelSize);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.imageChanged = false;
	}

	public ClientImagePanel() {
		try {
			this.image = ImageIO.read(new File("images/default.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dimension panelSize = this.getMaximumSize();
		this.setPreferredSize(panelSize);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.imageChanged = false;
	}

//	public ClientImagePanel(Client client) {
//		this.client = client;
//		Image localImage = null;
//		if (client != null) {
//			localImage = fileManager.getPhoto(client);
//			if (localImage != null) {
//				image = localImage;
//			}
//		} else {
//			try {
//				this.image = ImageIO.read(new File("images/default.png"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		Dimension panelSize = new Dimension(200, 250);
//		this.setPreferredSize(panelSize);
//		this.setBorder(BorderFactory.createEtchedBorder());
//		this.imageChanged = false;
//	}

	public void setVisable(boolean bool) {
		panel.setVisible(bool);
	}

	public void setImage(ImageIcon icon) {
		image = icon.getImage();
		this.imageChanged = true;
	}

	public void reset(Client client) {
		this.client = client;
		if (client != null && client.getPhoto() != null) {
				image = client.getPhoto();
		} else {
			try {
				this.image = ImageIO.read(new File("images/default.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		repaint();
	}

	public Image getPhoto() {
		return this.image;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public Client getClient() {
		return this.client;
	}

	public void setClientPhoto(Client client) {
		if (client != null && client.getPhoto() != null) {
				image = client.getPhoto();
 		} else {
			try {
				this.image = ImageIO.read(new File("images/default.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.client.setThumb(client.getThumb());
		this.imageChanged = true;
	}

	public boolean isChanged() {
		return imageChanged;
	}

}
