define(['diamond'], function (diamond) {
    diamond.lazy.controller('bdMapController', function ($rootScope, $scope, $http, $log) {

        var pointLat = "116.403981";//默认北京经纬度
        var pointLng = "39.915212";
        var fullAddress = "";

        $scope.province = "江苏";
        $scope.city = "南京";
        $scope.area = "浦口";
        $scope.address = "天浦路26号";
        $scope.pointLat = "118.666232";
        $scope.pointLng = "32.073093";

        //$scope.province = "";
        //$scope.city = "";
        //$scope.area = "";
        //$scope.address = "";
        //$scope.pointLat = "";
        //$scope.pointLng = "";

        var marker = {};
        var fullAddress = $scope.province + $scope.city + $scope.area + $scope.address;
        pointLat = $scope.pointLat ? $scope.pointLat : pointLat;
        pointLng = $scope.pointLng ? $scope.pointLng : pointLng;

        loadPage();

        function loadPage() {
            loadMapByKeyWord();
        }


        $scope.loadMapByKeyWord = loadMapByKeyWord;
        function loadMapByKeyWord() {
            /**
             * 地图基本配置
             */
            var map = new BMap.Map("allmap");
            var point = new BMap.Point(pointLat, pointLng);
            map.centerAndZoom(point, 16);
            map.enableScrollWheelZoom(true);
            markPoint(map, point);
            var myGeo = new BMap.Geocoder();// 创建地址解析器实例

            /**
             * 根据地址解析经纬度
             */
            myGeo.getPoint(fullAddress, function (pointByAddress) {// 将地址解析结果显示在地图上,并调整地图视野
                if (pointByAddress) {
                    point = pointByAddress;
                    markPoint(map, point);
                    map.centerAndZoom(pointByAddress, 16);
                } else {
                    $log.error("您选择地址没有解析到结果!");
                }
            }, '');//根据地址解析经纬度end

            /**
             * 初始化地图上的点：
             * 1、如果没有地址和经纬度，默认解析到天安门
             * 2、如果有经纬度但没有地址，则按照经纬度添加标记点
             * 3、如果有地址没有经纬度，则按照地址添加标记点
             * 4、如果地址和经纬度都有，则默认按照搜索地址，如地址有效则按照地址添加标记点，若地址解析无效，则按照经纬度添加标记点
             */
            function markPoint(map, point) {
                marker = new BMap.Marker(point);
                map.addOverlay(marker);
                marker.enableDragging();//标记点可移动
            }


            /**
             * 在地图上点击，选中点作为地址并标记
             */
            map.addEventListener("click", function (e) {
                map.removeOverlay(marker);

                var pt = e.point;
                marker = new BMap.Marker(pt);
                map.addOverlay(marker);

                myGeo.getLocation(pt, function (rs) {
                    var addComp = rs.addressComponents;
                    $log.debug(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);

                    $scope.province = addComp.province;
                    $scope.city = addComp.city;
                    $scope.area = addComp.district;
                    $scope.address = addComp.street + addComp.streetNumber;
                    $scope.pointLat = rs.point.lng;
                    $scope.pointLng = rs.point.lat;
                    $scope.$apply();
                });

            });//标记点点击事件end

        };
    });
});
