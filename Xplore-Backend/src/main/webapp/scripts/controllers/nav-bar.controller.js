/**
 * Created by burak on 20.12.2015.
 */
angular.module("XploreAppDep").controller("NavBarController",function($sessionStorage,$scope){
    $scope.user = function() {
        return $sessionStorage.user;
    };
});
