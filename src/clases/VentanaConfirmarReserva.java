package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmarReserva extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private int filaSeleccionada;
    private int seleccionado = 0;
    private static DefaultTableModel dtm = new DefaultTableModel();
    int op = -1;

    /**
     * Create the frame.
     */
    public VentanaConfirmarReserva(Dueno d, Alojamiento a) {
    	
    	setTitle("RESERVALO");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmarReserva.class.getResource("/Imagenes/Logo.png")));
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 464);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblConfirma = new JLabel("Confirmar Reserva");
        lblConfirma.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblConfirma.setBounds(177, 10, 257, 43);
        contentPane.add(lblConfirma);
        
        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblSeleccionado.setBounds(10, 337, 117, 31);
        contentPane.add(lblSeleccionado);
        
        JLabel lblNombre = new JLabel("");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNombre.setBounds(137, 336, 298, 31);
        contentPane.add(lblNombre);
        
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
        btnRegresar.setBounds(10, 388, 104, 31);
        contentPane.add(btnRegresar);
        
        JButton btnConfirmar = new JButton("Confirmar Reserva");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(op!=-1) {
        			a.eliminarReserva((String) table.getValueAt(seleccionado, 0));
					VentanaModificarCalendario v = new VentanaModificarCalendario(d,a);
					v.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna Solicitud!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}
        		
        	}
        });
        btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnConfirmar.setBackground(Color.LIGHT_GRAY);
        btnConfirmar.setBounds(363, 388, 163, 31);
        contentPane.add(btnConfirmar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 81, 516, 246);
        contentPane.add(scrollPane);
        
        table = new JTable();
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
                "Nombre del solicitante" , "Nombre del Alojamiento" , " Tipo"
            }
        ));
        scrollPane.setViewportView(table);
        
        String titulos[] = {"Nombre del solicitante", "Nombre del Alojamiento", "Tipo"};
        
        dtm = new DefaultTableModel(0,0) {
            
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };
        
        dtm.setColumnIdentifiers(titulos);
        
        table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getValueAt(0,0) != null){
					seleccionado = table.rowAtPoint(e.getPoint());
					lblNombre.setText((String) table.getValueAt(seleccionado, 0));
					op=0;
				}
				
			}
		});
		
		table.setModel(dtm);
        
        for(Alojamiento x : d.getAlojamiento()) {
        	for (SolicitudActiva s: x.getSolicitudesActivas()) {
        			dtm.addRow(new Object[] {s.getNombre(), x.getNombre(), x.getTipoDeAlojamientoString()});	
        	}	
		}        
        
    }
}
