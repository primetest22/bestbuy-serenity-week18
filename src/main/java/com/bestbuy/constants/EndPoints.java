package com.bestbuy.constants;

public class EndPoints {

    /**
     * This is Endpoints of BEST BUY api for product
     */
    public static final String CREATE_PRODUCT = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "products/{productId}";
    public static final String UPDATE_PRODUCT_BY_ID = "products/{productId}";
    public static final String DELETE_PRODUCT_BY_ID = "products/{productId}";

    //This is Endpoints for store
    public static final String GET_STORE = "/stores";
    public static final String UPDATE_STORE_BY_ID = "stores/{storeId}";
    public static final String DELETE_STORE_BY_ID = "stores/{storeId}";
    public static final String GET_SINGLE_STORE_BY_ID = "stores/{storeId}";

    //This is Endpoints for services
    public static final String GET_SERVICE = "/services";
    public static final String UPDATE_SERVICE_BY_ID = "services/{serviceId}";
    public static final String DELETE_SERVICE_BY_ID = "services/{serviceId}";
    public static final String GET_SINGLE_SERVICE_BY_ID = "services/{serviceId}";

    //This is Endpoints for categories
    public static final String GET_CATEGORIES = "/categories";
    public static final String UPDATE_CATEGORIES_BY_ID = "categories/{categoryId}";
    public static final String DELETE_CATEGORIES_BY_ID = "categories/{categoryId}";
    public static final String GET_SINGLE_CATEGORIES_BY_ID = "categories/{categoryId}";


}
