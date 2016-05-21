package clickbid.com.clickbid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import clickbid.com.clickbid.Bean.CheckOutBean;
import clickbid.com.clickbid.Bean.PaidListBean;
import clickbid.com.clickbid.R;

/**
 * Created by Syscraft on 19-May-16.
 */
public class CheckOutAdapter extends BaseAdapter {

    Context context;
    ArrayList<CheckOutBean> checkoutlist = new ArrayList<CheckOutBean>();

    public CheckOutAdapter(Context context, ArrayList<CheckOutBean> checkoutlist) {
        this.context = context;
        this.checkoutlist = checkoutlist;
    }

    @Override
    public int getCount() {
        return checkoutlist.size();
    }

    @Override
    public Object getItem(int position) {
        return checkoutlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        private TextView co_signedrontxt, co_signedronamount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder viewhold;
        final CheckOutBean checkOutBean = checkoutlist.get(position);
        View v = convertView;

        if (v == null) {

            viewhold = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = layoutInflater.inflate(R.layout.checkoutlistitem, parent, false);
            viewhold.co_signedrontxt = (TextView) v.findViewById(R.id.co_signedrontxt);
            viewhold.co_signedronamount = (TextView) v.findViewById(R.id.co_signedronamount);
            viewhold.co_signedrontxt.setText(checkOutBean.getCheckoutitem());
            viewhold.co_signedronamount.setText(checkOutBean.getCheckoutprice());

            if (position == 4){
                v.setBackgroundResource(R.color.green);
            }

        }
        return v;

    }
}
