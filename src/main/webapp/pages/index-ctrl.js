/**
 * Created by taolei on 16/7/13.
 */
var app = angular.module('index', []);
app.controller('indexCtrl', function ($scope, $http) {
    $scope.findGuest = function () {
        $http.get("guests?keyword=" + $scope.keyword);
    }
});