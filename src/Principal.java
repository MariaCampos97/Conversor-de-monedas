import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Bienvenidos al conversor de divisas");

        List<ConversorDeMonedas> conversorDeMonedas = new ArrayList<>();

        int exit = 0;
        while (exit != 1) {
            System.out.println("\n*******************************************");
            System.out.println("""
                    Seleccione un cambio de la lista siguiente:
                    1) USD =>> ARS
                    2) ARS =>> USD
                    3) USD =>> BRL
                    4) BRL =>> USD
                    5) USD =>> COP
                    6) COP =>> USD
                    7) Exit
                    8) Show exchanges log

                    USD: United States Dollar
                    ARS: Argentine Peso
                    BRL: Brazilian Real
                    COP: Colombian Peso
                    """);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Introducir opción de cambio de moneda: ");

            try {
                int exchangeOption = Integer.parseInt(scanner.nextLine());

                String monedaParaCambio = null;
                String conversionDeMoneda = null;
                switch (exchangeOption) {
                    case 1:
                        monedaParaCambio = "USD";
                        conversionDeMoneda = "ARS";
                        break;
                    case 2:
                        monedaParaCambio = "ARS";
                        conversionDeMoneda = "USD";
                        break;
                    case 3:
                        monedaParaCambio = "USD";
                        conversionDeMoneda = "BRL";
                        break;
                    case 4:
                        monedaParaCambio = "BRL";
                        conversionDeMoneda = "USD";
                        break;
                    case 5:
                        monedaParaCambio = "USD";
                        conversionDeMoneda = "COP";
                        break;
                    case 6:
                        monedaParaCambio = "COP";
                        conversionDeMoneda = "USD";
                        break;
                    case 7:
                        exit = 1;
                        break;
                    default:
                        System.out.println("¡La opción seleccionada (" + exchangeOption + ") no es válida!.");
                        break;
                }

                if (exchangeOption >= 1 && exchangeOption <= 6) {
                    ApiConnexion solicitar = new ApiConnexion();
                    IntercambioDeDivisa exchangeRate = solicitar.intercambio(monedaParaCambio, conversionDeMoneda);

                    if (exchangeRate != null) {
                        System.out.println("El tipo de cambio actual es " + exchangeRate.conversion_rate());

                        System.out.println("¿Cuánto " + monedaParaCambio + " quiere convertir? ");
                        Double total = Double.valueOf(scanner.nextLine());

                        double nuevoTotal = total * exchangeRate.conversion_rate();

                        System.out.println("El cambio de " +
                                total +
                                " [" + monedaParaCambio + "]" +
                                " es " +
                                nuevoTotal +
                                " [" + conversionDeMoneda + "]");

                        ConversorDeMonedas conversorDeMoneda = new ConversorDeMonedas(monedaParaCambio, conversionDeMoneda, total, nuevoTotal);
                        conversorDeMonedas.add(conversorDeMoneda);
                    } else {
                        System.out.println("Error al obtener la tasa de cambio.");
                    }
                } else if (exchangeOption == 7) {
                    System.out.println("Gracias por utilizar el conversor de divisas.");
                } else if (exchangeOption == 8) {
                    conversorDeMonedas.forEach(System.out::println);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("La opción seleccionada " + e.getMessage().toLowerCase() + ", ¡no es válida!");
            }
        }
    }
}