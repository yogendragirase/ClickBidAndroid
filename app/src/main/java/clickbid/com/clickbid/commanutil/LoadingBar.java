package clickbid.com.clickbid.commanutil;



import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

public class LoadingBar {

	Context context;
	ProgressDialog progDialog;

	public LoadingBar(Context context) {
		super();
		this.context = context;
		// progDialog = new Dialog(context);
		// progDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// progDialog.getWindow().setBackgroundDrawable(
		// new ColorDrawable(android.graphics.Color.TRANSPARENT));
		// progDialog.setContentView(R.layout.loader_dialog);
		// progDialog.setCancelable(false);

		progDialog = new ProgressDialog(context);
		// progDialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
		// here we set layout of progress dialog

		progDialog.setCancelable(false);
	}

	public void show(String msg) {
		if (progDialog != null) {
			// TextView text = (TextView) progDialog.findViewById(R.id.msg);
			// text.setText(msg);
			// text.setTypeface(Typeface.createFromAsset(context.getAssets(),
			// "eurof55.ttf"));
			progDialog.setMessage(msg);
			progDialog.show();
		}
	}

	public void dismiss() {
		if (progDialog != null && progDialog.isShowing()) {
			progDialog.dismiss();
		}
	}

}
