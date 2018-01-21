package com.app.mycontactlist.common.utils;
public class Constants {
    public static final String SPACE = " ";
    public static final String EMPTY_TEXT = "";

    public interface HeaderKeys {
        String API_VERSION_CODE = "X-VERSION-CODE";
        String X_CLIENT = "X-Client";
        String CONTENT_TYPE = "Content-Type";
        String CACHE_CONTROL = "Cache-Control";
        String AUTHORIZATION = "Authorization";
        String X_SESSION = "authtoken";
    }

    public interface AppConstants {
        String PLATFORM = "Android";
        String CONTENT_FORMAT = "application/json";
        String NO_CACHE = "no-cache";
    }



    public interface API_KEYS{
        String CONTACT_ID="contact_id";
        String USER_ID = "user_id";
        String MATCH_USER_ID="match_user_id";
        String USER_NAME="user_name";
        String LIKE_USER_ID = "like_user_id";
        String FLAG = "flag";
        String PET_ID = "pet_id";
        String PIC_ID="pic_id";
        String CATEGORY_ID = "category_id";
        String CATEGORY_TYPE = "category_type";
        String CURRENT_ADDRESS="current_address";
        String ADDRESS="address";
        String USER_TYPE="user_type";
        String ABOUT_ME="about_me";
        String PET_NAME = "pet_name";
        String ABOUT = "about";
        String AGE = "age";
        String IMAGE = "image";
        String GENDER = "gender";
        String EMAIL="email";
        String LAT="lat";
        String LNG="long";
        String DISTANCE="distance";
        String AGE_MIN="agemin";
        String AGE_MAX="agemax";
        String TYPE="type";
        String PAGE="page";
        String PER_PAGE="per_page";
    }
}
