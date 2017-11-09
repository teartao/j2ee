/**
 * Created by taolei on 2015/12/25.
 */
var demoModule = angular.module('demo', []);
demoModule.controller('demoCtrl', function ($scope, $http, $log) {
    $scope.showIcon = {
        editable: false,
        cleanable: false,
        loading: false
    };
    $scope.someText = "啊~~ 5~环 ";

    $scope.toEdit = function () {
        $scope.showIcon.editable = true;
    };

    $scope.saveEdit = function () {
        $scope.showIcon.loading = true;
        $http.get("./test.json").success(function () {
            $scope.showIcon.editable = false;
            $scope.showIcon.loading = false;

        });
    };

    $scope.cancelEdit = function () {
        $scope.showIcon.editable = false;
    };
    $scope.cleanText = function () {
        $scope.someText = "";
    }
});