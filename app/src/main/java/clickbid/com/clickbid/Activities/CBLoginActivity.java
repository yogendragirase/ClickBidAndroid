package clickbid.com.clickbid.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;
import clickbid.com.clickbid.constants.SessionManager;
import clickbid.com.clickbid.constants.WebServiceConstants;
import clickbid.com.clickbid.servercommunication.NetworkAvailablity;
import clickbid.com.clickbid.servercommunication.ServiceHandler;

public class CBLoginActivity extends AppCompatActivity {


    private TextView txt_lgoinheading;
    private EditText et_eventid, et_eventpassword;
    private Button btn_login;
    private String et_email, et_password;
    private SessionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.activity_cb__login);
        initilize();

        manager = new SessionManager(getApplicationContext());
        manager.checkLogin();

        if (manager.isLoggedIn()) {
            Intent intent1 = new Intent(getApplicationContext(), CBLandingPage.class);
            startActivity(intent1);
        } else {
            // close this activity
        }

        onclick();
    }

    //Intilizing widget
    private void initilize() {
        txt_lgoinheading = (TextView) findViewById(R.id.txt_bltxt);
        et_eventid = (EditText) findViewById(R.id.et_eventid);
        et_eventpassword = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        Constants.setfonttxt(txt_lgoinheading);
        Constants.setfontet(et_eventid);
        Constants.setfontet(et_eventpassword);
        Constants.setfontbtn(btn_login);
    }

    // Click on widget Method
    private void onclick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdata();
                if (NetworkAvailablity.chkStatus(CBLoginActivity.this)) {
                    if (validation()) {
                        new Login_Service().execute();
                    }
                } else {
                    Constants.showAlertDialog("Network Error", "NO Internet Connection Found", CBLoginActivity.this, false);
                }
            }
        });
    }


    public void getdata() {
        et_email = et_eventid.getText().toString();
        et_password = et_eventpassword.getText().toString();
    }


    public boolean validation() {
        if (et_email.length() == 0 || et_email == null
                || et_email.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Event ID Required", this, false);

            return false;
        } else if (et_password.length() == 0 || et_password == null || et_password.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Password is required", this, false);
            return false;
        }
        return true;
    }


    public class Login_Service extends AsyncTask<String, String, String> {
        String organizationname;
        public ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CBLoginActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("value of id :==" + et_email);
            ServiceHandler sh = new ServiceHandler();
            manager = new SessionManager(CBLoginActivity.this);
            // Making a request to url and getting response
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            String jsonStr = sh.makeServiceCall(WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_LOGIN) + et_email, ServiceHandler.GET, nameValuePairs, et_password);
            System.out.println("Response :------->" + jsonStr);

            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONObject dataobject = jsonObject.getJSONObject("data");
                organizationname = dataobject.getString("users_organization_name");
                System.out.println("Organization name :--" + organizationname);

                if (et_email.trim().length() > 0 && et_password.trim().length() > 0) {

                    if (!organizationname.equals(null)) {
                        System.out.println("In iF");
                        manager.createLoginSession(organizationname, et_email);
                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), CBLandingPage.class);
                        startActivity(i);
                        finish();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
}
