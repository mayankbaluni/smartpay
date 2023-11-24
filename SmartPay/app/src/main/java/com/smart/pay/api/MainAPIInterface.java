package com.smart.pay.api;

import com.smart.pay.models.input.MobileRechargeModel;
import com.smart.pay.models.output.AddProductToCartModel;
import com.smart.pay.models.output.AllChildCategoryOutput;
import com.smart.pay.models.output.AppointmentBookingInputModel;
import com.smart.pay.models.output.AppointmentBookingOutputModel;
import com.smart.pay.models.output.BookOrderInputModel;
import com.smart.pay.models.output.BookOrderModel;
import com.smart.pay.models.output.CategoryListModel;
import com.smart.pay.models.output.CommonOutput;
import com.smart.pay.models.output.DetailProductInputModel;
import com.smart.pay.models.output.EditUserAccountInputModel;
import com.smart.pay.models.output.FlashDealsProductsOutputModel;
import com.smart.pay.models.output.ForgotPasswordOutputModel;
import com.smart.pay.models.output.GetAccountDetailsOutputModel;
import com.smart.pay.models.output.GetAllFashionOffersModel;
import com.smart.pay.models.output.GetAllFlashDealsModel;
import com.smart.pay.models.output.GetAppSettings;
import com.smart.pay.models.output.GetBrandsOutput;
import com.smart.pay.models.output.GetCategoryDetailOutputModel;
import com.smart.pay.models.output.GetHomepageSliderOutputModel;
import com.smart.pay.models.output.GetOnlineCartOutput;
import com.smart.pay.models.output.GetSellerModel;
import com.smart.pay.models.output.GetShipmentDetailsOutputModel;
import com.smart.pay.models.output.GetTopOfTheDayOutputModel;
import com.smart.pay.models.output.GetTopShoppingOffersModel;
import com.smart.pay.models.output.GetWalletTransactionsOutput;
import com.smart.pay.models.output.IsSellerExist;
import com.smart.pay.models.output.NewServiceListModel;
import com.smart.pay.models.output.NewServiceListOutputModel;
import com.smart.pay.models.output.NormalResponseBody;
import com.smart.pay.models.output.PostSellerReviewInputModel;
import com.smart.pay.models.output.PostSellerReviewOutputModel;
import com.smart.pay.models.output.ProductOrderNewModel;
import com.smart.pay.models.output.ProductOrderNewOutputModel;
import com.smart.pay.models.output.ProductReviewListInputModel;
import com.smart.pay.models.output.ProductReviewListOutputModel;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.models.output.RateProductInputModel;
import com.smart.pay.models.output.SearchProductOutputModel;
import com.smart.pay.models.output.SellerReviewListOutputModel;
import com.smart.pay.models.output.ServiceListModel;
import com.smart.pay.models.output.TrackDeliveryInputModel;
import com.smart.pay.models.output.TrackDeliveryOutputModel;
import com.smart.pay.models.output.UpdateCartOutput;
import com.smart.pay.models.output.UserProfileOutput;
import com.smart.pay.models.output.YourAppointmentOutputModel;
import com.smart.pay.models.output.YourOrderOutputModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface MainAPIInterface<R extends Retrofit> {

    @Multipart
    @POST(Constants.USER_MOBILE_REGISTRATION)
    Call<UserProfileOutput> registerUserMobile(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part country_code,
            @Part MultipartBody.Part mobile
    );

    @Multipart
    @POST(Constants.VERIFY_USER_MOBILE)
    Call<NormalResponseBody> verifyUserMobile(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part mobile,
            @Part MultipartBody.Part otp
    );

    @Multipart
    @POST(Constants.USER_SIGNUP)
    Call<UserProfileOutput> userSignup(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part email,
            @Part MultipartBody.Part phone_no,
            @Part MultipartBody.Part username,
            @Part MultipartBody.Part password
    );


    @Multipart
    @POST(Constants.USER_LOGIN)
    Call<UserProfileOutput> userLogin(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part mobile,
            @Part MultipartBody.Part password
    );


    @GET(Constants.GET_ALL_CATEGORIES)
    Call<CategoryListModel> getCategories(@Header("X-API-KEY") String xAccessToken);

    @GET(Constants.GET_ALL_SUB_CAT)
    Call<CategoryListModel> getSubCategories(
            @Header("X-API-KEY") String xAccessToken,
            @Path("cat_id") String cat_id
    );


    @Multipart
    @POST(Constants.GET_SUBCAT_PRODUCTS)
    Call<ProductsListModel> getAllProducts(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part subcat_id
    );


    @POST(Constants.GET_SINGLE_PRODUCT_DETAIL)
    Call<ProductsListModel> getProductDetail(
            @Header("X-API-KEY") String xAccessToken,
            @Body DetailProductInputModel detailProductInputModel
    );


    @GET(Constants.GET_ALL_SERVICE_CATEGORIES)
    Call<ServiceListModel> getServiceCategories(@Header("X-API-KEY") String xAccessToken);


    @GET(Constants.GET_ALL_NEARBY_SELLERS)
    Call<GetSellerModel> getAllNearbySellers(
            @Header("X-API-KEY") String xAccessToken
    );


    @POST(Constants.GET_ALL_TRENDING_PRODUCTS)
    Call<ProductsListModel> getAllTrendingProducts(
            @Header("X-API-KEY") String xAccessToken
    );


    @Multipart
    @POST(Constants.GET_ALL_SELLER_SERVICES)
    Call<NewServiceListModel> getAllServices(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part seller_id
    );


//    @Multipart
//    @POST(Contants.GET_ALL_NEARBY_SELLERS_FROM_CATEGORY)
//    Call<SellerServiceListModel> getAllNearbySellers(
//            @Header("X-API-KEY") String xAccessToken,
//            @Part MultipartBody.Part cat_id
//    );


    @Multipart
    @POST(Constants.GET_ALL_YOUR_ORDERS)
    Call<YourOrderOutputModel> getAllYourOrders(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id
    );


    @POST(Constants.BOOK_ORDER)
    Call<BookOrderModel> bookOrder(
            @Header("X-API-KEY") String xAccessToken,
            @Body BookOrderInputModel bookOrderInputModell
    );


    @Multipart
    @POST(Constants.GET_ALL_YOUR_APPOINTMENTS)
    Call<YourAppointmentOutputModel> getAllYourAppointments(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id
    );

    @POST(Constants.BOOK_APPOINTMENT)
    Call<AppointmentBookingOutputModel> bookAppointment(
            @Header("X-API-KEY") String xAccessToken,
            @Body AppointmentBookingInputModel appointmentBookingInputModel

    );


    @POST(Constants.ADD_PRODUCT_TO_CART)
    Call<IsSellerExist> addProductToCart(
            @Header("X-API-KEY") String xAccessToken,
            @Body AddProductToCartModel addProductToCartModel

    );

    @Multipart
    @POST(Constants.GET_ONLINE_CART_LIST)
    Call<GetOnlineCartOutput> getOnlineCartList(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id
    );

    @Multipart
    @POST(Constants.UPDATE_INCREASE_CART)
    Call<UpdateCartOutput> updateCartIncrease(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id,
            @Part MultipartBody.Part seller_id,
            @Part MultipartBody.Part product_id
    );


    @Multipart
    @POST(Constants.UPDATE_DECREASE_CART)
    Call<UpdateCartOutput> updateCartDecrease(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id,
            @Part MultipartBody.Part seller_id,
            @Part MultipartBody.Part product_id
    );

    @Multipart
    @POST(Constants.UPDATE_DELETE_CART)
    Call<IsSellerExist> updateCartDelete(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id,
            @Part MultipartBody.Part seller_id,
            @Part MultipartBody.Part product_id
    );

    @POST(Constants.NEW_ORDER_BOOKING)
    Call<ProductOrderNewOutputModel> newOrderBookingRequest(
            @Header("X-API-KEY") String xAccessToken,
            @Body ProductOrderNewModel productOrderNewModel


    );


    @Multipart
    @POST(Constants.GET_CATEGORY_DETAIL)
    Call<GetCategoryDetailOutputModel> getCategoryDetail(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part category_id
    );

    @Multipart
    @POST(Constants.GET_ALL_CHILD_CATEGORIES)
    Call<AllChildCategoryOutput> getAllChildCategories(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part category_id
    );


    @GET(Constants.GET_ALL_BRANDS)
    Call<GetBrandsOutput> getAllBrands(
            @Header("X-API-KEY") String xAccessToken
    );

    @GET(Constants.GET_TOP_BRANDS)
    Call<GetBrandsOutput> getAllTopBrands(
            @Header("X-API-KEY") String xAccessToken
    );


    @Multipart
    @POST(Constants.SEARCH_ALL_PRODUCTS)
    Call<SearchProductOutputModel> searchAllProducts(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part search_query
    );

    @POST(Constants.RATE_REVIEW_SELLER_URL)
    Call<PostSellerReviewOutputModel> postSellerReview(
            @Header("X-API-KEY") String xAccessToken,
            @Body PostSellerReviewInputModel postSellerReviewInputModel


    );


    @POST(Constants.ADD_PRODUCT_REVIEW)
    Call<PostSellerReviewOutputModel> postProductReview(
            @Header("X-API-KEY") String xAccessToken,
            @Body RateProductInputModel rateProductInputModel
    );


    @Multipart
    @POST(Constants.GET_SELLER_REVIEW_LIST)
    Call<SellerReviewListOutputModel> getSellerReviewList(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part seller_id
    );


    @POST(Constants.GET_PRODUCT_REVIEW)
    Call<ProductReviewListOutputModel> getProductReviewList(
            @Header("X-API-KEY") String xAccessToken,
            @Body ProductReviewListInputModel productReviewListInputModel
    );

    @Multipart
    @POST(Constants.GET_ACCOUNT_DETAILS)
    Call<GetAccountDetailsOutputModel> getUserAccountDetails(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part customer_id
    );

    @POST(Constants.EDIT_USER_ACCOUNT)
    Call<PostSellerReviewOutputModel> postEditUserAccount(
            @Header("X-API-KEY") String xAccessToken,
            @Body EditUserAccountInputModel editUserAccountInputModel
    );

    @Multipart
    @POST(Constants.FORGOT_PASSWORD_REQUEST)
    Call<ForgotPasswordOutputModel> forgotPasswordRequest(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part email
    );

    @Multipart
    @POST(Constants.SEND_PASSWORD_OTP)
    Call<ForgotPasswordOutputModel> sendPasswordChangeOtp(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part user_id,
            @Part MultipartBody.Part email_id
    );

    @Multipart
    @POST(Constants.CHANGE_PASSWORD_URL)
    Call<ForgotPasswordOutputModel> changePasswordRequest(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part user_id,
            @Part MultipartBody.Part user_old_password,
            @Part MultipartBody.Part user_new_password,
            @Part MultipartBody.Part otp
    );


    @Multipart
    @POST(Constants.GET_SERVICES_BY_CATEGORY)
    Call<NewServiceListOutputModel> getAllServicesByCategory(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part category_id
    );

    @GET(Constants.GET_HOMEPAGE_SLIDER)
    Call<GetHomepageSliderOutputModel> getHomePageSlider(
            @Header("X-API-KEY") String xAccessToken
    );

    @GET(Constants.GET_HOMEPAGE_SLIDER2)
    Call<GetHomepageSliderOutputModel> getHomePageSlider2(
            @Header("X-API-KEY") String xAccessToken
    );

    @Multipart
    @POST(Constants.GET_SHIPMENT_DETAILS)
    Call<GetShipmentDetailsOutputModel> getShipmentDetails(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part order_id,
            @Part MultipartBody.Part product_id

    );

    @GET(Constants.GET_APP_SETTINGS)
    Call<GetAppSettings> getAppSettingsRequest(
            @Header("X-API-KEY") String xAccessToken
    );


    //Get All Flash Deals


    @GET(Constants.GET_ALL_FLASH_DEALS)
    Call<GetAllFlashDealsModel> getAllFlashDeals(
            @Header("X-API-KEY") String xAccessToken

    );

    //Get All Flash Deal Products


    @Multipart
    @POST(Constants.GET_FLASH_DEAL_PRODUCTS)
    Call<FlashDealsProductsOutputModel> getAllFlashDealProducts(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part products

    );

    //Get All Fashion Offers

    @GET(Constants.GET_ALL_FASHION_OFFERS)
    Call<GetAllFashionOffersModel> getAllFationOffers(
            @Header("X-API-KEY") String xAccessToken

    );


    // GET TOP OF THE DAY

    @GET(Constants.GET_ALL_TOP_OF_THE_DAY)
    Call<GetTopOfTheDayOutputModel> getAllTopOfTheDay(
            @Header("X-API-KEY") String xAccessToken

    );


    //Get Top Shopping Offers

    @GET(Constants.GET_TOP_SHOPPING_OFFERS)
    Call<GetTopShoppingOffersModel> getTopShoppingOffers(
            @Header("X-API-KEY") String xAccessToken

    );

    //Get Top Shopping Offers

    @Multipart
    @POST(Constants.GET_ALL_PRODUCTS_FROM_CHILD_CATEGORY)
    Call<ProductsListModel> getAllProductsFromChildCategory(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part child_category_id
    );

    //GET PRODUCTS BY BRAND

    @Multipart
    @POST(Constants.GET_ALL_PRODUCTS_FROM_BRAND)
    Call<ProductsListModel> getAllProductsFromBrand(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part brand_id
    );


    @Multipart
    @POST(Constants.GET_ALL_PRODUCTS_FROM_CHILD_CATEGORY_LIMITED)
    Call<ProductsListModel> getAllProductsFromChildCategoryLimited(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part child_category_id
    );


    @POST
    Call<TrackDeliveryOutputModel> getRealTimeTrackingData(
            @Header("Content-Type") String applcationJson,
            @Header("Trackingmore-Api-Key") String trackingKey,
            @Body TrackDeliveryInputModel trackDeliveryInputModel,
            @Url String url
    );


    //Smart Pay New API

    @Multipart
    @POST(Constants.ADD_MONEY_TO_WALLET)
    Call<IsSellerExist> addMoneyToWallet(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part wallet_id,
            @Part MultipartBody.Part amount
    );


    @Multipart
    @POST(Constants.SEND_MONEY_TO_USERS)
    Call<IsSellerExist> sendMoneyToWallet(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part sender_wallet_id,
            @Part MultipartBody.Part phone_no,
            @Part MultipartBody.Part amount
    );


    @Multipart
    @POST(Constants.GET_WALLET_TRANSACTIONS)
    Call<GetWalletTransactionsOutput> getWalletTransactions(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part wallet_id

    );


    @POST(Constants.PLACE_MOBILE_RECHARGE_REQUEST)
    Call<CommonOutput> placeMobileRechargeRequest(
            @Header("X-API-KEY") String xAccessToken,
            @Body MobileRechargeModel mobileRechargeModel
    );


}
