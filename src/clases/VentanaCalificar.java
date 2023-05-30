package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Toolkit;

public class VentanaCalificar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public VentanaCalificar(Usuario d, Alojamiento alojamiento) {
		setTitle("RESERVALO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCalificar.class.getResource("/Imagenes/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCalificar = new JLabel(d.getUser()+", califique el establecimiento "+alojamiento.getNombre());
		lblCalificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalificar.setFont(new Font("Arial", Font.BOLD, 20));
		lblCalificar.setBounds(36, 10, 575, 50);
		contentPane.add(lblCalificar);
		
		JLabel lblInfo = new JLabel("Seleccione la valoracion que le da al establecimiento");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblInfo.setBounds(75, 80, 485, 60);
		contentPane.add(lblInfo);
		
		JRadioButton rdbtn1 = new JRadioButton("1");

		rdbtn1.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtn1.setBounds(118, 162, 49, 21);
		contentPane.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("2");
		rdbtn2.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtn2.setBounds(218, 162, 37, 21);
		contentPane.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("3");
		rdbtn3.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtn3.setBounds(303, 162, 37, 21);
		contentPane.add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton("4");
		rdbtn4.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtn4.setBounds(386, 162, 44, 21);
		contentPane.add(rdbtn4);
		
		JRadioButton rdbtn5 = new JRadioButton("5");
		rdbtn5.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtn5.setBounds(476, 162, 44, 21);
		contentPane.add(rdbtn5);
		
		rdbtn1.addMouseListener(new MouseAdapter() { // metodos que hacen que cuando se selecciones un boton los otros se deseleccionan
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
				rdbtn5.setSelected(false);
			}
		});
		
		rdbtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
				rdbtn5.setSelected(false);
			}
		});
		
		rdbtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn4.setSelected(false);
				rdbtn5.setSelected(false);
			}
		});
		
		rdbtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn5.setSelected(false);
			}
		});
		
		rdbtn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(94, 278, 426, 80);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblComen = new JLabel("Agregue un comentario");
		lblComen.setFont(new Font("Arial", Font.PLAIN, 20));
		lblComen.setBounds(205, 222, 638, 60);
		contentPane.add(lblComen);
		
		JButton btnCalificar = new JButton("Calificar");
		btnCalificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!rdbtn1.isSelected()&&!rdbtn2.isSelected()&&!rdbtn3.isSelected()&&!rdbtn4.isSelected()&&!rdbtn5.isSelected()) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una calificacion!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int numero = 0;
					if(rdbtn1.isSelected()) {
						numero = 1;
					}
					else {
						if(rdbtn2.isSelected()) {
							numero = 2;
						}
						else {
							if(rdbtn3.isSelected()) {
								numero = 3;
							}
							else {
								if(rdbtn4.isSelected()) {
									numero = 4;
								}
								else {
									if(rdbtn5.isSelected()) {
										numero = 5;
									}
								}
							}
						}
					}
					
					String comentario = textField.getText();
					
					try {
						Calificacion.agregarCalificacion(alojamiento,d,comentario,numero);
					} catch (IOException e1) {
						
					}
					
					JOptionPane.showMessageDialog(null, "Se ha enviado la calificacion correctamente!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					
					ventanaHospedajes v = new ventanaHospedajes(d);
					v.setVisible(true);
					dispose();
					
				}
				
			}
		});
		btnCalificar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCalificar.setBounds(357, 413, 163, 32);
		contentPane.add(btnCalificar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaHospedajes v = new ventanaHospedajes(d);
				v.setVisible(true);
				dispose();
				
			}
		});
		btnRegresar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRegresar.setBounds(94, 413, 163, 32);
		contentPane.add(btnRegresar);
		
		this.setLocationRelativeTo(null);
	}
}
