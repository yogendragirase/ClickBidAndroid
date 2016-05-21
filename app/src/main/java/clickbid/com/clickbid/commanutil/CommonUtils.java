package clickbid.com.clickbid.commanutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class CommonUtils {

	// It's for showing progress dialog messages
	public static final String PLEASE_WAIT = "Please Wait...";
	public static final String LOADING = "Loading...";

	// public static final String NETWORK_ERROR = "No Connection";

	// It's dialog for show error/success message with positive button
	public static void showAlertDialog(final String title, String message,
			final Context context) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
		alertbox.setMessage(message);
		alertbox.setTitle(title);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	static MediaPlayer mediaPlayer;

	public static void playSound(Context context, int soundId) {

		mediaPlayer = MediaPlayer.create(context, soundId);

		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mediaPlayer.start();

	}
	public static void playSoundWithLoop(Context context, int soundId) {

		mediaPlayer = MediaPlayer.create(context, soundId);

		try {
			mediaPlayer.setLooping(true);
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mediaPlayer.start();
		

	}

	public static void stopSound(Context context) {

		try {
			if (mediaPlayer.isPlaying()) {
				// changes by me
				if (mediaPlayer != null) {
					mediaPlayer.stop();
					mediaPlayer.reset();
					mediaPlayer.release();
					// mp = null;
					// mp = new MediaPlayer();
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void hide_keyboard_from(Context context, View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void vibrateCall(Context context) {

		Vibrator vibe = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(200);

	}

	// It's dialog for show message with positive button

	public static void showAlert(String message, final Context context) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
		alertbox.setMessage(message);
		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	// It's toast for show error/success message
	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

	}

	// This is Strictmode threadpolicy handeling code
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void setThreadPolicy() {
		try {
			if (Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Check valid email address
	public static boolean isEmailValid(String email) {
		String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
				+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
				+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
				+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches())
			return true;
		else
			return false;
	}

	public static String dobFormat(String dateStr) {
		if (dateStr.equals("") || dateStr == null || dateStr.equals("null")
				|| dateStr.equals("0000-00-00")) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());

		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat formatChange = new SimpleDateFormat("dd MMM yyyy",
				Locale.getDefault());
		String datef = formatChange.format(date);

		return datef;

	}

	public static Bitmap decodeFile(String path) {
		int rotate = 0;
		File f = new File(path);
		try {

			ExifInterface exif = new ExifInterface(f.getAbsolutePath());
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_270:
				rotate = 270;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				rotate = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				rotate = 90;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);

		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 300;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f),
					null, o2);
			Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
					bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			return rotatedBitmap;
		} catch (FileNotFoundException e) {
		}
		return null;
	}

}
