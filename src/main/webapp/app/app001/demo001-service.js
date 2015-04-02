define(['diamond'], function (diamond) {
    diamond.lazy.service('demo001Service', function ($http, $log) {
        return {
            personGetRequest: function () {
                $http.get("personGet/" + 111);

            },
            personPostRequest: function (personObj) {
                $http.post("personPost", personObj);

            },
            indexRequest: function () {
                $http.get("index");
            }
        };
    })
});