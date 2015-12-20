angular.module('XploreApp', [
        'XploreAppDep',
        'ui.router'
    ])
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {

                // It's very handy to add references to $state and $stateParams to the $rootScope
                // so that you can access them from any scope within your applications.For example,
                // <li ng-class="{ active: $state.includes('contacts.list') }"> will set the <li>
                // to active whenever 'contacts.list' or one of its decendents is active.
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


                //////////////////////////
                // State Configurations //
                //////////////////////////

                // Use $stateProvider to configure your states.
                $stateProvider

                //////////
                // Home //
                //////////

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
                            }
                        }
                    })
            }
        ]
    );
angular.module('XploreAppDep', []);