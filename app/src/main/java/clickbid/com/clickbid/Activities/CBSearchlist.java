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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import clickbid.com.clickbid.Adapter.SearchListAdapter;
import clickbid.com.clickbid.Bean.SearchBean;
import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;
import clickbid.com.clickbid.constants.SessionManager;
import clickbid.com.clickbid.constants.WebServiceConstants;
import clickbid.com.clickbid.servercommunication.NetworkAvailablity;
import clickbid.com.clickbid.servercommunication.ServiceHandler;

/**
 * Created by Syscraft on 10-May-16.
 */
public class CBSearchlist extends AppCompatActivity {

    private Toolbar toolbar;
    ActionBar actionBar;
    private ListView lv_searchlist;
    // public static String registerlistsize;
    private SearchListAdapter searchadapter;
    // private SearchBean searchBean;
    public static ArrayList<SearchBean> registerdlist = new ArrayList<SearchBean>();
    // public static ArrayList<SearchBean> guestlist = new ArrayList<SearchBean>();
    private String searchtext, eventid;
    private HashMap<String, String> getdetails = new HashMap<String, String>();

    //// TODO: 13-May-16
    Intent i;
    String searchKeyword;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);
        initilize();
        setuplist();
    }

    private void initilize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_searchlist = (ListView) findViewById(R.id.lv_searchlist);
        setupActionBar();
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
        LayoutInflater inflator = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.paidheader, null);
        TextView header = (TextView) v.findViewById(R.id.txt_paidheader);
        Intent get_intent = getIntent();
        searchtext = get_intent.getStringExtra("search");
        header.setText("Result for -" + searchtext);
        header.setTextColor(Color.parseColor("#000000"));
        Constants.setfonttxt(header);
        TextView backbutton = (TextView) v.findViewById(R.id.txtbtn_paidback);
        backbutton.setBackgroundResource(R.mipmap.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBar.setCustomView(v, param);
    }

    private void setuplist() {
        sessionManager = new SessionManager(CBSearchlist.this);
        getdetails = sessionManager.getUserDetails();

        eventid = getdetails.get(sessionManager.KEY_EMAIL);
        System.out.println("Event id :===" + eventid);

       /* LayoutInflater inflater = getLayoutInflater();
        View headerView = inflater.inflate(R.layout.listheader, null);
        TextView txtx_listheader = (TextView) headerView.findViewById(R.id.txtx_listheader);
        Constants.setfonttxt(txtx_listheader);
        lv_searchlist.addHeaderView(headerView);*/

        //// TODO: 13-May-16 changes in serach api

        // Following are details for Search Results API :

        // 1) For butlerBidder :
        // URL: http://clickbid.cc/cbapi/butler-bidder/123755

        //a) 123755 is bidder id


        //  2) For butlerGuest :
        //  URL: http://clickbid.cc/cbapi/butler-guest/2

        //  a) 2 is guest id

        //// TODO: 13-May-16
        i = getIntent();
        if (i != null) {
            searchKeyword = i.getStringExtra("search");
            if (NetworkAvailablity.chkStatus(CBSearchlist.this)) {
                new Search_Service().execute(searchKeyword);
            } else {
                Constants.showAlertDialog("Network Error", "Something went wrong, please try agin later", CBSearchlist.this, false);
            }
        }

        //// TODO: 13-May-16 end

       /* searchBean = new SearchBean();
        registerdlist.clear();
        for (int i = 0; i <= 10; i++) {
            searchBean.setUsername("John Smith");
            searchBean.setViewtype("REGISTERD");
            registerdlist.add(searchBean);
        }
        System.out.println("Size of list ==" + registerdlist.size());
        searchadapter = new SearchListAdapter(getApplicationContext(), registerdlist);
        lv_searchlist.setAdapter(searchadapter);*/
    }

    public class Search_Service extends AsyncTask<String, String, String> {

        public ProgressDialog pDialog;
        //String url = "http://clickbid.cc/cbapi/butler-landing/10";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CBSearchlist.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //  System.out.println("value of id :==" + et_email);
            ServiceHandler sh = new ServiceHandler();
            //  manager = new SessionManager(CBSearchlist.this);
            // Making a request to url and getting response
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //http://clickbid.cc/cbapi/butler-landing/10/abc
            //   String jsonStr = sh.makeServiceCall(url,ServiceHandler.GET,nameValuePairs);
            String jsonStr = sh.makeServiceCall(WebServiceConstants.SEARCH_URL + eventid + "/" + strings[0], ServiceHandler.GET, nameValuePairs);
            Log.e(" Response Search ", "Response Search:------->" + jsonStr);

            // String jsonStr = sh.makeServiceCall(WebServiceConstants.SEARCH_URL + sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL) + "/" + strings[0], ServiceHandler.GET, nameValuePairs);
            //Log.e(" Response Search " ,"Response Search:------->" + jsonStr);

            try {
                if (jsonStr != null) {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject dataobject = jsonObject.getJSONObject("data");
                    JSONArray arraybiders = dataobject.getJSONArray("bidders");
                    registerdlist.clear();
                    if (arraybiders.length() > 0) {
                        registerdlist.add(new SearchBean("TITLE", "false"));
                        for (int i = 0; i < arraybiders.length(); i++) {
                            JSONObject object = arraybiders.getJSONObject(i);
                            SearchBean search = new SearchBean();
                            String id = object.optString("id");
                            search.setId(id);
                            String first_name = object.optString("first_name");
                            search.setFirst_name(first_name);
                            String last_name = object.optString("last_name");
                            search.setLast_name(last_name);
                            String bidder_number = object.optString("bidder_number");
                            search.setBidder_number(bidder_number);
                            String company = object.optString("company");
                            search.setCompany(company);
                            String table_name = object.optString("table_name");
                            search.setTable_name(table_name);
                            String phone = object.optString("phone");
                            search.setPhone(phone);
                            String email = object.optString("email");
                            search.setEmail(email);
                            String ccs_last_four = object.optString("ccs_last_four");
                            search.setCcs_last_four(ccs_last_four);
                            String customer_profile_id = object.optString("customer_profile_id");
                            search.setCustomer_profile_id(customer_profile_id);
                            String payment_profile_id = object.optString("payment_profile_id");
                            search.setPayment_profile_id(payment_profile_id);
                            String beanstream_id = object.optString("beanstream_id");
                            search.setBeanstream_id(beanstream_id);
                            String beanstream_last_four = object.optString("beanstream_last_four");
                            search.setBeanstream_last_four(beanstream_last_four);
                            String has_bids = object.optString("has_bids");
                            search.setHas_bids(has_bids);
                            String has_purchases = object.optString("has_purchases");
                            search.setHas_purchases(has_purchases);
                            String has_checkouts = object.optString("has_checkouts");
                            search.setHas_checkouts(has_checkouts);
                            search.setViewtype("REGISTERD");
                            registerdlist.add(search);
                        }
                    }

                    JSONArray arrayguest = dataobject.getJSONArray("guests");
                    if (arrayguest.length() > 0) {
                        registerdlist.add(new SearchBean("TITLE", "true"));
                        for (int j = 0; j < arrayguest.length(); j++) {
                            JSONObject object = arrayguest.getJSONObject(j);
                            SearchBean search = new SearchBean();
                            String id = object.optString("id");
                            search.setId(id);
                            String first_name = object.optString("first_name");
                            search.setFirst_name(first_name);
                            String last_name = object.optString("last_name");
                            search.setLast_name(last_name);
                            String email = object.optString("email");
                            search.setEmail(email);
                            String phone = object.optString("phone");
                            search.setPhone(phone);
                            String table_name = object.optString("table_name");
                            search.setTable_name(table_name);
                            String receipt = object.optString("receipt");
                            search.setReceipt(receipt);
                            String registration_id = object.optString("registration_id");
                            search.setRegistration_id(registration_id);
                            search.setViewtype("GUEST");
                            registerdlist.add(search);
                        }
                    }
                    if (arraybiders.length() == 0 && arrayguest.length() == 0) {
                        jsonStr = "error";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (s.equals("error")) {
                Toast.makeText(CBSearchlist.this, "No results found.", Toast.LENGTH_SHORT).show();
                ;
            } else {
                if (registerdlist != null && registerdlist.size() > 0) {
                    searchadapter = new SearchListAdapter(getApplicationContext(), registerdlist);
                    lv_searchlist.setAdapter(searchadapter);
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        new Search_Service().execute(searchKeyword);
    }
}
