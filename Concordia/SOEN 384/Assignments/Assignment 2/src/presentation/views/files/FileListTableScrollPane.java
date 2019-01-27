package presentation.views.files;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.files.FileListTableModel;



import domain.files.FileList;

public class FileListTableScrollPane extends JScrollPane{

	private static final long serialVersionUID = 4327894023L;
	
	private FileListTable fileListTable;
	
	private FileListTableScrollPane(FileListTable fileListTable){
		super(fileListTable);
		this.fileListTable = fileListTable;
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				"Clients"));
	}
	public FileListTableScrollPane(){
		super();
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		String fileListTableScrollBorderText="Clients";
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				fileListTableScrollBorderText));
	}
	public static FileListTableScrollPane getNewInstance(FileList fileList){
		FileListTable fileListTable = new FileListTable(new FileListTableModel());
		fileListTable.setList(fileList);
		return new FileListTableScrollPane(fileListTable);
		
	}
	public FileListTable getTable(){
		return fileListTable;
	}
}
