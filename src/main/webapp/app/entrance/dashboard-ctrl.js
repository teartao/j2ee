define(['diamond'], function (diamond,demo001Service) {
    diamond.lazy.controller('dashboardCtrl', function ($rootScope, $scope, $http) {
        loadPage();

        function loadPage() {
            $scope.testString ="testString sss";
        }
    });
});
