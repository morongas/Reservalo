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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaModificarCalendario extends JFrame {

	private JPanel contentPane;
	private int indexAlo = 0;
	private int indexDueno = 0;
	private static ColeccionDeDuenos duenos = new ColeccionDeDuenos();
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public VentanaModificarCalendario(Dueno d, Alojamiento a) {
		setTitle("RESERVALO");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModificarCalendario.class.getResource("/Imagenes/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		indexAlo = buscarIndexAlojamiento(a.getNombre(),d);
		
		JsonEngine gson = new JsonEngine();
		try {
			duenos = gson.cargarDuenos();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		indexDueno = buscarIndexDueno(d.getUser());
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDia.setBounds(31, 134, 47, 31);
		contentPane.add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMes.setBounds(209, 134, 41, 31);
		contentPane.add(lblMes);
		
		JLabel lblAno = new JLabel("A\u00F1o");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAno.setBounds(383, 134, 41, 31);
		contentPane.add(lblAno);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox.setBounds(77, 134, 87, 31);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023"}));
		comboBox_1.setBounds(434, 134, 87, 31);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_2.setBounds(260, 134, 87, 31);
		contentPane.add(comboBox_2);
		
		JLabel lblDisponibilidad = new JLabel("Ocupado / Disponible");
		lblDisponibilidad.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDisponibilidad.setBounds(104, 210, 298, 31);
		contentPane.add(lblDisponibilidad);
		
		JButton btnDisponible = new JButton("DISPONIBLE");
		btnDisponible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblDisponibilidad.getText().equals("Disponible")) {
					JOptionPane.showMessageDialog(null, "Esta fecha ya se encuentra disponible");
				} else {
					a.eliminarFecha(comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString());
					d.getAlojamiento().set(indexAlo, a);
					duenos.getColeccionDuenos().set(indexDueno, d);
					try {
						gson.guardarDuenos(duenos);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblDisponibilidad.setText("Disponible");;
				}

			}
		});
		btnDisponible.setBackground(Color.GREEN);
		btnDisponible.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisponible.setBounds(433, 337, 138, 31);
		contentPane.add(btnDisponible);
		
		JLabel lblNombre = new JLabel("Nombre del establecimiento: " + a.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNombre.setBounds(31, 81, 490, 31);
		contentPane.add(lblNombre);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaDueno v = new ventanaDueno(d);
				v.setVisible(true);
				dispose();
				
			}
		});
		btnRegresar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegresar.setBounds(10, 337, 104, 31);
		contentPane.add(btnRegresar);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEstado.setBounds(31, 210, 63, 31);
		contentPane.add(lblEstado);
		
		JButton btnOCUPADO = new JButton("OCUPADO");
		btnOCUPADO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lblDisponibilidad.getText().equals("Ocupado")) {
					JOptionPane.showMessageDialog(null, "Esta fecha ya se encuentra ocupada");
				} else {
					
					a.getFechas().add(new Fecha(comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString()));
					d.getAlojamiento().set(indexAlo, a);
					duenos.getColeccionDuenos().set(indexDueno, d);
					try {
						gson.guardarDuenos(duenos);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblDisponibilidad.setText("Ocupado");
					//guardo una nueva fecha ocupada
				}
				
			}
		});
		btnOCUPADO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOCUPADO.setBackground(Color.RED);
		btnOCUPADO.setBounds(286, 337, 138, 31);
		contentPane.add(btnOCUPADO);
		
		JLabel lblMarcar = new JLabel("Marcar como:");
		lblMarcar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMarcar.setBounds(376, 296, 298, 31);
		contentPane.add(lblMarcar);
		
		JLabel lblModificar = new JLabel("Modificar Calendario");
		lblModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblModificar.setBounds(184, 10, 257, 43);
		contentPane.add(lblModificar);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarDisponibilidad(comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),a)) {
					lblDisponibilidad.setText("Ocupado");
				} else {
					lblDisponibilidad.setText("Disponible");
				}
			}
		});
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarDisponibilidad(comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),a)) {
					lblDisponibilidad.setText("Ocupado");
				} else {
					lblDisponibilidad.setText("Disponible");
				}
			}
		});
		
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarDisponibilidad(comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),a)) {
					lblDisponibilidad.setText("Ocupado");
				} else {
					lblDisponibilidad.setText("Disponible");
				}
			}
		});
		
		this.setLocationRelativeTo(null);
		
	}
	
	public static boolean verificarDisponibilidad(String dia, String mes, String anio, Alojamiento a) {
		
		return a.verificarFechaOcupada(new Fecha(dia, mes, anio)); //si es true, esta ocupada
		
	}
	
	public static int buscarIndexAlojamiento(String nombre, Dueno d) {
		for(int i=0; i<d.getAlojamiento().size(); i++) {
			if(d.getAlojamiento().get(i).getNombre().equals(nombre)) {
				return i;
			}
		}
		return 0;
	}
	
	public static int buscarIndexDueno(String user) {
		for(int i=0; i<duenos.getColeccionDuenos().size(); i++) {
			if(user.equals(duenos.getColeccionDuenos().get(i).getUser())) {
				return i;
			}
		}
		return 0;
	}
}
