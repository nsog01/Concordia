package domain.images;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

//all imports are here

public class ImageUploader extends JFrame {

	private static final long serialVersionUID = 432178904L;
	private JLabel title;
	private String imgLocationText="Image Location:";
	private JLabel ImageLocation = new JLabel(imgLocationText);
	private String browseText="Browse";
	private JButton browse = new JButton(browseText);
	private String uploadText="Upload";
	private JButton upload = new JButton(uploadText);
	private JTextField imgLocationTf = new JTextField();
	private File file;
	private String fileName;

	public ImageUploader() {
		String imgUploadClientPhotoText="Upload Client Photo";
		this.title = new JLabel(imgUploadClientPhotoText);
		this.title.setFont(new Font("sarif", Font.BOLD, 16));
		String imgUploaderText="Image uploader";
		this.setTitle(imgUploaderText);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width/3, screen.height/3);
		setLayout(new MigLayout());
		this.add(title, "wrap 20, span");
		this.add(ImageLocation);
		this.add(imgLocationTf, "w 200, wrap");
		this.add(browse, "align right, span, wrap");
		this.add(upload, "align right, span");
		this.pack();
		setVisible(true);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		fileName = file.getName();
		imgLocationTf.setText(fileName);
	}

	public void setBrowseListener(ActionListener uploadHandler) {
		browse.addActionListener(uploadHandler);
	}

	public void setUploadListener(ActionListener upLoadHandler) {
		upload.addActionListener(upLoadHandler);
	}

	public JButton getBrowse() {
		return this.browse;
	}

	public JButton getUpload() {
		return upload;
	}
	
}
