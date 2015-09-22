define(['diamond', './demo001-service'], function (diamond) {
    diamond.lazy.controller('demo001Controller', function ($rootScope, $scope, $http, $log, demo001Service) {
        $scope.xList = [
            {id: "001", name: 'name1'},
            {id: "002", name: 'name2'},
            {id: "003", name: 'name3'},
            {id: "004", name: 'name4'},
            {id: "005", name: 'name5'}];

        loadPage();

        function loadPage() {

            var personObj = {id: 1, name: '张三', sex: '男', age: 23};
            $scope.doGet = function () {
                demo001Service.personGetRequest().success(function (response) {
                    $log.debug("<<<<<<<<<<<<<<<<");
                    $log.debug("[返回]get person 成功");
                    $log.debug(response);
                });
            };

            $scope.doPost = function () {
                demo001Service.personPostRequest(personObj).success(function (response) {
                    $log.debug("<<<<<<<<<<<<<<<<");
                    $log.debug("[返回]post person 成功");
                    $log.debug(response);
                });
            };


            $scope.getStudentList = function () {
                demo001Service.indexRequest().success(function (response) {
                    $log.debug("<<<<<<<<<<<<<<<<");
                    $log.debug("[返回]getJson 成功");
                    $log.debug(response);
                    $scope.studentList = response;
                });
            };


        }
    });
});


