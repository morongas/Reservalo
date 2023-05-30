package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ventanaAgregarEst extends JFrame {

	private JPanel contentPane;
	
	public static int getCont() {
		return cont;
	}
	
	public static void setCont(int cont) {
		ventanaAgregarEst.cont = cont;
	}
	
	private static int cont =0 ;
	public static boolean agregoHab = false;
	private JTextField textNombreEst;
	private JTextField textNum;
	private ColeccionDeDuenos duenos;
	private Dueno aux;
	private Alojamiento alojamiento;
	private JComboBox comboTipo;
	
	public ventanaAgregarEst(Dueno d) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaAgregarEst.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		aux = d;
		JsonEngine json = new JsonEngine();
		duenos = new ColeccionDeDuenos();
		JLabel textCont = new JLabel("");
		JTextPane textDir = new JTextPane();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonRegresar = new JButton("Regresar");
		botonRegresar.setBackground(Color.LIGHT_GRAY);
		botonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ventanaDueno v1 = new ventanaDueno(d);
				v1.setVisible(true);
				dispose();
				setCont(0);
				agregoHab = false; //tiene que agregar ajuro una habitacion
				
			}
		});
		botonRegresar.setBounds(222, 402, 146, 63);
		botonRegresar.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		contentPane.add(botonRegresar);
		
		JLabel lblNewLabel = new JLabel("Nombre del Establecimiento: ");
		lblNewLabel.setBounds(173, 128, 209, 13);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 19));
		contentPane.add(lblNewLabel);
		
		textNombreEst = new JTextField();
		textNombreEst.setBounds(372, 127, 166, 19);
		contentPane.add(textNombreEst);
		textNombreEst.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de establecimiento:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(173, 156, 190, 19);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Direccion del establecimiento:");
		lblNewLabel_2.setBounds(171, 185, 190, 24);
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Numero de telefono:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(173, 329, 190, 19);
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		contentPane.add(lblNewLabel_3);
		
		textNum = new JTextField();
		textNum.setBounds(372, 330, 146, 19);
		contentPane.add(textNum);
		textNum.setColumns(10);

		comboTipo = new JComboBox();
		comboTipo.setBounds(371, 154, 147, 21);
		comboTipo.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 18));
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Hotel", "Posada", "Apartamento"}));
		contentPane.add(comboTipo);
		
		JComboBox comboPromo = new JComboBox();
		comboPromo.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		comboPromo.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
		comboPromo.setBounds(372, 291, 53, 21);
		contentPane.add(comboPromo);
		
		JLabel lblNewLabel_4 = new JLabel("Habitaciones: ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(222, 358, 146, 19);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		contentPane.add(lblNewLabel_4);
		
		JButton botonAgregarHab = new JButton("Agregar Habitacion");
		botonAgregarHab.setBackground(Color.LIGHT_GRAY);
		botonAgregarHab.setBounds(372, 358, 146, 21);
		botonAgregarHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombreEst.getText().isEmpty()||textDir.getText().isEmpty()||textNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(validarNumeros(textNum.getText().trim()) == false) {
						JOptionPane.showMessageDialog(null, "El numero de telefono es incorrecto","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						
						if(cont==0) {
							alojamiento = new Alojamiento(textNombreEst.getText(),comboTipo.getSelectedIndex(),textDir.getText(),textNum.getText(),0,new ColeccionHabitaciones(), comboPromo.getSelectedItem().toString());
						}
						try {
							json.alojamientoTemp(alojamiento);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						setCont(1);
						ventanaHab habi = new ventanaHab(aux,1);
						habi.setVisible(true);
						dispose();
					}
				}
			}
		});
		
		if((Alojamiento.existeTemporal()==true) && (cont ==1)) {
			 try {
				 alojamiento = json.cargarAlo();
			} catch (Exception e) {
				// TODO: handle exception
			}
				textNombreEst.setText(alojamiento.getNombre());
				comboTipo.setSelectedIndex(alojamiento.getTipoDeAlojamiento());
				textDir.setText(alojamiento.getDireccion());
				textNum.setText(alojamiento.getTelefono());
				textCont.setText(String.valueOf(alojamiento.getcHabitaciones()));
				comboPromo.setSelectedItem(alojamiento.getPromocion());
		}
			
		try {
			duenos = json.cargarDuenos();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		contentPane.add(botonAgregarHab);
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setBackground(Color.LIGHT_GRAY);
		botonAgregar.setBounds(457, 402, 146, 63);
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				if(textNombreEst.getText().isEmpty()||textDir.getText().isEmpty()||textNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(validarNumeros(textNum.getText().trim()) == false) {
						JOptionPane.showMessageDialog(null, "El numero de telefono es incorrecto","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						if(agregoHab == false)
							JOptionPane.showMessageDialog(null, "Debe agregar habitaciones","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						else {
						
									try {
										Alojamiento aux2 = json.cargarAlo();
										aux2.setNombre(textNombreEst.getText());
										aux2.setDireccion(textDir.getText());
										aux2.setTelefono(textNum.getText());
										aux2.setTipoDeAlojamiento(comboTipo.getSelectedIndex());
										aux2.setPromocion(comboPromo.getSelectedItem().toString());
										d.setAlojamiento(aux2);
									} catch (FileNotFoundException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
										if(duenos.getColeccionDuenos().get(i).getUser().equals(d.getUser()))
											duenos.getColeccionDuenos().set(i, d);
									}
									try {
										json.guardarDuenos(duenos);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									agregoHab = false;
									setCont(0);
									eliminarTemporal();
						
									JOptionPane.showMessageDialog(null, "Se ha agregado el establecimiento " + textNombreEst.getText() + " correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
									ventanaDueno ventana = new ventanaDueno(d);
									ventana.setVisible(true);
									dispose();
							
					   }
					}
				}
			}
		});
		botonAgregar.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		contentPane.add(botonAgregar);
		

		textCont.setHorizontalAlignment(SwingConstants.CENTER);
		textCont.setBounds(638, 362, 45, 15);
		contentPane.add(textCont);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 190, 147, 75);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(textDir);
		
		JLabel lblCantidadAgregada = new JLabel("Cantidad agregada:  ");
		lblCantidadAgregada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadAgregada.setBounds(525, 361, 119, 15);
		contentPane.add(lblCantidadAgregada);
		
		JLabel lblNewLabel_5 = new JLabel("Registro de alojamiento");
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.ITALIC, 24));
		lblNewLabel_5.setBounds(46, 32, 276, 54);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Este alojamiento posee promocion:");
		lblNewLabel_6.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_6.setBounds(136, 294, 246, 13);
		contentPane.add(lblNewLabel_6);
		
		
		this.setLocationRelativeTo(null);
	}
	
	public static void eliminarTemporal() {
		File archivo = new File("Temporal.json");
		archivo.delete();
	}
	
	public static boolean validarNumeros(String cadena) {
		
		if (cadena.matches("[0-9]*") == false) {
			return false;
		}
		if (cadena.length() != 11) {
			return false;
		}
		return true;
	}
	
	public static boolean existeNombre(ColeccionDeDuenos duenos,String nombre) {
		boolean ver = false;
		
		for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
			for(int j=0; j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
				if (duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre().toUpperCase().equals(nombre.toUpperCase()) ) {
				ver = true;
				}
			}
		}
		return ver;	
	}
}
