package ua.nure.kn.morozova.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DatabaseException;
import ua.nure.kn.morozova.usermanagement.util.Messages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EditPanel extends JPanel implements ActionListener {

	private MainFrame parentFrame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private JPanel fieldPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private Long bufferedId;

	public EditPanel(MainFrame parent) {
		this.parentFrame = parent;
		initialize();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			User user = new User();
			user.setId(bufferedId);
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			} 
			try {
				parentFrame.getUserDao().update(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
		
		clearFields();
		this.setVisible(false);
		parentFrame.showBrowsePanel();
	}


    private void initialize() {
        this.setName("editPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);

    }
    
	private JPanel getButtonsPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);

		}
		return buttonPanel;
	}
	
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); 
			cancelButton.setName("cancelButton"); 
			cancelButton.setActionCommand("cancel"); 
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); 
			okButton.setName("okButton"); 
			okButton.setActionCommand("ok"); 
			okButton.addActionListener(this);
		}
		return okButton;
	}


	
	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}
	
	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);

	}
	
	private void clearFields() {
		getFirstNameField().setText(""); 
		getFirstNameField().setBackground(Color.white);

		getLastNameField().setText(""); 
		getLastNameField().setBackground(Color.white);

		getDateOfBirthField().setText(""); 
		getDateOfBirthField().setBackground(Color.white);

	}
	
	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			addLabeledField(fieldPanel, Messages.getString("AddPanel.firstname"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.lastname"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$

		}
		return fieldPanel;
	}
	

    void showUser(Long id) {
        try {
            bufferedId = id;
            User user = parentFrame.getUserDao().find(id);
            getFirstNameField().setText(user.getFirstName());
            getLastNameField().setText(user.getLastName());
            getDateOfBirthField().setText(DateFormat.getDateInstance().format(user.getDateOfBirth()));
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            parentFrame.showBrowsePanel();
        }
    }

}

	

