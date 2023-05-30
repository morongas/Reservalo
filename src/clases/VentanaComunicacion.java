package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class VentanaComunicacion extends JFrame {

    private JPanel contentPane;
    private int indexAlo = 0;
	private int indexDueno = 0;
	private static ColeccionDeDuenos duenos = new ColeccionDeDuenos();


    public VentanaComunicacion(Alojamiento a, Usuario u, Dueno d) {
    	
    	
    	indexAlo = buscarIndexAlojamiento(a.getNombre(),d);
		
		JsonEngine gson = new JsonEngine();
		try {
			duenos = gson.cargarDuenos();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		indexDueno = buscarIndexDueno(d.getUser());
    	
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaComunicacion.class.getResource("/Imagenes/Logo.png")));
    	setTitle("RESERVALO");
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 472, 297);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblComunicacion = new JLabel("Datos del Hospedaje");
        lblComunicacion.setFont(new Font("Arial", Font.BOLD, 24));
        lblComunicacion.setBounds(107, 11, 512, 46);
        contentPane.add(lblComunicacion);
        
        JLabel lbltelefono = new JLabel("Telefono:");
        lbltelefono.setFont(new Font("Arial", Font.BOLD, 18));
        lbltelefono.setBounds(64, 136, 85, 46);
        contentPane.add(lbltelefono);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setBounds(64, 87, 85, 46);
        contentPane.add(lblNombre);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//HAY QUE MANDAR LA SOLICITUD //NO QUIERO MANDAR LA PUTA SOLICITUD COMO HACEMOS PS?
        		
        		a.getSolicitudesActivas().add(new SolicitudActiva(u.getUser())); 
        		d.getAlojamiento().set(indexAlo, a);
        		duenos.getColeccionDuenos().set(indexDueno, d);
        		try {
					gson.guardarDuenos(duenos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		JOptionPane.showMessageDialog(null, "Se envio una solicitud de reserva al hospedaje para notificar su interes en reservar","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        		VentanaUsuario v = new VentanaUsuario(u);
        		v.setVisible(true);
        		dispose();
        		
        	}
        });
        btnAceptar.setBackground(Color.LIGHT_GRAY);
        btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAceptar.setBounds(169, 219, 138, 31);
        contentPane.add(btnAceptar);
        
        JLabel lblNombreEstablecimiento = new JLabel("");
        lblNombreEstablecimiento.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNombreEstablecimiento.setBounds(169, 93, 241, 32);
        contentPane.add(lblNombreEstablecimiento);
        lblNombreEstablecimiento.setText(a.getNombre());
        
        JLabel lblNombreEstablecimiento_1 = new JLabel("");
        lblNombreEstablecimiento_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNombreEstablecimiento_1.setBounds(169, 142, 241, 32);
        contentPane.add(lblNombreEstablecimiento_1);
        lblNombreEstablecimiento_1.setText(a.getTelefono());
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 6));
        panel.setBounds(-15, 66, 502, 10);
        contentPane.add(panel);
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