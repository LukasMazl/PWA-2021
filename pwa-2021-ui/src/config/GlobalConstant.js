class GlobalConstant {
    static HTTP_PROTOCOL = "http://";
    static DOMAIN_NAME = "pwa-2021.herokuapp.com";
    static WEB_SOCKET_URL_SUFFIX = "/ws";


    /*static HTTP_PROTOCOL = "http://";
    static DOMAIN_NAME = "localhost:8080";*/
    static FULL_WEB_SOCKET_URL = GlobalConstant.HTTP_PROTOCOL + GlobalConstant.DOMAIN_NAME + GlobalConstant.WEB_SOCKET_URL_SUFFIX;

}

export default GlobalConstant;
