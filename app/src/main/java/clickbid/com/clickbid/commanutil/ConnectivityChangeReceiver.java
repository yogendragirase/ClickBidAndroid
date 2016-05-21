package clickbid.com.clickbid.commanutil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.widget.Toast;

public class ConnectivityChangeReceiver
               extends BroadcastReceiver {

   public static boolean status;

@Override
   public void onReceive(Context context, Intent intent) {
	 status = NetworkUtil.getConnectivityStatusString(context);
	   
//       Toast.makeText(context, status, Toast.LENGTH_LONG).show();
   }


}