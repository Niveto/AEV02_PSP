package Multihilo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulacionMT implements Runnable {

	private int tipoProteina;
    private int numProteina;

    public SimulacionMT(int tipoProteina, int numProteina) {
        this.tipoProteina = tipoProteina;
        this.numProteina = numProteina;
    }

    public void run() {
        crearFichero(tipoProteina, numProteina);
    }
    /**
     * Calcula la simulación de la proteina
     * @param type tipo de proteina
     * @return el total del calculo de la simulación
     */
	public static double simulation(int type) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, type);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
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
	        
	        DecimalFormat formatoTotal = new DecimalFormat("0.00");
	        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd_HHmmss_SS");

			FileWriter fw = new FileWriter("./src/Ficheros/PROT_MT_" + tipoProteina + "_n" + numProteina +"_"+ formato.format(new Date(inicio))+".sim");
			fw.write(String.valueOf(formato.format(new Date(inicio))+"\n"));
			fw.write(String.valueOf(formato.format(new Date(fin))+"\n"));
			fw.write(String.valueOf(formatoTotal.format(tiempoTranscurrido)+"\n"));
			fw.write(String.valueOf(calculo+"\n"));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
