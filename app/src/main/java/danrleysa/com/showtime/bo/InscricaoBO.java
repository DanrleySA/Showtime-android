package danrleysa.com.showtime.bo;

import danrleysa.com.showtime.model.Evento;
import danrleysa.com.showtime.model.Inscricao;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Danrley on 13/06/2017.
 */

public class InscricaoBO {

    private InscricaoBO() {

    }

    public static InscricaoBO getInstance() {
        return new InscricaoBO();
    }

    /*public Boolean isInscrito(Usuario usuario, Evento evento){
        Call call = new RetrofitInicializador().getInscricaoService().verificarInscricao(usuario, evento);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    if (response.errorBody() == null){
                        if (response.body() != null){

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        return
    }*/
}
