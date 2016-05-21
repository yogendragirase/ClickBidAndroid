package clickbid.com.clickbid.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;
import clickbid.com.clickbid.constants.SessionManager;

/**
 * Created by Syscraft on 06-May-16.
 */
public class CBLandingPage extends BaseActivity implements View.OnClickListener  {


    private TextView txt_searchbutton, txt_landingheader, txt_bidderlookup, txt_addabidder, txt_logout;
    private LinearLayout ll_logout, ll_addbidder;
    private SessionManager sessionManager;
    private EditText et_landingsearch;
    private String searchtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landinglayout);
        intilize();
        onclicked();
    }

    private void getvalues() {
        searchtext = et_landingsearch.getText().toString();
    }


    private void intilize() {
        sessionManager = new SessionManager(CBLandingPage.this);
        txt_searchbutton = (TextView) findViewById(R.id.txt_searchbutton);
        txt_landingheader = (TextView) findViewById(R.id.txt_landingheader);
        txt_bidderlookup = (TextView) findViewById(R.id.txt_bidderlookup);
        txt_addabidder = (TextView) findViewById(R.id.txt_addabidder);
        txt_logout = (TextView) findViewById(R.id.txt_logout);
        et_landingsearch = (EditText) findViewById(R.id.et_landingsearch);
        ll_addbidder = (LinearLayout) findViewById(R.id.ll_addbidder);
        ll_logout = (LinearLayout) findViewById(R.id.ll_logout);

        Constants.setfonttxt(txt_searchbutton);
        Constants.setfonttxt(txt_landingheader);
        Constants.setfonttxt(txt_bidderlookup);
        Constants.setfonttxt(txt_addabidder);
        Constants.setfonttxt(txt_logout);
        Constants.setfontet(et_landingsearch);

    }

    private void onclicked() {
        txt_searchbutton.setOnClickListener(this);
        ll_addbidder.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txt_searchbutton:
                getvalues();
                if (validation()) {
                    Intent search_intent = new Intent(getApplicationContext(), CBSearchlist.class);
                    search_intent.putExtra("search", searchtext);
                    startActivity(search_intent);
                    new MyBackgroundTask(this).execute("firstTask");
                    et_landingsearch.setText("");
                }
                break;

            case R.id.ll_logout:
                Constants.showLogoutDialog("Logout", "Do you really want to logout", CBLandingPage.this, false);
                break;

            case R.id.ll_addbidder:
                Intent addbidderintent = new Intent(getApplicationContext(), CbnewBidder.class);
                startActivity(addbidderintent);
                break;

            default:
                break;
        }
    }

    public boolean validation() {

        if (searchtext.length() == 0 || searchtext == null || searchtext.equalsIgnoreCase("")) {
            Constants.showAlertDialog("Required", "Search can't be left empty", this, false);
            return false;
        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        return;
    }


    public  void loadUIFirst(){


    }
  @Override
  public void getInBackgroundFirst(){

    }
}
