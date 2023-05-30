package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ventanaHab extends JFrame {

	private JPanel contentPane;
	private JTextField textCant;
	private JTextField textPrecio;
	private Alojamiento aux;
	
	public ventanaHab(Dueno d, int num) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaHab.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		JsonEngine json = new JsonEngine();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de Habitacion:");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel.setBounds(52, 53, 135, 31);
		contentPane.add(lblNewLabel);
		
		JComboBox comboTipo = new JComboBox();
		comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Doble ", "Triple ", "Quad", "Queen", "King", "Twin", "Double-Double", "Suite"}));
		comboTipo.setBounds(209, 58, 113, 21);
		contentPane.add(comboTipo);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de personas:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_1.setBounds(52, 104, 147, 21);
		contentPane.add(lblNewLabel_1);
		
		textCant = new JTextField();
		textCant.setBounds(209, 106, 96, 19);
		contentPane.add(textCant);
		textCant.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Precio de la Habitacion (USD):");
		lblNewLabel_1_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 145, 208, 21);
		contentPane.add(lblNewLabel_1_1);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(209, 147, 96, 19);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		try {
			aux = json.cargarAlo();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton botonRegresar = new JButton("Regresar");
		botonRegresar.setBackground(Color.LIGHT_GRAY);
		botonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(num==1) {
					ventanaAgregarEst v = new ventanaAgregarEst(d);
					v.setVisible(true);
					try {
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				else {
					try {
						ventanaEditarEst v1 = new ventanaEditarEst(aux,d);
						v1.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				dispose();
			}
		});
		botonRegresar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		botonRegresar.setBounds(218, 217, 131, 47);
		contentPane.add(botonRegresar);
	
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setBackground(Color.LIGHT_GRAY);
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textCant.getText().isEmpty() || textPrecio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe llenar los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if((!validarNumeros(textCant.getText())) || (!validarNumeros(textPrecio.getText()))) {
						JOptionPane.showMessageDialog(null, "Solo son permitidos numeros en los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						String tipo = comboTipo.getSelectedItem().toString();
						int cPersonas = Integer.parseInt(textCant.getText());
						int precio = Integer.parseInt(textPrecio.getText());
						aux.getHabitaciones().agregarHabitacion(tipo, cPersonas, precio);
						aux.setcHabitaciones(aux.getcHabitaciones()+1);
						try {
							json.alojamientoTemp(aux);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(num==1) {
							ventanaAgregarEst ventana = new ventanaAgregarEst(d);
							ventana.agregoHab = true;
							ventana.setVisible(true);
						}else {
							ventanaEditarEst edit;
							try {
								edit = new ventanaEditarEst(aux,d);
								edit.setVisible(true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						dispose();
					}
				}
			}
		});
		botonAgregar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		botonAgregar.setBounds(56, 217, 131, 47);
		contentPane.add(botonAgregar);
		
		this.setLocationRelativeTo(null);
	}
	
	public static boolean validarNumeros(String cadena) {
		return cadena.matches("[0-9]*");
	}

}
