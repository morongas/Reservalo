package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private ColeccionDeDuenos duenos;
	private JTable table;
	private static DefaultTableModel dtm = new DefaultTableModel();
	private ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
	int op = -1;


	public VentanaAdmin(Administrador admin) {
		setTitle("RESERVALO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAdmin.class.getResource("/Imagenes/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Bienvenido " + admin.getUser());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombre.setBounds(10, 10, 592, 41);
		contentPane.add(lblNombre);
		
		JLabel labelSeleccionado = new JLabel("");
		labelSeleccionado.setBounds(306, 330, 256, 21);
		contentPane.add(labelSeleccionado);
		
		
		/*JComboBox<String> comboBox = new JComboBox<String>();   // tambien se pueden mostrar los verificados con el combobox
		comboBox.setBounds(199, 124, 318, 21);
		contentPane.add(comboBox);*/
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaInicio w = new VentanaInicio();
				w.setVisible(true);
				setVisible(false);
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCerrarSesion.setBounds(77, 378, 145, 29);
		contentPane.add(btnCerrarSesion);
		
		JsonEngine json = new JsonEngine();
		try {
			duenos = json.cargarDuenos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		 if (existeAlojamiento()) {
				for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
					for(int j=0; j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
						if ((duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).isSolicitoVerificacion()== true) &&
								!(duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado())) {
							alojamientos.add(duenos.getColeccionDuenos().get(i).getAlojamiento().get(j));
						}
					}
				}
			/*	for (int i=0; i<alojamientos.size(); i++) {
					comboBox.addItem(alojamientos.get(i).getNombre());  // aqui se carga el comboBox en caso de necesitarlo
				}*/
		 }
		
		JButton btnVerificar = new JButton("Verificar Alojamiento");
		btnVerificar.setBackground(Color.LIGHT_GRAY);
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(op==-1) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun establecimiento!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
				
					for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
						for(int j=0; j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
							if (duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre().equals(labelSeleccionado.getText())){
								
								int op = JOptionPane.showConfirmDialog(null, "Esta seguro que verificar el establecimiento?");
								if(op == JOptionPane.YES_OPTION) {
									
									duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).setVerificado(true);
									try {
										json.guardarDuenos(duenos);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									JOptionPane.showMessageDialog(null, "El establecimiento " + labelSeleccionado.getText() + 
									 " ha sido verificado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
									
									
								}
								else {
									JOptionPane.showMessageDialog(null, "No se pudo verificar el establecimiento","Mensaje",JOptionPane.INFORMATION_MESSAGE);
								}
								
								VentanaAdmin w = new VentanaAdmin(admin);
								w.setVisible(true);
								setVisible(false);
							}
						}
					}
				}
				
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerificar.setBounds(442, 378, 210, 29);
		contentPane.add(btnVerificar);

		
		JLabel lblAlojamientosPorVerificar = new JLabel("Alojamientos por verficar");
		lblAlojamientosPorVerificar.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblAlojamientosPorVerificar.setBounds(57, 76, 231, 21);
		contentPane.add(lblAlojamientosPorVerificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 114, 610, 189);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tipo", "Nombre", "Cantidad de habitaciones", "Telefono","Rif" , "Estado"
			}
		));
		scrollPane.setViewportView(table);
		
		String titulos[] = {"Tipo","Nombre","Cantidad de habitaciones","Telefono","Rif" , "Estado"};
		
		dtm = new DefaultTableModel(0,0) {
			
			@Override
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		
		dtm.setColumnIdentifiers(titulos);
		

		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getValueAt(0,0) != null){
					int seleccionado = table.rowAtPoint(e.getPoint());
					labelSeleccionado.setText((String) table.getValueAt(seleccionado, 1));
					op=0;
				}
				
			}
		});
		
		table.setModel(dtm);
		
		JLabel lblSeleccion = new JLabel("Seleccionado:");
		lblSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeleccion.setBounds(207, 330, 117, 21);
		contentPane.add(lblSeleccion);
		
		if(!alojamientos.isEmpty()) { //llenar tabla
			for(Alojamiento a : alojamientos) {
				dtm.addRow(new Object[] {
						tipo(a.getTipoDeAlojamiento()),a.getNombre(),a.getcHabitaciones(),a.getTelefono(),a.getRif(),a.getEstado()
						});
				System.out.println(a.getEstado());
			}
		}
		else {
			for(int i=0; i<100; i++) {
				dtm.addRow(new Object[] {null,null,null,null,null});
			}
		}
		
		this.setLocationRelativeTo(null);
		

	}
	
	public boolean existeAlojamiento() { 
		boolean ver = false;
		
		for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
			for(int j=0; j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
				if (duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado() == false) {
				ver = true;
				}
			}
		}
		return ver;
		
	}
	
	
	public static String tipo(int i) {
		switch(i) {
			case 0:
				return "Hotel";
			
			case 1:
				return "Posada";
			
			case 2:
				return "Apartamento";
			
			default:
				return null;
		}
	}
}
