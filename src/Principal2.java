import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal2 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ConsultarApi consulta = new ConsultarApi();
        int opcion = 0;
        String moneda_base = "";
        String moneda_destino = "";
        int error = 0;
        List<String> listaResultado = new ArrayList<>();

        String menu = """
                ***************************************************
                Sea Bienvenido al Conversor de Monedas!
                   Código  Nombre                País
                1. ARS     Peso Argentino        Argentina 
                2. BRL     Real Brasileño        Brazil
                3. COP     Peso Colombiano       Colombia
                4. EUR     Euro                  Unión Europea
                5. JPY     Yen Japonés           Japón
                6. PEN     Sol Peruano           Perú
                7. USD     Dólar estadounidense  Estados Unidos
                
                8. Salir
                ***************************************************
                      Convertir de Moneda 1 a Moneda 2
                ***************************************************
                """;

        do{
            System.out.println(menu);
            do{
                try{
                    error = 0;
                    System.out.println("Ingrese la opcion de moneda 1 : ");
                    opcion = Integer.valueOf(entrada.nextLine());
                    moneda_base = validarOpcion(opcion);
                    if(moneda_base.equalsIgnoreCase("opcion invalida")){
                        error = 1;
                        System.out.println("Ingrese una opcion valida!");
                    }
                }catch (NumberFormatException e){
                    System.out.println("El monto ingresado tiene que ser un valor numérico!");
                    error = 1;
                }

            }while(error==1);

            if(opcion!=8){
                do{
                    try{
                        error = 0;
                        System.out.println("Ingrese la opcion de moneda 2 : ");
                        opcion = Integer.valueOf(entrada.nextLine());
                        moneda_destino = validarOpcion(opcion);
                        if(moneda_destino.equalsIgnoreCase("opcion invalida")){
                            error = 1;
                            System.out.println("Ingrese una opcion valida!");
                        }
                    }catch (NumberFormatException e){
                        System.out.println("El monto ingresado tiene que ser un valor numérico!");
                        error = 1;
                    }
                }while(error==1);

                if(opcion!=8){

                    do{
                        error = 0;
                        try{
                            System.out.println("Ingrese el monto a convertir: ");
                            double monto = Double.parseDouble(entrada.nextLine());

                            Conversion conversion = consulta.convertirMoneda(moneda_base,moneda_destino);
                            //System.out.println(conversion);

                            double conversion_rate = conversion.conversion_rate();
                            double monto_convertir = monto*conversion_rate;

                            String resultado = "El valor "+monto+" "+conversion.base_code()+" corresponde al valor final de =>>> "+
                                    monto_convertir+" "+conversion.target_code();
                            System.out.println(resultado);
                            listaResultado.add(resultado);
                        }catch (NumberFormatException e){
                            error = 1;
                            System.out.println("El monto ingresado tiene que ser un valor numérico!");
                        }
                    }while(error==1);
                }else{
                    System.out.println("El usuario ha salido del programa!");
                }

            }else {
                System.out.println("El usuario ha salido del programa!");
            }

        }while(opcion!=8);

        if(listaResultado.size()>0){
            System.out.println("Conversiones Realizadas: ");
            for (String result: listaResultado){
                System.out.println(result);
            }
        }

    }

    public static String validarOpcion(int moneda){

        String resultado = "";

        switch (moneda){
            case 1:
                resultado = "ARS";
                break;
            case 2:
                resultado = "BRL";
                break;
            case 3:
                resultado = "COP";
                break;
            case 4:
                resultado = "EUR";
                break;
            case 5:
                resultado = "JPY";
                break;
            case 6:
                resultado = "PEN";
                break;
            case 7:
                resultado = "USD";
                break;
            case 8:
                resultado = "salir";
                break;

            default:
                resultado = "Opcion invalida";
        }
        return resultado;
    }

}
