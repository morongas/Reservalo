package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.util.regex.*;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;  


public class VentanaIngresarImagen extends JFrame {

	private JPanel contentPane;
	JsonEngine json = new JsonEngine();
	ColeccionDeDuenos duenos;

	public VentanaIngresarImagen(Dueno d, Alojamiento alojamiento) {
		
		try {
			duenos = json.cargarDuenos();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setTitle("Agregar Imagenes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Agregar Imagenes");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(14, 11, 176, 54);
		contentPane.add(lblNewLabel_1);
		
		ArrayList<String> imagenes = new ArrayList<String>();
		
		JLabel imageHolder = new JLabel("");
		imageHolder.setHorizontalAlignment(SwingConstants.CENTER);
		imageHolder.setIcon(null);
		imageHolder.setForeground(Color.BLACK);
		imageHolder.setBackground(Color.BLACK);
		imageHolder.setBounds(157, 76, 440, 239);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		imageHolder.setBorder(border);
		imageHolder.setIcon(new ImageIcon(VentanaIngresarImagen.class.getResource("/Imagenes/Logoblur.png")));				
		contentPane.add(imageHolder);
		
		//Combo box
		JComboBox<String> imageSel = new JComboBox<String>(); 
		imageSel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		imageSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (imageSel.getSelectedItem() != null) { //si el cb no esta vacio
				//System.out.println(imageSel.getSelectedItem().toString());
				imageHolder.setIcon(new ImageIcon(VentanaIngresarImagen.class.getResource("/Imagenes/" + imageSel.getSelectedItem().toString())));				
				} else {
					imageHolder.setIcon(new ImageIcon(VentanaIngresarImagen.class.getResource("/Imagenes/Logoblur.png")));				
				}
				
			}
		});
		imageSel.setBounds(14, 76, 133, 31);
		
		if (alojamiento.getImagenes().isEmpty() == false) {
			for (String s: alojamiento.getImagenes())
			imageSel.addItem(s);
		}
		
		contentPane.add(imageSel);

		
		JButton btnBuscarImagen = new JButton("Buscar Imagen");
		btnBuscarImagen.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		btnBuscarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//boton	
				JFileChooser fc = new JFileChooser(); //entramos en un file chooser
		    	FileNameExtensionFilter filter = new FileNameExtensionFilter("img, png y jpg","img", "png", "jpg");
		    	fc.setFileFilter(filter); //filtro para solo poder elegir img, png y jpg
		    	
		    	//Cambiamos el directorio por el de la aplicacion
		    	File chooserFile = null;
				try {
					chooserFile = new File((new File(".").getCanonicalPath() + "\\bin\\Imagenes"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	fc.setCurrentDirectory(chooserFile);
		    	
		    	int opcion = fc.showOpenDialog(null);
		    	if(opcion != JFileChooser.CANCEL_OPTION) {
		    				    		
		    			String ruta = fc.getSelectedFile().getPath();
		    			
		    			//validamos que el archivo elegido sea una imagen y que no este repetida
		    		//	System.out.println(ruta);
		    			Pattern pattern = Pattern.compile("(?<=Imagenes.)\\w+.+");
		    			Matcher m = pattern.matcher(ruta);
		    			if(m.find()) {
			    			String direccion = m.group();
			    			direccion.trim();
			    			
			    			boolean verif = false;
			    			 for (String string : alojamiento.getImagenes()) {
			    	               if(string.equals(direccion)){
			    	            	   verif = true;
			    	               }
			    	           }
			    			 			    			 
			    			if (!verif) {
		    				alojamiento.getImagenes().add(direccion); // se agrega la imagen al arreglo de imagenes
		    				imageSel.addItem(direccion); // se agrega la imagen al combo box
		    				JOptionPane.showMessageDialog(null, "La imagen se agrego correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
		    				} else {
			    				JOptionPane.showMessageDialog(null, "El archivo seleccionado no es valido", "Error", JOptionPane.ERROR_MESSAGE);
			    			}	

		    			} else {
		    				JOptionPane.showMessageDialog(null, "El archivo seleccionado no es valido", "Error", JOptionPane.ERROR_MESSAGE);
		    			}
		    	}
			}
		});
		btnBuscarImagen.setBounds(463, 19, 126, 45);
		contentPane.add(btnBuscarImagen);
		
		JButton btnEliminarImagen = new JButton("Eliminar Imagen");
		btnEliminarImagen.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		btnEliminarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index;
				index = imageSel.getSelectedIndex(); // guardamos el indice de la imagen seleccionada en el cb
				imageSel.removeItem(imageSel.getSelectedItem());
				alojamiento.getImagenes().remove(index); //eliminamos la imagen en la coleccion 
			}
		});
		btnEliminarImagen.setBounds(14, 326, 136, 45);
		contentPane.add(btnEliminarImagen);
		
		JButton btnContinuar = new JButton("Guardar y Salir");
		btnContinuar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = JOptionPane.showConfirmDialog(null, "Desea salir?");
				
				if (i==JOptionPane.YES_OPTION) {
					
					for (int k =0;  k<d.getAlojamiento().size(); k++) {
						if (d.getAlojamiento().get(k).getNombre().equals(alojamiento.getNombre())) {
							d.getAlojamiento().set(k, alojamiento);
						}
					}
					
					for (int j = 0; j<duenos.getColeccionDuenos().size() ; j++ ){
						if (duenos.getColeccionDuenos().get(j).getUser().equals(d.getUser())) {
							duenos.getColeccionDuenos().set(j, d);
						}
					}
					
					try {
						json.guardarDuenos(duenos);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				JOptionPane.showMessageDialog(null, "La(s) Imagen(es) se han guardado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
					dispose();	
				}
				
				ventanaDueno ventana = new ventanaDueno(d);
				ventana.setVisible(true);
				dispose();
				
			}
		});
		btnContinuar.setBounds(463, 326, 126, 45);
		contentPane.add(btnContinuar);

		this.setLocationRelativeTo(null);
	}
}
