package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaSolicitarVerificacion extends JFrame {

	private JPanel contentPane;
	private JTextField fieldRif;
	private JTextField fieldDireccion;
	private JTextField fieldCodigoPostal;
	private ColeccionDeDuenos duenos;
	
	public VentanaSolicitarVerificacion(Alojamiento alojamiento, Dueno d) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JsonEngine json = new JsonEngine();
		
		try {
			duenos = json.cargarDuenos();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblSolicitarVerificacion = new JLabel("Solicitar Verificacion");
		lblSolicitarVerificacion.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 25));
		lblSolicitarVerificacion.setBounds(24, 21, 289, 45);
		contentPane.add(lblSolicitarVerificacion);
		
		JLabel lblNewLabel = new JLabel("Ingrese RIF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel.setBounds(185, 70, 115, 45);
		contentPane.add(lblNewLabel);
		
		fieldRif = new JTextField();
		fieldRif.setBounds(314, 77, 152, 32);
		contentPane.add(fieldRif);
		fieldRif.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblEstado.setBounds(185, 124, 115, 45);
		contentPane.add(lblEstado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(314, 129, 152, 36);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"","Amazonas","Anzoátegui","Apure","Aragua","Barinas","Bolívar","Carabobo","Cojedes","Delta Amacuro","Distrito Capital","Falcón","Guárico","Lara","Mérida","Miranda","Monagas","Nueva Esparta","Portuguesa","Sucre","Táchira","Trujillo","Vargas", "Yaracuy", "Zulia"}));
		contentPane.add(comboBox);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblDireccion.setBounds(185, 189, 115, 45);
		contentPane.add(lblDireccion);
		
		fieldDireccion = new JTextField();
		fieldDireccion.setColumns(10);
		fieldDireccion.setBounds(314, 191, 215, 83);
		contentPane.add(fieldDireccion);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal");
		lblCodigoPostal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoPostal.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblCodigoPostal.setBounds(174, 291, 131, 45);
		contentPane.add(lblCodigoPostal);
		
		fieldCodigoPostal = new JTextField();
		fieldCodigoPostal.setColumns(10);
		fieldCodigoPostal.setBounds(314, 298, 152, 32);
		contentPane.add(fieldCodigoPostal);
		
		JButton btnEnviarSolicitudDe = new JButton("Enviar Solicitud de Verificacion");
		btnEnviarSolicitudDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fieldCodigoPostal.getText().isEmpty() || fieldDireccion.getText().isEmpty() || fieldRif.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if((validarNumeros(fieldCodigoPostal.getText().trim()) == false) ||(validarNumeros(fieldRif.getText().trim()) == false))  {
						JOptionPane.showMessageDialog(null, "Solo puede ingresar numeros en los campos: Codigo Postal y Rif","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
							for(int j=0; j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
								if (duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre().toUpperCase().equals(alojamiento.getNombre().toUpperCase())) {
									duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).setSolicitoVerificacion(true);
									duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).setCodigoPostal(Integer.parseInt(fieldCodigoPostal.getText()));
									duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).setRif(Integer.parseInt(fieldRif.getText()));
									duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).setEstado(comboBox.getSelectedItem().toString());
								}
							}
						}
						
						try {
							json.guardarDuenos(duenos);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Su verificacion ha sido enviada!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
						ventanaDueno w = new ventanaDueno(d);
						w.setVisible(true);
						setVisible(false);
					}
				}
				
				
				
			}
		});
		btnEnviarSolicitudDe.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		btnEnviarSolicitudDe.setFocusable(false);
		btnEnviarSolicitudDe.setBackground(Color.LIGHT_GRAY);
		btnEnviarSolicitudDe.setBounds(461, 384, 204, 45);
		contentPane.add(btnEnviarSolicitudDe);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaDueno w = new ventanaDueno(d);
				w.setVisible(true);
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		btnCancelar.setFocusable(false);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.setBounds(24, 384, 157, 45);
		contentPane.add(btnCancelar);
		
		this.setLocationRelativeTo(null);
	}
	public static boolean validarNumeros(String cadena) {
		return cadena.matches("[0-9]*");
	}
}
