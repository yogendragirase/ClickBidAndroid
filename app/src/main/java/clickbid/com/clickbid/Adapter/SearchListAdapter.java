package clickbid.com.clickbid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import clickbid.com.clickbid.Activities.CBCheckout;
import clickbid.com.clickbid.Activities.CBEditBidder;
import clickbid.com.clickbid.Activities.CBPaidListActivty;
import clickbid.com.clickbid.Bean.SearchBean;
import clickbid.com.clickbid.R;

/**
 * Created by Syscraft on 10-May-16.
 */
public class SearchListAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;
    ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
    public String getid ;

    public SearchListAdapter(Context context, ArrayList<SearchBean> searchlist) {
        this.context = context;
        this.searchlist = searchlist;
    }

    @Override
    public int getCount() {
        return searchlist.size();
    }

    @Override
    public Object getItem(int position) {
        return searchlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (searchlist.get(position).getViewtype().equals("REGISTERD")) {
            return 0;
        } else if (searchlist.get(position).getViewtype().equals("GUEST")) {
            return 1;
        } else if (searchlist.get(position).getViewtype().equals("TITLE")) {
            return 2;
        }
        return 1;
    }

    public class ViewHolder{
       public LinearLayout llBidder;
        public TextView txt_editsearch,txt_registerusername,txt_creditinfo,txt_hasbid,txt_haspaiditem,txt_invoice,txt_cartitem;
        public LinearLayout llGuest;
        public TextView txt_listbtnadd,txt_guestname;
        public TextView txtx_listheader,txtx_listheader_hint;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder viewhold;
        final SearchBean bean = searchlist.get(position);
        View v = convertView;
        int type = getItemViewType(position);

          //set adapter according to viewholder with multiple views
       // if(v == null){
            viewhold = new ViewHolder();
            if(type == 0){
                v = layoutInflater.inflate(R.layout.searchlistitemsection, parent, false);
                viewhold.llBidder = (LinearLayout)v.findViewById(R.id.llBidder);
                viewhold.llBidder.setOnClickListener(this);
                viewhold.txt_editsearch = (TextView)v.findViewById(R.id.txt_editsearch);
                viewhold.txt_registerusername = (TextView)v.findViewById(R.id.txt_registerusername);
                viewhold.txt_creditinfo = (TextView)v.findViewById(R.id.txt_creditinfo);
                viewhold.txt_hasbid = (TextView)v.findViewById(R.id.txt_hasbid);
                viewhold.txt_haspaiditem = (TextView)v.findViewById(R.id.txt_haspaiditem);

                viewhold.txt_invoice = (TextView)v.findViewById(R.id.txt_invoice);
                viewhold.txt_cartitem = (TextView)v.findViewById(R.id.txt_cartitem);
                viewhold.txt_editsearch.setTag(position);
                viewhold.txt_cartitem.setTag(position);

                if(bean.getHas_purchases().equals("0"))
                {
                    viewhold.txt_invoice.setVisibility(View.INVISIBLE);
                }else
                {
                    viewhold.txt_invoice.setVisibility(View.VISIBLE);
                }

                if(bean.getHas_bids().equals("0"))
                {
                    viewhold.txt_cartitem.setVisibility(View.VISIBLE);
                }else
                {
                    viewhold.txt_cartitem.setVisibility(View.VISIBLE);
                }

                viewhold.txt_registerusername.setText(bean.getLast_name() + "-" + bean.getFirst_name() + " #" + bean.getBidder_number());

                viewhold.txt_editsearch.setOnClickListener(this);
                viewhold.txt_cartitem.setOnClickListener(this);
                viewhold.txt_invoice.setOnClickListener(this);


            }
            else if(type == 1){
                v = layoutInflater.inflate(R.layout.serachlistitemsection2,parent,false);
                viewhold.llGuest = (LinearLayout)v.findViewById(R.id.llGuest);
                viewhold.llGuest.setOnClickListener(this);
                viewhold.txt_listbtnadd = (TextView)v.findViewById(R.id.txt_listbtnadd);
                viewhold.txt_guestname = (TextView)v.findViewById(R.id.txt_guestname);
                viewhold.txt_guestname.setText(bean.getLast_name() + "-" + bean.getFirst_name());
                viewhold.txt_listbtnadd.setTag(position);
                viewhold.txt_listbtnadd.setOnClickListener(this);
            }
           else if(type == 2)
            {
                v = layoutInflater.inflate(R.layout.listheader,parent,false);
                viewhold.txtx_listheader = (TextView)v.findViewById(R.id.txtx_listheader);
                viewhold.txtx_listheader_hint = (TextView)v.findViewById(R.id.txtx_listheader_hint);
                if(bean.getIs_guest().equals("true"))
                {
                    viewhold.txtx_listheader.setText("REGISTERED GUEST RESULTS");
                    viewhold.txtx_listheader_hint.setVisibility(View.VISIBLE);
                }else
                {
                    viewhold.txtx_listheader.setText("BIDDER RESULTS");
                    viewhold.txtx_listheader_hint.setVisibility(View.GONE);
                }
            }
        else{

            }

            /*if(position == Integer.parseInt(CBSearchlist.registerlistsize)){
                v = layoutInflater.inflate(R.layout.listheader,parent,false);
                viewhold.txtx_listheader = (TextView)v.findViewById(R.id.txtx_listheader);
                viewhold.txtx_listheader.setText("Guest User");
            }*/
       // }


        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_editsearch:
                int i = (int) v.getTag();
                getid = searchlist.get(i).getId();
                Intent editintent = new Intent(context, CBEditBidder.class);
                editintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                editintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                editintent.putExtra("id",getid);
                editintent.putExtra("type","register");
                context.startActivity(editintent);
                break;

            case R.id.txt_cartitem:
                int k = (int)v.getTag();
                getid = searchlist.get(k).getId();
                Intent cartintent = new Intent(context, CBCheckout.class);
                cartintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cartintent.putExtra("id",getid);
                context.startActivity(cartintent);
                break;

            case R.id.txt_invoice:
                Intent invoiceintent = new Intent(context, CBPaidListActivty.class);
                invoiceintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(invoiceintent);
                break;
            case R.id.llBidder:
               /* Intent editBidder = new Intent(context,CBEditBidder.class);
                context.startActivity(editBidder);*/
                break;
            case R.id.llGuest:
                break;

            case R.id.txt_listbtnadd:

                int j = (int) v.getTag();
                getid = searchlist.get(j).getId();
                String regid = searchlist.get(j).getRegistration_id();
                Intent editGuest = new Intent(context,CBEditBidder.class);
                editGuest.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                editGuest.putExtra("id",getid);
              //  editGuest.putExtra("regisid",regid);
                editGuest.putExtra("type","guest");
                context.startActivity(editGuest);

        }
    }
}
