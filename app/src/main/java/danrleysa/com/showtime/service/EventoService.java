package danrleysa.com.showtime.service;

import danrleysa.com.showtime.model.Evento;
import danrleysa.com.showtime.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by danrley on 23/05/17.
 */

public interface EventoService {

    @POST("evento")
    Call<Void> merge(@Body Evento evento);
}
