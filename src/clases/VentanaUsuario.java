package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private ColeccionDeAdmins admins;
	private ColeccionDeDuenos duenos;
	private ColeccionUsuariosNormales usuariosNormales;

	/**
	 * Create the frame.
	 */
	public VentanaUsuario(Usuario d) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaUsuario.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JsonEngine json = new JsonEngine();
		admins = new ColeccionDeAdmins();
		duenos = new ColeccionDeDuenos();
		usuariosNormales = new ColeccionUsuariosNormales();
		
		JLabel lblNombreUsuario = new JLabel("Bienvenido " + d.getUser());
		lblNombreUsuario.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 25));
		lblNombreUsuario.setBounds(25, 20, 311, 45);
		contentPane.add(lblNombreUsuario);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar sesion?");
				if(i == JOptionPane.YES_OPTION) {
					VentanaInicio v = new VentanaInicio();
					v.setVisible(true);
					dispose();
				}
				
			}
		});
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCerrarSesion.setBounds(25, 368, 147, 31);
		contentPane.add(btnCerrarSesion);
		
		JButton btnEditarDatos = new JButton("Gestionar Datos");
		btnEditarDatos.setFocusable(false);
		btnEditarDatos.setBackground(Color.LIGHT_GRAY);
		btnEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					VentanaDatosUsuarios v = new VentanaDatosUsuarios(d);
					v.setVisible(true);
					dispose();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEditarDatos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarDatos.setBounds(253, 225, 255, 54);
		contentPane.add(btnEditarDatos);
		
		JButton btnBusqueda = new JButton("Busqueda de Alojamientos");
		btnBusqueda.setFocusable(false);
		btnBusqueda.setBackground(Color.LIGHT_GRAY);
		btnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaHospedajes busq = new ventanaHospedajes(d);
				busq.setVisible(true);
				dispose();
			}
		});
		btnBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBusqueda.setBounds(253, 151, 255, 54);
		contentPane.add(btnBusqueda);
		
		this.setLocationRelativeTo(null);
	}
	
}
