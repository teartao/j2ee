function demo001Controller($scope, $http, $log) {

    var personObj = {id: 1, name: '张三', sex: '男', age: 23};

    $http.get("../personGet/" + 111).success(function (response) {
        $log.debug("[返回]get person 成功");
        $log.debug(response);
    });

    $http.post("../personPost", personObj).success(function (response) {
        $log.debug("[返回]post person 成功");
        $log.debug(response);
    });

    $http.get("../index").success(function (response) {
        $log.debug("[返回]index 成功");
        $log.debug(response);
        $scope.studentList=response;
    });
}

