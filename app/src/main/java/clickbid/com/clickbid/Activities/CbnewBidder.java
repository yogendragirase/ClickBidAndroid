package clickbid.com.clickbid.Activities;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import clickbid.com.clickbid.servercommunication.NetworkAvailablity;
import clickbid.com.clickbid.servercommunication.ServiceHandler;

/**
 * Created by Syscraft on 07-May-16.
 */
public class CbnewBidder extends AppCompatActivity implements View.OnClickListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    ActionBar actionBar;
    Toolbar toolbar;
    private TextView nb_headerhintext1, headerhinttext2, nb_passtext, nb_cnfpasstext, nb_nametxt, nb_firstlasttxt, nb_phonetxt, nb_companytxt, nb_emailtxt, nb_tablenametxt, nb_cardnumbertxt, nb_cvvtxt, nb_cvvtxthint, nb_expirationdatetxt;
    private EditText nb_etfirstname, nb_etlastname, nb_etphoneno, nb_etemail, nb_ettable, nb_etcardnumber, nb_etcvvnumber, nb_etexpirationdate, nb_etpass, nb_cnfetpass, nb_etcompany;
    private Button nb_swipebtn, nb_addbutton;
    private String strfname, strlname, strphoneno, stremail, strtableno, strcardno, strcvvno, strexpirydate, strpass, strcnfpass, strcompany;
    private CheckBox nb_cksendwelcometext, nb_ckrequirepass;
    private LinearLayout ll_nb_pass, ll_nb_cnfpass, nb_datepicker;
    private String usereventid, regid, passcheck, passwelcome;
    private HashMap<String, String> getevnet = new HashMap<String, String>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbidderllayout);
        initiize();
        setonclick();
    }

    private void initiize() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupActionBar();
        nb_headerhintext1 = (TextView) findViewById(R.id.headerhinttext2);
        headerhinttext2 = (TextView) findViewById(R.id.headerhinttext2);
        nb_nametxt = (TextView) findViewById(R.id.nb_nametxt);
        nb_firstlasttxt = (TextView) findViewById(R.id.nb_firstlasttxt);
        nb_companytxt = (TextView) findViewById(R.id.nb_companytxt);
        nb_phonetxt = (TextView) findViewById(R.id.nb_phonetxt);
        nb_emailtxt = (TextView) findViewById(R.id.nb_emailtxt);
        nb_tablenametxt = (TextView) findViewById(R.id.nb_tablenametxt);
        nb_cardnumbertxt = (TextView) findViewById(R.id.nb_cardnumbertxt);
        nb_passtext = (TextView) findViewById(R.id.nb_passtext);
        nb_cvvtxt = (TextView) findViewById(R.id.nb_cvvtxt);
        nb_cvvtxthint = (TextView) findViewById(R.id.nb_cvvtxthint);
        nb_cnfpasstext = (TextView) findViewById(R.id.nb_cnfpasstext);
        nb_expirationdatetxt = (TextView) findViewById(R.id.nb_expirationdatetxt);
        nb_etfirstname = (EditText) findViewById(R.id.nb_etfirstname);
        nb_etlastname = (EditText) findViewById(R.id.nb_etlastname);
        nb_etphoneno = (EditText) findViewById(R.id.nb_etphoneno);
        nb_etemail = (EditText) findViewById(R.id.nb_etemail);
        nb_ettable = (EditText) findViewById(R.id.nb_ettable);
        nb_etcardnumber = (EditText) findViewById(R.id.nb_etcardnumber);
        nb_etcvvnumber = (EditText) findViewById(R.id.nb_etcvvnumber);
        nb_etcompany = (EditText) findViewById(R.id.nb_etcompany);
        nb_etexpirationdate = (EditText) findViewById(R.id.nb_etexpirationdate);
        nb_etpass = (EditText) findViewById(R.id.nb_etpass);
        nb_cnfetpass = (EditText) findViewById(R.id.nb_cnfetpass);
        nb_etexpirationdate = (EditText) findViewById(R.id.nb_etexpirationdate);
        nb_ckrequirepass = (CheckBox) findViewById(R.id.nb_ckrequirepass);
        nb_cksendwelcometext = (CheckBox) findViewById(R.id.nb_cksendwelcometext);
        ll_nb_pass = (LinearLayout) findViewById(R.id.ll_nb_pass);
        ll_nb_cnfpass = (LinearLayout) findViewById(R.id.ll_nb_cnfpass);
        nb_datepicker = (LinearLayout) findViewById(R.id.nb_datepicker);
        nb_swipebtn = (Button) findViewById(R.id.nb_swipebtn);
        nb_addbutton = (Button) findViewById(R.id.nb_addbutton);

        //Setup Font
        Constants.setfonttxt(nb_headerhintext1);
        Constants.setfonttxt(headerhinttext2);
        Constants.setfonttxt(nb_nametxt);
        Constants.setfonttxt(nb_firstlasttxt);
        Constants.setfonttxt(nb_phonetxt);
        Constants.setfonttxt(nb_emailtxt);
        Constants.setfonttxt(nb_tablenametxt);
        Constants.setfonttxt(nb_companytxt);
        Constants.setfonttxt(nb_cardnumbertxt);
        Constants.setfonttxt(nb_cnfpasstext);
        Constants.setfonttxt(nb_cvvtxt);
        Constants.setfonttxt(nb_cvvtxthint);
        Constants.setfonttxt(nb_passtext);
        Constants.setfonttxt(nb_expirationdatetxt);
        Constants.setfontet(nb_etfirstname);
        Constants.setfontet(nb_etlastname);
        Constants.setfontet(nb_etphoneno);
        Constants.setfontet(nb_etemail);
        Constants.setfontet(nb_ettable);
        Constants.setfontet(nb_etcompany);
        Constants.setfontet(nb_etcardnumber);
        Constants.setfontet(nb_etcvvnumber);
        Constants.setfontet(nb_etexpirationdate);
        Constants.setfontbtn(nb_swipebtn);
        Constants.setfontbtn(nb_addbutton);

        sessionManager = new SessionManager(CbnewBidder.this);
        getevnet = sessionManager.getUserDetails();
        usereventid = getevnet.get(SessionManager.KEY_EMAIL);
    }

    private void setonclick() {
        nb_swipebtn.setOnClickListener(this);
        nb_addbutton.setOnClickListener(this);
        nb_cksendwelcometext.setOnClickListener(this);
        nb_ckrequirepass.setOnClickListener(this);
        nb_etexpirationdate.setOnClickListener(this);
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
        header.setText("New Bidder");

        header.setTextColor(Color.parseColor("#000000"));


        TextView backbutton = (TextView) v.findViewById(R.id.txtbtn_back);

        backbutton.setBackgroundResource(R.mipmap.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

              /*  Intent ine = new Intent(getApplicationContext(),CBCheckout.class);
                startActivity(ine);*/

            }
        });


        actionBar.setCustomView(v, param);

    }

    public void getdata() {

        strfname = nb_etfirstname.getText().toString();
        strlname = nb_etlastname.getText().toString();
        strphoneno = nb_etphoneno.getText().toString();
        stremail = nb_etemail.getText().toString();
        strtableno = nb_ettable.getText().toString();
        strcardno = nb_etcardnumber.getText().toString();
        strcvvno = nb_etcvvnumber.getText().toString();
        strexpirydate = nb_etexpirationdate.getText().toString();
        strpass = nb_etpass.getText().toString();
        strcnfpass = nb_cnfetpass.getText().toString();
        strcompany = nb_etcompany.getText().toString();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nb_addbutton:
                if (NetworkAvailablity.chkStatus(CbnewBidder.this)) {
                    getdata();
                    validate();
                    if (nb_cksendwelcometext.isChecked()) {
                        passwelcome = "1";

                    } else {
                        passwelcome = "0";
                    }

                    if (nb_ckrequirepass.isChecked()) {
                        passcheck = "1";

                    } else {
                        passcheck = "0";
                    }

                    System.out.println("passwelcome=" + passwelcome + "passcheck==" + passcheck);
                    new Add_Bidder().execute();
                } else {
                    Toast.makeText(CbnewBidder.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.nb_swipebtn:
                break;

            case R.id.nb_cksendwelcometext:
                if (nb_cksendwelcometext.isChecked()) {
                    passwelcome = "1";

                } else {
                    passwelcome = "0";
                }

                break;

            case R.id.nb_ckrequirepass:
                if (nb_ckrequirepass.isChecked()) {
                    passcheck = "1";
                    ll_nb_pass.setVisibility(View.VISIBLE);
                    ll_nb_cnfpass.setVisibility(View.VISIBLE);
                } else {
                    passcheck = "0";
                    ll_nb_pass.setVisibility(View.GONE);
                    ll_nb_cnfpass.setVisibility(View.GONE);
                }

                break;

            case R.id.nb_etexpirationdate:

                Calendar calendar = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(CbnewBidder.this, calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH);
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");

            default:
                break;

        }
    }

    public boolean validate() {
        if (strfname.length() == 0 || strfname == null
                || strfname.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "First Name Required", this, false);

            return false;
        } else if (strlname.length() == 0 || strlname == null || strlname.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "LastName is required", this, false);
            return false;
        } else if (strphoneno.length() == 0 || strphoneno == null || strphoneno.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Password is required", this, false);
            return false;
        } else if (Constants.checkEmail(stremail)) {

            Constants.showAlertDialog("Error", "Invalid Email Address", this, false);
            return false;
        } else if (strcardno.length() == 0 || strlname == null || strlname.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Invalid Card", this, false);
            return false;
        } else if (strcvvno.length() == 0 || strcvvno == null || strcvvno.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Invalid CVV number", this, false);
            return false;
        }


        return true;

    }


    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM", Locale.US);
        String time = sdf.format(new Date(date));
        nb_etexpirationdate.setText(time);
    }


    public class Add_Bidder extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        String result;
        String url = "https://clickbid.cc/cbapi/butler-updatebidder/" + usereventid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CbnewBidder.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            ServiceHandler guest_handler = new ServiceHandler();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
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
            System.out.println("New Bidder Response ==" + response);

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

                Toast.makeText(CbnewBidder.this, "User Successfully Added", Toast.LENGTH_SHORT).show();
                finish();
                //  Intent intent = new Intent(CBEditBidder.this,CBLandingPage.class);
                //startActivity(intent);

            } else {
                Toast.makeText(CbnewBidder.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
