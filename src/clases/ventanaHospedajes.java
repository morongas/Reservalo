package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaHospedajes extends JFrame {
	private DefaultTableModel model;
	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel dtm = new DefaultTableModel();
	private JsonEngine json = new JsonEngine();
	private ColeccionDeDuenos d;
	private int filaSeleccionada;
	private Object [][] data;
	int op = -1;
	
	public ventanaHospedajes(Usuario u) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaAgregarEst.class.getResource("/Imagenes/LogoInicio.png")));
		setTitle("RESERVALO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			 d = json.cargarDuenos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("Filtro por Estado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(706, 54, 134, 36);
		contentPane.add(lblNewLabel);
		JCheckBox checkPromocion = new JCheckBox("Hospedajes en Promocion");
		checkPromocion.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkPromocion.setBounds(706, 141, 245, 48);
		contentPane.add(checkPromocion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Amazonas", "Anzo\u00E1tegui", "Apure", "Aragua", "Barinas", "Bol\u00EDvar", "Carabobo", "Cojedes", "Delta Amacuro", "Distrito Capital", "Falc\u00F3n", "Gu\u00E1rico", "Lara", "M\u00E9rida", "Miranda", "Monagas", "Nueva Esparta", "Portuguesa", "Sucre", "T\u00E1chira", "Trujillo", "Vargas", "Yaracuy", "Zulia"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(706, 87, 193, 36);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Hospedajes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(29, 26, 204, 41);
		contentPane.add(lblNewLabel_1);
		
		
		
		Icon foto = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logoblur.png"));

		

	        String[] columnNames = {"Nombre", "Estado", "Tipo", "Foto", "Promocion"};
	        
	        //llenamos el objeto con la informacion necesario de los hospedajes
	        data = sinFiltro();
	        

	        model = new DefaultTableModel(data, columnNames)
	        {
	            //  Returning the Class of each column will allow different
	            //  renderers to be used based on Class
	            public Class getColumnClass(int column)
	            {
	                return getValueAt(0, column).getClass();
	            }
	        };
	        
	        table = new JTable( model );
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
	        table.setFillsViewportHeight(true);
	        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	        table.setPreferredScrollableViewportSize(table.getPreferredSize());
	        table.setRowHeight(60);
	        
	        
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	        table.setDefaultRenderer(String.class, centerRenderer);

	        JScrollPane scrollPane = new JScrollPane( table );
	        scrollPane.setBounds(29, 78, 667, 442);
	        getContentPane().add( scrollPane );

		
			scrollPane.setViewportView(table);
			contentPane.add(scrollPane);
			
			JButton btnBuscar = new JButton("BUSCAR");
			btnBuscar.addActionListener(new ActionListener() {
			   //String[] columnNames = {"Nombre", "Estado", "Tipo", "Foto", "Promocion"};
			   //Object [][] data =null;
			   boolean aux = false;
				public void actionPerformed(ActionEvent e) {
				    if(checkPromocion.isSelected()) {
			        	if(table.getModel()!=null)
			        		borrarTabla();
			        	if(comboBox.getSelectedItem()==""){
			        		 aux = true;
			        		 data = promocion(); 
			        		 table.setModel(new DefaultTableModel(data, columnNames));
			        	}else {
			        		aux = true;
			        		data = promoConEst(comboBox.getSelectedItem());
			        		table.setModel(new DefaultTableModel(data, columnNames));
			        	}
			        }else {
			        	if(table.getModel()!=null) {
			        		borrarTabla();
			        	}
			        	if(comboBox.getSelectedItem()!="") {
			        		aux = true;
			        		data = busqEst(comboBox.getSelectedItem());
			        		table.setModel(new DefaultTableModel(data, columnNames));
			        	}else {
			        		aux = true;
			        		data = sinFiltro();
			        		table.setModel(new DefaultTableModel(data, columnNames));
			        	}
			        }
				    if(data[0][0] == null) {
				    	data = sinFiltro();
				    	table.setModel(new DefaultTableModel(data, columnNames));
				    	JOptionPane.showMessageDialog(null, "No se encontro ningun establecimiento con estos requisitos");
				    }
				}
			});
			btnBuscar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			btnBuscar.setBounds(721, 423, 208, 36);
			contentPane.add(btnBuscar);
			
			JButton btnNewButton = new JButton("Seleccionar Hospedaje");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(op != -1) {
						VentanaHospedajeSeleccionado selec = new VentanaHospedajeSeleccionado(buscarSeleccionado(),u, buscarDueno());
						selec.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "No ha seleccionado ningun hospedaje","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewButton.setBounds(722, 469, 207, 41);
			contentPane.add(btnNewButton);
			
			JButton btnRegresar = new JButton("REGRESAR");
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					VentanaUsuario v = new VentanaUsuario(u);
					v.setVisible(true);
					dispose();
					
				}
			});
			btnRegresar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			btnRegresar.setBounds(25, 530, 208, 36);
			contentPane.add(btnRegresar);
			
			JButton btnCalificar = new JButton("Agregar calificacion");
			btnCalificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(op!=-1) {
						VentanaCalificar v = new VentanaCalificar(u,buscarSeleccionado());
						v.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "No ha seleccionado ningun hospedaje!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
			btnCalificar.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnCalificar.setBounds(722, 515, 207, 41);
			contentPane.add(btnCalificar);
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	private Object[][] sinFiltro(){
		int fil = numeroDeHospedajes();
		int i =0;
		int j;
		int aux= 0;
		Object [][] respuesta = new Object[fil][5];
		while(d.getColeccionDuenos().size()>i) {
		    j =0;
			while(d.getColeccionDuenos().get(i).getAlojamiento().size()>j) {
				if(d.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado()) {
					respuesta[aux][0] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre();
					respuesta[aux][1] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado();
					respuesta[aux][2] = tipo(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getTipoDeAlojamiento());
				//	respuesta[aux][3] = new ImageIcon("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0));
					respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0)));
					//respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logo.png"));
					respuesta[aux][4] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion();
					aux++;
				}
				j++;
			}
			i++;
		}
		return respuesta;
	}
	private Object[][] busqEst(Object selectedItem) {
		int fil = numeroDeHospedajes();
		int i =0;
		int j;
		int aux= 0;
		Object [][] respuesta = new Object[fil][5];
		while(d.getColeccionDuenos().size()>i) {
		    j =0;
			while(d.getColeccionDuenos().get(i).getAlojamiento().size()>j) {
				if(d.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado()) {
					if(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado().equals(selectedItem.toString())) {
						respuesta[aux][0] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre();
						respuesta[aux][1] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado();
						respuesta[aux][2] = tipo(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getTipoDeAlojamiento());
						//respuesta[aux][3] = new ImageIcon("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0));
						respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0)));
						//respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logo.png"));
						respuesta[aux][4] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion();
						aux++;
					}
				}
				j++;
			}
			i++;
		}
		return respuesta;
	}

	private Object[][] promoConEst(Object selectedItem) {
		System.out.println(selectedItem);
		int fil = numeroDeHospedajes();
		int i =0;
		int j;
		int aux= 0;
		Object [][] respuesta = new Object[fil][5];
		while(d.getColeccionDuenos().size()>i) {
		    j =0;
			while(d.getColeccionDuenos().get(i).getAlojamiento().size()>j) {
				if((d.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado())&&(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion().equals("SI"))) {
					if(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado().equals(selectedItem.toString())) {
						respuesta[aux][0] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre();
						respuesta[aux][1] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado();
						respuesta[aux][2] = tipo(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getTipoDeAlojamiento());
						//respuesta[aux][3] = new ImageIcon("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0));
						respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0)));
						//respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logo.png"));
						respuesta[aux][4] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion();
						aux++;
					}
				}
				j++;
			}
			i++;
		}
		return respuesta;
	}

	private Object[][] promocion() {
		int fil = numeroDeHospedajes();
		int i =0;
		int j;
		int aux= 0;
		Object [][] respuesta = new Object[fil][5];
		while(d.getColeccionDuenos().size()>i) {
		    j =0;
			while(d.getColeccionDuenos().get(i).getAlojamiento().size()>j) {
				if((d.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado())&&(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion().equals("SI"))) {
					respuesta[aux][0] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre();
					respuesta[aux][1] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getEstado();
					respuesta[aux][2] = tipo(d.getColeccionDuenos().get(i).getAlojamiento().get(j).getTipoDeAlojamiento());
					//respuesta[aux][3] = new ImageIcon("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0));
					respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/" + d.getColeccionDuenos().get(i).getAlojamiento().get(aux).getImagenes().get(0)));
					//respuesta[aux][3] = new ImageIcon(ventanaHospedajes.class.getResource("/Imagenes/Logo.png"));
					respuesta[aux][4] = d.getColeccionDuenos().get(i).getAlojamiento().get(j).getPromocion();
					aux++;
				}
				j++;
			}
			i++;
		}
		return respuesta;
	}
	private int numeroDeHospedajes() {
		int i=0;
		int j;
		int respuesta=0;
		while(d.getColeccionDuenos().size()>i) {
			j=0;
			while(d.getColeccionDuenos().get(i).getAlojamiento().size()>j) {
				if(d.getColeccionDuenos().get(i).getAlojamiento().get(j).isVerificado()) {
					respuesta++;
				}
				j++;
			}
			i++;
		}
		return respuesta;
	}
	private void borrarTabla() {
		model.setRowCount(0);
		for(int i=0;i<model.getRowCount();i++) {
			model.removeRow(i);
			System.out.println("urr");
		}
	}
	
	private String tipo(int x) {
		if(x==0) {
			return "Hotel";
		}else if(x==1) {
			return "Posada";
		}else {
			return "Apartamento";
		}
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	public Alojamiento buscarSeleccionado() {
		Alojamiento respuesta= null; 
		for(int x=0;x<d.getColeccionDuenos().size();x++) {
			for(Alojamiento b: d.getColeccionDuenos().get(x).getAlojamiento()) {
				if(b.getNombre().toString()== data[filaSeleccionada][0].toString()) {
					respuesta = b;
				}
			}
		}
		return respuesta;
	}
	
	public Dueno buscarDueno() {
		Dueno respuesta= null; 
		for(int x=0;x<d.getColeccionDuenos().size();x++) {
			for(Alojamiento b: d.getColeccionDuenos().get(x).getAlojamiento()) {
				if(b.getNombre().toString()== data[filaSeleccionada][0].toString()) {
					respuesta = d.getColeccionDuenos().get(x);
				}
			}
		}
		return respuesta;
	}
	
}
