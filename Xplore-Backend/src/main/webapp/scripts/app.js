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
                    .when("/","/viewTag/1")
                    .otherwise('/');

                $stateProvider
                    .state("home", {
                        // Use a url of "/" to set a state as the "index".
                        url: "/",
                    })
                    .state("login", {
                        url: "/login",
                        templateUrl: 'views/login.html',
                    })
                    .state("signup", {
                        url: "/signup",
                        templateUrl: 'views/signup.html',
                    })
                    .state("profile", {
                        url: "/profile",
                        templateUrl: 'views/profile.html',
                    })
                    .state("viewTag", {
                        url: '/viewTag/:tagId',
                        templateUrl: 'views/viewTag.html'
                    })
                    .state("viewContribution", {
                        url: "/viewContribution/:contributionId",
                        templateUrl: 'views/viewContribution.html',
                    })
                    .state("viewSearch", {
                        url: "/viewSearch/:searchText",
                        templateUrl: 'views/viewSearch.html',
                    })
                    .state("contribute", {
                        url: "/contribute",
                        templateUrl: 'views/contribute.html'
                    })
                    .state("logout", {
                        url: "/logout",
                        templateUrl: 'views/logout.html'
                    })
                    .state("advancedSearch", {
                        url: "/advancedSearch",
                        templateUrl: 'views/advancedSearch.html'
                    })
                ;
            }
        ]
    );
angular.module('XploreAppDep', [
    'ui.select',
    'ngSanitize',
    'ngStorage'
]);