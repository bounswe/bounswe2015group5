/**
 * Created by burak on 20.12.2015.
 */
angular.module('XploreAppDep').controller('LoginCtrl', function ($scope, $rootScope , $http, $state) {
    $scope.loginUser = function(){
        //if validated
        $rootScope.user = {name: "deneme"   , surname: "surname" , email: "email"};
        $state.go('home');
    };
});