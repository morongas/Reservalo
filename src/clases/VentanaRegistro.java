package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textClave;
	private ColeccionUsuariosNormales usuariosNormales;
	private ColeccionDeDuenos duenos;
	private JTextField textConfirmarClave;
	
	public VentanaRegistro() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		JsonEngine json = new JsonEngine();
		usuariosNormales = new ColeccionUsuariosNormales();
		duenos = new ColeccionDeDuenos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel.setBounds(106, 108, 177, 34);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFocusable(false);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Turista", "Due\u00F1o de alojamiento"}));
		comboBox.setBounds(293, 117, 169, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel_1.setBounds(92, 179, 191, 19);
		contentPane.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(293, 182, 208, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Clave (min. 6 caracteres):");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel_2.setBounds(52, 208, 231, 21);
		contentPane.add(lblNewLabel_2);
		
		textClave = new JTextField();
		textClave.setBounds(293, 211, 208, 19);
		contentPane.add(textClave);
		textClave.setColumns(10);
		
		textConfirmarClave = new JTextField();

		if(duenos.existenUsuarios()!=false) {
			try {
				duenos = json.cargarDuenos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(usuariosNormales.existenUsuarios()!=false) {
			try {
				usuariosNormales = json.cargarUsuariosNormales();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBackground(Color.LIGHT_GRAY);
		botonAceptar.setFocusable(false);
		botonAceptar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textNombre.getText();
				String clave = textClave.getText();
				if((user.isEmpty())||(clave.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(Clave.verificarClave(clave) == false) {
						JOptionPane.showMessageDialog(null, "La clave debe contener minimo 6 caracteres","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						if(usuarioValido(user) == false) {
							JOptionPane.showMessageDialog(null, "El usuario no puede contener espacios!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							if(clave.equals(textConfirmarClave.getText())== false) {
								JOptionPane.showMessageDialog(null, "Las claves no coinciden!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								if(comboBox.getSelectedItem().toString().equals("Turista")) {
									if(usuarioDuenoValido(user)) {
										JOptionPane.showMessageDialog(null, "Su usuario no puede tener .hsp al final ya que es turista!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
									}
									else {
										if(usuariosNormales.getColeccionUsuarios().isEmpty() == false) {
											if(usuariosNormales.existeUsuario(user)) {
												JOptionPane.showMessageDialog(null, "El nombre de usuario no esta disponible!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
											}
											else {
												Usuario nuevoUsuario = new Usuario(user,Clave.encriptarClave(clave));
												usuariosNormales.agregarUsuario(nuevoUsuario);
												try {
													json.guardarUsuariosNormales(usuariosNormales);
												} catch (IOException e1) {
													e1.printStackTrace();
												}
												JOptionPane.showMessageDialog(null, "Se ha registrado correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
												VentanaInicio inicio = new VentanaInicio();
												inicio.setVisible(true);
												dispose();
											}
										}
										else {
											Usuario nuevoUsuario = new Usuario(user,Clave.encriptarClave(clave));
											usuariosNormales.agregarUsuario(nuevoUsuario);
											try {
												json.guardarUsuariosNormales(usuariosNormales);
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											JOptionPane.showMessageDialog(null, "Se ha registrado correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
											VentanaInicio inicio = new VentanaInicio();
											inicio.setVisible(true);
											dispose();
										}
									}
								}
								else {
									if(usuarioDuenoValido(user) == false) {
										JOptionPane.showMessageDialog(null, "Debe agregar .hsp al final del usuario!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
									}
									else {
										if(duenos.getColeccionDuenos().isEmpty() == false) {
											if(duenos.existeUsuario(user)) {
												JOptionPane.showMessageDialog(null, "El nombre de usuario no esta disponible!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
											}
											else {
												Dueno nuevoDueno = new Dueno(user,Clave.encriptarClave(clave));
												duenos.agregarDueno(nuevoDueno);
												try {
													json.guardarDuenos(duenos);
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												JOptionPane.showMessageDialog(null, "Se ha registrado correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
												VentanaInicio inicio = new VentanaInicio();
												inicio.setVisible(true);
												dispose();
											}
										}
										else {
											Dueno nuevoDueno = new Dueno(user,Clave.encriptarClave(clave));
											duenos.agregarDueno(nuevoDueno);
											try {
												json.guardarDuenos(duenos);
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											JOptionPane.showMessageDialog(null, "Se ha registrado correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
											VentanaInicio inicio = new VentanaInicio();
											inicio.setVisible(true);
											dispose();
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		botonAceptar.setBounds(328, 279, 134, 34);
		contentPane.add(botonAceptar);
		
		JButton botonRegresar = new JButton("Regresar");
		botonRegresar.setBackground(Color.LIGHT_GRAY);
		botonRegresar.setFocusable(false);
		botonRegresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio inicio = new VentanaInicio();
				inicio.setVisible(true);
				dispose();
			}
		});
		botonRegresar.setBounds(173, 279, 141, 34);
		contentPane.add(botonRegresar);
		
		JLabel lblNewLabel_3 = new JLabel("Registro de usuario");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.ITALIC, 24));
		lblNewLabel_3.setBounds(21, 29, 397, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nota: si escoge la opcion \"Due\u00F1o de alojamiento\" debera agregar \r\n\".hsp\" al final de su nombre de usuario");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_4.setBounds(31, 142, 482, 34);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_1 = new JLabel("Confirmar clave:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(52, 239, 231, 21);
		contentPane.add(lblNewLabel_2_1);
		
		textConfirmarClave.setColumns(10);
		textConfirmarClave.setBounds(293, 240, 208, 19);
		contentPane.add(textConfirmarClave);
		
		this.setLocationRelativeTo(null);
	}
	
	public static boolean usuarioValido(String user) {
		for(int i=0;i<user.length();i++) {
			if(user.charAt(i) == ' ')
				return false;
		}
		return true;
	}
	
	public static boolean usuarioDuenoValido(String user) {
		if(user.charAt(0) == '.')
			return false;
		else {
			String ver = "";
			int i=0;
			int j=user.length()-1;
			while(i<4) {
				ver = user.charAt(j) + ver;
				i++;
				j--;
			}
			if(ver.equals(".hsp"))
				return true;
			else
				return false;
		}
	}
}
