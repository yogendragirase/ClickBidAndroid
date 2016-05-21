package clickbid.com.clickbid.Activities;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.BraintreePaymentActivity;
import com.braintreepayments.api.PaymentRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import clickbid.com.clickbid.Adapter.CheckOutAdapter;
import clickbid.com.clickbid.Authorize.LoginActivity;
import clickbid.com.clickbid.Bean.CheckOutBean;
import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;
import clickbid.com.clickbid.constants.SessionManager;
import clickbid.com.clickbid.constants.WebServiceConstants;
import clickbid.com.clickbid.servercommunication.ServiceHandler;

/**
 * Created by Syscraft on 07-May-16.
 */
public class CBCheckout extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    ActionBar actionBar;
    private ListView checkout_lv;
    private EditText co_etfirstname, co_etlastname, co_cardnumbr, co_cvvet, co_etexpirydate, co_phonenotxt, co_etemail;
    private Button co_swipebtn, co_paybtn;
    private String strcredit, strfname, strlname, strcrdno, strcvvno, strexpirydate, strphntxt, stremail, streventid, struserid;
    private TextView co_signedrontxt, co_signedronamount, co_raisepaddletxt, raisepaddleamount, co_totaltxt, co_totalamount, co_txtpaymenttype;
    private TextView co_nametext, co_firstlastname, co_cardnumbertxt, co_cvvtxt, cv_cvvhinttxt, co_cvexpirydate, co_phonetxt, co_emailtxt;
    CheckOutBean checkOutBean;
    String braintree_token;
    private Spinner co_etcredit;
    ArrayList<CheckOutBean> slist = new ArrayList<CheckOutBean>();
    HashMap<String, String> getdetails = new HashMap<String, String>();
    CheckOutAdapter checkOutAdapter;
    private SessionManager sessionManager;
    public static int REQUEST_CODE = 1;
    public String paymenttype = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutlayout);
        initiize();
        onclicked();
        setupfont();
    }

    private void onclicked() {
        co_swipebtn.setOnClickListener(this);
        co_paybtn.setOnClickListener(this);
        co_etcredit.setOnItemSelectedListener(this);
    }

    private void initiize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupActionBar();
        checkout_lv = (ListView) findViewById(R.id.checkout_lv);
        sessionManager = new SessionManager(CBCheckout.this);
        getdetails = sessionManager.getUserDetails();
        streventid = getdetails.get(SessionManager.KEY_EMAIL);
        Intent getid = getIntent();
        struserid = getid.getStringExtra("id");
        System.out.println("User id -" + struserid + "Eventid " + streventid);

        for (int i = 0; i <= 4; i++) {
            checkOutBean = new CheckOutBean();
            checkOutBean.setCheckoutitem("Signed Ron Clark Lithograph (#110)");
            checkOutBean.setCheckoutprice("$100");
            slist.add(checkOutBean);
        }

        checkOutAdapter = new CheckOutAdapter(CBCheckout.this, slist);
        checkout_lv.setAdapter(checkOutAdapter);
        LayoutInflater inflater = getLayoutInflater();
        View footerView = inflater.inflate(R.layout.checkoutfooter, null);
        checkout_lv.addFooterView(footerView);
        co_txtpaymenttype = (TextView) footerView.findViewById(R.id.co_txtpaymenttype);
        co_nametext = (TextView) footerView.findViewById(R.id.co_nametext);
        co_firstlastname = (TextView) footerView.findViewById(R.id.co_firstlastname);
        co_cardnumbertxt = (TextView) footerView.findViewById(R.id.co_cardnumbertxt);
        co_cvvtxt = (TextView) footerView.findViewById(R.id.co_cvvtxt);
        cv_cvvhinttxt = (TextView) footerView.findViewById(R.id.cv_cvvhinttxt);
        co_cvexpirydate = (TextView) footerView.findViewById(R.id.co_cvexpirydate);
        co_phonetxt = (TextView) footerView.findViewById(R.id.co_phonetxt);
        co_emailtxt = (TextView) footerView.findViewById(R.id.co_emailtxt);
        co_etcredit = (Spinner) footerView.findViewById(R.id.co_etcredit);
        co_etfirstname = (EditText) footerView.findViewById(R.id.co_etfirstname);
        co_etlastname = (EditText) footerView.findViewById(R.id.co_etlastname);
        co_cardnumbr = (EditText) footerView.findViewById(R.id.co_cardnumbr);
        co_cvvet = (EditText) footerView.findViewById(R.id.co_cvvet);
        co_etexpirydate = (EditText) footerView.findViewById(R.id.co_etexpirydate);
        co_phonenotxt = (EditText) footerView.findViewById(R.id.co_phonenotxt);
        co_etemail = (EditText) footerView.findViewById(R.id.co_etemail);
        co_swipebtn = (Button) footerView.findViewById(R.id.co_swipebtn);
        co_paybtn = (Button) footerView.findViewById(R.id.co_paybtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Payment_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        co_etcredit.setAdapter(adapter);
        new Checkout_service().execute();
    }

    private void setupfont() {
        Constants.setfonttxt(co_txtpaymenttype);
        Constants.setfonttxt(co_nametext);
        Constants.setfonttxt(co_firstlastname);
        Constants.setfonttxt(co_cardnumbertxt);
        Constants.setfonttxt(co_cvvtxt);
        Constants.setfonttxt(cv_cvvhinttxt);
        Constants.setfonttxt(co_cvexpirydate);
        Constants.setfonttxt(co_phonetxt);
        Constants.setfonttxt(co_emailtxt);
        Constants.setfontet(co_etfirstname);
        Constants.setfontet(co_etlastname);
        Constants.setfontet(co_cardnumbr);
        Constants.setfontet(co_cvvet);
        Constants.setfontet(co_etexpirydate);
        Constants.setfontet(co_phonenotxt);
        Constants.setfontet(co_etemail);
        Constants.setfontbtn(co_swipebtn);
        Constants.setfontbtn(co_paybtn);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        // actionBar.setIcon(R.drawable.ic_launcher);
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

        TextView header = (TextView) v.findViewById(R.id.txt_header);
        header.setText("CHECKOUT");
        Constants.setfonttxt(header);
        header.setTextColor(Color.parseColor("#000000"));

        TextView backbutton = (TextView) v.findViewById(R.id.txtbtn_back);

        backbutton.setBackgroundResource(R.mipmap.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            /*    Intent ine = new Intent(getApplicationContext(),CBPaidListActivty.class);
                startActivity(ine);*/

            }
        });

        actionBar.setCustomView(v, param);

    }

    public void getdata() {
        strfname = co_etfirstname.getText().toString();
        strlname = co_etlastname.getText().toString();
        strcrdno = co_cardnumbr.getText().toString();
        strcvvno = co_cvvet.getText().toString();
        strexpirydate = co_etexpirydate.getText().toString();
        strphntxt = co_phonenotxt.getText().toString();
        stremail = co_etemail.getText().toString();
    }


    public boolean validate() {
        if (strfname.length() == 0 || strfname == null
                || strfname.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "First Name Required", this, false);

            return false;
        } else if (strlname.length() == 0 || strlname == null || strlname.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "LastName is required", this, false);
            return false;
        } else if (strphntxt.length() == 0 || strphntxt == null || strphntxt.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Phone Number is required", this, false);
            return false;
        } /*else if (Constants.checkEmail(stremail)) {

            Constants.showAlertDialog("Error", "Invalid Email Address", this, false);
            return false;
        } */ else if (strcrdno.length() == 0 || strcrdno == null || strcrdno.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Invalid Card", this, false);
            return false;
        } else if (strcvvno.length() == 0 || strcvvno == null || strcvvno.equalsIgnoreCase("")) {

            Constants.showAlertDialog("Error", "Invalid CVV number", this, false);
            return false;
        }


        return true;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.co_paybtn:
                getdata();

                if (validate()) {
                    if (paymenttype.equalsIgnoreCase("1")) {

                        onBraintreeSubmit(v);

                    } else if (paymenttype.equalsIgnoreCase("2")) {
                        Intent authorize = new Intent(CBCheckout.this, LoginActivity.class);
                        startActivity(authorize);
                    }
                }
                break;


            case R.id.co_swipebtn:
                break;

            default:
                break;
        }

    }


    public void onBraintreeSubmit(View v) {

        System.out.println("Token is :--" + braintree_token);
        PaymentRequest paymentRequest = new PaymentRequest()
                .clientToken(braintree_token);
        startActivityForResult(paymentRequest.getIntent(this), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentMethodNonce paymentMethodNonce = data.getParcelableExtra(
                        BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE
                );
                String nonce = paymentMethodNonce.getNonce();

                System.out.println("Nonce :--" + nonce);
                // Send the nonce to your server.
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = co_etcredit.getSelectedItem().toString();
        System.out.println("Name of item is :--"+item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class Checkout_service extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        String url = "https://clickbid.cc/cbapi/butler-biddercheckout/" + streventid + "/" + struserid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CBCheckout.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            ServiceHandler serviceHandler = new ServiceHandler();
            String checkout_response = serviceHandler.makeServiceCall(url, ServiceHandler.GET);
            System.out.println("CHeckout Response is :--" + checkout_response);

            try {
                JSONObject object = new JSONObject(checkout_response);
                JSONObject checkout_Obj = object.getJSONObject("data");
                String token = checkout_Obj.getString("braintree_token");
                System.out.println("Token " + token);
                braintree_token = checkout_Obj.getString("braintree_token");


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


    public class Checkout_Params extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        String url = "https://clickbid.cc/cbapi/butler-biddercheckout/" + streventid + "/" + struserid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            ServiceHandler sh = new ServiceHandler();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("first_name", strfname));
            nameValuePairs.add(new BasicNameValuePair("last_name", strlname));
            nameValuePairs.add(new BasicNameValuePair("phone", strphntxt));
            nameValuePairs.add(new BasicNameValuePair("email", stremail));
            nameValuePairs.add(new BasicNameValuePair("company", ""));
            // nameValuePairs.add(new BasicNameValuePair("table_name",));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
