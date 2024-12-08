package Multiproceso;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulacionMP {
	
	public static void main(String[] args) {
		int tipoProteina = Integer.parseInt(args[0]);
		int numProteina=Integer.parseInt(args[1]);
		crearFichero(tipoProteina, numProteina);
	}
	
	/**
     * Calcula la simulación de la proteina
     * @param type tipo de proteina
     * @return el total del calculo de la simulación
     */
	public static double simulation (int type) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, type);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
		calc = Math.sin(Math.pow(Math.random(),2));
		}
		return calc;
		}
	/**
	 * Crea un fichero con la informacion requerida
	 * @param tipoProteina tipo de proteina que vamos a evaluar
	 * @param numProteina numero de la proteina que vamos a evaluar
	 */
	public static void crearFichero(int tipoProteina, int numProteina) {
		try {
			long inicio = System.currentTimeMillis();
	        double calculo = simulation(tipoProteina); 
	        long fin = System.currentTimeMillis();
	        double tiempoTranscurrido = fin - inicio;

	        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
	        String inicioFormato = formato.format(new Date(inicio));
	        String finFormato = formato.format(new Date(fin));
	        
			FileWriter fw = new FileWriter("./src/Ficheros/PROT_MP_" + tipoProteina + "_n" + numProteina +"_"+ inicio+".sim");
			fw.write(String.valueOf(inicioFormato+"\n"));
			fw.write(String.valueOf(finFormato+"\n"));
			fw.write(String.valueOf(tiempoTranscurrido+"\n"));
			fw.write(String.valueOf(calculo+"\n"));
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
