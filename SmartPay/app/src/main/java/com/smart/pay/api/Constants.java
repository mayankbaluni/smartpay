package com.smart.pay.api;


public class Constants {

    public static final String SERVER_URL = "http://workoholicks.com/demo/smartpay/admin/";

    public static final String USER_MOBILE_REGISTRATION = "Smartpay/user_phone_no_registration";
    public static final String VERIFY_USER_MOBILE = "Smartpay/verfiy_user_phone_no";
    public static final String USER_SIGNUP = "Smartpay/user_signup";
    public static final String USER_LOGIN = "Smartpay/user_login";


    public static final String GET_ALL_SELLER_PRODUCTS = "Product/get_all_product";
    public static final String GET_ALL_CATEGORIES = "Webservice_App/get_cat";
    public static final String GET_ALL_SUB_CAT = "Webservice_App/get_sub_cat/{cat_id}";

    public static final String GET_ALL_SERVICE_CATEGORIES = "Webservice_App/get_service_cat";

    public static final String GET_SUBCAT_PRODUCTS = "Product/get_all_product_subcat";

    public static final String GET_SINGLE_PRODUCT_DETAIL = "Product/get_single_product";

    public static final String BOOK_ORDER = "Webservice_App/bookorder";

    public static final String BOOK_APPOINTMENT = "Webservice_App/service_booking";


    public static final String UPLOAD_IMAGE_FOLDER = SERVER_URL + "assets/uploads/";
    public static final String PRODUCT_IMAGE_PATH = SERVER_URL + "assets/uploads/product_images/";
    public static final String SERVICE_IMAGE_PATH = SERVER_URL + "assets/uploads/service_images/";

    public static final String SELLER_IMAGE_PATH = SERVER_URL;

    public static final String GET_ALL_NEARBY_SELLERS = "Webservice_App/get_all_top_sellers";

    public static final String GET_ALL_SELLER_SERVICES = "Services/get_all_services";

    public static final String GET_ALL_NEARBY_SELLERS_FROM_CATEGORY = "Webservice_App/get_all_sellers_cat";


    public static final String GET_ALL_YOUR_ORDERS = "Webservice_App/userorders";

    public static final String GET_ALL_YOUR_APPOINTMENTS = "Webservice_App/userbooking";

    public static final String GET_ALL_TRENDING_PRODUCTS = "Product/get_all_trending_product";

    public static final String GET_TOP_BRANDS = "Webservice_App/get_all_brands_with_products";

    public static final String RATE_REVIEW_SELLER_URL = "Webservice_App/rate_seller";
    public static final String GET_SELLER_REVIEW_LIST = "Webservice_App/get_seller_review";

    public static final String SEARCH_ALL_PRODUCTS = "Webservice_App/searchproduct";

    public static final String ADD_PRODUCT_REVIEW = "Webservice_App/rate_product";
    public static final String GET_PRODUCT_REVIEW = "Webservice_App/get_all_product_reviews";

    public static final String GET_ACCOUNT_DETAILS = "Webservice_App/get_user_account_details";

    public static final String EDIT_USER_ACCOUNT = "Webservice_App/edit_user_account";

    public static final String FORGOT_PASSWORD_REQUEST = "Webservice_App/forgot_password";
    public static final String SEND_PASSWORD_OTP = "Webservice_App/send_change_password_otp";
    public static final String CHANGE_PASSWORD_URL = "Webservice_App/change_password";

    public static final String GET_SERVICES_BY_CATEGORY = "Webservice_App/get_list_of_services";

    public static final String GET_HOMEPAGE_SLIDER = "Webservice_App/get_all_homepage_slider";
    public static final String GET_HOMEPAGE_SLIDER2 = "Webservice_App/get_all_homepage_slider2";


    public static final String GET_REAL_TIME_DELIVERY_TRACKING = "https://api.trackingmore.com/v2/trackings/realtime";

    public static final String GET_SHIPMENT_DETAILS = "Webservice_App/get_shipment_details";

    public static final String GET_APP_SETTINGS = "Webservice_App/get_all_app_settings";

    public static final String GET_ALL_FLASH_DEALS = "Webservice_App/get_all_flash_deals";
    public static final String GET_FLASH_DEAL_PRODUCTS = "Webservice_App/get_all_flash_deal_products";

    public static final String GET_ALL_FASHION_OFFERS = "Webservice_App/get_fashion_offers";
    public static final String GET_ALL_TOP_OF_THE_DAY = "Webservice_App/get_top_of_the_day";

    public static final String GET_TOP_SHOPPING_OFFERS = "Webservice_App/get_top_shopping_offers";

    public static final String GET_ALL_PRODUCTS_FROM_CHILD_CATEGORY = "Product/get_all_product_child_cat";
    public static final String GET_ALL_PRODUCTS_FROM_BRAND = "Product/get_all_products_by_brand";

    public static final String GET_ALL_PRODUCTS_FROM_CHILD_CATEGORY_LIMITED = "Product/get_all_product_child_cat_limited";


    //For Adding to Product To Cart

    public static final String ADD_PRODUCT_TO_CART = "Neworders/add_product_to_cart";
    public static final String GET_ONLINE_CART_LIST = "Neworders/get_all_cart_list";
    public static final String UPDATE_INCREASE_CART = "Neworders/update_increase_product_qunatity";
    public static final String UPDATE_DECREASE_CART = "Neworders/update_decrease_product_qunatity";
    public static final String UPDATE_DELETE_CART = "Neworders/delete_product_from_cart";

    public static final String NEW_ORDER_BOOKING = "Neworders/add_new_booking";

    public static final String GET_CATEGORY_DETAIL = "Webservice_App/get_products_by_category";

    public static final String GET_ALL_CHILD_CATEGORIES = "Webservice_App/get_child_categories_by_category";

    public static final String GET_ALL_BRANDS = "Webservice_App/get_all_brands";


    //Smart Pay New API

    public static final String ADD_MONEY_TO_WALLET = "Smartpay/add_money_to_wallet";
    public static final String SEND_MONEY_TO_USERS = "Smartpay/send_money_to_user_wallet";
    public static final String GET_WALLET_TRANSACTIONS = "Smartpay/get_all_wallet_transactions";

    public static final String PLACE_MOBILE_RECHARGE_REQUEST="Smartpay/add_mobile_recharge_request";


}
