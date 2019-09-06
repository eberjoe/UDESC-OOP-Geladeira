package geladeira;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeladeiraApp {

	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
		
		Geladeira minhaGeladeira = new Geladeira();
		int ventRef;
		int ventCon;
		
		// Simulação da seleção de temperatura
		
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		
		System.out.println("Entre uma temperatura para o refrigerador:");
		minhaGeladeira.compartimento2.setSelecaoTemperatura(Float.parseFloat(reader.readLine()));
		System.out.println("A temperatura configurada para o refrigerador é de " + minhaGeladeira.compartimento2.getSelecaoTemperatura() + " graus.");
		
		System.out.println("Entre uma temperatura para o congelador:");
		minhaGeladeira.compartimento1.setSelecaoTemperatura(Float.parseFloat(reader.readLine()));
		System.out.println("A temperatura configurada para o congelador é de " + minhaGeladeira.compartimento1.getSelecaoTemperatura() + " graus.\n");
		
		// Simulação da medição dos sensores e do controle de temperatura
		
		minhaGeladeira.compartimento1.sensor.setTemperaturaMedida(25);
		minhaGeladeira.compartimento2.sensor.setTemperaturaMedida(25);
		
		do {
			ventRef = minhaGeladeira.compartimento2.ventilador.isLigado() ? 1: 0;
			ventCon = minhaGeladeira.compartimento1.ventilador.isLigado() ? 1: 0;

			minhaGeladeira.compartimento2.sensor.setTemperaturaMedida(minhaGeladeira.compartimento2.sensor.getTemperaturaMedida() - ventRef);
			System.out.println("O sensor do refrigerador está medindo " + minhaGeladeira.compartimento2.sensor.getTemperaturaMedida() + " graus.");
			if (minhaGeladeira.compartimento2.sensor.getTemperaturaMedida() > minhaGeladeira.compartimento2.getSelecaoTemperatura()) {
				minhaGeladeira.compartimento2.ventilador.setLigado(true); // liga ventilador
				System.out.println("O ventilador do refrigerador foi acionado.");
			}
			else {
				minhaGeladeira.compartimento2.ventilador.setLigado(false);
				System.out.println("O ventilador do refrigerador foi desligado.");
			}
			
			minhaGeladeira.compartimento1.sensor.setTemperaturaMedida(minhaGeladeira.compartimento1.sensor.getTemperaturaMedida() - ventCon);
			System.out.println("O sensor do congelador está medindo " + minhaGeladeira.compartimento1.sensor.getTemperaturaMedida() + " graus.");
			if (minhaGeladeira.compartimento1.sensor.getTemperaturaMedida() > minhaGeladeira.compartimento1.getSelecaoTemperatura()) {
				minhaGeladeira.compartimento1.ventilador.setLigado(true); // liga ventilador
				minhaGeladeira.compartimento1.compressor.setLigado(true); // liga compressor
				System.out.println("O ventilador e o compressor do congelador foram acionados.");
			}
			else {
				minhaGeladeira.compartimento1.ventilador.setLigado(false);
				minhaGeladeira.compartimento1.compressor.setLigado(false);
				System.out.println("O ventilador e o compressor do congelador foram desligados.");
			}
			System.out.println();

			Thread.sleep(1000);
		} while (minhaGeladeira.compartimento1.compressor.isLigado());
	}

}
