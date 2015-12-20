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
                        views: {
                            'graph': {
                                templateUrl: 'views/graph.html',
                                controller: 'HomeGraphCtrl'
                            }
                        }
                    })
                    .state("viewTag", {
                        url: '/viewTag/:tagId',
                        views: {
                            'content': {
                                templateUrl: 'views/viewTag.html'
                            },
                            'graph': {
                                templateUrl: 'views/graph.html',
                                controller: 'ViewTagGraphCtrl'
                            }
                        }
                    })
                    .state("login", {
                        // Use a url of "/" to set a state as the "index".
                        url: "/login",
                        views: {
                            'content': {
                                templateUrl: 'views/login.html',
                                controller: 'LoginCtrl'
                            },
                            'graph': {
                                templateUrl: 'views/graph.html',
                                controller: 'HomeGraphCtrl'
                            }
                        }
                    })
            }
        ]
    );
angular.module('XploreAppDep', []);