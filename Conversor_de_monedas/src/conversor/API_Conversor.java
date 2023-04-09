package conversor;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class API_Conversor {
	public static void main(String[] args) {
		//Definiendo variables y Arrays
		Object [] conversores = {"Conversor de monedas", "Conversor de temperatura"};
		String opcion = "", monedaAConvertir = "", monedaFinal = "", monedaSeleccionada1 = "", monedaSeleccionada2 = "";
		double cantidadAConvertir = 1;
		boolean validacion = false;
		int continuar = 0;
		
		while (continuar == 0)	{
			
			try {	//Pidiendo el tipo de conversor que desea usar el usuario
				opcion = (JOptionPane.showInputDialog(null,"¿Que conversor desea usar?", "Elegir",JOptionPane.QUESTION_MESSAGE,null,conversores, conversores[0]).toString());
			} catch (Exception ex) {
				ex.getMessage();
				ex.printStackTrace();
			}
			
			switch (opcion) { //Switch para cambiar entre conversores dependiendo del elegido por el usuario
			
			case "Conversor de monedas":
				String [] monedas = {"Dolar Estadounidense (USD)","Euro (EUR)", "Lempiras (HND)", 
						"Peso Argentino (ARS)", "Peso Mexicano (MXN)"};
				try { //Pidiendo el tipo de moneda que desea convertir el usuario y la cantidad que desea el usuario
					monedaSeleccionada1 = (JOptionPane.showInputDialog(null,"Elige la moneda que quieres cambiar", "Conversor de monedas",JOptionPane.QUESTION_MESSAGE,null,monedas, monedas[0]).toString());
					while (validacion == false) {
						try {
							validacion = true;
							cantidadAConvertir = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca la cantidad que desea convertir", "Conversor de monedas", JOptionPane.QUESTION_MESSAGE));
						} catch(Exception ex) {
							validacion = false;
							ex.getMessage();
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "!Solo introduzca valores numericos¡", "!ERROR¡", JOptionPane.ERROR_MESSAGE);
						}
					}
					monedaSeleccionada2 = (JOptionPane.showInputDialog(null,"Elige la moneda a la que quieres cambiar", "Conversor de monedas",JOptionPane.QUESTION_MESSAGE,null,monedas, monedas[0]).toString());
					switch (monedaSeleccionada1) {// Cambiando el String visual por uno que sepa interpretar la API
					case "Dolar Estadounidense (USD)":
						monedaAConvertir = Monedas.values()[0].toString();
						break;
					case "Euro (EUR)":
						monedaAConvertir = Monedas.values()[1].toString();
						break;
					case "Lempiras (HND)":
						monedaAConvertir = Monedas.values()[2].toString();
						break;
					case "Peso Argentino (ARS)":
						monedaAConvertir = Monedas.values()[3].toString();
						break;
					case "Peso Mexicano (MXN)":
						monedaAConvertir = Monedas.values()[4].toString();
						break;
					}
					
					switch (monedaSeleccionada2) {// Cambiando el String visual por uno que sepa interpretar la API
					case "Dolar Estadounidense (USD)":
						monedaFinal = Monedas.values()[0].toString();
						break;
					case "Euro (EUR)":
						monedaFinal = Monedas.values()[1].toString();
						break;
					case "Lempiras (HND)":
						monedaFinal = Monedas.values()[2].toString();
						break;
					case "Peso Argentino (ARS)":
						monedaFinal = Monedas.values()[3].toString();
						break;
					case "Peso Mexicano (MXN)":
						monedaFinal = Monedas.values()[4].toString();
						break;
					}
					
				} catch (Exception ex) {
					ex.getMessage();
					ex.printStackTrace();
				}
				
				if (monedaAConvertir != "" && monedaFinal != "") {// Comparador para saber si están todos los datos necesarios para continuar con la ejecución de la API
					try { //Comienzo de la ejecución de la API
						URL url = new URL("https://api.exchangerate.host/latest?base="+monedaAConvertir+"&symbols="+monedaFinal+"&amount="+cantidadAConvertir+"");
						HttpURLConnection con = (HttpURLConnection) url.openConnection();
						con.setRequestMethod("GET");
						
						int responseCode = con.getResponseCode();
						if (responseCode != 200) {
							throw new RuntimeException("Ocurrio un error: "+responseCode);
						} else {
							InputStream strm = con.getInputStream();
							byte [] arrStream = strm.readAllBytes();
							
							String ctnJson = "";
							
							for(byte tmp: arrStream) { //Compilando los caracteres en un String
								ctnJson += (char)tmp;
							}
							System.out.println(ctnJson);
							
							JSONObject json = new JSONObject(ctnJson);
							JSONObject rates = (JSONObject) json.get("rates");
							JOptionPane.showMessageDialog(null, "El valor del "+monedaSeleccionada1+" en "+monedaSeleccionada2+" es de: "+rates.get(monedaFinal)+" ("+monedaFinal+")", "Resultado del conversor", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "¡No se introducieron todos los datos para la conversion", "!ERROR¡", JOptionPane.ERROR_MESSAGE); //Mensaje final con la conversión de las monedas
				}
				break;
				
			case "Conversor de temperatura":
				String [] medidasDeTemperatura = {"Celsius (C°)" ,"Fahrenheit (F°)" ,"Kelvin (K)"};
				double resultado = 0;
				String temperaturaSeleccionada1, temperaturaSeleccionada2;
				temperaturaSeleccionada1 = (JOptionPane.showInputDialog(null,"Elige la temperatura que quieres cambiar", "Conversor de temperatura",JOptionPane.QUESTION_MESSAGE,null,medidasDeTemperatura, medidasDeTemperatura[0]).toString());
				while (validacion == false) {
					try {
						validacion = true;
						cantidadAConvertir = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca la cantidad que desea convertir", "Conversor de temperatura", JOptionPane.QUESTION_MESSAGE));
					} catch(Exception ex) {
						validacion = false;
						ex.getMessage();
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "!Solo introduzca valores numericos¡", "!ERROR¡", JOptionPane.ERROR_MESSAGE);
					}
				}
				temperaturaSeleccionada2 = (JOptionPane.showInputDialog(null,"Elige la temperatura que quieres cambiar", "Conversor de temperatura",JOptionPane.QUESTION_MESSAGE,null,medidasDeTemperatura, medidasDeTemperatura[0]).toString());
				if (temperaturaSeleccionada1 == medidasDeTemperatura[0] && temperaturaSeleccionada2 == medidasDeTemperatura[1]) {
					 resultado = ((cantidadAConvertir * 1.8) + 32); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else if (temperaturaSeleccionada1 == medidasDeTemperatura[0] && temperaturaSeleccionada2 == medidasDeTemperatura[2]) {
					 resultado = (cantidadAConvertir + 273.15); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else if (temperaturaSeleccionada1 == medidasDeTemperatura[1] && temperaturaSeleccionada2 == medidasDeTemperatura[0]) {
					 resultado = ((cantidadAConvertir - 32) / 1.8); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else if (temperaturaSeleccionada1 == medidasDeTemperatura[1] && temperaturaSeleccionada2 == medidasDeTemperatura[2]) {
					 resultado = ((5/9)*(cantidadAConvertir - 32) + 273.15); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else if (temperaturaSeleccionada1 == medidasDeTemperatura[2] && temperaturaSeleccionada2 == medidasDeTemperatura[0]) {
					 resultado = ((cantidadAConvertir - 32) / 1.8); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else if (temperaturaSeleccionada1 == medidasDeTemperatura[2] && temperaturaSeleccionada2 == medidasDeTemperatura[1]) {
					 resultado = (1.8 * (cantidadAConvertir - 273.15) + 32); 
					 JOptionPane.showMessageDialog(null, "Los "+cantidadAConvertir+" grados "+temperaturaSeleccionada1+" en grados "+temperaturaSeleccionada2+" son:"+resultado, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
	
				} else {
					JOptionPane.showMessageDialog(null, "Su temperatura en "+temperaturaSeleccionada1+" es: "+cantidadAConvertir+" "+temperaturaSeleccionada1, "Conversor de temperatura", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
			}
			continuar = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Cuadro de confirmación", JOptionPane.YES_NO_OPTION);
			if (continuar == 1) {
				JOptionPane.showMessageDialog(null, "Programa finalizado", "Muchas gracias por preferirnos", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
	}
}
