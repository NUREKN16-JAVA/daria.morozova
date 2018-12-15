package ua.nure.kn.morozova.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DatabaseException;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parentFrame;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonsPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailsButton;

	public BrowsePanel(MainFrame mainFrame) {
		parentFrame = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.add(getAddButton());
			buttonsPanel.add(getEditButton());
			buttonsPanel.add(getDeleteButton());
			buttonsPanel.add(getdDetailsButton());
		}
		return buttonsPanel;
	}

	private JButton getdDetailsButton() {
		if (detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setText("Details"); // localize //$NON-NLS-1$
			detailsButton.setName("detailsButton"); //$NON-NLS-1$
			detailsButton.setActionCommand("details"); //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete"); // localize //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText("Edit"); // localize //$NON-NLS-1$
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Add"); // localize //$NON-NLS-1$
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
			addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	public JTable getUserTable() {
		if (userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable"); //$NON-NLS-1$

		}
		return userTable;
	}

	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parentFrame.getUserDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			this.setVisible(false);
			parentFrame.showAddPanel();
		}

		if ("edit".equalsIgnoreCase(actionCommand)) {
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Row was not selected", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				Long selectedUserId = (Long) userTable.getValueAt(userTable.getSelectedRow(), 0);

				this.setVisible(false);
				parentFrame.showEditPanel(selectedUserId);
			}
		}

		if ("delete".equalsIgnoreCase(actionCommand)) {
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Row was not selected", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				long bufferedId = (long) userTable.getValueAt(userTable.getSelectedRow(), 0);
				System.out.println(bufferedId);
				System.out.println("jopa");
				int choice = JOptionPane.showOptionDialog(null, "You really want to delete?", "Delete?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (choice == JOptionPane.YES_OPTION) {
					try {
						User user = new User();
						user.setId(bufferedId);
						parentFrame.getUserDao().delete(user);
					} catch (DatabaseException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				initTable();
			}
		}

		if ("details".equalsIgnoreCase(actionCommand)) {
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Row was not selected", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				Long selectedUserId = (Long) userTable.getValueAt(userTable.getSelectedRow(), 0);
				this.setVisible(false);
				parentFrame.showDetailsPanel(selectedUserId);
			}
		}
	}
}
