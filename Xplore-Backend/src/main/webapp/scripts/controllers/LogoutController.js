/**
 * Created by burak on 20.12.2015.
 */

angular.module('XploreAppDep').controller('LogoutCtrl', function ($scope, $rootScope , $http, $state) {
    // http call to server
    $rootScope.user = null;
    $state.go('home');
});