define(['diamond'], function (diamond) {
    diamond.lazy.controller('msgController', function ($rootScope, $scope, $http) {
        loadPage();

        function loadPage() {
            var data = {
                "touser": "@all",
                "msgtype": "text",
                "agentid": "wxaadda5e7d05d38b6",
                "text": {
                    "content": "Holiday Request For Pony(http://xxxxx)"
                },
                "safe": "0"
            };
            sendMessage(data);
        }

        function sendMessage(param) {
            var access_token = 'E_x96IMtRe-uxiKbxIL1BPVFermBXy_8JUhov4yJDkHFHh_VjeQNaR1AUgX2jaSKn04hW3yLmhLeGLI5mgG5xA';
            var url = 'https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token';
            $http.post(url, param).success(function (response) {
                $log.debug("success ------- >");
                $log.debug(response);
            });
        }




    });
});
