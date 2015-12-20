/**
 * Created by burak on 20.12.2015.
 */
angular.module("XploreAppDep").directive("navBar",function(){
    return {
        restrict: 'E',
        templateUrl: 'views/nav-bar.html',
        controller: 'NavBarController',
        controllerAs: 'navbarCtrl'
    };
});