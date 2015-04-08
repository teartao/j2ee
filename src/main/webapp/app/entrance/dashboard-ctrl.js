define(['diamond'], function (diamond) {
    diamond.lazy.controller('dashboardCtrl', function ($rootScope, $scope, $http) {
        loadPage();

        function loadPage() {
            $scope.testString ="I'm in dashboard";

            $scope.submitForm = function (isValid) {
                if (!isValid) {
                    alert('验证失败');
                }
            };
        }
    });
});
