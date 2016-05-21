package clickbid.com.clickbid.servercommunication;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkAvailablity {
	public static boolean chkStatus(Context context) {
		// TODO Auto-generated method stub
		final ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr.getActiveNetworkInfo() != null
				&& connMgr.getActiveNetworkInfo().isAvailable()
				&& connMgr.getActiveNetworkInfo().isConnected()) {

			return true;
		} else {

			return false;
		}
	}

	public NetworkAvailablity() {
		// TODO Auto-generated constructor stub
		super();
	}
}
