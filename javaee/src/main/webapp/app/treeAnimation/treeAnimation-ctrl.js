define(['diamond', '../base/tree-service', 'treeAnimationService'], function (diamond) {
    diamond.lazy.controller('treeAnimationController', function (treeAnimationService, angularTreeService, $rootScope, $scope, $http) {
        //init data
        var jsonData = {};
        loadPage();

        function loadPage() {
            $http.get("resources/data/treedata.json").success(function (response) {
                if (response) {
                    jsonData = angularTreeService.getTreeModel(
                        response,
                        {pkId:"id", fkId: "parentid", rootValue:"root", label:"name"},
                        {nodeName:"name", sonNode:"children", withOtherNodeProperty: true}
                    );
                }
                treeAnimationService.init(jsonData[0]);
            });
        }
    });
});
