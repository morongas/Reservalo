package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class ventanaDueno extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel dtm = new DefaultTableModel();
	int op = -1;
	
	public ventanaDueno(Dueno d) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaDueno.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel labelDuenoActual = new JLabel("New label");
		contentPane.setLayout(null);
		JLabel labelAlojamiento = new JLabel("Alojamiento seleccionado: ");
		labelAlojamiento.setHorizontalAlignment(SwingConstants.CENTER);
		labelAlojamiento.setBounds(408, 436, 176, 13);
		contentPane.add(labelAlojamiento);
		
		File archivo = new File("Temporal.json");
		archivo.delete();
		
		Alojamiento b = new Alojamiento();
		
		if(b.existeTemporal2())
			ventanaEditarEst.eliminarTemporal2();
		
		JsonEngine json = new JsonEngine();
		
		JLabel labelSeleccionado = new JLabel("");
		labelSeleccionado.setBounds(573, 436, 147, 13);
		contentPane.add(labelSeleccionado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 130, 723, 283);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tipo", "Nombre", "Cantidad de habitaciones", "Telefono", "Verificado"
			}
		));
		scrollPane.setViewportView(table);
		
		String titulos[] = {"Tipo","Nombre","Cantidad de habitaciones","Telefono","Verificado"};
		
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
		
		ArrayList<Alojamiento> arreglo = d.getAlojamiento();
		
		if(!arreglo.isEmpty()) {
			for(Alojamiento a : arreglo) {
				dtm.addRow(new Object[] {tipo(a.getTipoDeAlojamiento()),a.getNombre(),a.getcHabitaciones(),a.getTelefono(),a.isVerificado()});
			}
		}
		else {
			for(int i=0; i<100; i++) {
				dtm.addRow(new Object[] {null,null,null,null,null});
			}
		}
		
		JLabel lblNewLabel = new JLabel("Te damos la bienvenida, ");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(36, 30, 229, 13);
		contentPane.add(lblNewLabel);
		String name = nombre(d);
		labelDuenoActual.setText(name);
		
		JButton botonAgregar = new JButton("Registrar Alojamiento");
		botonAgregar.setBackground(Color.LIGHT_GRAY);
		botonAgregar.setFocusable(false);
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAgregarEst agregarEst = new ventanaAgregarEst(d);
				setVisible(false);
				agregarEst.setVisible(true);
			}
		});
		botonAgregar.setBounds(171, 517, 168, 39);
		contentPane.add(botonAgregar);
		
		JButton botonEditar = new JButton("Editar Alojamiento");
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					if(op==-1) {
						JOptionPane.showMessageDialog(null, "No ha seleccionado ningun establecimiento!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						//se pasa a la ventana editar alojamiento con la funcion buscarSeleccionado
						ventanaEditarEst editar;
						try {
							json.alojamientoTemp2(buscarSeleccionado(labelSeleccionado.getText(),d));
							editar = new ventanaEditarEst(buscarSeleccionado(labelSeleccionado.getText(),d),d);
							editar.setVisible(true);
							dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				}
			}
		});
		botonEditar.setBackground(Color.LIGHT_GRAY);
		botonEditar.setFocusable(false);
		botonEditar.setBounds(405, 517, 142, 39);
		contentPane.add(botonEditar);
		
		JButton botonVerficar = new JButton("Verificacion de Alojamiento");
		botonVerficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// aqui verificamos que haya enviadop la solicitud para verificar el alojamiento 
				if(op==-1) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun establecimiento!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(buscarSeleccionado(labelSeleccionado.getText(),d).isVerificado()) {
						JOptionPane.showMessageDialog(null, "El establecimiento seleccionado ya ha sido verificado","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						if(buscarSeleccionado(labelSeleccionado.getText(),d).getImagenes().size()<1) {
							JOptionPane.showMessageDialog(null, "El establecimiento no se puede verificar sin haber agregado imagenes","Error",JOptionPane.ERROR_MESSAGE);
						}
					
						else {
							//se pasa a la ventana editar alojamiento con la funcion buscarSeleccionado
							VentanaSolicitarVerificacion window;
							try {
								json.alojamientoTemp2(buscarSeleccionado(labelSeleccionado.getText(),d));
								window = new VentanaSolicitarVerificacion(buscarSeleccionado(labelSeleccionado.getText(),d),d);
								window.setVisible(true);
								dispose();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
				
				
			}
		});
		botonVerficar.setBackground(Color.LIGHT_GRAY);
		botonVerficar.setFocusable(false);
		botonVerficar.setBounds(557, 517, 203, 39);
		contentPane.add(botonVerficar);
		

		labelDuenoActual.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 24));
		labelDuenoActual.setBounds(248, 27, 420, 19);
		contentPane.add(labelDuenoActual);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar sesion?");
				if(op == JOptionPane.YES_OPTION) {
					VentanaInicio v1 = new VentanaInicio();
					v1.setVisible(true);
					dispose();
				}
			}
		});
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setBounds(14, 517, 147, 39);
		contentPane.add(btnCerrarSesion);
		
		JLabel lblNewLabel_1 = new JLabel("Listado de sus establecimientos:");
		lblNewLabel_1.setBounds(147, 107, 372, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton botonAggImag = new JButton("Agregar Imagenes");
		botonAggImag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (op != -1) {
					VentanaIngresarImagen aggImagen = new VentanaIngresarImagen(d, buscarSeleccionado(labelSeleccionado.getText(),d));		
					aggImagen.setVisible(true);
					dispose();
				} else {
    				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun establecimiento", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		botonAggImag.setFocusable(false);
		botonAggImag.setBackground(Color.LIGHT_GRAY);
		botonAggImag.setBounds(770, 517, 142, 39);
		contentPane.add(botonAggImag);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(361, 507, 5, 58);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 4);
		lblNewLabel_2.setBorder(border);
		contentPane.add(lblNewLabel_2);
		
		JButton btnVerCalificaciones = new JButton("Ver calificaciones");
		btnVerCalificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(op==-1) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun establecimiento","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(buscarSeleccionado(labelSeleccionado.getText(),d).getCalificaciones().size()<1) {
						JOptionPane.showMessageDialog(null, "Ningun turista ha calificado el establecimiento seleccionado!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else { // si tiene calificaciones
						
					 VentanaVerCalificaciones v = new VentanaVerCalificaciones(buscarSeleccionado(labelSeleccionado.getText(),d), d);
					 v.setVisible(true);
					 dispose();
					}
				}
				
			}
		});
		btnVerCalificaciones.setFocusable(false);
		btnVerCalificaciones.setBackground(Color.LIGHT_GRAY);
		btnVerCalificaciones.setBounds(770, 470, 142, 39);
		contentPane.add(btnVerCalificaciones);
		
		JButton btnVerFechasOcupadas = new JButton("Ver Fechas Ocupadas");
		btnVerFechasOcupadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerFechasOcupadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFechasOcupadas FO = new VentanaFechasOcupadas(buscarSeleccionado(labelSeleccionado.getText(),d), d);		
				FO.setVisible(true);
				dispose();	
			}
		});
		btnVerFechasOcupadas.setFocusable(false);
		btnVerFechasOcupadas.setBackground(Color.LIGHT_GRAY);
		btnVerFechasOcupadas.setBounds(591, 470, 169, 39);
		contentPane.add(btnVerFechasOcupadas);
		
		JButton btnConfirmarReservas = new JButton("Confirmar Reservas");
		btnConfirmarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (op != -1) {
					VentanaConfirmarReserva MC = new VentanaConfirmarReserva(d, buscarSeleccionado(labelSeleccionado.getText(),d));		
					MC.setVisible(true);
					dispose();
				} else {
    				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun establecimiento", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConfirmarReservas.setFocusable(false);
		btnConfirmarReservas.setBackground(Color.LIGHT_GRAY);
		btnConfirmarReservas.setBounds(405, 470, 176, 39);
		contentPane.add(btnConfirmarReservas);
		this.setLocationRelativeTo(null);
	}
	
	public static String nombre(Dueno d) {
		String aux = "";
		boolean aux2 = false;
		int i=0;
		while(!(aux2)) {
			if(d.getUser().charAt(i)!='.') {
				aux = aux + d.getUser().charAt(i);
				i=i+1;
			}else {
				aux2=true;
			}
		}
		return aux;
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
	
	public static Alojamiento buscarSeleccionado(String nombre,Dueno d) { //buscamos un alojamiento por nombre
		ArrayList<Alojamiento> arreglo = d.getAlojamiento();
		for(Alojamiento a : arreglo) {
			if(a.getNombre().equals(nombre))
				return a;
		}
		return null;
	}
}
