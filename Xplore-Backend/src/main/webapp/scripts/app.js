angular.module('XploreApp', ['ui.bootstrap',
        'XploreAppDep',
        'ui.router'
    ])
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        ['$stateProvider', '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider
                    .otherwise('/');

                $stateProvider
                    .state("home", {
                        // Use a url of "/" to set a state as the "index".
                        url: "/",
                    })
                    .state("viewTag", {
                        url: '/viewTag/:tagId',
                        templateUrl: 'views/viewTag.html'
                    })
                    .state("login", {
                        // Use a url of "/" to set a state as the "index".
                        url: "/login",
                        templateUrl: 'views/login.html',
                        controller: 'LoginCtrl'
                    })
                    .state("signup", {
                        // Use a url of "/" to set a state as the "index".
                        url: "/signup",
                        templateUrl: 'views/signup.html',
                        controller: 'SignUpCtrl'
                    })
                ;
            }
        ]
    );
angular.module('XploreAppDep', []);