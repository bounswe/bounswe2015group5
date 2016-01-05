/**
 * Created by burak on 20.12.2015.
 */
angular.module('XploreAppDep').controller('LoginCtrl', function ($scope, $rootScope , $http, $state) {
    $rootScope.userData = {
        username: "",
        password: ""
    };

    $scope.loginUser = function () {
        $http.post('user/login', $rootScope.userData).success(function (response){
            if(response){
                $state.reload();
                $state.go('home');
            }else {
                window.alert("Wrong username or password");
            }
        });
    };

});