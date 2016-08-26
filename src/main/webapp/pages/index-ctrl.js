/**
 * Created by taolei on 16/7/13.
 */
var app = angular.module('index', []);
app.controller('indexCtrl', function ($scope, $http) {
    $scope.guestList = [];
    $scope.findGuest = function () {
        $http.get("guests?keyword=" + $scope.userName).success(function (response) {
            $scope.guestList = response.data['guests']
        });
    };
    $scope.selectGuest = function (name) {
        $scope.userName = name;
    };
    $scope.addMoney = function () {
        if ($scope.userName == null || $scope.userName == '') {
            alert("请输入客人名!");
            return;
        }
        if ($scope.amount == null || $scope.amount == '') {
            alert("钱呢??!!");
            return;
        }
        if ($scope.amount % 100 != 0) {
            alert("礼金居然是零钱 ?");
            return;
        }
        var param = {
            price: $scope.amount,
            name: $scope.userName
        };
        $http.post("addMoney", param).success(function (response) {
            if (response.success = true) {
                var guest = response.data;
                alert("添加成功");
                console.log("添加成功 --->> : " + guest.name + " : " + guest.price);
            }
        });
    };

});