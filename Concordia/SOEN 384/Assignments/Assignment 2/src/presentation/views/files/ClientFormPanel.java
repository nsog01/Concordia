package presentation.views.files;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import presentation.controllers.files.ClientFormPanelController;
import presentation.models.files.LanguageListComboBoxModel;
import presentation.models.files.ProvinceListComboBoxModel;
import presentation.views.menus.LanguageSingleton;
import presentation.views.menus.LocaleChangeEvent;
import presentation.views.menus.LocaleChangeListener;

import domain.files.Client;
import domain.files.Client.Sex;
import domain.files.Language;
import domain.files.Province;
import domain.states.NewState;
import net.miginfocom.swing.MigLayout;

public class ClientFormPanel extends JPanel implements Observer, LocaleChangeListener{

	private static final long serialVersionUID = -1637654529652375409L;
	private boolean updatePhoto = false;
	private JPanel formPanel;
	private JPanel imagePanel;
	private JButton uploadImage;
	
	private JLabel nameLabel;
	private JLabel medicareLabel;
	private JLabel dateOfBirthLabel;
	private JLabel sexLabel;
	private JLabel languageLabel;
	private JLabel secondLanguageLabel;
	private JLabel warning;
	
	/* New fields */
	private JLabel maritalStatus;
	private JLabel workStatus;
	private JLabel canadaStatus;
	private JLabel origon;
	private JLabel refferal;
	private JLabel reason;
	
	private JTextField maritalStatusText;
	private JTextField workStatusText;
	private JTextField workStatusExtraText;
	private JTextField canadaStatusText;
	private JTextField canadaStatusExtraText;
	private JTextField origonText;
	private JTextField origonExtraText;
	private JTextField refferalText;
	private JTextField refferalExtraText;
	private JTextField reasonText;
	private JTextField reasonExtraText;
//	private JTextField incomeText;
	/* End new fields*/
	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField middleName;
	private JTextField dateOfBirth;
	private JFormattedTextField medicare;
	private JTextField sex;
	private ProvinceComboBox provinceComboBox;
	private JButton submit;
	private JButton delete;
	private MakeAppointmentButton appointment;
	private LanguageComboBox languageComboBox;
	private LanguageComboBox secondLanguageComboBox;
	private ClientFormPanelController controller;
	private Dimension screenSize;
	
	private EmptyBannerPanel banner;
	
	public ClientFormPanel(Client client) {
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		client.addObserver(this);
		this.controller = new ClientFormPanelController(this);
		this.controller.setClient(client);
		this.initializeElements();
		this.addComponents();
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	public ClientFormPanel() {
		
		this.controller = new ClientFormPanelController(this);
		this.initializeElements();
		this.addComponents();
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	
	private void initializeElements() {
		
		String clientFormPanelManageText="Manage Client File";
		String clientFormPanelNameText="Name: ";
		String clientFormPanelMedicareText="Medicare: ";
		String clientFormPanelDOBText="Date of Birth: ";
		String clientFormPanelMotherText="Mother Tongue: ";
		String clientFormPanelCommLangText="Communication Language: ";
		String clientFormPanelGenderText="Gender: ";
		String clientFormPanelUploadImageText="Upload Image";
		String clientFormPanelMaritalText="Marital Status";
		String clientFormPanelWorkStatusText="Work Status";
		String clientFormPanelCitizeshipText="Citizenship Status";
		String clientFormPanelOriginText="Origin";
		String clientFormPanelReferralText="Refferal";
		String clientFormPanelReasonText="Reason for Need";
		String clientFormPanelSubmitText="Submit";
		String clientFormPanelDeleteText="Delete";
		String clientFormPanelSubmit2Text="Add Client";
		
		setLayout(new MigLayout("", "[][]", "10[][]"));
		banner = new EmptyBannerPanel(clientFormPanelManageText);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		nameLabel = new JLabel(clientFormPanelNameText);
		medicareLabel = new JLabel(clientFormPanelMedicareText);
		dateOfBirthLabel = new JLabel(clientFormPanelDOBText);
		languageLabel = new JLabel(clientFormPanelMotherText);
		secondLanguageLabel = new JLabel(clientFormPanelCommLangText);
		sexLabel = new JLabel(clientFormPanelGenderText);
		formPanel = new JPanel(new MigLayout());
		imagePanel = new JPanel(new MigLayout());
		languageComboBox = new LanguageComboBox();
		provinceComboBox = new ProvinceComboBox(new ProvinceListComboBoxModel());
		secondLanguageComboBox = new LanguageComboBox();
		warning = new JLabel();
		warning.setForeground(Color.RED);
		this.uploadImage = new JButton(clientFormPanelUploadImageText);
		this.uploadImage.addActionListener(this.controller.uploadAction());
		/* New elements */
		 maritalStatus = new JLabel(clientFormPanelMaritalText);
		 workStatus = new JLabel(clientFormPanelWorkStatusText);
		 canadaStatus = new JLabel(clientFormPanelCitizeshipText);
		 origon = new JLabel(clientFormPanelOriginText);
		 refferal = new JLabel(clientFormPanelReferralText);
		 reason = new JLabel(clientFormPanelReasonText);
		
		/*end new elements*/
		
		if (this.controller.getClient() != null){
			submit = new JButton(clientFormPanelSubmitText);
			Client client = controller.getClient();
			this.firstName = new JTextField(this.controller.getClient().getFirstName());
			this.middleName = new JTextField(this.controller.getClient().getMiddleName());
			this.lastName = new JTextField(this.controller.getClient().getLastName());
			this.medicare = new JFormattedTextField(createFormatter("UUUU########"));
			this.medicare.setValue(this.controller.getClient().getMedicare());
			
			this.medicare.addKeyListener(this.controller.medicareAction());
			SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
			this.dateOfBirth = new JTextField(format.format(this.controller.getClient().getDOB()));
			this.sex = new JTextField(this.controller.getClient().getSex().toString());
			this.controller.setClientImagePanel(this.controller.getClient());
			languageComboBox.setSelectedItem(controller.getClient().getMotherTongueString());
			secondLanguageComboBox.setSelectedItem(controller.getClient().getSecondLangString());
			delete = new JButton(clientFormPanelDeleteText);
			appointment = new MakeAppointmentButton(controller.getClient());
			
			/* new elements */
			maritalStatusText = new JTextField(client.getMaritalStatusString());
			workStatusText = new JTextField(client.getWorkStatusString());
			workStatusExtraText = new JTextField(client.getExtraWorkStatusField());
			canadaStatusText = new JTextField(client.getCanadianStatusString());
			canadaStatusExtraText = new JTextField(client.getExtraCanadianStatusField());
			origonText = new JTextField(client.getOriginString());
			origonExtraText = new JTextField(client.getExtraOriginField());
			refferalText = new JTextField(client.getReferralString());
			refferalExtraText = new JTextField(client.getReferralExtra());
			reasonText = new JTextField(client.getReasonString());
			reasonExtraText = new JTextField(client.getReasonExtra());
			/* end */
		} else {
			this.firstName = new JTextField();
			this.middleName = new JTextField();
			this.lastName = new JTextField();
			this.medicare = new JFormattedTextField(createFormatter("UUUU########"));
			this.medicare.addKeyListener(this.controller.medicareAction());
			this.dateOfBirth = new JTextField();
			this.sex = new JTextField();
			submit = new JButton(clientFormPanelSubmit2Text);
			this.controller.setClient(new Client());
			this.controller.setClientImagePanel(this.controller.getClient());
			delete = new JButton(clientFormPanelDeleteText);
			
			/* new elements */
			maritalStatusText = new JTextField();
			workStatusText = new JTextField();
			workStatusExtraText = new JTextField();
			canadaStatusText = new JTextField();
			canadaStatusExtraText = new JTextField();
			origonText = new JTextField();
			origonExtraText = new JTextField();
			refferalText = new JTextField();
			refferalExtraText = new JTextField();
			reasonText = new JTextField();
			reasonExtraText = new JTextField();
//			incomeText = new JTextField();
			/* end */
			
		}
	}
	public void addComponents(){
		add(banner, "north");
		formPanel.setBorder(BorderFactory.createEtchedBorder());
		formPanel.add(nameLabel);
		formPanel.add(firstName, "split 3, grow");
		formPanel.add(middleName, "grow");
		formPanel.add(lastName, "grow, wrap");
		formPanel.add(medicareLabel);
		formPanel.add(medicare, "wrap, grow, w 300");
		formPanel.add(dateOfBirthLabel);
		formPanel.add(dateOfBirth, "wrap, grow, w 300");
		formPanel.add(sexLabel);
		formPanel.add(sex, "wrap, grow, w 300");
		
		//------------------WHY?????????? FML
		
		//formPanel.add(provinceLbl, "wrap,grow" );
		//formPanel.add(provinceComboBox,"wrap, grow");*/
		
		//----------------------------------------------
		formPanel.add(secondLanguageLabel);
		formPanel.add(secondLanguageComboBox, "wrap, grow");
		formPanel.add(languageLabel);
		formPanel.add(languageComboBox, "wrap, grow");
		/*new elements */
		formPanel.add(maritalStatus);
		formPanel.add(maritalStatusText, "wrap, grow");
		formPanel.add(workStatus);
		formPanel.add(workStatusText, "split 2, w 150");
		formPanel.add(workStatusExtraText, "w 150, wrap");
		formPanel.add(canadaStatus);
		formPanel.add(canadaStatusText, "split 2, w 150");
		formPanel.add(canadaStatusExtraText, "w 150, wrap");
		formPanel.add(origon);
		formPanel.add(origonText, "split 2, w 150");
		formPanel.add(origonExtraText, "w 150, wrap");
		formPanel.add(refferal);
		formPanel.add(refferalText, "split2, w 150");
		formPanel.add(refferalExtraText, "w 150, wrap");
		formPanel.add(reason);
		formPanel.add(reasonText, "split 2, w 150");
		formPanel.add(reasonExtraText, "w 150, wrap");
		/* end new elements*/
		formPanel.add(warning, "wrap, span, grow");
		imagePanel.setBorder(null);
		imagePanel.add(this.controller.getImagePanel(), "wrap");
		imagePanel.add(this.uploadImage);
		this.add(imagePanel, "h "+screenSize.height/2);
		this.add(formPanel, "h "+screenSize.height/2+", wrap");
		if(!(this.controller.getClient().getState() instanceof NewState)){
			this.add(appointment, "align left");
			this.add(delete, "span, split 2, align right");
			this.add(submit);
		}
		else
			this.add(submit, "span, align right");
	}
	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return lastName.getText();
	}

	public String getMiddleName() {
		return middleName.getText();
	}

	public String getDateOfBirth() {
		return dateOfBirth.getText();
	}
	public void setDateOfBirth(Date dob){
		SimpleDateFormat format =  new SimpleDateFormat("MMMM d, yyyy");
			if(dob != null)
			this.dateOfBirth.setText(format.format(dob));
	}
	public void setSex(Sex sex){
		if (sex != null)
			this.sex.setText(sex.toString());
	}
	public String getSex() {
		return sex.getText();
	}
	public String getMedicare(){
		return this.medicare.getText();
	}
	public void setSubmitText(String text){
		submit.setText(text);
	}
	public JButton getSubmitButton(){
		return this.submit;
	}
	public void addSubmitListener(ActionListener actionListner){
		this.submit.addActionListener(actionListner);
		
	}
	public ClientImagePanel getClientImagePanel(){
		return controller.getImagePanel();
	}
	public JButton getDeleteButton(){
		return this.delete;
	}
	
	public Client getClient(){
		return this.controller.getClient();
	}
	public void setClient(Client client){
		this.controller.setClient(client);
	}
	public Image getPhoto(){
		return this.controller.getImagePanel().getPhoto();
	}
	
	public Image getThumb(){
		if (getPhoto() != null){
		BufferedImage resizedImage = new BufferedImage(45, 60, ((BufferedImage)getPhoto()).getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(this.getPhoto(), 0, 0, 45, 60, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		return resizedImage;
		}
		return null;
	}
	
	public String getMaritalStatus() {
		return maritalStatusText.getText();
	}
	public String getWorkStatus(){
		return workStatusText.getText();
	}
	public String getWorkStatusExtra(){
		return workStatusExtraText.getText();
	}
	public String getCanadaStatus() {
		return canadaStatusText.getText();
	}
	public String getCanadaStatusExtra() {
		return canadaStatusExtraText.getText();
	}
	public String getOrigon() {
		return origonText.getText();
	}
	public String getOrigonExtra() {
		return origonExtraText.getText();
	}
	public String getRefferal() {
		return refferalText.getText();
	}
	public String getRefferalExtra() {
		return refferalExtraText.getText();
	}
	public String getReason() {
		return reasonText.getText();
	}
	public String getReasonExtra() {
		return reasonExtraText.getText();
	}
	public void addToFormPanel(Component c, String s){
		formPanel.add(c, s);
	}
	public boolean isPhotoUpdated(){
		return updatePhoto;
	}
	public void photoUpdated(){
		updatePhoto = true;
	}
	public Language getMotherTongue() {
		return ((LanguageListComboBoxModel)languageComboBox.getModel()).getSelected();
	}
	public Province getProvince(){
		return ((ProvinceListComboBoxModel) provinceComboBox.getModel()).getSelected(); 
	}
	public Language getCommLanguage() {
		return ((LanguageListComboBoxModel)secondLanguageComboBox.getModel()).getSelected();
	}
	@Override
	public void update(Observable o, Object arg) {
		controller.setClient((Client) o);
	}
	
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
				formatter = new MaskFormatter(s);
			} 
		catch (java.text.ParseException exc) {
			
			Component frame = null;
			String invalidMedicareMsgText="The Medicare Format is not valid";
			JOptionPane.showMessageDialog(frame,invalidMedicareMsgText );
			}
			return formatter;
		}
	
	public Client retrieveClient(){
		if (((LanguageListComboBoxModel) secondLanguageComboBox.getModel()).getSelected() == null){
			String secondLangText="Please select a language of communication";
			warning.setText(secondLangText);
			return null;
		} else
			warning.setText(null);
		Client c = null;
		if (controller.getClient() != null){
			controller.getClient().set(this.getFirstName(), this.getLastName(), this.getMiddleName(), this.getPhoto(), this.getThumb(), this.getDateOfBirth(),
				this.getSex(), this.getMedicare(), ((LanguageListComboBoxModel) languageComboBox.getModel()).getSelected(), 
				((LanguageListComboBoxModel) secondLanguageComboBox.getModel()).getSelected(), null);
			controller.getClient().commit();
		} else {
			c = new Client(getFirstName(), getMiddleName(), getLastName(), getMedicare(), getMotherTongue(),
					getCommLanguage(), getMaritalStatus(), getWorkStatus(), getWorkStatusExtra(), getCanadaStatus(),
					getCanadaStatusExtra(), getOrigon(), getOrigonExtra(), getRefferal(), getRefferalExtra(), getReason(),
					getReasonExtra());
		}
			return c;
	}
	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		this.nameLabel.setText(messages.getString("clientFormPanelNameText"));
		this.medicareLabel.setText(messages.getString("clientFormPanelMedicareText"));
		this.dateOfBirthLabel.setText(messages.getString("clientFormPanelDOBText"));
		this.languageLabel.setText(messages.getString("clientFormPanelCommLangText"));
		this.secondLanguageLabel.setText(messages.getString("clientFormPanelGenderText"));
		this.sexLabel.setText(messages.getString("clientFormPanelGenderText"));
		this.uploadImage.setText(messages.getString("clientFormPanelUploadImageText"));
		
		
	}
}
