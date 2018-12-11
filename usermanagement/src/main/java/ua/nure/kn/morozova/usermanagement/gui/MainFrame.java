package ua.nure.kn.morozova.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DaoFactory;
import ua.nure.kn.morozova.usermanagement.db.UserDao;
import ua.nure.kn.morozova.usermanagement.util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final UserDao UserDao = null;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private UserDao dao;
	private JPanel editPanel;
	private JPanel deletePanel;
	private JPanel detailsPanel;

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}

	
	public UserDao getDao() {
		return dao;
	}

    
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("user_management")); //localize //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }


	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}
	
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);

	}

	public void showAddPanel() {
		showPanel(getAddPanel());	
	}
	
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}
	

    public void showEditPanel(Long id) {
        showPanel(getEditPanel(id));
    }

    public JPanel getEditPanel(Long id) {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        ((EditPanel) editPanel).showUser(id);
        return editPanel;
    }

    public void showDeletePanel(Long id) {
        showPanel(getDeletePanel(id));
    }

    private JPanel getDeletePanel(Long id) {
        if (deletePanel == null) {
            deletePanel = new DeletePanel(this);
        }
        ((DeletePanel) deletePanel).setBufferedId(id);
        return deletePanel;
    }



    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
        
    }

	public UserDao getUserDao() {
		return UserDao;
	}


    public void showDetailsPanel(Long id) {
        showPanel(getDetailsPanel(id));
    }

    public JPanel getDetailsPanel(Long id) {
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this);
        }
        ((DetailsPanel) detailsPanel).showUser(id);
        return detailsPanel;
    }


}


