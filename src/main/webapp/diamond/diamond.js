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