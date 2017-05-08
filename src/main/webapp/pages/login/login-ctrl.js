/**
 * Created by taolei on 2017/5/8.
 */

var app = angular.module('login', []);
app.controller('loginCtrl', function ($scope, $http,$log) {
    $scope.login = function () {
        var param = {
            userName: $scope.userName,
            userName: $scope.password
        };
        $http.post("/cm/login" ,param).success(function (response) {
            $log.debug(response);
        });
    };
});