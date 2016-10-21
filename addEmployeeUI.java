import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Insets;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class addEmployeeUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFirstname;
	private JTextField txtMiddlename;
	private JTextField txtLastname;
	private JTextField txtEmail;
	private JLabel lblThephoto;
	private File filePhoto;
	private String inPhoto;
	private	BufferedImage imageOfEmployee = null;
	private DB db = new DB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					addEmployeeUI frame = new addEmployeeUI();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public addEmployeeUI()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Setings.getMainIcon()));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 30, 30, 30, 30, 0, 30, 30, 30, 0};
		gbl_contentPane.rowHeights = new int[] {0, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 0;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstname = new JTextField();
		txtFirstname.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_txtFirstname = new GridBagConstraints();
		gbc_txtFirstname.gridwidth = 7;
		gbc_txtFirstname.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstname.gridx = 2;
		gbc_txtFirstname.gridy = 0;
		contentPane.add(txtFirstname, gbc_txtFirstname);
		txtFirstname.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name: ");
		lblMiddleName.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblMiddleName = new GridBagConstraints();
		gbc_lblMiddleName.anchor = GridBagConstraints.EAST;
		gbc_lblMiddleName.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiddleName.gridx = 1;
		gbc_lblMiddleName.gridy = 1;
		contentPane.add(lblMiddleName, gbc_lblMiddleName);
		
		txtMiddlename = new JTextField();
		txtMiddlename.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMiddlename = new GridBagConstraints();
		gbc_txtMiddlename.gridwidth = 7;
		gbc_txtMiddlename.insets = new Insets(0, 0, 5, 0);
		gbc_txtMiddlename.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMiddlename.gridx = 2;
		gbc_txtMiddlename.gridy = 1;
		contentPane.add(txtMiddlename, gbc_txtMiddlename);
		txtMiddlename.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 2;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		txtLastname = new JTextField();
		txtLastname.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_txtLastname = new GridBagConstraints();
		gbc_txtLastname.gridwidth = 7;
		gbc_txtLastname.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastname.gridx = 2;
		gbc_txtLastname.gridy = 2;
		contentPane.add(txtLastname, gbc_txtLastname);
		txtLastname.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 7;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
		gbc_lblPhoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoto.gridx = 1;
		gbc_lblPhoto.gridy = 4;
		contentPane.add(lblPhoto, gbc_lblPhoto);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("HP Simplified Light", Font.PLAIN, 12));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				upload();
			}
		});
		GridBagConstraints gbc_btnUpload = new GridBagConstraints();
		gbc_btnUpload.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpload.anchor = GridBagConstraints.WEST;
		gbc_btnUpload.gridx = 2;
		gbc_btnUpload.gridy = 4;
		contentPane.add(btnUpload, gbc_btnUpload);
		
		lblThephoto = new JLabel("");
		GridBagConstraints gbc_lblThephoto = new GridBagConstraints();
		gbc_lblThephoto.gridwidth = 4;
		gbc_lblThephoto.gridheight = 4;
		gbc_lblThephoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblThephoto.gridx = 3;
		gbc_lblThephoto.gridy = 4;
		contentPane.add(lblThephoto, gbc_lblThephoto);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				saveClick();
				dispose();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 7;
		gbc_btnSave.gridy = 8;
		contentPane.add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 8;
		gbc_btnCancel.gridy = 8;
		contentPane.add(btnCancel, gbc_btnCancel);
	}
	
	/* ----------------------------------------------------------------------------------------- */
	void saveClick()
	{
        saveFileToServer(inPhoto, filePhoto);
        addEmployeeToDB();
        clear();
	}
	/* ----------------------------------------------------------------------------------------- */
	void upload()
	{
		choosePhoto();
        setImege(imageOfEmployee);
	}
	
	/* ----------------------------------------------------------------------------------------- */
	private void choosePhoto()
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
		chooser.setFileFilter(filter);
		int returnValue = chooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            filePhoto = chooser.getSelectedFile();
            inPhoto = filePhoto.getName();
            try
			{
				imageOfEmployee = ImageIO.read(filePhoto);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
        }
	}
	/* ----------------------------------------------------------------------------------------- */
	void setImege(BufferedImage locImg)
	{
		
		String photo = "No Photo!";
		try
		{
			lblThephoto.setText(null);
			Image newimg = locImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			lblThephoto.setIcon(new ImageIcon(newimg));
		} catch (Exception e)
		{
			lblThephoto.setIcon(null);
			lblThephoto.setText(photo);
			e.printStackTrace();
		}
	}
	/* ----------------------------------------------------------------------------------------- */
	void saveFileToServer(String filename, File file)
	{
		SendFile sf = new SendFile();
		sf.setTheFile(file);
		sf.run();		
	}
	/* ----------------------------------------------------------------------------------------- */
	void addEmployeeToDB()
	{
		String fn_uf =  txtFirstname.getText();
		String mn_uf =  txtMiddlename.getText();
		String ln_uf =  txtLastname.getText();

		String fn = null ;
		String mn = null ;
		String ln = null ;

		if (fn_uf != null && (!fn_uf.isEmpty()) )
			fn =  fn_uf.substring(0, 1).toUpperCase() + fn_uf.substring(1).toLowerCase();
		else 
			fn = "";

		if (mn_uf != null && (!mn_uf.isEmpty()) )
			mn =  mn_uf.substring(0, 1).toUpperCase() + mn_uf.substring(1).toLowerCase();
		else
			mn_uf = "";

		if (ln_uf != null && (!ln_uf.isEmpty()) )
			ln =  ln_uf.substring(0, 1).toUpperCase() + ln_uf.substring(1).toLowerCase();
		else 
			ln_uf = "";

		String em =  txtEmail.getText();
		String ph =  inPhoto;

		if (ph == null || (ph.isEmpty()) )
			ph = "000012.png";

		Employee emp = new Employee(fn, mn, ln, em, ph);
		try
		{
			db.connectDB();
			db.addEmployee(emp);
			db.disconnectDB();
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this,
				e.getMessage(),
				"Error!!!",
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	/* ----------------------------------------------------------------------------------------- */
	void clear()
	{
		txtFirstname.setText("");
		txtMiddlename.setText("");
		txtLastname.setText("");
		txtEmail.setText("");
		lblThephoto.setIcon(null);
		lblThephoto.setText("");
		filePhoto 		= null;
		inPhoto 		= null;
		imageOfEmployee = null;
		contentPane.repaint();
	}
}
