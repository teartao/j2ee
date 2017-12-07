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
        if (validate()) {
            var param = {
                name: $scope.userName,
                price: $scope.amount
            };
            $http.post("addMoney", param).success(function (response) {
                if (response.success == true) {
                    var guest = response.data;
                    alert("出价成功");
                    console.log("出价成功 --->> : " + guest.name + " : " + guest.price);
                } else {
                    alert(response.error)
                }
            });
        }
    };

    $scope.addGuest = function () {
        if (validate()) {
            var param = {
                name: $scope.userName,
                price: $scope.amount
            };
            $http.post("addGuest", param).success(function (response) {
                if (response.success == true) {
                    var guest = response.data;
                    alert("客人添加成功");
                    console.log("客人添加成功 : " + guest.name + " : " + guest.price);
                } else {
                    alert(response.error)
                }
            });
        }
    };

    function validate() {
        if ($scope.userName == null || $scope.userName == '') {
            alert("请输入客人名 !");
            return false;
        }
        if ($scope.amount == null || $scope.amount == '') {
            alert("钱呢 ??!!");
            return false;
        }
        if ($scope.amount % 100 != 0) {
            alert("你居然给零钱 ?");
            return false;
        }
        return true;
    }

    $scope.enter = function (ev) {
        if (ev.keyCode !== 13) return;
        $scope.addMoney();
    }

});