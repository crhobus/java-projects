app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
                when('/', {
                    //Index
                    redirectTo: '/app'
                }).
                when('/app/', {
                    redirectTo: '/app/login'
                }).
                when('/app/login', {
                    templateUrl: './app/login/login.html',
                    controller: 'LoginCtrl'
                }).
                when('/app/home', {
                    templateUrl: './app/principal/principal.html',
                    controller: 'PrincipalCtrl'
                }).
                when('/app/cliente', {
                    templateUrl: './app/cliente/cliente.html',
                    controller: 'ClienteCtrl'
                }).
                otherwise({
                    template: '<p>Not found</p>'
                });
    }]);
