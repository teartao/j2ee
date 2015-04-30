var appConfig = {
    appName: 'app',//app文件名
    isServer: true, //数据是否来源服务器
    localDataConfig: '../../resources/local-test/data-config', //应用测试数据相对于main.js的路径
    appSrcPath: "./", //自己编写的应用相对于main.js的路径
    appViewPath: "app/",//模块页面路径
    appContextPath: './app-context'//Context配置
};
/**
 * RequireJS 配置
 */
require.config({
    paths: {
        angular: '../lib/angular/angular',
        angularMock: '../lib/angular-mocks/angular-mocks',
        jquery: '../lib/jquery/jquery.min',
        diamond: '../diamond/diamond',
        highCharts: '../lib/highcharts/highcharts',
        ol: '../lib/ol/ol',
        highChartsNg: '../lib/highcharts-ng/highcharts-ng.min',
        bootstrap: '../lib/bootstrap/bootstrap.min',
        uEditorConfig: '../lib/ueditor1_4_3/ueditor.config',//配置文件
        uEditorAll: '../lib/ueditor1_4_3/ueditor.all.min',//编辑器源码文件,
        jitCore: '../lib/jit/jit',
        treeAnimationService: '../app/treeAnimation-service',
        goJs:'../lib/go-js/release/go-debug'

    },
    shim: {
        angularMock: {
            deps: ['angular']
        },
        angular: {
            deps: ['jquery'],
            exports: 'angular'
        },
        bootstrap: {
            deps: ['jquery']
        },
        highCharts: {
            deps: ['jquery'],
            exports: 'Highcharts'
        },
        highChartsNg: {
            deps: ['jquery', 'angular', 'highCharts']
        },
        common: {
            deps: ['jquery']
        },
        scroll: {
            deps: ['jquery']
        },
        jitCore:{
            exports:'jit'
        },
         treeAnimationService: {
            deps: ['jitCore']
        }
    }
});

require(//如果这里引用了，那么将在所有页面中加载这些定义好的js，如果只是某个页面需要使用，那么只需在哪个页面对应的ctrl头部引入定义好的js即可
    [
        appConfig.appContextPath,
        'angular',
        'diamond',
        'jquery',
        'highCharts',
        'highChartsNg',
        'uEditorAll',
        'uEditorConfig',
        'jitCore',
        'goJs'
    ]
    , function (context, angular, diamond) {
        diamond.run(function ($rootScope, $http, $log) {
                $rootScope.appContext = {
                    modules: [],
                    modulePage: {}//模块页面
                };

                loadApp();

                // 应用加载
                function loadApp() {

                }

                /**
                 * 初始欢迎页
                 */
                $rootScope.initWelcomePage = function () {
                    $rootScope.openPage('dashboard');
                };

                $rootScope.openPage = function (pageCode) {
                    var page = context.pages[pageCode];
                    if (page == null) {
                        return;
                    }
                    var controllerFilePath = appConfig.appSrcPath + page.controller;
                    var viewURL = appConfig.appViewPath + page.templateUrl;
                    require([controllerFilePath], function () {
                        $rootScope.$apply(function () {
                            $rootScope.appContext.modulePage = {
                                viewURL: viewURL,
                                viewCtrl: controllerFilePath
                            };
                        });
                    });
                };

            }
        );

        angular.element(document).ready(function () {
            angular.bootstrap(angular.element('body'), ['diamond', 'highcharts-ng']);
        });

    }
);





