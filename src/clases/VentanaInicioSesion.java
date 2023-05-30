package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textClave;
	private ColeccionDeAdmins admins;
	private ColeccionDeDuenos duenos;
	private ColeccionUsuariosNormales usuariosNormales;

	public VentanaInicioSesion() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicioSesion.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JsonEngine json = new JsonEngine();
		admins = new ColeccionDeAdmins();
		duenos = new ColeccionDeDuenos();
		usuariosNormales = new ColeccionUsuariosNormales();
		
		JLabel lblNewLabel_3 = new JLabel("Inicio de sesion");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.ITALIC, 24));
		lblNewLabel_3.setBounds(38, 36, 397, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel_1.setBounds(129, 111, 191, 19);
		contentPane.add(lblNewLabel_1);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(330, 114, 208, 19);
		contentPane.add(textUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("Clave:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblNewLabel_2.setBounds(89, 140, 231, 21);
		contentPane.add(lblNewLabel_2);
		
		textClave = new JTextField();
		textClave.setColumns(10);
		textClave.setBounds(330, 143, 208, 19);
		contentPane.add(textClave);
		
		admins = json.cargarAdmins();
		
		if(duenos.existenUsuarios())
			duenos = json.cargarDuenos();
		
		if(usuariosNormales.existenUsuarios())
			usuariosNormales = json.cargarUsuariosNormales();
		
		admins = json.cargarAdmins();
		
		//Falta el inicio de admins
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((textUsuario.getText().isEmpty())||(textClave.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String user = textUsuario.getText();
					String clave = textClave.getText();
					
					if(usuarioDuenoValido(user)) {
						if(duenos.existeUsuario(user) == false) {
							JOptionPane.showMessageDialog(null, "El usuario no existe!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							Dueno actual = duenos.getDueno(user);
							if(Clave.desencriptarClave(actual.getClave()).equals(clave)) {
								setVisible(false);
								ventanaDueno vDueno = new ventanaDueno(actual);
								vDueno.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "La clave es incorrecta!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					else {
						if (!usuarioAdminValido(user)) {
							if(usuariosNormales.existeUsuario(user) == false) {
								JOptionPane.showMessageDialog(null, "El usuario no existe!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								Usuario actual = usuariosNormales.getUsuario(user);
								if(Clave.desencriptarClave(actual.getClave()).equals(clave)) {
									VentanaUsuario v = new VentanaUsuario(actual);
									v.setVisible(true);
									dispose();
								}
								else {
									JOptionPane.showMessageDialog(null, "La clave es incorrecta!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						else {
							if(!admins.existeAdmin(user)) {
								JOptionPane.showMessageDialog(null, "El usuario no existe!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								Administrador actual = admins.getAdmin(user);
								if(actual.getClave().equals(clave)) {
									VentanaAdmin v = new VentanaAdmin(actual);
									v.setVisible(true);
									dispose();
								}
								else {
									JOptionPane.showMessageDialog(null, "La clave es incorrecta!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		botonAceptar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonAceptar.setFocusable(false);
		botonAceptar.setBackground(Color.LIGHT_GRAY);
		botonAceptar.setBounds(334, 186, 134, 34);
		contentPane.add(botonAceptar);
		
		JButton botonRegresar = new JButton("Regresar");
		botonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio v1 = new VentanaInicio();
				v1.setVisible(true);
				dispose();
			}
		});
		botonRegresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonRegresar.setFocusable(false);
		botonRegresar.setBackground(Color.LIGHT_GRAY);
		botonRegresar.setBounds(179, 186, 141, 34);
		contentPane.add(botonRegresar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaInicioSesion.class.getResource("/Imagenes/LogoInicio.png")));
		lblNewLabel.setBounds(251, 264, 153, 91);
		contentPane.add(lblNewLabel);
		
		this.setLocationRelativeTo(null);
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
	
	public static boolean usuarioAdminValido(String user) {
		
		if(user.length() > 14) {
			String ver = "";
			for(int i=0;i<14;i++) {
				
				ver = ver + user.charAt(i);
				
			}
			if(ver.equals("AdminReservalo")) {
				
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
}
