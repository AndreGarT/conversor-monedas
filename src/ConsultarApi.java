import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarApi {

    //API KEY : 4ef000d783cf7dec6c513de3
    //EJEMPLO : https://v6.exchangerate-api.com/v6/4ef000d783cf7dec6c513de3/latest/USD

    public Conversion convertirMoneda(String moneda_base, String moneda_destino){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4ef000d783cf7dec6c513de3/pair/"+moneda_base+"/"+moneda_destino);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            return new Gson().fromJson(json,Conversion.class);

        }catch (Exception e){
            throw new RuntimeException("Pelicula No encontrada");
        }
    }


}
