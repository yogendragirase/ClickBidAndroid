package clickbid.com.clickbid.constants;

/**
 * Created by Syscraft on 10-May-16.
 */
public class WebServiceConstants {
//url
    public static final String URL = "http://clickbid.cc/zendapp/public/";

    //// TODO: 13-May-16  search url

    public static final String SEARCH_URL = "http://clickbid.cc/cbapi/butler-landing/";

//method name

    public static final String METHOD_LOGIN = "butler-login/";
    public static final String EDIT_REGISTERURL = "http://clickbid.cc/cbapi/butler-bidder/";
    public static final String EDIT_GUESTURL = "http://clickbid.cc/cbapi/butler-guest/";
    public static final String UPDATE_REGISTERUSER = "https://clickbid.cc/cbapi/butler-updatebidder/";
    public static final String Butler_checkout = "https://clickbid.cc/cbapi/butler-biddercheckout/";

    public static String getMethodUrl(String methodName) {
        String url = "";
        url = URL + methodName;
        /*if(methodName.equalsIgnoreCase("user/get_all_schools"))
		{
			url="http://198.38.86.247/rideversity_server/index.php/webservices/";
		}*/
        return url;
    }

}
