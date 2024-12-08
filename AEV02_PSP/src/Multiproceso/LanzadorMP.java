package Multiproceso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorMP {

	public static void main(String[] args) {
		int tipoProteina = Integer.parseInt(args[0]);
		int numProteina = Integer.parseInt(args[1]);
		lanzadorMultiproceso(tipoProteina, numProteina);
	}
	/**
	 * lanza el multiproceso
	 * @param tiposProteinas tipo de proteina que vamos a analizar
	 * @param numerosProteinas numero de proteina que vamos a analizar
	 */
	public static void lanzadorMultiproceso(int tiposProteinas, int numerosProteinas) {
		String classe = "Multiproceso.SimulacionMP";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = classe;

		List<Process> procesos = new ArrayList<>();
		try {

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(String.valueOf(tiposProteinas));
			command.add(String.valueOf(numerosProteinas));

			ProcessBuilder builder = new ProcessBuilder(command);
			builder.inheritIO();

			Process process = builder.start();
			procesos.add(process);

			for (Process proc : procesos) {
				proc.waitFor();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
