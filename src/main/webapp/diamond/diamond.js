/**
 * Description:RequireJS diamond定义类
 * User: laibb
 * Version:edc-ui-0.1.0
 * Date: 14-2-17 上午11:31
 */
var diamondRequirements = (function () {
    var depends = [
        'angular'
    ];
    if (!appConfig.isServer) {
        var mock = "./mock/httpmock-app"
        depends.push(mock);
    }
    return depends;
}());

define(diamondRequirements, function (angular) {
    var diamondModule;
    if (appConfig.isServer) {
        diamondModule = angular.module('diamond', [
        ]);
    } else {
        diamondModule = angular.module('diamond', [
            'httpMock'
        ]);
    }
    //IE7 Support
    diamondModule.config(function ($sceProvider) {
        $sceProvider.enabled(false);
    });
    //lazy-loading
    diamondModule.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $animateProvider) {
        diamondModule.lazy = {
            controller: $controllerProvider.register,
            directive: $compileProvider.directive,
            filter: $filterProvider.register,
            factory: $provide.factory,
            service: $provide.service,
            animation: $animateProvider.register
        }
    });

    return diamondModule;
});