/**
 * Created by taolei on 2015/12/25.
 */
var htdModule = angular.module('htd', []);
htdModule.controller('htdCtrl', function ($scope, $http, $log) {
    $scope.showDetail=0;
    $scope.menus = [];
    $http.get("./menu.json").success(function (menuData) {
        $scope.menus = menuData;
    });
    $scope.floors = [];
    $http.get("./floor.json").success(function (floorData) {
        $scope.floors = floorData;
    });
});

