
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ConsultarApi consulta = new ConsultarApi();
        String moneda_base = "";
        String moneda_destino = "";
        int opcion = 0;
        List<String> listaResultado = new ArrayList<>();

        String menu = """
                
                ***************************************************
                Sea Bienvenido al Conversor de Monedas!
                
                1. Dólar ==> Peso Argentino
                2. Peso Argentino ==> Dólar
                3. Dólar ==> Sol Peruano
                4. Sol Peruano ==> Dólar
                5. Dólar ==> Peso Colombiano
                6. Peso Colombiano ==> Dólar
                7. Salir
                **************************************************
                """;

        do{
            System.out.println(menu);
            int error = 0;
            do {
                try{
                    System.out.println("Eliga una opcion valida: ");
                    opcion = Integer.valueOf(entrada.nextLine());
                    error = 0;
                    switch (opcion) {
                        case 1:
                            moneda_base = "USD";
                            moneda_destino = "ARS";
                            break;
                        case 2:
                            moneda_base = "ARS";
                            moneda_destino = "USD";
                            break;
                        case 3:
                            moneda_base = "USD";
                            moneda_destino = "PEN";
                            break;
                        case 4:
                            moneda_base = "PEN";
                            moneda_destino = "USD";
                            break;
                        case 5:
                            moneda_base = "USD";
                            moneda_destino = "COP";
                            break;
                        case 6:
                            moneda_base = "COP";
                            moneda_destino = "USD";
                            break;
                        case 7:
                            break;
                        default:
                            error = 1;
                            System.out.println("Opcion ingresada inválida, ingrese alguna opcion del menu!");
                    }
                }catch (NumberFormatException e){
                    error=1;
                    System.out.println("Ingrese solo valores numéricos!");
                }
            }while(error==1);

            if(opcion!=7){

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

        }while(opcion!=7);

        if(listaResultado.size()>0){
            System.out.println("Conversiones Realizadas: ");
            for (String result: listaResultado){
                System.out.println(result);
            }
        }
    }
}
