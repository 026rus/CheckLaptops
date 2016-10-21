import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;

public class EditLaptop extends JDialog
{
	/**
	 * 
	 */
	JDialog that = this;
	private static final long serialVersionUID = 1L;
	private final JPanel contentLaptopPanel = new JPanel();
	private final JPanel contentEmployeePanel = new JPanel();
	private JTabbedPane tabbedPane;
	private JPanel editPane;
	private DB db = new DB();
	DefaultTableModel laptop_model = new DefaultTableModel();
	DefaultTableModel employee_model = new DefaultTableModel();
	private final String PHOTOD = Setings.getDefoultePhoto();
	private JTable laptop_table;
	private JTextField tag_textField;
	private JTextField equipment_textField;
	private JTextField firstname_textField;
	private JTextField lastname_textField;
	private JTextArea notes_textField;
	private JLabel lblPhoto;
	private String inPhoto;
	private File filePhoto;
	private Laptop correntLaptop = null;
	private JTable employee_table;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args)
	{
		try
		{
			EditLaptop dialog = new EditLaptop(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditLaptop(Laptop cl)
	{
		this.correntLaptop = cl;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Setings.getMainIcon()));
		setBounds(100, 100, 836, 812);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("HP Simplified", Font.PLAIN, 11));
			// ImageIcon laptopIcon = new ImageIcon(Setings.getLaptopIconFile());
			getContentPane().add(tabbedPane);
			tabbedPane.addTab("Laptops", null, contentLaptopPanel, null);
			contentLaptopPanel.setBackground(SystemColor.menu);
			contentLaptopPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentLaptopPanel.setLayout(new BorderLayout(0, 0));
			JScrollPane laptop_scrollPane = new JScrollPane();
			laptop_scrollPane.getViewport().setBackground(SystemColor.window);
			contentLaptopPanel.add(laptop_scrollPane);
			laptop_table = new JTable();
			laptop_table.setBackground(SystemColor.window);
			laptop_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			laptop_table.setFont(new Font("HP Simplified", Font.PLAIN, 12));
			ListSelectionModel laptop_rowSM = laptop_table.getSelectionModel();
			laptop_scrollPane.setViewportView(laptop_table);
			{
				laptop_rowSM.addListSelectionListener(new ListSelectionListener()
				{
					@Override
					public void valueChanged(ListSelectionEvent e)
					{
						if (e.getValueIsAdjusting()) return ; //editRow();
					
						ListSelectionModel lsm = (ListSelectionModel) e.getSource();
						
						if(lsm.isSelectionEmpty()) return;
						else
						{
							laptopselected(laptop_table);
							// editRow();
						}
					}
				});
				populateTable(laptop_table, 4);
				laptop_table.getModel().addTableModelListener(new TableModelListener()
						{
	
							@Override
							public void tableChanged(TableModelEvent e)
							{
								System.out.println(e);
								updateUI();
							}
					
						});
			}
			contentEmployeePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentEmployeePanel.setBackground(SystemColor.menu);
			tabbedPane.addTab("Emoyee", null, contentEmployeePanel, null);
			if (correntLaptop != null) tabbedPane.setSelectedIndex(1);
			contentEmployeePanel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane employee_scrollPane = new JScrollPane();
				contentEmployeePanel.add(employee_scrollPane, BorderLayout.CENTER);
				{
					employee_table = new JTable();
					employee_table.setBackground(SystemColor.window);
					employee_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					employee_table.setFont(new Font("HP Simplified", Font.PLAIN, 12));
					ListSelectionModel employee_rowSM = employee_table.getSelectionModel();
					employee_scrollPane.setViewportView(employee_table);
					{
						employee_rowSM.addListSelectionListener(new ListSelectionListener()
						{
							@Override
							public void valueChanged(ListSelectionEvent e)
							{
								if (e.getValueIsAdjusting()) return ; //editRow();
							
								ListSelectionModel lsm = (ListSelectionModel) e.getSource();
								
								if(lsm.isSelectionEmpty()) return;
								else
								{
									laptopselected(employee_table);
									// editRow();
								}
							}
						});
						populateTable(employee_table, 6);
					}
				}
			}
		
		}
		{
			editPane = new JPanel();
			editPane.setBackground(SystemColor.menu);
			getContentPane().add(editPane);
			GridBagLayout gbl_editPane = new GridBagLayout();
			gbl_editPane.columnWidths = new int[]{115, 334, 0, 0};
			gbl_editPane.rowHeights = new int[]{20, 20, 20, 20, 0, 0, 3, 0};
			gbl_editPane.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_editPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			Border b;
			editPane.setLayout(gbl_editPane);
			{
				JLabel tagLabel = new JLabel("TAG");
				tagLabel.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_tagLabel = new GridBagConstraints();
				gbc_tagLabel.ipadx = 5;
				gbc_tagLabel.anchor = GridBagConstraints.EAST;
				gbc_tagLabel.insets = new Insets(0, 0, 5, 5);
				gbc_tagLabel.gridx = 0;
				gbc_tagLabel.gridy = 0;
				editPane.add(tagLabel, gbc_tagLabel);
			}
			{
				String tempTAG = "";
				if (correntLaptop != null)  tempTAG = correntLaptop.getTag();
				tag_textField = new JTextField(tempTAG);
				tag_textField.setEditable(false);
				tag_textField.setDisabledTextColor(Color.BLACK);
				tag_textField.setBackground(Color.WHITE);
				tag_textField.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				b = tag_textField.getBorder();
				GridBagConstraints gbc_tag_textField = new GridBagConstraints();
				gbc_tag_textField.weightx = 0.5;
				gbc_tag_textField.fill = GridBagConstraints.BOTH;
				gbc_tag_textField.insets = new Insets(0, 0, 5, 5);
				gbc_tag_textField.gridx = 1;
				gbc_tag_textField.gridy = 0;
				editPane.add(tag_textField, gbc_tag_textField);
				tag_textField.setColumns(10);
			}
			{
				lblPhoto = new JLabel("Photo");
				lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
				lblPhoto.setBorder(b);
				lblPhoto.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				try
				{
					lblPhoto.setText(null);
					BufferedImage locImg = ImageIO.read(new File(PHOTOD));
					Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
					lblPhoto.setIcon(new ImageIcon(newimg));
				} catch (IOException e)
				{
					lblPhoto.setIcon(null);
					lblPhoto.setText("Photo");
										e.printStackTrace();
				}
				GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
				gbc_lblPhoto.weighty = 1.0;
				gbc_lblPhoto.weightx = 1.0;
				gbc_lblPhoto.fill = GridBagConstraints.BOTH;
				gbc_lblPhoto.gridwidth = 6;
				gbc_lblPhoto.gridheight = 6;
				gbc_lblPhoto.insets = new Insets(0, 0, 5, 0);
				gbc_lblPhoto.gridx = 2;
				gbc_lblPhoto.gridy = 0;
				editPane.add(lblPhoto, gbc_lblPhoto);
			}
			{
				JLabel equipmentLabel = new JLabel("Equipment");
				equipmentLabel.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_equipmentLabel = new GridBagConstraints();
				gbc_equipmentLabel.ipadx = 5;
				gbc_equipmentLabel.anchor = GridBagConstraints.EAST;
				gbc_equipmentLabel.insets = new Insets(0, 0, 5, 5);
				gbc_equipmentLabel.gridx = 0;
				gbc_equipmentLabel.gridy = 1;
				editPane.add(equipmentLabel, gbc_equipmentLabel);
			}
			{
				String tempName = "";
				if (correntLaptop != null)  tempName = "Laptop";
				equipment_textField = new JTextField(tempName);
				equipment_textField.setEditable(false);
				equipment_textField.setDisabledTextColor(Color.BLACK);
				equipment_textField.setBackground(Color.WHITE);
				equipment_textField.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_equipment_textField = new GridBagConstraints();
				gbc_equipment_textField.weightx = 0.5;
				gbc_equipment_textField.fill = GridBagConstraints.BOTH;
				gbc_equipment_textField.insets = new Insets(0, 0, 5, 5);
				gbc_equipment_textField.gridx = 1;
				gbc_equipment_textField.gridy = 1;
				editPane.add(equipment_textField, gbc_equipment_textField);
				equipment_textField.setColumns(10);
			}
			{
				JLabel firstNameLabel = new JLabel("First Name");
				firstNameLabel.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
				gbc_firstNameLabel.ipadx = 5;
				gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
				gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_firstNameLabel.gridx = 0;
				gbc_firstNameLabel.gridy = 2;
				editPane.add(firstNameLabel, gbc_firstNameLabel);
			}
			{
				firstname_textField = new JTextField();
				firstname_textField.setEnabled(false);
				firstname_textField.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				firstname_textField.setDisabledTextColor(Color.BLACK);
				GridBagConstraints gbc_firstname_textField = new GridBagConstraints();
				gbc_firstname_textField.weightx = 0.5;
				gbc_firstname_textField.fill = GridBagConstraints.BOTH;
				gbc_firstname_textField.insets = new Insets(0, 0, 5, 5);
				gbc_firstname_textField.gridx = 1;
				gbc_firstname_textField.gridy = 2;
				editPane.add(firstname_textField, gbc_firstname_textField);
				firstname_textField.setColumns(10);
			}
			{
				JLabel lastNameLabel = new JLabel("Last Name");
				lastNameLabel.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
				gbc_lastNameLabel.ipadx = 5;
				gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
				gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lastNameLabel.gridx = 0;
				gbc_lastNameLabel.gridy = 3;
				editPane.add(lastNameLabel, gbc_lastNameLabel);
			}
			{
				lastname_textField = new JTextField();
				lastname_textField.setEnabled(false);
				lastname_textField.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				lastname_textField.setDisabledTextColor(Color.BLACK);
				GridBagConstraints gbc_lastname_textField = new GridBagConstraints();
				gbc_lastname_textField.weightx = 0.5;
				gbc_lastname_textField.insets = new Insets(0, 0, 5, 5);
				gbc_lastname_textField.fill = GridBagConstraints.BOTH;
				gbc_lastname_textField.gridx = 1;
				gbc_lastname_textField.gridy = 3;
				editPane.add(lastname_textField, gbc_lastname_textField);
				lastname_textField.setColumns(10);
			}
			{
				JLabel notesLabel = new JLabel("Notes");
				notesLabel.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_notesLabel = new GridBagConstraints();
				gbc_notesLabel.ipadx = 5;
				gbc_notesLabel.anchor = GridBagConstraints.EAST;
				gbc_notesLabel.insets = new Insets(0, 0, 5, 5);
				gbc_notesLabel.gridx = 0;
				gbc_notesLabel.gridy = 4;
				editPane.add(notesLabel, gbc_notesLabel);
			}
			{
				notes_textField = new JTextArea();
				notes_textField.setEditable(false);
				notes_textField.setBorder(b);
				notes_textField.setFont(new Font("HP Simplified", Font.PLAIN, 12));
				GridBagConstraints gbc_notes_textField = new GridBagConstraints();
				gbc_notes_textField.weightx = 0.5;
				gbc_notes_textField.gridheight = 2;
				gbc_notes_textField.insets = new Insets(0, 0, 5, 5);
				gbc_notes_textField.fill = GridBagConstraints.BOTH;
				gbc_notes_textField.gridx = 1;
				gbc_notes_textField.gridy = 4;
				editPane.add(notes_textField, gbc_notes_textField);
				notes_textField.setColumns(10);
			}
			{
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.menu);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				{
					JButton btnAdd = new JButton("Add Laptop");
					btnAdd.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent arg0) 
						{
							addLaptop();
						}
					});

					{
						JButton btnAddEmployee = new JButton("Add Employee");
						btnAddEmployee.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								addNewEmployy();
							}
						});
						btnAddEmployee.setFont(new Font("HP Simplified Light", Font.PLAIN, 11));
						buttonPane.add(btnAddEmployee);
					}
					btnAdd.setFont(new Font("HP Simplified", Font.PLAIN, 11));
					buttonPane.add(btnAdd);
				}
				{
					JButton btnUpdate = new JButton("Save");
					btnUpdate.setFont(new Font("HP Simplified", Font.PLAIN, 11));
					btnUpdate.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0)
						{
							updateLaptop();							
							populateTable(laptop_table, 4);
							tabbedPane.setSelectedIndex(0);
							that.dispose();
						}
					});
					{
						JButton btnDelete = new JButton("Delete");
						btnDelete.setFont(new Font("HP Simplified", Font.PLAIN, 11));
						btnDelete.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent arg0) 
							{
								deleteLaptop();
							}
						});
						buttonPane.add(btnDelete);
					}
					buttonPane.add(btnUpdate);
				}
			}
		}
		if (correntLaptop!=null)
		{
		addLaptop(correntLaptop);
		that.addComponentListener(new ComponentListener()
			{
				@Override
				public void componentShown(ComponentEvent e)
				{
					afterSetUI(correntLaptop.getTag());
				}

				@Override
				public void componentResized(ComponentEvent e) { }
				@Override
				public void componentMoved(ComponentEvent e) { }
				@Override
				public void componentHidden(ComponentEvent e) { }
			});
		}
	}
	
	private void addNewEmployy()
	{
		tabbedPane.setSelectedIndex(1);
		that.setAlwaysOnTop(false);
		JDialog dialog = new addEmployeeUI();
		dialog.setModal(true);
		dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		that.setAlwaysOnTop(true);
		populateTable(employee_table, 6);
	}
	private void afterSetUI(String tag)
	{
		// show message to explain what to do ( for additing new laptop )
		JOptionPane.showMessageDialog(that, Setings.getChooseEmployeeMessage());
			
		int sr = findlaptop(tag);
		if (sr > 0) laptop_table.setRowSelectionInterval(sr, sr);
		tabbedPane.setSelectedIndex(1);
	}
	/* --------------------------------------------------------------------------------------- */
	private void updateLaptop()
	{
		int employeeRow = employee_table.getSelectedRow();
		
		if ( employeeRow < 0 )
		{
			JOptionPane.showMessageDialog(this,
							Setings.getUpdateEmployeeErrorMessage(),
							"Error!!!",
							JOptionPane.ERROR_MESSAGE);
			return;
		}
		int emp_id	 	= Integer.parseInt(employee_table.getValueAt(employeeRow, 0).toString());
		String tag 		= tag_textField.getText();
		String node 	= notes_textField.getText();
		String fname 	= firstname_textField.getText();
		String lname	= lastname_textField.getText();

		System.out.println("Info to UPDATE  emp_id: " + emp_id + " TAG: " + tag + " node: " + node );
		Laptop temp = new Laptop(tag, emp_id, node);
		
		int addYesNo = JOptionPane.showConfirmDialog(
			    this,
			    "Do you want to associate this laptop with "
			    + fname + " " + lname,
			    "Add LapTop",
			    JOptionPane.YES_NO_OPTION);
		if (addYesNo == JOptionPane.YES_OPTION)
		{
			try
			{
				db.connectDB();
				db.updatetLaptop(temp);
				db.disconnectDB();
			} catch (SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
								e.getMessage(),
								"Error!!!",
								JOptionPane.ERROR_MESSAGE);
			}
		}
		else 
			System.out.println("Donnt wont to add LapTop!!!");
	}
	/* ----------------------------------------------------------------------------------------- */
	/* Ceking if user filed in all requared filds */
	private boolean recFeal(Laptop lap)
	{
		return !lap.getTag().isEmpty();
	}
	/* ----------------------------------------------------------------------------------------- */
	private void setImege()
	{

		String photo = PHOTOD;
		if (filePhoto != null)
		{
			try
			{
				lblPhoto.setText(null);
				BufferedImage locImg = ImageIO.read(filePhoto);
				Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				lblPhoto.setIcon(new ImageIcon(newimg));
			} catch (IOException e)
			{
				lblPhoto.setIcon(null);
				lblPhoto.setText(photo);
				e.printStackTrace();
			}
		}	
		else if (inPhoto != null)
		{
			photo = db.getUrlDBhttp() + inPhoto; // correcting url to account for local files
			try
			{
				lblPhoto.setText(null);
				BufferedImage locImg = ImageIO.read(new File(photo));
				Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				lblPhoto.setIcon(new ImageIcon(newimg));
			} catch (IOException e)
			{
				lblPhoto.setIcon(null);
				lblPhoto.setText(photo);
				e.printStackTrace();
			}
		}	
		else try
		{
			lblPhoto.setText(null);
			BufferedImage locImg = ImageIO.read(new File(photo));
			Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
			lblPhoto.setIcon(new ImageIcon(newimg));
		} catch (IOException e)
		{
			lblPhoto.setIcon(null);
			lblPhoto.setText(photo);
			e.printStackTrace();
		}
	}
	/* ----------------------------------------------------------------------------------------- */
	private void updateUI()
	{
		int rowLaptop 		= laptop_table.getSelectedRow(),
			rowEmployee		= -1;
		String stren = laptop_table.getValueAt(rowLaptop, Setings.LAPTOP_EMPLOYEE_ID).toString();
		if (!stren.equals(""))
			rowEmployee = findemployee(stren);
		
		System.out.println("Laptops row: " + rowLaptop);
		System.out.println("Employee row: " + rowEmployee);

		String fname = "";
		String lname = "";
		if (rowEmployee < 0)
		{
			inPhoto = null;
		}
		else
		{
			inPhoto = employee_table.getValueAt(rowEmployee, Setings.EMPLOYEE_PHOTO).toString();
			fname = employee_table.getValueAt(rowEmployee, Setings.EMPLOYEE_FIRST_NAME).toString();
			lname = employee_table.getValueAt(rowEmployee, Setings.EMPLOYEE_LAST_NAME).toString();
		}
		
		filePhoto = null;
		
		tag_textField.setText(laptop_table.getValueAt(rowLaptop, Setings.LAPTOP_TAG).toString());
		equipment_textField.setText(Setings.getEquipmentName());
		firstname_textField.setText(fname);
		lastname_textField.setText(lname);
		notes_textField.setText(laptop_table.getValueAt(rowLaptop, Setings.LAPTOP_NOTES).toString());
		addimage(inPhoto);
		laptop_table.clearSelection();
	}
	/* ----------------------------------------------------------------------------------------- */
	private void clearEnterUI()
	{
		inPhoto = null;
		filePhoto = null;
		tag_textField.setText("");
		equipment_textField.setText("");
		firstname_textField.setText("");
		lastname_textField.setText("");
		notes_textField.setText("");
		String photo = PHOTOD;
		try
		{
			lblPhoto.setText(null);
			BufferedImage locImg = ImageIO.read(new File(photo));
			Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
			lblPhoto.setIcon(new ImageIcon(newimg));
		} catch (IOException e)
		{
			lblPhoto.setIcon(null);
			lblPhoto.setText(photo);
			e.printStackTrace();
		}
			
		laptop_table.clearSelection();
	}
	/* ----------------------------------------------------------------------------------------- */
	private void deleteLaptop()
	{
		int selectedRow = laptop_table.getSelectedRow();
			
		if ((tabbedPane.getSelectedIndex() == 0 ) && (selectedRow >= 0) )
		{
			int addYesNo = JOptionPane.showConfirmDialog(
			    	this,
			    	Setings.getLaptopDeleteMessage(),
			    	"Delete Laptop",
			    	JOptionPane.YES_NO_OPTION);
			if (addYesNo == JOptionPane.YES_OPTION)
			{
				
				// There is no row selected !!!
				if (selectedRow < 0) return;
		
				int id = Integer.parseInt(laptop_table.getValueAt(selectedRow, 0).toString());
		
				try
				{
					db.connectDB();
					db.deleteLaptopByID(id);
					db.disconnectDB();
					clearEnterUI();
					populateTable(laptop_table, 4);
					}
				catch ( SQLException e)
				{
					JOptionPane.showMessageDialog(this,
									e.getMessage(),
									"Error!!!",
									JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,
							Setings.getDeleteErrorTabMessage(),
							"Error!!!",
							JOptionPane.ERROR_MESSAGE);
		}
		
	}
	/* ----------------------------------------------------------------------------------------- */
	/* Aditing laptop stright from Equipment UI */
	private void addLaptop(Laptop newLaptop)
	{
		if (findlaptop(newLaptop.getTag()) > 0 )
		{
			
			JOptionPane.showMessageDialog(this,
							Setings.getLaptopExistMessage(),
							"Error!!!",
							JOptionPane.ERROR_MESSAGE);
			return;
		}

		try
		{
			db.connectDB();
			db.addLaptop(newLaptop);
			db.disconnectDB();
			clearEnterUI();
			populateTable(laptop_table, 4);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					e.getMessage(),
					"Error!!!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	/* ----------------------------------------------------------------------------------------- */
	private void addLaptop()
	{
		String TheTAG = getTAG();
		if (TheTAG == null ) return;
		if (findlaptop(TheTAG) > 0 )
		{
			
			JOptionPane.showMessageDialog(this,
							Setings.getLaptopExistMessage(),
							"Error!!!",
							JOptionPane.ERROR_MESSAGE);
			return;
		}

		int selectedRow = laptop_table.getSelectedRow();
		if (selectedRow >= 0)
		{
			clearEnterUI();
			String[] temp = {"","","","","","",""};
			laptop_model.addRow(temp);
			tag_textField.requestFocus();
			setImege();
			return;
			
		}
		selectedRow--;
		Laptop lap = new Laptop();
		lap.setTag(TheTAG);
		
		if (recFeal(lap))
		{
			try
			{
				db.connectDB();
				db.addLaptop(lap);
				db.disconnectDB();
				clearEnterUI();
				populateTable(laptop_table, 4);
				afterSetUI(TheTAG);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
						e.getMessage(),
						"Error!!!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,
					"The TAG can not be empty!",
					"Error!!!",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	/* ----------------------------------------------------------------------------------------- */
	private String getTAG()
	{
		String retval = null;
		 retval = (String)JOptionPane.showInputDialog(
                 this,
                 Setings.getEnterTheTagPromt() 
                 + "",
                 "");
		return retval;
	}
	/* ----------------------------------------------------------------------------------------- */
	private void populateTable(JTable table, int numcoll)
	{
		DefaultTableModel model = new DefaultTableModel();
		while (model.getRowCount() >0 ) 	model.removeRow(0);
		laptop_model.setColumnIdentifiers(db.getLaptopColomNames());
		System.out.println("The Number of numcoll = " + numcoll);
		try
		{
			
			Object[][] temp;
			String[] collnames;
			if (numcoll == 4)
				{
					temp = db.getLaptopTable();
					collnames = db.getLaptopColomNames();
				}
			else 
				{
					temp = db.getEmployeeTable();
					collnames = db.getEmployeeColomNames();
				}
			
			model.setColumnIdentifiers(collnames);
			
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(numcoll-1).setPreferredWidth(200);
			if (numcoll >=5 ) table.getColumnModel().getColumn(numcoll-2).setPreferredWidth(300);

			for (int i=0; i<temp.length; i++)
			{
				model.addRow(temp[i]);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/* ----------------------------------------------------------------------------------------- */
	private void laptopselected(JTable table)
	{
		int selectedRow = table.getSelectedRow();
		int numOfColomn = table.getColumnCount();
		
		String[] arrstr = new String[numOfColomn]; 

		for (int i=0; i<numOfColomn; i++)
			if ( table.getValueAt(selectedRow, i) != null) arrstr[i] = table.getValueAt(selectedRow, i).toString();
				else arrstr[i] = "NULL";
		
		if (numOfColomn == 4 )
		{
			tag_textField.setText(arrstr[1]);
			notes_textField.setText(arrstr[3]);
			equipment_textField.setText("Laptop");
			if (!arrstr[2].equals(""))
			{
				int rr = findemployee(arrstr[2] );
				if (rr > 0)
				{
					firstname_textField.setText(employee_table.getValueAt(rr, Setings.EMPLOYEE_FIRST_NAME).toString());
					lastname_textField.setText(employee_table.getValueAt(rr, Setings.EMPLOYEE_LAST_NAME).toString());
					addimage(employee_table.getValueAt(rr, Setings.EMPLOYEE_PHOTO).toString());
				}
				else 
				{
					firstname_textField.setText("");
					lastname_textField.setText("");
					addimage(Setings.getDefoultePhoto());
				}
			}
		
		}
		else if (numOfColomn == 6 )
		{
			firstname_textField.setText(arrstr[1]);
			lastname_textField.setText(arrstr[3]);
			addimage(arrstr[5]);
		}
	}
	/* ----------------------------------------------------------------------------------------- */
	private int findlaptop(String str)
	{
		int x=-1;
		for (int i=0; i<laptop_table.getRowCount(); i++)
		{
			if (laptop_table.getValueAt(i, 1).equals(str))
			{
				x=i;
				break;
			}
		}
		return x;
	}
	/* ----------------------------------------------------------------------------------------- */
	private int findemployee(String str)
	{
		int x=-1;
		for (int i=0; i<employee_table.getRowCount(); i++)
		{
			if (employee_table.getValueAt(i, 0).equals(str))
			{
				x=i;
				break;
			}
		}
		return x;
	}
	/* ----------------------------------------------------------------------------------------- */
	private void addimage(String photo)
	{
		
		if ( ( photo.contains(PHOTOD) ) || ( photo.equals("NULL") ) || (photo.equals("")))
		{

			photo = PHOTOD;
			try
			{
				lblPhoto.setText(null);
				BufferedImage locImg = ImageIO.read(new File(photo));
				Image newimg = locImg.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				lblPhoto.setIcon(new ImageIcon(newimg));
			} catch (IOException e)
			{
				lblPhoto.setIcon(null);
				lblPhoto.setText(photo);
									e.printStackTrace();
			}
		}
		else
		{
			URL url;
			try
			{
				photo = db.getUrlDBhttp() + db.getUrlPeopelfolder() + photo;
				url = new URL(photo);
				System.out.println("EditRow() URL of Photo: " + photo);
				Image img = ImageIO.read(url);
				Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(newimg);
				lblPhoto.setText(null);
				lblPhoto.setIcon(image);
				lblPhoto.setVisible(true);
				editPane.repaint();
			}
			catch (Exception e)
			{
				lblPhoto.setIcon(null);
				lblPhoto.setText(photo);
				e.printStackTrace();
			}
		}
	}
}
