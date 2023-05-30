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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaDatosUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldClave;
	private JTextField textField;
	private JTextField textNuevoNombreUsuario;
	private ColeccionDeAdmins admins;
	private ColeccionDeDuenos duenos;
	private ColeccionUsuariosNormales usuariosNormales;


	public VentanaDatosUsuarios(Usuario d) throws FileNotFoundException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDatosUsuarios.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModificar = new JLabel("Que desea Modificar?");
		lblModificar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblModificar.setBounds(158, 20, 253, 31);
		contentPane.add(lblModificar);
	
		JsonEngine json = new JsonEngine();
		admins = new ColeccionDeAdmins();
		duenos = new ColeccionDeDuenos();
		usuariosNormales = new ColeccionUsuariosNormales();
		
		usuariosNormales = json.cargarUsuariosNormales();
		
		JLabel lblConfirmarClave = new JLabel("Confirmar clave:");
		lblConfirmarClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarClave.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblConfirmarClave.setBounds(10, 175, 231, 21);
		contentPane.add(lblConfirmarClave);
		
		JLabel lblClave = new JLabel("Nueva Clave: ");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClave.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblClave.setBounds(0, 144, 241, 21);
		contentPane.add(lblClave);
		
		textFieldClave = new JTextField();
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(251, 148, 208, 19);
		contentPane.add(textFieldClave);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(251, 179, 208, 19);
		contentPane.add(textField);
		
		JButton botonRegresar = new JButton("Regresar");
		botonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaUsuario v = new VentanaUsuario(d);
				v.setVisible(true);
				dispose();
				
			}
		});
		botonRegresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonRegresar.setFocusable(false);
		botonRegresar.setBackground(Color.LIGHT_GRAY);
		botonRegresar.setBounds(126, 254, 141, 34);
		contentPane.add(botonRegresar);
		
		
		JLabel lblNuevoNombreUsuario = new JLabel("Nuevo Nombre de usuario:");
		lblNuevoNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNuevoNombreUsuario.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNuevoNombreUsuario.setBounds(-72, 160, 313, 21);
		contentPane.add(lblNuevoNombreUsuario);
		lblNuevoNombreUsuario.setVisible(false);
		
		
		textNuevoNombreUsuario = new JTextField();
		textNuevoNombreUsuario.setColumns(10);
		textNuevoNombreUsuario.setBounds(251, 164, 208, 19);
		contentPane.add(textNuevoNombreUsuario);
		textNuevoNombreUsuario.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		
				if(comboBox.getSelectedItem().toString().equals("Clave")) {
					lblNuevoNombreUsuario.setVisible(false);
					textNuevoNombreUsuario.setVisible(false);
					textField.setVisible(true);
					textFieldClave.setVisible(true);
					lblClave.setVisible(true);
					lblConfirmarClave.setVisible(true);
				}
				else {
					lblNuevoNombreUsuario.setVisible(true);
					textNuevoNombreUsuario.setVisible(true);
					textField.setVisible(false);
					textFieldClave.setVisible(false);
					lblClave.setVisible(false);
					lblConfirmarClave.setVisible(false);
				}
			}
		});
	    comboBox.setFont(new Font("Arial", Font.BOLD, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Clave", "Nombre de Usuario"}));
		comboBox.setBounds(158, 71, 215, 31);
		contentPane.add(comboBox);
	
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem().toString().equals("Clave")) {
					
					if(!textField.getText().isEmpty() && !textFieldClave.getText().isEmpty()) {
						
						if(Clave.verificarClave(textField.getText())&& !Clave.desencriptarClave(d.getClave()).equals(textField.getText())) { //verifica que la clave no sea la misma que la anterior
							
							if(textField.getText().equals(textFieldClave.getText())) {
								
								int dialogButton = JOptionPane.showConfirmDialog (null, "Seguro que desea cambiar su clave?");
;
								
								if(dialogButton == JOptionPane.YES_OPTION) {
					               usuariosNormales.getUsuario(d.getUser()).setClave(Clave.encriptarClave(textField.getText()));
					               d.setClave(Clave.encriptarClave(textField.getText()));
					               
					               try {
										json.guardarUsuariosNormales(usuariosNormales);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					               
					               JOptionPane.showMessageDialog(null, "Se ha modificado su clave correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					             
			                   	}
								
								if(dialogButton == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "No se ha modificado su clave","Mensaje",JOptionPane.INFORMATION_MESSAGE);
								}
							}
							
							else {
								JOptionPane.showMessageDialog(null, "Las claves no coinciden","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
						
						else {
							JOptionPane.showMessageDialog(null, "La clave debe ser distinta a la anterior","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Debe llenar todos los campos","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
				//aqui se modifica el nombre de usuario 
				if (comboBox.getSelectedItem().toString().equals("Nombre de Usuario")) {
					
					if(!textNuevoNombreUsuario.getText().isEmpty()) {
						
						if(!usuariosNormales.existeUsuario(textNuevoNombreUsuario.getText())) {
							//aqui se modifica el nombre de usuario
							usuariosNormales.getUsuario(d.getUser()).setUser(textNuevoNombreUsuario.getText());
							d.setUser(textNuevoNombreUsuario.getText().trim()); 
							try {
								json.guardarUsuariosNormales(usuariosNormales);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "Se ha cambiado el nombre de usuario correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						}
						
						else {
							JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Debe llenar todos los campos","Mensaje",JOptionPane.INFORMATION_MESSAGE);	
					}
					
				}
				
			}
		});
		botonAceptar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonAceptar.setFocusable(false);
		botonAceptar.setBackground(Color.LIGHT_GRAY);
		botonAceptar.setBounds(277, 254, 134, 34);
		contentPane.add(botonAceptar);
		
		this.setLocationRelativeTo(null);
	
	}
}
