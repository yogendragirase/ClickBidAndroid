package clickbid.com.clickbid.Activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import clickbid.com.clickbid.Adapter.InvoiceListAdapter;
import clickbid.com.clickbid.Bean.PaidListBean;
import clickbid.com.clickbid.R;
import clickbid.com.clickbid.constants.Constants;

/**
 * Created by Syscraft on 07-May-16.
 */
public class CBPaidListActivty extends AppCompatActivity {

    private Toolbar toolbar;
    ActionBar actionBar;
    private ListView lv_paidlistview ;
    private InvoiceListAdapter invoiceListAdapter;
    private PaidListBean paidListBean;
    private TextView pi_username,nl_listhinttext;
    private ArrayList<PaidListBean> paidlist = new ArrayList<PaidListBean>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paiditemlayout);
        initiize();

    }


    private void initiize() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_paidlistview = (ListView) findViewById(R.id.lv_paidlistview);
        pi_username = (TextView)findViewById(R.id.pi_username);
        nl_listhinttext = (TextView)findViewById(R.id.nl_listhinttext);
        Constants.setfonttxt(pi_username);
        Constants.setfonttxt(nl_listhinttext);
        setupActionBar();
        paidListBean = new PaidListBean();

        for (int i =0;i<=10;i++){
            paidListBean.setPaiditem("#110-Signed Ron CLark Lithograph");
            paidlist.add(paidListBean);
        }

        invoiceListAdapter = new InvoiceListAdapter(getApplicationContext(),paidlist);
        lv_paidlistview.setAdapter(invoiceListAdapter);
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
        // param.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
//        Drawable actionBarBackground = new ColorDrawable(Color.WHITE);
//        actionBar.setBackgroundDrawable(actionBarBackground);

        LayoutInflater inflator = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.paidheader, null);

        TextView header = (TextView) v.findViewById(R.id.txt_paidheader);
        header.setText("PAID ITEM LIST");
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
}
