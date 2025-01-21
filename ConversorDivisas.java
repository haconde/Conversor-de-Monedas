import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorDivisas {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/1b1b0d2155e95befef082598/latest/";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    convertirCOPToEUR(scanner);
                    break;
                case 2:
                    convertirCOPToUSD(scanner);
                    break;
                case 3:
                    convertirUSDToCOP(scanner);
                    break;
                case 4:
                    convertirUSDToEUR(scanner);
                    break;
                case 5:
                    convertirEURToCOP(scanner);
                    break;
                case 6:
                    convertirEURToUSD(scanner);
                    break;
                case 7:
                    System.out.println("¡Adiós!");
                    System.exit(0);
                default:
                    System.out.println("No seleccionaste una opción disponible. Prueba un número del 1 al 7.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n✧･ﾟ: *✧･ﾟ:* ✨ *:･ﾟ✧*:･ﾟ✧\n");
        System.out.println("Hola. Aquí puedes convertir las siguientes monedas:");
        System.out.println("\n✧･ﾟ: *✧･ﾟ:* ✨ *:･ﾟ✧*:･ﾟ✧\n");
        System.out.println("1. Convertir COP a EUR");
        System.out.println("2. Convertir COP a USD");
        System.out.println("3. Convertir USD a COP");
        System.out.println("4. Convertir USD a EUR");
        System.out.println("5. Convertir EUR a COP");
        System.out.println("6. Convertir EUR a USD");
        System.out.println("7. Cerrar");
        System.out.println("\n✧･ﾟ: *✧･ﾟ:* ✨ *:･ﾟ✧*:･ﾟ✧\n");
        System.out.print("Digita el número de la opción de conversión de moneda que desees: ");
    }

    private static void convertirCOPToEUR(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en COP: ");
        double monto1 = scanner.nextDouble();

        String url = BASE_URL + "COP";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioEUR = jsonobj.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
        double monto2 = monto1 * tipoCambioEUR;

        System.out.println("El valor es " + monto2 + " EUR");
    }

    private static void convertirCOPToUSD(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en COP: ");
        double monto2 = scanner.nextDouble();

        String url = BASE_URL + "COP";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioUSD = jsonobj.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
        double monto1 = monto2 * tipoCambioUSD;

        System.out.println("El valor es " + monto1 + " USD");
    }


    private static void convertirUSDToCOP(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en USD: ");
        double monto3 = scanner.nextDouble();

        String url = BASE_URL + "USD";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioGBP = jsonobj.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
        double monto4 = monto3 * tipoCambioGBP;

        System.out.println("El valor es " + monto4 + " COP");
    }

    private static void convertirUSDToEUR(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en USD: ");
        double monto4 = scanner.nextDouble();

        String url = BASE_URL + "USD";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioJPY = jsonobj.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
        double monto3 = monto4 * tipoCambioJPY;

        System.out.println("El valor es " + monto3 + " EUR");
    }

    private static void convertirEURToCOP(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en EUR: ");
        double monto5 = scanner.nextDouble();

        String url = BASE_URL + "EUR";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioMXN = jsonobj.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
        double monto6 = monto5 * tipoCambioMXN;

        System.out.println("El valor es " + monto6 + " COP");
    }

    private static void convertirEURToUSD(Scanner scanner) throws IOException {
        System.out.print("Ingresa el valor en EUR: ");
        double monto6 = scanner.nextDouble();

        String url = BASE_URL + "EUR";
        JsonObject jsonobj = makeRequest(url);

        double tipoCambioCLP = jsonobj.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
        double monto5 = monto6 * tipoCambioCLP;

        System.out.println("El valor es " + monto5 + " USD");
    }

    private static JsonObject makeRequest(String url_str) throws IOException {
        // Setting URL
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        String req_result = jsonobj.get("result").getAsString();
        if (!req_result.equals("success")) {
            throw new IOException("Error: API request unsuccessful");
        }

        return jsonobj;
    }
}
