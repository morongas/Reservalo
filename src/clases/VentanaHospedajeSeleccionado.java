package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class VentanaHospedajeSeleccionado extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPrecio;
	private ImageIcon imagen;
	private int aux=0;
	public VentanaHospedajeSeleccionado(Alojamiento alojamiento, Usuario u, Dueno d) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Nombre del Hospedaje:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 269, 73);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Precio por Noche:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(21, 199, 164, 24);
		contentPane.add(lblNewLabel_1);
		
		ImageIcon fondo = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logoblur.png"));
		
		JLabel lblImagenes = new JLabel("");
		lblImagenes.setBackground(Color.GRAY);
		lblImagenes.setForeground(Color.GRAY);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		lblImagenes.setBorder(border);
		lblImagenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenes.setBounds(289, 143, 443, 304);
		contentPane.add(lblImagenes);

		
		ImageIcon flechai = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Flechaizq.png"));
		ImageIcon flechad = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Flechader.png"));
		
		JLabel lblIzquierda = new JLabel("");
		lblIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//cuando hacemos click tiene que cambiar la imagen
				int i = alojamiento.getImagenes().size()-1;
				if(aux==0) {
					imagen = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + alojamiento.getImagenes().get(i)));
					lblImagenes.setIcon(imagen);
					aux = alojamiento.getImagenes().size() - 1;
				}else {
					aux--;
					imagen = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + alojamiento.getImagenes().get(aux)));
					lblImagenes.setIcon(imagen);
				}
			}
		});
		lblIzquierda.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzquierda.setBounds(405, 478, 105, 56);
		lblIzquierda.setIcon(flechai);
		contentPane.add(lblIzquierda);
		
		JLabel lblDerecha = new JLabel("");
		lblDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //cuando hacemos click tiene que cambiar la imagen
				int i = alojamiento.getImagenes().size()-1;
				if(aux==i) {
					imagen = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + alojamiento.getImagenes().get(0)));
					lblImagenes.setIcon(imagen);
					aux = 0;
				}else {
					aux++;
					imagen = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + alojamiento.getImagenes().get(aux)));
					lblImagenes.setIcon(imagen);
				}
			}
		});
		lblDerecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblDerecha.setBounds(520, 478, 105, 56);
		lblDerecha.setIcon(flechad);
		contentPane.add(lblDerecha);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Esta seguro que desea reservar?");
				if(x==JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "A continuacion se le mostrara el numero telefonico del hospedaje\n para que se comunique con el mismo", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
					VentanaComunicacion v = new VentanaComunicacion(alojamiento,u, d);
					v.setVisible(true);
					dispose();
				}
				
			}
		});
		btnReservar.setBounds(31, 506, 129, 50);
		contentPane.add(btnReservar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Galeria de Fotos");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1.setBounds(288, 107, 158, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tipo de Habitacion");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1_1_1.setBounds(21, 107, 176, 37);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textName = new JTextField();
		textName.setEditable(false);
		textName.setBounds(280, 37, 196, 24);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setBounds(157, 199, 105, 24);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		textName.setText(alojamiento.getNombre());
		JComboBox<Habitacion>  comboBox = new JComboBox<Habitacion>();
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Habitacion h = (Habitacion) comboBox.getSelectedItem();
            	textPrecio.setText(String.valueOf(h.getPrecio()));
            }
        });
		comboBox.setBounds(21, 146, 209, 37);
		contentPane.add(comboBox);
		for(Habitacion hab: alojamiento.getHabitaciones().getColeccionHabitacion()) {
            comboBox.addItem(hab);
        }
		imagen = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + alojamiento.getImagenes().get(0)));
		lblImagenes.setIcon(imagen);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaHospedajes v = new ventanaHospedajes(u);
				v.setVisible(true);
				dispose();
				
			}
		});
		btnRegresar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnRegresar.setBounds(170, 506, 129, 50);
		contentPane.add(btnRegresar);
	}
}