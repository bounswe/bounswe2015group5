/**
 * Created by burak on 20.12.2015.
 */

angular.module('XploreAppDep').controller('SignUpCtrl', function ($scope, $rootScope , $http, $state) {
    $scope.signupUser = function(){
        //if validated
        $rootScope.user = {name: "deneme"   , surname: "surname" , email: "email"};
        $scope.userData = {};
        $state.go('home');
    };
});