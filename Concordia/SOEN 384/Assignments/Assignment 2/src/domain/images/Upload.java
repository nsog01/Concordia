package domain.images;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import presentation.views.files.ClientImagePanel;

public class Upload implements ActionListener {

	private ClientImagePanel clientImagePanel;
	private ImageUploader uploader; 
	
	public Upload(ImageUploader up, ClientImagePanel clientImagePanel) {
		this.uploader = up;
		this.clientImagePanel = clientImagePanel;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();

		if (e.getSource().equals(uploader.getBrowse())) {
			chooser.setCurrentDirectory(new File("."));

			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".jpg")
							|| f.isDirectory();
				}

				public String getDescription() {
					String imgUploadDescriptionText="JPG Images";
					return imgUploadDescriptionText;
				}
			});

			int r = chooser.showOpenDialog(new JFrame());
			if (r == JFileChooser.APPROVE_OPTION) {
				uploader.setFile(chooser.getSelectedFile());
			}
		}
		if (e.getSource().equals(uploader.getUpload())) {
//			file = chooser
			BufferedImage resizedImage = null;
			try {
				if (uploader.getFile() != null){
					BufferedImage photo = ImageIO.read(uploader.getFile());
					resizedImage = new BufferedImage(200, 300, photo.getType());
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(photo, 0, 0, 200, 300, null);
					g.dispose();
					g.setComposite(AlphaComposite.Src);
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					}
				clientImagePanel.getClient().setPhoto(resizedImage);
				clientImagePanel.setImage(new ImageIcon(clientImagePanel.getClient().getPhoto()));
				clientImagePanel.repaint();
				this.uploader.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
