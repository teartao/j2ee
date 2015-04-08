define(['diamond'], function (diamond) {
    diamond.lazy.service('demo001Service', function ($http, $log) {
        return {
            personGetRequest: function () {
                $log.debug(">>>>>>>>>>>>>>>>>>>>>>");
                $log.debug("[请求]post person 成功");
                return $http.get("personGet/" + 111);
            },
            personPostRequest: function (personObj) {
                $log.debug(">>>>>>>>>>>>>>>>>>>>>>");
                $log.debug("[请求]post person 成功");
                return $http.post("personPost", personObj);
            },
            indexRequest: function () {
                //!!!!!!!!!!!!!!!!! following is error !!!!!!!!!!!
                //why cannot use angular global variable here as following
                //diamond doesn't load the angularJS?

                //$log.debug(">>>>>>>>>>>>>>>>>>>>>>"); //TypeError: Cannot read property 'debug' of undefined
                $log.debug("[请求]post getJson 成功");
                return $http.get("getJson");
            }

        };
    })

    /*return {
        indexRequest: function ($http, $log) {
            //!!!!!!!!!!!!!!!!! following is error !!!!!!!!!!!
            //why cannot use angular global variable here as following
            //diamond doesn't load the angularJS?

            //$log.debug(">>>>>>>>>>>>>>>>>>>>>>"); //TypeError: Cannot read property 'debug' of undefined
            $log.debug("[请求]post person 成功");
            return $http.get("index");
        }
    }*/
});