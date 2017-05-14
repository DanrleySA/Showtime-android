package danrleysa.com.showtime.controller.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.controller.Fragment.LoginFragment;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_container_login, new LoginFragment()).commit();
        }
    }


}
