define(['diamond'], function (diamond) {
    diamond.lazy.controller('dashboardCtrl', function ($rootScope, $scope, $http) {
        loadPage();

        function loadPage() {
            $scope.testString ="testString sss";
        }
    });
});
