import GLOBAL_CONST from "../config/GlobalConstant";

class ApiCaller {

    static ORIGIN_URL = GLOBAL_CONST.HTTP_PROTOCOL + GLOBAL_CONST.DOMAIN_NAME;
    static REST_API_PREFIX = "/api/v1";
    static REST_API_ENDPOINT = ApiCaller.ORIGIN_URL + ApiCaller.REST_API_PREFIX;

    static GET_LAST_MESSAGES_FROM_ROOM = ApiCaller.REST_API_ENDPOINT + "/room/messages";


    static getCall(url, data, callback, errorCallback, onResp) {
        const keys = Object.keys(data);
        console.log(data);
        console.log(keys);
        let callUrl = url + "?";
        for(let key in keys) {
            callUrl += keys[key] + "=" + data[keys[key]] + "&";
        }
        console.log(callUrl);
        this.call(callUrl, "GET", null, callback, errorCallback, onResp);
    }

    static call(url, method, data, callback, errorCallback, onResp) {
        fetch(url, {
            method: method,
            headers: {
                'Origin': GLOBAL_CONST.HTTP_PROTOCOL + GLOBAL_CONST.DOMAIN_NAME,
                'Content-Type': 'application/json',
                'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Set-cookies, Authorization'
            },
            mode: "cors",
            data: null
        })
            .then(res => {
                if (onResp !== undefined) {
                    return onResp(res);
                } else {
                    return res.json();
                }
            })
            .then((data) => {
                    callback(data);
            })
            .catch((data) => {
                if (errorCallback !== undefined) {
                    errorCallback(data);
                } else {
                    console.log(data);
                }
            })
    }
}

export default ApiCaller;
