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
        }
    };

    context.programs = {};

    context.menus = [
    ];

    return context;
});