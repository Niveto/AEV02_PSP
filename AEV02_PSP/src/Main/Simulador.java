package Main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Multihilo.SimulacionMT;
import Multiproceso.LanzadorMP;


import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import java.awt.event.ActionEvent;

public class Simulador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador frame = new Simulador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Simulador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spnProteina1 = new JSpinner();
		spnProteina1.setBounds(165, 67, 88, 31);
		contentPane.add(spnProteina1);
		
		JLabel lblNewLabel = new JLabel("Fet per Javi Armero i Nicolás Vega");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel.setBounds(321, 383, 237, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblTiempoMT = new JLabel("Tiempo recorrido de multihilo:");
		lblTiempoMT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTiempoMT.setBounds(299, 70, 319, 20);
		contentPane.add(lblTiempoMT);
		
		JLabel lblTiempoMP = new JLabel("Tiempo recorrido de multiproceso:");
		lblTiempoMP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTiempoMP.setBounds(299, 119, 319, 20);
		contentPane.add(lblTiempoMP);
		
		JSpinner spnProteina2 = new JSpinner();
		spnProteina2.setBounds(165, 116, 88, 31);
		contentPane.add(spnProteina2);
		
		JSpinner spnProteina3 = new JSpinner();
		spnProteina3.setBounds(165, 167, 88, 31);
		contentPane.add(spnProteina3);
		
		JSpinner spnProteina4 = new JSpinner();
		spnProteina4.setBounds(165, 215, 88, 31);
		
		contentPane.add(spnProteina4);
		
		JLabel lblProteinaPrimaria = new JLabel("Proteica Primaria");
		lblProteinaPrimaria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProteinaPrimaria.setBounds(10, 76, 259, 20);
		contentPane.add(lblProteinaPrimaria);
		
		JLabel lblProteicaSecundaria = new JLabel("Proteica Secundaria");
		lblProteicaSecundaria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProteicaSecundaria.setBounds(10, 125, 259, 20);
		contentPane.add(lblProteicaSecundaria);
		
		JLabel lblProteicaTerciaria = new JLabel("Proteica Terciaria");
		lblProteicaTerciaria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProteicaTerciaria.setBounds(10, 176, 259, 20);
		contentPane.add(lblProteicaTerciaria);
		
		JLabel lblProteicaCuaternaria = new JLabel("Proteica Cuaternaria");
		lblProteicaCuaternaria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProteicaCuaternaria.setBounds(10, 224, 259, 20);
		contentPane.add(lblProteicaCuaternaria);
		
		JButton btnSimular = new JButton("Simular");
		
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] numeroProteinas = {
					    (int) spnProteina1.getValue(),
					    (int) spnProteina2.getValue(),
					    (int) spnProteina3.getValue(),
					    (int) spnProteina4.getValue()
					};
				long contadorMP = 0;
				long contadorMT=0;
				long inicioMP=0;
				long finMP=0;
				long inicioMT=0;
				long finMT=0;
				for (int tipo = 1; tipo < numeroProteinas.length; tipo++) {
				    for (int i = 1; i <= numeroProteinas[tipo-1]; i++) {
				        inicioMP = System.currentTimeMillis();
				        LanzadorMP.lanzadorMultiproceso(tipo, i);
				        finMP = System.currentTimeMillis();
				        contadorMP += (finMP - inicioMP);
        
				        SimulacionMT st = new SimulacionMT(tipo, i);
				        Thread t1 = new Thread(st);
				        inicioMT = System.currentTimeMillis();
				        t1.start();
				       
				        try {			 	
				            t1.join();
				            finMT = System.currentTimeMillis();
					        contadorMT += (finMT - inicioMT);
				        } catch (InterruptedException e1) {
				            e1.printStackTrace();
				        }     
				    }
				}


				DecimalFormat formato = new DecimalFormat("0.00");
				lblTiempoMP.setText("Tiempo recorrido de multiproceso: " + formato.format(contadorMP / 1000.0));
				lblTiempoMT.setText("Tiempo recorrido de multihilo: " + formato.format(contadorMT / 1000.0));
				
			}
		});
		btnSimular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSimular.setBounds(10, 279, 243, 44);
		contentPane.add(btnSimular);
		
		JLabel lblTitulo = new JLabel("Simulador de proteinas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTitulo.setBounds(10, 10, 341, 47);
		contentPane.add(lblTitulo);
	}

}
