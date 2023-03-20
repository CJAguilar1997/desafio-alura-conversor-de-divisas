package conversor;

import javax.swing.JOptionPane;

public class Menu {

	public static void main(String[] args) {
		char Repetir = 'Y';
		String[] opciones= {"Conversor de monedas", "Conversor de temperatura"};
		String[] tiposMoneda= {"De Pesos Mexicanos a Dolares",
				"De Pesos Mexicanos a Euros", "De Pesos Mexicanos a Lempiras", "De Pesos Mexicanos a Pesos Argentinos", "De Pesos Mexicanos a Pesos Colombianos",
				"De Lempiras a Dolares", "De Lempiras a Euros", "De Lempiras a Pesos Mexicanos", 
				"De Lempiras a Pesos Colombianos", "De Lempiras a Pesos Argentinos", 
				"De Pesos Colombianos a Dolares", "De Pesos Colombianos a Euros", 
				"De Pesos Colombianos a Pesos Mexicanos", "De Pesos Colombianos a Lempiras", 
				"De Pesos Colombianos a Pesos Argentinos", "De Dolares a Euros", 
				"De Dolares a Pesos Mexicanos", "De Dolares a Lempiras", 
				"De Dolares a Pesos Colombianos", "De Dolares a Pesos Argentinos", 
				"De Euros a Dolares", "De Euros a Pesos Mexicanos", "De Euros a Lempiras", 
				"De Euros a Pesos Colombianos", "De Euros a Pesos Argentinos"};
		
		while (Repetir == 'Y') {
			
			String opcion = (JOptionPane.showInputDialog(null, "Selecciona el convertidor que quieres utilizar", 
					"Convertidor", JOptionPane.PLAIN_MESSAGE, null, opciones, "Conversor de monedas")).toString();
			switch(opcion) {
				case "Conversor de monedas":
					try {
						double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca la cantidad a convertir"));
					} catch(NumberFormatException ex) {
						ex.printStackTrace();
						System.out.println("Se intentó seguir con el espacio vacio");
					}
					String conversion = (JOptionPane.showInputDialog(null, "Elija los tipos de moneda", 
							"Tipos de monedas", JOptionPane.PLAIN_MESSAGE, null, tiposMoneda, 
							"De Pesos Mexicanos a Dolares")).toString();
					break;
					
				case "Conversor de temperatura":
					float valorTemperatura = Float.parseFloat(JOptionPane.showInputDialog(null, "Introduzca la cantidad a convertir", "Conversor de Temperatura", JOptionPane.DEFAULT_OPTION));
					String[] unidadesDeMedicion= {"Celsius (°C) a Fahrenheit (°F)", "Celsius (°C) a Kelvin (K)", 
							"Fahrenheit (°F) a Celsius (°C)", "Fahrenheit (°F) a Kelvin (K)", "Kelvin (K) a Celsius (°C)", 
							"Kelvin (K) a Celsius (°C)"};
					opcion = (JOptionPane.showInputDialog(null, "Selecciona la unidad de medición que quieres utilizar", 
							"Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE, null, unidadesDeMedicion, "Celsius (°C) a Fahrenheit (°F)")).toString();
					switch(opcion) {
						case "Celsius (°C) a Fahrenheit (°F)":
							double temperatura = (valorTemperatura * 1.8) + 32;
							JOptionPane.showMessageDialog(null, "La temperatura es de: " + temperatura + "(°F)", "Conversor de Temperatura", JOptionPane.INFORMATION_MESSAGE);
							break;
					}
					break;
					
			}
			Repetir = 'N';
		}
	}

}
