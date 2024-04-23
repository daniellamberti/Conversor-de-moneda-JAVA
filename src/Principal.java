import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;

public class Principal {

    public static void printMenu() {
        System.out.println(
                        "1) Dolar =>> Peso argentino\n" +
                        "2) Peso argentino =>> Dolar\n" +
                        "3) Dolar =>> Real brasileño\n" +
                        "4) Real brasileño =>> Dolar\n" +
                        "5) Dolar =>> Peso colombiano\n" +
                        "6) Peso colombiano =>> Dolar\n" +
                        "7) Salir\n"
        );
    }

    public static void main(String[] args) {

        ConsultaCurrency consulta = new ConsultaCurrency();
        String baseCurrency = "USD";
        String datosAPI = consulta.buscarCurrencyJson("USD");

        Gson gson = new Gson();
        String jsonResponse = consulta.buscarCurrencyJson("USD");
        ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

        Map<String, Double> conversionRates = response.getConversionRates();

        double arsRate = conversionRates.get("ARS");
        double brlRate = conversionRates.get("BRL");
        double copRate = conversionRates.get("COP");

        Scanner userInput = new Scanner(System.in);
        System.out.println();
        System.out.println("************************************");
        System.out.println();
        System.out.println();
        printMenu();

        System.out.println("Elija una opción válida");
        int opcion = userInput.nextInt();

        if (opcion == 7) {
            System.out.println("Ha decidido salir del programa, gracias.");
        } else {

            while ((opcion < 1) || (opcion > 7)) {
                System.out.println("Debe seleccionar alguna opción entre números 1 y 7");
                opcion = userInput.nextInt();
            }

            do {
                switch (opcion) {
                    case 1:
                        multiplicaMethod(opcion, arsRate, "[USD]", "[ARS]");
                        break;

                    case 2:
                        divideMethod(opcion, arsRate, "[ARS]", "[USD]");
                        break;

                    case 3:
                        multiplicaMethod(opcion, brlRate, "[USD]", "[BRL]");
                        break;

                    case 4:
                        divideMethod(opcion, brlRate, "[BRL]", "[USD]");
                        break;

                    case 5:
                        multiplicaMethod(opcion, copRate, "[USD]", "[COP]");
                        break;

                    case 6:
                        divideMethod(opcion, copRate, "[COP]", "[USD]");
                        break;
                }

                System.out.println("Desea elegir otra opción?");
                System.out.println();
                printMenu();
                opcion = userInput.nextInt();

                while ((opcion < 1) || (opcion > 7)) {
                    System.out.println("Debe seleccionar alguna opción entre números 1 y 7");
                    printMenu();
                    opcion = userInput.nextInt();
                }

            } while (opcion != 7);

            System.out.println("Ha decidido salir del programa, gracias.");
            userInput.close();
        }
    }

    private static void multiplicaMethod(int opcion, double secondRate, String fRate, String sRate) {
        System.out.println("Ingrese la cantidad a convertir");
        Scanner cantidad = new Scanner(System.in);
        double monto = cantidad.nextDouble();
        System.out.println("El valor de " + monto + " " + fRate + " corresponde al valor final de: " + (monto * secondRate) + " " + sRate);
        System.out.println();
    }

    private static void divideMethod(int opcion, double secondRate, String fRate, String sRate) {
        System.out.println("Ingrese la cantidad a convertir");
        Scanner cantidad = new Scanner(System.in);
        double monto = cantidad.nextDouble();
        System.out.println("El valor de " + monto + " " + fRate + " corresponde al valor final de: " + (monto / secondRate) + " " + sRate);
        System.out.println();
    }
}