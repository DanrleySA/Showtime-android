package danrleysa.com.showtime.retrofit;

import danrleysa.com.showtime.service.UsuarioService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danrley on 16/05/17.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:8080/showtimews/rest/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public UsuarioService getUsuarioService() {
        return retrofit.create(UsuarioService.class);
    }
}
