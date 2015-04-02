define(['diamond','./demo001-service'], function (diamond,demo001Service) {
    diamond.lazy.controller('demo001Controller', function ($rootScope, $scope, $http,$log) {
        loadPage();

        function loadPage() {

            var personObj = {id: 1, name: '张三', sex: '男', age: 23};
            demo001Service.personGetRequest().success(function (response) {
                $log.debug("==========================");
                $log.debug("[返回]get person 成功");
                $log.debug(response);
            });

            demo001Service.personPostRequest(personObj).success(function (response) {
                $log.debug("==========================");
                $log.debug("[返回]post person 成功");
                $log.debug(response);
            });

            demo001Service.indexRequest().success(function (response) {
                $log.debug("==========================");
                $log.debug("[返回]index 成功");
                $log.debug(response);
                $scope.studentList = response;
            })
        }
    });
});


