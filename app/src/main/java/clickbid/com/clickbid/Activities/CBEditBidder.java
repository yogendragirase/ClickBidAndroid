package clickbid.com.clickbid.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;
import clickbid.com.clickbid.constants.SessionManager;
import clickbid.com.clickbid.constants.WebServiceConstants;
import clickbid.com.clickbid.servercommunication.NetworkAvailablity;
import clickbid.com.clickbid.servercommunication.ServiceHandler;

/**
 * Created by Syscraft on 10-May-16.
 */
public class CBEditBidder extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    ActionBar actionBar;
    Toolbar toolbar;
    private TextView etb_headerhintext1, etb_headerhinttext2, etb_companytxt, etb_nametxt, etb_firstlasttxt, etb_phonetxt, etb_emailtxt, etb_tablenametxt, etb_cardnumbertxt, etb_cvvtxt, etb_cvvtxthint, etb_expirationdatetxt;
    private EditText etb_etfirstname, etb_etlastname, etb_etcompany, etb_etphoneno, etb_etemail, etb_ettable, etb_etcardnumber, etb_etcvvnumber, etb_etexpirationdate, etb_etpass, etb_cnfetpass;
    private Button etb_swipebtn, etb_addbutton;
    private String strfname, strlname, strphoneno = null, stremail, strtableno, strcardno, strcvvno, strexpirydate, id, strbiddernumber, strcompany, usertype, strpass;
    private TextView header;
    private String number;
    private StringBuilder str;
    private CheckBox etb_cksendwelcometext, etb_ckrequirepass;
    private LinearLayout ll_etb_pass, ll_etb_cnfpass;
    private String usereventid, regid, passcheck, passwelcome;

    HashMap<String, String> getdetails = new HashMap<String, String>();
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editbidderlayout);
        sessionManager = new SessionManager(CBEditBidder.this);
        getdetails = sessionManager.getUserDetails();
        usereventid = getdetails.get(SessionManager.KEY_EMAIL);
        initiize();
        Intent getid = getIntent();
        id = getid.getStringExtra("id");
        //  regid = getid.getStringExtra("regisid");
        usertype = getid.getStringExtra("type");
        System.out.println("id===" + id);
        System.out.println("usertype== " + usertype);

        if (!usertype.equals(null)) {

            if (usertype.equalsIgnoreCase("register")) {
                etb_ckrequirepass.setVisibility(View.GONE);
                etb_cksendwelcometext.setVisibility(View.GONE);
                ll_etb_cnfpass.setVisibility(View.GONE);
                ll_etb_pass.setVisibility(View.GONE);
                new Edit_UserService().execute();
                //  new UpdateGuest().execute();

            } else {
                etb_ckrequirepass.setVisibility(View.VISIBLE);
                etb_cksendwelcometext.setVisibility(View.VISIBLE);
                ll_etb_cnfpass.setVisibility(View.VISIBLE);
                ll_etb_pass.setVisibility(View.VISIBLE);

                new Edit_GuestWebservice().execute();
            }
        }
        setupActionBar();
        clickable();
    }

    private void clickable() {
        etb_addbutton.setOnClickListener(this);
        etb_ckrequirepass.setOnClickListener(this);
        etb_cksendwelcometext.setOnClickListener(this);

        etb_etexpirationdate.setOnClickListener(this);
        etb_etexpirationdate.setFocusable(false);
    }

    private void initiize() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etb_headerhintext1 = (TextView) findViewById(R.id.etb_headerhinttext2);
        etb_headerhinttext2 = (TextView) findViewById(R.id.etb_headerhinttext2);
        ll_etb_pass = (LinearLayout) findViewById(R.id.ll_etb_pass);
        ll_etb_cnfpass = (LinearLayout) findViewById(R.id.ll_etb_cnfpass);
        etb_nametxt = (TextView) findViewById(R.id.etb_nametxt);
        etb_firstlasttxt = (TextView) findViewById(R.id.etb_firstlasttxt);
        etb_phonetxt = (TextView) findViewById(R.id.etb_phonetxt);
        etb_emailtxt = (TextView) findViewById(R.id.etb_emailtxt);
        etb_tablenametxt = (TextView) findViewById(R.id.etb_tablenametxt);
        etb_companytxt = (TextView) findViewById(R.id.etb_companytxt);
        etb_cardnumbertxt = (TextView) findViewById(R.id.etb_cardnumbertxt);
        etb_cvvtxt = (TextView) findViewById(R.id.etb_cvvtxt);
        etb_cvvtxthint = (TextView) findViewById(R.id.etb_cvvtxthint);
        etb_expirationdatetxt = (TextView) findViewById(R.id.etb_expirationdatetxt);
        etb_etfirstname = (EditText) findViewById(R.id.etb_etfirstname);
        etb_etlastname = (EditText) findViewById(R.id.etb_etlastname);
        etb_etphoneno = (EditText) findViewById(R.id.etb_etphoneno);
        etb_etemail = (EditText) findViewById(R.id.etb_etemail);
        etb_ettable = (EditText) findViewById(R.id.etb_ettable);
        etb_etcardnumber = (EditText) findViewById(R.id.etb_etcardnumber);
        etb_etcvvnumber = (EditText) findViewById(R.id.etb_etcvvnumber);
        etb_etexpirationdate = (EditText) findViewById(R.id.etb_etexpirationdate);
        etb_etpass = (EditText) findViewById(R.id.etb_etpass);
        etb_etcompany = (EditText) findViewById(R.id.etb_etcompany);
        etb_etexpirationdate = (EditText) findViewById(R.id.etb_etexpirationdate);
        etb_cksendwelcometext = (CheckBox) findViewById(R.id.etb_cksendwelcometext);
        etb_ckrequirepass = (CheckBox) findViewById(R.id.etb_ckrequirepass);
        etb_swipebtn = (Button) findViewById(R.id.etb_swipebtn);
        etb_addbutton = (Button) findViewById(R.id.etb_addbutton);

        //Setup Font
        Constants.setfonttxt(etb_headerhintext1);
        Constants.setfonttxt(etb_headerhinttext2);
        Constants.setfonttxt(etb_nametxt);
        Constants.setfonttxt(etb_firstlasttxt);
        Constants.setfonttxt(etb_phonetxt);
        Constants.setfonttxt(etb_emailtxt);
        Constants.setfonttxt(etb_tablenametxt);
        Constants.setfonttxt(etb_cardnumbertxt);
        Constants.setfonttxt(etb_companytxt);
        Constants.setfonttxt(etb_cvvtxt);
        Constants.setfonttxt(etb_cvvtxthint);
        Constants.setfonttxt(etb_expirationdatetxt);
        Constants.setfontet(etb_etfirstname);
        Constants.setfontet(etb_etlastname);
        Constants.setfontet(etb_etphoneno);
        Constants.setfontet(etb_etemail);
        Constants.setfontet(etb_ettable);
        Constants.setfontet(etb_etcardnumber);
        Constants.setfontet(etb_etcvvnumber);
        Constants.setfontet(etb_etexpirationdate);
        Constants.setfontet(etb_etcompany);
        Constants.setfontbtn(etb_swipebtn);
        Constants.setfontbtn(etb_addbutton);
        Constants.setfontck(etb_ckrequirepass);
        Constants.setfontck(etb_cksendwelcometext);

    }

    public void getvalue() {
        strfname = etb_etfirstname.getText().toString();
        strlname = etb_etlastname.getText().toString();
        strphoneno = etb_etphoneno.getText().toString();
        stremail = etb_etemail.getText().toString();
        strtableno = etb_ettable.getText().toString();
        strcardno = etb_etcardnumber.getText().toString();
        strcvvno = etb_etcvvnumber.getText().toString();
        strpass = etb_etpass.getText().toString();
        strcompany = etb_etcompany.getText().toString();

    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayShowCustomEnabled(true);

        android.support.v7.app.ActionBar.LayoutParams param = new android.support.v7.app.ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        param.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
//        Drawable actionBarBackground = new ColorDrawable(Color.WHITE);
//        actionBar.setBackgroundDrawable(actionBarBackground);

        LayoutInflater inflator = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.header, null);
        Toolbar parent = (Toolbar) v.getParent();
//        parent.setContentInsetsAbsolute(0, 0);
        TextView header = (TextView) v.findViewById(R.id.txt_header);
        Constants.setfonttxt(header);
        header.setText("Bidder #" + id);
        header.setTextColor(Color.parseColor("#000000"));
        TextView backbutton = (TextView) v.findViewById(R.id.txtbtn_back);
        backbutton.setBackgroundResource(R.mipmap.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBar.setCustomView(v, param);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etb_addbutton:
                if (NetworkAvailablity.chkStatus(CBEditBidder.this)) {
                    if (!usertype.equals(null)) {
                        if (usertype.equalsIgnoreCase("register")) {
                            getvalue();
                            new UpdateBidder().execute();
                        } else {
                            new UpdateGuest().execute();
                        }
                    }

                } else {
                    Toast.makeText(CBEditBidder.this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.etb_ckrequirepass:
                if (etb_ckrequirepass.isChecked()) {
                    passcheck = "1";
                    ll_etb_pass.setVisibility(View.VISIBLE);
                    ll_etb_cnfpass.setVisibility(View.VISIBLE);
                } else {
                    passcheck = "0";
                    ll_etb_pass.setVisibility(View.GONE);
                    ll_etb_cnfpass.setVisibility(View.GONE);
                }
                break;
            case R.id.etb_cksendwelcometext:
                if (etb_cksendwelcometext.isChecked()) {
                    passwelcome = "1";

                } else {
                    passwelcome = "0";
                }
                break;

            case R.id.etb_expirationdatetxt:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(CBEditBidder.this, calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH);
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");


        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd", Locale.US);
        String time = sdf.format(new Date(date));
        etb_etexpirationdate.setText(time);

    }

    public class Edit_UserService extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CBEditBidder.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            ServiceHandler handler = new ServiceHandler();
            String response_str = handler.makeServiceCall(WebServiceConstants.EDIT_REGISTERURL + id, ServiceHandler.GET);
            System.out.println("EDIT SCREEN RESPONSE :--" + response_str);

            try {
                JSONObject jsonObject = new JSONObject(response_str);
                JSONObject data_jsnobject = jsonObject.optJSONObject("data");
                strfname = data_jsnobject.getString("first_name");
                strlname = data_jsnobject.getString("last_name");
                strbiddernumber = data_jsnobject.getString("bidder_number");
                strcompany = data_jsnobject.getString("company");
                strtableno = data_jsnobject.getString("table_name");
                strcardno = data_jsnobject.getString("ccs_last_four");
                strphoneno = data_jsnobject.optString("phone");

                if (!strphoneno.equals("null")) {
                    System.out.println("Phone no is " + strphoneno);
                    JSONArray phonearray = data_jsnobject.optJSONArray("phone");
                    if (phonearray.length() != 0) {
                        str = new StringBuilder();
                        for (int i = 0; i < phonearray.length(); i++) {

                            strphoneno = phonearray.get(i).toString();
                            System.out.println("Pno ====" + strphoneno);
                            str.append(strphoneno + ",");

                            System.out.println("Phone np ==" + str.toString().substring(0, str.toString().length() - 1));

                        }
                    }
                } else {
                    strphoneno = null;
                    System.out.println("IN ElSE");
                }
/*
                JSONArray emailarray = data_jsnobject.getJSONArray("email");
                for (int j = 0; j < emailarray.length(); j++) {
                    stremail = emailarray.get(j).toString();
                    System.out.println("Email ====" + stremail);
                }*/


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
                if (strfname.equalsIgnoreCase("null")) {
                } else {
                    etb_etfirstname.setText(strfname);
                }

                if (strlname.equalsIgnoreCase("null")) {
                } else {
                    etb_etlastname.setText(strlname);
                }

                if (strbiddernumber.equalsIgnoreCase("null")) {
                } else {
//            header.setText("Bidder #"+strbiddernumber);
                }

                if (strtableno.equalsIgnoreCase("null")) {
                } else {
                    etb_ettable.setText(strtableno);
                }

                if (strphoneno != null) {

                    etb_etphoneno.setText(str.toString().substring(0, str.toString().length() - 1));

                } else {
                    etb_etphoneno.setText("");
                }
                // checkvalidation();

                if (strcardno.equalsIgnoreCase("null")) {
                } else {
                    etb_etcvvnumber.setText(strtableno);
                }

            }
        }
    }


    public class Edit_GuestWebservice extends AsyncTask<String, String, String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CBEditBidder.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            ServiceHandler handler = new ServiceHandler();
            String response_str = handler.makeServiceCall(WebServiceConstants.EDIT_GUESTURL + id, ServiceHandler.GET);
            System.out.println("EDIT GUEST RESPONSE :--" + response_str);

            try {
                JSONObject jsonObject = new JSONObject(response_str);
                JSONObject data_jsnobjguest = jsonObject.getJSONObject("data");
                id = data_jsnobjguest.getString("id");
                strfname = data_jsnobjguest.getString("first_name");
                strlname = data_jsnobjguest.getString("last_name");
                strbiddernumber = data_jsnobjguest.getString("email");
                strtableno = data_jsnobjguest.getString("table_name");
                strcardno = data_jsnobjguest.getString("registration_id");


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing()) {
                pDialog.dismiss();

                if (strfname.equalsIgnoreCase("null")) {
                } else {
                    etb_etfirstname.setText(strfname);
                }

                if (strlname.equalsIgnoreCase("null")) {
                } else {
                    etb_etlastname.setText(strlname);
                }

                if (strbiddernumber.equalsIgnoreCase("null")) {
                } else {
//            header.setText("Bidder #"+strbiddernumber);
                }

                if (strtableno.equalsIgnoreCase("null")) {
                } else {
                    etb_ettable.setText(strtableno);
                }

                if (strphoneno != null) {

                    etb_etphoneno.setText(str.toString().substring(0, str.toString().length() - 1));

                } else {
                    etb_etphoneno.setText("");
                }
                // checkvalidation();

                if (strcardno.equalsIgnoreCase("null")) {
                } else {
                    etb_etcvvnumber.setText(strtableno);
                }

            }
        }
    }

    public class UpdateBidder extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CBEditBidder.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            ServiceHandler handler = new ServiceHandler();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("first_name", strfname));
            nameValuePairs.add(new BasicNameValuePair("last_name", strlname));
            nameValuePairs.add(new BasicNameValuePair("company", strcompany));
            nameValuePairs.add(new BasicNameValuePair("table_name", strtableno));
            nameValuePairs.add(new BasicNameValuePair("phone ", strphoneno));
            nameValuePairs.add(new BasicNameValuePair("email", stremail));
            nameValuePairs.add(new BasicNameValuePair("ccs_last_four", strcardno));

            nameValuePairs.add(new BasicNameValuePair("customer_profile_id", ""));
            nameValuePairs.add(new BasicNameValuePair("payment_profile_id", ""));
            nameValuePairs.add(new BasicNameValuePair("beanstream_id", ""));
            nameValuePairs.add(new BasicNameValuePair("last_four", strcvvno));


            String response_str = handler.makeServiceCall(WebServiceConstants.UPDATE_REGISTERUSER + usereventid + "/" + id, ServiceHandler.PUT, nameValuePairs);
            System.out.println("Update Response :== " + response_str);

            try {
                JSONObject obj = new JSONObject(response_str);
                JSONObject dataobj = obj.getJSONObject("data");
                result = dataobj.getString("SUCCESS");


            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (result.equalsIgnoreCase("Records updated successfully.")) {

                Toast.makeText(CBEditBidder.this, "Updated Succesfully", Toast.LENGTH_SHORT).show();
                finish();
                //  Intent intent = new Intent(CBEditBidder.this,CBLandingPage.class);
                //startActivity(intent);

            } else {
                Toast.makeText(CBEditBidder.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class UpdateGuest extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        String result;
        String url = "https://clickbid.cc/cbapi/butler-updatebidder/" + usereventid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CBEditBidder.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            ServiceHandler guest_handler = new ServiceHandler();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("guest", id));
            nameValuePairs.add(new BasicNameValuePair("first_name", strfname));
            nameValuePairs.add(new BasicNameValuePair("last_name", strlname));
            nameValuePairs.add(new BasicNameValuePair("accept_texts", passwelcome));
            nameValuePairs.add(new BasicNameValuePair("table_name", strtableno));
            nameValuePairs.add(new BasicNameValuePair("password_required ", passcheck));
            nameValuePairs.add(new BasicNameValuePair("password", strpass));
            nameValuePairs.add(new BasicNameValuePair("ccs_last_four", strcardno));
            nameValuePairs.add(new BasicNameValuePair("company", strcompany));
            nameValuePairs.add(new BasicNameValuePair("phone", strphoneno));
            nameValuePairs.add(new BasicNameValuePair("email", stremail));
            nameValuePairs.add(new BasicNameValuePair("customer_profile_id", ""));
            nameValuePairs.add(new BasicNameValuePair("payment_profile_id", ""));
            nameValuePairs.add(new BasicNameValuePair("beanstream_id", ""));
            nameValuePairs.add(new BasicNameValuePair("last_four", strcvvno));

            String response = guest_handler.makeServiceCall(url, ServiceHandler.POST, nameValuePairs);
            System.out.println("Guest EDIT Response ==" + response);

            try {
                JSONObject obj = new JSONObject(response);
                JSONObject dataobj = obj.getJSONObject("data");
                result = dataobj.getString("SUCCESS");


            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (result.equalsIgnoreCase("Records inserted successfully.")) {

                Toast.makeText(CBEditBidder.this, "Updated Succesfully", Toast.LENGTH_SHORT).show();
                finish();
                //  Intent intent = new Intent(CBEditBidder.this,CBLandingPage.class);
                //startActivity(intent);

            } else {
                Toast.makeText(CBEditBidder.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
