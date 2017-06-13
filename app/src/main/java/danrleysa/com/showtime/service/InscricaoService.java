package danrleysa.com.showtime.service;

import danrleysa.com.showtime.model.Evento;
import danrleysa.com.showtime.model.Inscricao;
import danrleysa.com.showtime.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by danrley on 23/05/17.
 */

public interface InscricaoService {

    @GET("inscricao/{usuario}/{evento}")
    Call<Inscricao> verificarInscricao(@Path("usuario") Usuario usuario, @Path("evento") Evento evento);

    @POST("inscricao/{usuario}/{evento}")
    Call<String> merge(@Path("usuario") Long idUsuario, @Path("evento") Long idEvento);
}
