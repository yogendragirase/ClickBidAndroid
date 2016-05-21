package clickbid.com.clickbid.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;


public abstract class BaseActivity extends Activity {

    /**
     * pageTitle is passed between activities to
     * during interaction . It holds the value of
     * tag passed during a button click event
     */
    protected String pageTitle;

    protected String error;
    protected View v;
    protected String errorMessage;
    protected String errorCode;
    protected String mobile;
    protected String compareString;
    //private Document outputDOM;


    public void showDialog(String title, String alert, final boolean finishActivity) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(alert);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (finishActivity == true)
                    finish();
            }
        });
        alertDialog.show();

    }

    public void showDialogs(int title, int alert, final boolean finishActivity) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(alert);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (finishActivity == true)
                    finish();
            }
        });
        alertDialog.show();

    }

    public void showDialogToOpenActivity(String title, String alert, boolean finishActivity, final Intent intent) {
        if (((Activity) this).isFinishing() == false) {
            final boolean finish = finishActivity;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(title);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(alert);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(intent, 0);

                    if (finish == true)
                        finish();
                }
            });
            alertDialog.show();
        }
    }


    public class MyBackgroundTask extends AsyncTask<String, Object, String> {

        protected ProgressDialog waitSpinner;
        Context context;

        public MyBackgroundTask(Context context) {
            waitSpinner = new ProgressDialog(context);
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // initialize views like progress dialog.
            waitSpinner.setCancelable(true);
            waitSpinner.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        public void onCancel(DialogInterface dialog) {
                            finish();
                        }
                    });

            BaseActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    waitSpinner = ProgressDialog.show(context, "Please Wait ...",
                            "Loading ...", true);
                }


            });
        }

        @Override
        protected String doInBackground( String... json) {
            compareString=json[0];
            if(compareString.equalsIgnoreCase("firstTask")){
                getInBackgroundFirst();}
            else if(compareString.equalsIgnoreCase("secondTask")){
                getInBackgroundSecond();
            }
            else if(compareString.equalsIgnoreCase("thirdTask")){
                getInBackgroundThird();
            }
            else if(compareString.equalsIgnoreCase("forthTask")){
                getInBackgroundForth();
            }
            else if(compareString.equalsIgnoreCase("fifthTask")){
                getInBackgroundFifth();
            }
            else if(compareString.equalsIgnoreCase("sixthTask")){
                getInBackgroundSixth();
            }
            else if(compareString.equalsIgnoreCase("seventhTask")){
                getInBackgroundSeventh();
            }
            else if(compareString.equalsIgnoreCase("eighthTask")){
                getInBackgroundEigth();
            }
            return null;
        }

        @Override
        public void onPostExecute(String success) {
            BaseActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!((Activity) BaseActivity.this).isFinishing())
                        waitSpinner.cancel();
                    if (error != null && !error.equalsIgnoreCase("")) {
                        showDialog("", error, false);
                        error = "";
                    } else
                        try {
                            if(compareString.equalsIgnoreCase("firstTask")){
                                loadUIFirst();}
                            else if(compareString.equalsIgnoreCase("secondTask")){
                                loadUISecond();
                            }
                            else if(compareString.equalsIgnoreCase("thirdTask")){
                                loadUIThird();
                            }
                            else if(compareString.equalsIgnoreCase("forthTask")){
                                loadUIForth();
                            }
                            else if(compareString.equalsIgnoreCase("fifthTask")){
                                loadUIFifth();
                            }
                            else if(compareString.equalsIgnoreCase("sixthTask")){
                                loadUISixth();
                            }
                            else if(compareString.equalsIgnoreCase("seventhTask")){
                                loadUISeven();
                            }
                            else if(compareString.equalsIgnoreCase("eightTask")){
                                loadUIEigth();
                            }
                        } catch (Exception e) {
                            showDialog("", "Oops! Something went wrong", false);
                            e.printStackTrace();
                        }

                }
            });

        }


        /**
         * refactor
         * it use showDialogToOpenActivity method in place of it
         *
         * @param msg
         * @param v1
         */
        public void showMsg(String msg, View v1) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
            dialog.setTitle("");
            dialog.setMessage(msg);
            final View v = v1;
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel();
                }
            });
            dialog.show();

        }

        public void showConfermationMsg(String msg, final Intent intent, final boolean isFinish) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
            dialog.setTitle("");
            dialog.setMessage(msg);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    startActivityForResult(intent, 0);
                    if (isFinish)
                        finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel();
                }
            });
            dialog.show();
        }

    }

    protected void getInBackgroundSecond() {
    }

    protected void getInBackgroundFirst() {
    }
    protected void getInBackgroundThird() {
    }
    protected void getInBackgroundForth() {
    }
    protected void getInBackgroundFifth() {
    }
    protected void getInBackgroundSixth() {
    }
    protected void getInBackgroundSeventh() {
    }
    protected void getInBackgroundEigth() {
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    protected void loadUIFirst() throws Exception {
        // TODO Auto-generated method stub

    }  protected void loadUISecond() throws Exception {
        // TODO Auto-generated method stub

    }  protected void loadUIThird() throws Exception {
        // TODO Auto-generated method stub

    }  protected void loadUIForth() throws Exception {
        // TODO Auto-generated method stub

    }  protected void loadUIFifth() throws Exception {
        // TODO Auto-generated method stub

    }protected void loadUISixth() throws Exception {
        // TODO Auto-generated method stub

    }
    protected void loadUISeven() throws Exception {
        // TODO Auto-generated method stub

    }   protected void loadUIEigth() throws Exception {
        // TODO Auto-generated method stub

    }

}
