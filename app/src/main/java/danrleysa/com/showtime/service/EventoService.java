package danrleysa.com.showtime.service;

import java.util.List;

import danrleysa.com.showtime.model.Evento;
import danrleysa.com.showtime.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by danrley on 23/05/17.
 */

public interface EventoService {

    @POST("evento")
    Call<Void> merge(@Body Evento evento);

    @GET("evento")
    Call<List<Evento>> listarEventos();

    @GET("evento")
    Call<List<Evento>> listarEventosInscritos(@Path("usuario") Long idUsuario);
}
