package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaFechasOcupadas extends JFrame {
	
	
	private int filaSeleccionada;
	public int op = -1;
    private static DefaultTableModel dtm = new DefaultTableModel();


	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaFechasOcupadas(Alojamiento a, Dueno d) {
		setTitle("RESERVALO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFechasOcupadas.class.getResource("/Imagenes/Logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 403);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 JTable table = new JTable();
	        table.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if(table.getValueAt(0,0) != null){
	        			int seleccionado = table.rowAtPoint(e.getPoint());
						filaSeleccionada = seleccionado;
						op = 0;
					}
	        	}
	        });
	        table.setModel(new DefaultTableModel(
	            new Object[][] {
	            },
	            new String[] {
	                "Dia" , "Mes" , "año"
	            }
	        ));
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(56, 67, 395, 236);
	        contentPane.add(scrollPane);
	        scrollPane.setViewportView(table);
	        
	        String titulos[] = {"Dia" , "Mes" , "año"};
	        
	        dtm = new DefaultTableModel(0,0) {
	            
	            @Override
	            public boolean isCellEditable(int row,int column) {
	                return false;
	            }
	        };
	        
	        dtm.setColumnIdentifiers(titulos);
	        table.setModel(dtm);
	        
	        for (Fecha f: a.getFechas()) {
	       		dtm.addRow(new Object[] {f.getDia(), f.getMes(), f.getAnio()});	
	        }	  
	        
	        
	        JLabel lblFechasOcupadas = new JLabel("Fechas Ocupadas del Establecimiento " + a.getNombre());
	        lblFechasOcupadas.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblFechasOcupadas.setBounds(26, 13, 587, 43);
	        contentPane.add(lblFechasOcupadas);
	        
	        JButton btnRegresar = new JButton("Regresar");
	        btnRegresar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		ventanaDueno v = new ventanaDueno(d);
					v.setVisible(true);
					dispose();
	        	}
	        });
	        btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnRegresar.setBackground(Color.LIGHT_GRAY);
	        btnRegresar.setBounds(198, 320, 104, 31);
	        contentPane.add(btnRegresar);
	}
}
