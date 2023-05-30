package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/Imagenes/LogoInicio.png")));
		setResizable(false);
		setTitle("RESERVALO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonIniciarSesion = new JButton("Iniciar Sesion");
		botonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicioSesion v2;
				try {
					v2 = new VentanaInicioSesion();
					v2.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		botonIniciarSesion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonIniciarSesion.setFocusable(false);
		botonIniciarSesion.setBackground(Color.LIGHT_GRAY);
		botonIniciarSesion.setBounds(147, 179, 112, 38);
		contentPane.add(botonIniciarSesion);
		
		JButton botonRegistrarse = new JButton("Registrarse");
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro v2 = new VentanaRegistro();
				v2.setVisible(true);
				dispose();
			}
		});
		botonRegistrarse.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonRegistrarse.setFocusable(false);
		botonRegistrarse.setBackground(Color.LIGHT_GRAY);
		botonRegistrarse.setBounds(288, 179, 112, 38);
		contentPane.add(botonRegistrarse);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int op = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir?");
				if(op == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		});
		botonSalir.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		botonSalir.setFocusable(false);
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setBounds(222, 243, 112, 38);
		contentPane.add(botonSalir);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogo.setIcon(new ImageIcon(VentanaInicio.class.getResource("/Imagenes/LogoInicio.png")));
		labelLogo.setBounds(157, 26, 220, 142);
		contentPane.add(labelLogo);
		
		this.setLocationRelativeTo(null);
	}
}
