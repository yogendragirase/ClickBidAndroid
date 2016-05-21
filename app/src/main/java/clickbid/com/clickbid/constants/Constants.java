package clickbid.com.clickbid.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Constants {

	// public static String deviceToken = "";

	public static AlertDialog alertDialog;
	public static AlertDialog.Builder alertbox;
	public static AssetManager asm;
	public static Typeface tf;
	public static Context context;

	public static final String IS_LOGIN_IN = "isLogin";
	public static final String USER_FIRST_NAME = "firstName";
	public static final String USER_LAST_NAME = "lastName";
	public static final String USER_ID = "userId";
	public static final String USER_DOB = "userDob";
	public static final String USER_PASSWORD = "userPass";
	public static final String USER_GENDER = "gender";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_PHONE = "userPhone";
	public static final String USER_PHOTO = "userPhoto";
	public static final String MEMBER_STATUS = "status";
	public static final String MEMBER_ADD_DATE = "add_date";
	public static final String USER_ROLE = "role";
	public static final String OAUTH_PROVIDER = "oauth_provider";
	public static final String OAUTH_UID = "oauth_uid";

	public static final String USER_COUNTRY = "country";
	public static final String USER_STATE = "state";
	public static final String USER_CITY = "city";
	public static final String USER_ADDRESS = "address";

	public static final String VALUE_OFF = "off";
	public static final String VALUE_ON = "on";

	public static final String DEVICE_TOKEN = "DEVICE_TOKEN";

	public static final String PLEASE_WAIT = "Please wait......";
	
	/* Register Screen Contant */
	public static final String TITLE = "title";
	public static final String BODY = "body";
	public static final String TYPE = "type";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String ROLES = "roles";
	public static final String CLIENT_TERMS = "client_terms";
	public static final String CONTACT_NUMBER = "contact_num";
	public static final String ARTIST_TERMS = "artist_terms";
	public static final String ARTIST_PHOTO = "artist_photo";
	public static final String U_ID = "u_id";
	public static final String NAME = "name";
	public static final String IS_LOGIN = "is_login";
	public static final String USER_TYPE = "user_type";
	public static final String PROFILE_PIC = "profile_Pic";
	public static final String MAIL = "mail";
	public static final String FB_USER_ID = "fb_Id";
	public static final String TOTAL_CREDIT = "total_credit";
	public static final String INTRODUCTION = "introduction";
	public static final String PHONE_NUMBER = "phone_number";
	public static final String FB_MAIL = "fb_mail";
	public static final String FACEBOOK_PRO_PIC = "pro_pic";
	public static final String ARTIST_PHOTO_ONE = "artistONe";
	public static final String ARTIST_PHOTO_TWO = "artist_two";
	public static final String ARTIST_PHOTO_THREE = "Artist_three";
	public static final String ARTIST_NID = "artist_nid";
	public static final String REMINDER_DATE = "reminder_date";
	public static final String REMINDER_TIME = "reminder_time";

	public static int readChatValue = 0;
	public static String fb_name="fb_name";
	public static String fb_image_encode="encode_image";



	public static void showAlertDialog(final String title, String message,
									   final Context context, final boolean redirectToPreviousScreen) {
		if (alertDialog != null && alertDialog.isShowing()) {
		} else {
			alertbox = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
			alertbox.setMessage(message);
			alertbox.setTitle(title);
			alertbox.setCancelable(false);
			/*alertbox.setTitle(Gravity.CENTER);*/
			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					alertDialog.dismiss();
				}
			});
			alertDialog = alertbox.create();
			alertDialog.show();
		}
	alertDialog.setCanceledOnTouchOutside(true);
	}

	//EMail Validation
	public static boolean checkEmail(String email) {
		String expression = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*"
				+ "+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		Pattern emailPattern = Pattern.compile(expression);
		return emailPattern.matcher(email).matches();
	}


	public static void setfonttxt(TextView tx){
		tf = Typeface.createFromAsset(tx.getContext()
				.getAssets(), "font/open-sans.light.ttf");
		tx.setTypeface(tf);
	}

	public static void setfontet(EditText et){
		tf = Typeface.createFromAsset(et.getContext()
				.getAssets(), "font/open-sans.light.ttf");

		et.setTypeface(tf);
	}

	public static void setfontbtn(Button btn){
		tf = Typeface.createFromAsset(btn.getContext()
				.getAssets(), "font/open-sans.light.ttf");
		btn.setTypeface(tf);
	}


	public static void setfontck(CheckBox checkBox){
		tf = Typeface.createFromAsset(checkBox.getContext()
				.getAssets(), "font/open-sans.light.ttf");
		checkBox.setTypeface(tf);
	}
	public static void showLogoutDialog(final String title, String message,
									   final Context context, final boolean redirectToPreviousScreen) {
		if (alertDialog != null && alertDialog.isShowing()) {
		} else {
			alertbox = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
			alertbox.setMessage(message);
			alertbox.setTitle(title);
			alertbox.setCancelable(false);
			/*alertbox.setTitle(Gravity.CENTER);*/
			alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SessionManager sessionManager = new SessionManager(context);
					sessionManager.checkLogin();
					sessionManager.logoutUser();
				}
			});

			alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alertDialog = alertbox.create();
			alertDialog.show();
		}
		alertDialog.setCanceledOnTouchOutside(true);
	}
}
