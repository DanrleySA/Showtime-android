package danrleysa.com.showtime.service;

import java.util.List;

import danrleysa.com.showtime.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by danrley on 16/05/17.
 */

public interface UsuarioService {

    @GET("usuario/{id}")
    Call<Usuario> getById(@Path("id") Long id);

    @POST("usuario")
    Call<String> merge(@Body Usuario usuario);

    @GET("usuario/{email}/{senha}")
    Call<Usuario> getByPorEmailAndSenha(@Path("email") String email, @Path("senha") String senha);

    @GET("usuario/idEvento/{idEvento}")
    Call<List<Usuario>> listarInscritos(@Path("idEvento") Long idEvento);
}

