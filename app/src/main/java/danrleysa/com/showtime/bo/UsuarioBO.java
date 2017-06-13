package danrleysa.com.showtime.bo;

import android.widget.EditText;

import java.io.Serializable;

import danrleysa.com.showtime.util.Utils;

/**
 * Created by Danrley on 10/06/2017.
 */

public class UsuarioBO implements Serializable {

    private UsuarioBO() {

    }

    public static UsuarioBO getInstance() {
        return new UsuarioBO();
    }

    public String validaCampos(EditText nome, EditText email, EditText senha, EditText senhaConfirm) {
        String nomeTxt = nome.getText().toString();
        String emailTxt = email.getText().toString();
        String senhaTxt = senha.getText().toString();
        String senhaConfirmTxt = senhaConfirm.getText().toString();

        if (nomeTxt.replace(" ", "").isEmpty() || emailTxt.replace(" ", "").isEmpty()
                || senhaTxt.replace(" ", "").isEmpty() || senhaConfirmTxt.replace(" ", "").isEmpty()) {

            return "Preencha todos os campos";
        } else {
            if (!Utils.isValidEmail(emailTxt)) {
                return "Email inválido";
            } else if (senhaTxt.length() < 6) {
                return "A senha deve ter pelo menos 6 caracteres";
            } else if (!senhaTxt.equals(senhaConfirmTxt)) {
                return "Senhas não correspondem";
            } else {
                return "";
            }
        }
    }

    public String validaCampos(EditText email, EditText senha) {
        String emailTxt = email.getText().toString();
        String senhaTxt = senha.getText().toString();

        if (emailTxt.isEmpty() || senhaTxt.isEmpty()) {
            return "Preencha todos os campos!";
        } else {
            if (!Utils.isValidEmail(emailTxt)) {
                return "Email inválido";
            } else if (senhaTxt.length() < 6) {
                return "A senha contém no mínimo 6 caracteres";
            } else {
                return "";
            }
        }
    }


}
