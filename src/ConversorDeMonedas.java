import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversorDeMonedas {
    private String monedaParaCambio;
    private String conversionDeMoneda;
    private double total;
    private double nuevoTotal;
    private String localDate;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        public ConversorDeMonedas(String monedaParaCambio, String conversionDeMoneda, Double total, Double nuevoTotal) {
            this.monedaParaCambio = monedaParaCambio;
            this.conversionDeMoneda = conversionDeMoneda;
            this.total = total;
            this.nuevoTotal = nuevoTotal;
            localDate = LocalDateTime.now().format(dtf);
        }

        @Override
        public String toString() {
            return localDate + "\t\t" + String.format("%.2f", total) + " [" + monedaParaCambio + "] " +
                    "--> " + String.format("%.2f",nuevoTotal) + " [" + conversionDeMoneda + "]";
        }

}
