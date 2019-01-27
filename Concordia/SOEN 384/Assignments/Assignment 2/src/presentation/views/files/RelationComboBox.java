package presentation.views.files;


import javax.swing.JComboBox;


import presentation.models.files.RelationComboBoxModel;



public class RelationComboBox extends JComboBox {
		
		private static final long serialVersionUID = 78493214231L;

		public RelationComboBox(RelationComboBoxModel model){
			super(model);
		}
		
		public RelationComboBoxModel getBoxModel(){
			return (RelationComboBoxModel) this.dataModel;
			
		}
		
	}

