package danrleysa.com.showtime.util;

import android.text.TextUtils;

/**
 * Created by Danrley on 22/04/2017.
 */

public class Utils {

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
