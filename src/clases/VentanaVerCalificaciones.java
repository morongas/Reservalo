package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Color;

public class VentanaVerCalificaciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaVerCalificaciones(Alojamiento a, Dueno d) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaVerCalificaciones.class.getResource("/Imagenes/Logo.png")));
		setTitle("RESERVALO");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Calificaciones del Establecimiento " + a.getNombre());
		lblNombre.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		lblNombre.setBounds(21, 22, 520, 41);
		contentPane.add(lblNombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 89, 699, 283);
		contentPane.add(scrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usuario", "Calificacion", "Comentario"
			}
		));
		scrollPane.setViewportView(table);
		
		String titulos[] = {"Usuario", "Calificacion", "Comentario"};
		
		DefaultTableModel dtm = new DefaultTableModel(0,0) {
			
			@Override
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		
		dtm.setColumnIdentifiers(titulos);
		
		table.setModel(dtm);
		
		table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer()); //para mostra mas de una linea por celda

		ArrayList<Alojamiento> arreglo = d.getAlojamiento();
		
			for(Calificacion cal : a.getCalificaciones()) {
				dtm.addRow(new Object[] {cal.getUsuario().getUser(), cal.getCalificacion(), cal.getComentario()});
			}
		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaDueno v = new ventanaDueno(d);
				v.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRegresar.setBounds(534, 403, 163, 32);
		contentPane.add(btnRegresar);
		
		this.setLocationRelativeTo(null);
	}
}
