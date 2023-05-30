package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ventanaEditarEst extends JFrame {

	private JPanel contentPane;
	private Alojamiento copia;
	private JTextField textNombre;
	private JTextField textNum;
	private JTable table;
	private JComboBox comboTipo;
	private JTextArea textDir;
	private DefaultTableModel tabla;
	private int select =-1;
	private Dueno aux;
	private ColeccionDeDuenos duenos;
	private JsonEngine json;
	
	public ventanaEditarEst(Alojamiento alojamiento, Dueno d) throws IOException {
		setResizable(false);
		json = new JsonEngine();
		aux = d;
		copia = alojamiento;
		tabla = new DefaultTableModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSeleccionado = new JLabel("");
		labelSeleccionado.setBounds(391, 372, 147, 13);
		contentPane.add(labelSeleccionado);
		
		JLabel lblNewLabel = new JLabel("*Para editar solo necesitara sobrescribir los campos*");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
		lblNewLabel.setBounds(116, 10, 494, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del establecimiento: ");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(105, 87, 185, 25);
		contentPane.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(301, 87, 213, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tipo de Establecimiento:");
		lblNewLabel_1_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(105, 122, 185, 25);
		contentPane.add(lblNewLabel_1_1);
		
		comboTipo = new JComboBox();
		comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Hotel", "Posada", "Apartamento"}));
		comboTipo.setBounds(301, 123, 164, 21);
		contentPane.add(comboTipo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Direccion:");
		lblNewLabel_1_1_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(105, 157, 185, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 159, 213, 22);
		contentPane.add(scrollPane);
		
		textDir = new JTextArea();
		scrollPane.setViewportView(textDir);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Telefono:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(105, 192, 185, 25);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textNum = new JTextField();
		textNum.setBounds(301, 197, 131, 19);
		contentPane.add(textNum);
		textNum.setColumns(10);
		if(alojamiento.existeTemporal()!=false) {
			try {
				copia = json.cargarAlo();
				textNombre.setText(copia.getNombre());
				comboTipo.setSelectedIndex(copia.getTipoDeAlojamiento());
				textDir.setText(copia.getDireccion());
				textNum.setText(copia.getTelefono());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			duenos = json.cargarDuenos();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Habitaciones:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1.setBounds(105, 227, 185, 25);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		JButton bontonGuardar = new JButton("Guardar Cambios");
		bontonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Esta seguro que desea guardar los cambios?\nSi cambio el nombre, el tipo, direccion o telefono\nse tendra que someter nuevamente a verificacion el alojamiento");
				if(op == JOptionPane.YES_OPTION) {
					copia.setNombre(textNombre.getText());
					copia.setTelefono(textNum.getText());
					copia.setDireccion(textDir.getText());
					copia.setTipoDeAlojamiento(comboTipo.getSelectedIndex());
					try {
						json.alojamientoTemp(copia);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					Alojamiento aux2 = new Alojamiento();
					Alojamiento aux3 = new Alojamiento();
					try {
						aux2 = json.cargarAlo2();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					int j=0;
					for(int i=0;i<aux.getAlojamiento().size();i++) {
						if(aux.getAlojamiento().get(i).getNombre().equals(aux2.getNombre())) {
							try {
								aux.getAlojamiento().set(i, json.cargarAlo());
								aux3 = json.cargarAlo();
								j=i;
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
					if((!aux2.getNombre().equals(aux3.getNombre()))||(!aux2.getDireccion().equals(aux3.getDireccion()))||(!aux2.getTelefono().equals(aux3.getTelefono()))||(aux2.getTipoDeAlojamiento() != aux3.getTipoDeAlojamiento())) {
						aux.getAlojamiento().get(j).setVerificado(false);
					}
					
					eliminarTemporal();
					eliminarTemporal2();
					
					for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
						if(duenos.getColeccionDuenos().get(i).getUser().equals(d.getUser()))
							duenos.getColeccionDuenos().set(i, aux);
					}
					try {
						json.guardarDuenos(duenos);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ventanaDueno vDueno = new ventanaDueno(d);
					vDueno.setVisible(true);

					dispose();
				}
			}
		});
		bontonGuardar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		bontonGuardar.setBounds(91, 433, 164, 41);
		contentPane.add(bontonGuardar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eliminarTemporal2();
				if(alojamiento.existeTemporal()) {
					eliminarTemporal();
				}
				ventanaDueno v = new ventanaDueno(d);
				v.setVisible(true);
				dispose();
				
			}
		});
		botonCancelar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		botonCancelar.setBounds(423, 433, 164, 41);
		contentPane.add(botonCancelar);
		tabla.addColumn("Tipo de habitacion");
		tabla.addColumn("Cantidad");
		tabla.addColumn("Precio");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(200, 234, 338, 128);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(tabla);
		
		JButton botonAgregar = new JButton("Agregar Habitacion");
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty()||textDir.getText().isEmpty()||textNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(validarNumeros(textNum.getText().trim()) == false) {
						JOptionPane.showMessageDialog(null, "Solo puede ingresar numeros en el campo: Numero de telefono","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						copia.setNombre(textNombre.getText());
						copia.setTelefono(textNum.getText());
						copia.setDireccion(textDir.getText());
						copia.setTipoDeAlojamiento(comboTipo.getSelectedIndex());
						try {
							eliminarTemporal();
							json.alojamientoTemp(copia);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						ventanaHab habi = new ventanaHab(aux,2);
						habi.setVisible(true);
						dispose();
					}
				}
			}
		});
		botonAgregar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		botonAgregar.setBounds(548, 246, 156, 33);
		contentPane.add(botonAgregar);
	
		JButton botonBorrar = new JButton("Borrar Habitacion");
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(select !=-1) {
					copia.getHabitaciones().getColeccionHabitacion().remove(select);
					copia.setcHabitaciones(copia.getcHabitaciones()-1);
					botonBorrar.setEnabled(false);
					eliminarTemporal();
					try {
						json.alojamientoTemp(copia);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					borrarTabla();
					cargarDatos();
					copia.setNombre(textNombre.getText());
					copia.setTelefono(textNum.getText());
					copia.setDireccion(textDir.getText());
					copia.setTipoDeAlojamiento(comboTipo.getSelectedIndex());
					try {
						eliminarTemporal();
						json.alojamientoTemp(copia);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna habitacion!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		botonBorrar.setEnabled(false);
		botonBorrar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		botonBorrar.setBounds(548, 301, 143, 33);
		contentPane.add(botonBorrar);
		
		JLabel lblSeleccionada = new JLabel("Seleccionada:");
		lblSeleccionada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionada.setBounds(234, 372, 147, 13);
		contentPane.add(lblSeleccionada);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getValueAt(0,0) != null){
					int seleccionado = table.rowAtPoint(e.getPoint());
					labelSeleccionado.setText((String) table.getValueAt(seleccionado, 0));
					select = seleccionado;
					botonBorrar.setEnabled(true);
				}
			}
		});
		
		cargarDatos();
		
		this.setLocationRelativeTo(null);
		
	}
	public void cargarDatos() {
		ColeccionHabitaciones hab = new ColeccionHabitaciones();
		if(copia.existeTemporal()==false) {
			hab = copia.getHabitaciones();
			String nombre = copia.getNombre();
			String direc = copia.getDireccion();
			String tel = copia.getTelefono();
			int tipo = copia.getTipoDeAlojamiento();
			textNombre.setText(nombre);
			textNum.setText(tel);
			textDir.setText(direc);
			comboTipo.setSelectedIndex(tipo);
			tabla.setRowCount(0);
			for(int i=0;i<hab.getColeccionHabitacion().size();i++) {
				Object[] fila={
					hab.getColeccionHabitacion().get(i).getTipo(),
					hab.getColeccionHabitacion().get(i).getcPersonas(),
					hab.getColeccionHabitacion().get(i).getPrecio()
				};
				tabla.addRow(fila);
			}
		}else {
			try {
				Alojamiento auxiliar = json.cargarAlo();
				hab = auxiliar.getHabitaciones();
				String nombre = auxiliar.getNombre();
				String direc = auxiliar.getDireccion();
				String tel = auxiliar.getTelefono();
				int tipo = auxiliar.getTipoDeAlojamiento();
				textNombre.setText(nombre);
				textNum.setText(tel);
				textDir.setText(direc);
				comboTipo.setSelectedIndex(tipo);
				tabla.setRowCount(0);
				for(int i=0;i<hab.getColeccionHabitacion().size();i++) {
					Object[] fila={
						hab.getColeccionHabitacion().get(i).getTipo(),
						hab.getColeccionHabitacion().get(i).getcPersonas(),
						hab.getColeccionHabitacion().get(i).getPrecio()
					};
					tabla.addRow(fila);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	public void borrarTabla() {
		ColeccionHabitaciones hab = new ColeccionHabitaciones();
		hab = copia.getHabitaciones();
		tabla.setRowCount(0);
		for(int i=0;i<tabla.getRowCount();i++) {
			tabla.removeRow(i);
		}
	}
	public static boolean validarNumeros(String cadena) {
		return cadena.matches("[0-9]*");
	}
	public static void eliminarTemporal() {
		File archivo = new File("Temporal.json");
		archivo.delete();
	}
	
	public static void eliminarTemporal2() {
		File archivo = new File("Temporal2.json");
		archivo.delete();
	}

}
