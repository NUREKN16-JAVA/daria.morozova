package ua.nure.kn.morozova.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.morozova.usermanagement.db.DatabaseException;
import ua.nure.kn.morozova.usermanagement.util.Messages;

public class DeletePanel extends JPanel implements ActionListener {

	private Long userId;
	private MainFrame parentFrame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private JPanel fieldPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;


		public DeletePanel(MainFrame parent) {
			this.parentFrame = parent;
			initialize();
		}

		private void initialize() {
			this.setName("deletePanel"); //$NON-NLS-1$
			this.setLayout(new BorderLayout());
			this.add(getFieldPanel(), BorderLayout.NORTH);
			this.add(getButtonPanel(), BorderLayout.SOUTH);
		}

		private JPanel getButtonPanel() {
			if (buttonPanel == null) {
				buttonPanel = new JPanel();
				buttonPanel.add(getOkButton());
				buttonPanel.add(getCancelButton());
			}
			return buttonPanel;
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


		private JButton getCancelButton() {
			if (cancelButton == null) {
				cancelButton = new JButton();
				cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
				cancelButton.setName("cancelButton"); //$NON-NLS-1$
				cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
				cancelButton.addActionListener(this);
			}
			return cancelButton;
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

	    public void setBufferedId(Long id) {
	        this.userId = id;
	    }
	    
		public void actionPerformed(ActionEvent e) {
			if ("ok".equalsIgnoreCase(e.getActionCommand())) { //$NON-NLS-1$
	            try {
	                parentFrame.getUserDao().delete(parentFrame.getUserDao().find(userId));
	            } catch (DatabaseException e1) {
	                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        setVisible(false);
	        parentFrame.showBrowsePanel();
	    }
}

