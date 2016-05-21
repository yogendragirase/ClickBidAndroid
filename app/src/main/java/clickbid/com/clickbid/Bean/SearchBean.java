package clickbid.com.clickbid.Bean;

/**
 * Created by Syscraft on 09-May-16.
 */
public class SearchBean {

    String username;
    String creditcarddetails;
    String hasbid;
    String haspaiditem;
   /* String viewtype;

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreditcarddetails() {
        return creditcarddetails;
    }

    public void setCreditcarddetails(String creditcarddetails) {
        this.creditcarddetails = creditcarddetails;
    }

    public String getHasbid() {
        return hasbid;
    }

    public void setHasbid(String hasbid) {
        this.hasbid = hasbid;
    }

    public String getHaspaiditem() {
        return haspaiditem;
    }

    public void setHaspaiditem(String haspaiditem) {
        this.haspaiditem = haspaiditem;
    }

    //// TODO: 13-May-16

    String viewtype;
    String is_guest;

    public String getIs_guest() {
        return is_guest;
    }

    public void setIs_guest(String is_guest) {
        this.is_guest = is_guest;
    }

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }

    public SearchBean(String viewtype, String is_guest) {
        this.viewtype = viewtype;
        this.is_guest = is_guest;
    }

    public SearchBean() {
    }

    String id;
    String first_name;
    String last_name;
    String bidder_number;
    String company;
    String table_name;
    String phone;
    String email;
    String ccs_last_four;
    String customer_profile_id;
    String payment_profile_id;
    String beanstream_id;
    String beanstream_last_four;
    String has_bids;
    String has_purchases;
    String has_checkouts;

    String receipt;
    String registration_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBidder_number() {
        return bidder_number;
    }

    public void setBidder_number(String bidder_number) {
        this.bidder_number = bidder_number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCcs_last_four() {
        return ccs_last_four;
    }

    public void setCcs_last_four(String ccs_last_four) {
        this.ccs_last_four = ccs_last_four;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomer_profile_id() {
        return customer_profile_id;
    }

    public void setCustomer_profile_id(String customer_profile_id) {
        this.customer_profile_id = customer_profile_id;
    }

    public String getPayment_profile_id() {
        return payment_profile_id;
    }

    public void setPayment_profile_id(String payment_profile_id) {
        this.payment_profile_id = payment_profile_id;
    }

    public String getBeanstream_id() {
        return beanstream_id;
    }

    public void setBeanstream_id(String beanstream_id) {
        this.beanstream_id = beanstream_id;
    }

    public String getBeanstream_last_four() {
        return beanstream_last_four;
    }

    public void setBeanstream_last_four(String beanstream_last_four) {
        this.beanstream_last_four = beanstream_last_four;
    }

    public String getHas_bids() {
        return has_bids;
    }

    public void setHas_bids(String has_bids) {
        this.has_bids = has_bids;
    }

    public String getHas_purchases() {
        return has_purchases;
    }

    public void setHas_purchases(String has_purchases) {
        this.has_purchases = has_purchases;
    }

    public String getHas_checkouts() {
        return has_checkouts;
    }

    public void setHas_checkouts(String has_checkouts) {
        this.has_checkouts = has_checkouts;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }
}
