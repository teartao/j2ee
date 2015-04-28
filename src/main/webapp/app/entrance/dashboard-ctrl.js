define(['diamond'], function (diamond) {
    diamond.lazy.controller('dashboardCtrl', function ($rootScope, $scope, $http) {
        loadPage();

        function loadPage() {
            $scope.testString = "I'm in dashboard";

            $scope.submitForm = function (isValid) {
                if (!isValid) {
                    alert('验证失败');
                }
            };
        }

        $scope.getList = function getList() {

            $.ajax({
                //提交数据的类型 POST GET
                type: "GET",
                //提交的网址
                url: "getGoodList",
                //提交的数据
                data: {pageNumber: "3", pageSize: "10"},
                //返回数据的格式
//            datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
                //在请求之前调用的函数
                /*beforeSend: function () {
//                $("#msg").html("logining");
                    console("Request");
                },*/
                //成功返回之后调用的函数
                success: function (data) {
//                $("#msg").html(decodeURI(data));
                    console.log("success");
                },
                //调用执行后调用的函数
                complete: function (XMLHttpRequest, textStatus) {
                    alert(XMLHttpRequest.responseText);
                    alert(textStatus);
                    //HideLoading();
                },
                //调用出错执行的函数
                error: function () {
                    //请求出错处理
                }
            });
        }

    });
});
