define(function () {
    var context = {};

    context.pages = {
        dashboard: {
            controller: 'entrance/dashboard-ctrl',
            templateUrl: 'entrance/dashboard.html'
        },
        demo001: {
            controller: 'app001/demo001-ctrl',
            templateUrl: 'app001/demo001.html'
        },
        demo002: {
            controller: 'app002/demo002-ctrl',
            templateUrl: 'app002/demo002.html'
        },
        demo003: {
            controller: 'app003/demo003-ctrl',
            templateUrl: 'app003/demo003.html'
        },
        treeAnimation: {
            controller: 'treeAnimation/treeAnimation-ctrl',
            templateUrl: 'treeAnimation/treeAnimation.html'
        }
    };

    context.programs = {};

    context.menus = [
    ];

    return context;
});