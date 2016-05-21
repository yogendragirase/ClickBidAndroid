package clickbid.com.clickbid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import clickbid.com.clickbid.Bean.PaidListBean;
import clickbid.com.clickbid.Bean.SearchBean;
import clickbid.com.clickbid.R;

/**
 * Created by Syscraft on 10-May-16.
 */
public class InvoiceListAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<PaidListBean> paidlist = new ArrayList<PaidListBean>();


    public InvoiceListAdapter(Context context, ArrayList<PaidListBean> paidlist) {
        this.context = context;
        this.paidlist = paidlist;
    }

    @Override
    public int getCount() {
        return paidlist.size();
    }

    @Override
    public Object getItem(int position) {
        return paidlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{

        public TextView txt_paiditemtxt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder viewhold;
        final PaidListBean paidbean = paidlist.get(position);
        View v = convertView;

        if(v ==null){

            viewhold = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = layoutInflater.inflate(R.layout.paidlistitem, parent, false);
            viewhold.txt_paiditemtxt = (TextView)v.findViewById(R.id.txt_paiditemtxt);
            viewhold.txt_paiditemtxt.setText(paidbean.getPaiditem());


        }
        return v;
    }
}
