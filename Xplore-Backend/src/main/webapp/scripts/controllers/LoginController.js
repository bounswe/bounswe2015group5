/**
 * Created by burak on 20.12.2015.
 */
angular.module('XploreAppDep').controller('LoginCtrl', function ($scope, $sessionStorage, $http, $state, $httpParamSerializerJQLike) {
    $scope.loginUser = function () {
        $http({
            method: 'POST',
            url: 'user/log',
            data: $httpParamSerializerJQLike($scope.userData),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
        })
            .success(function (data) {
                if (data === true) {
                    $sessionStorage.user = {username: $scope.userData.username};
                    console.log($sessionStorage.user.username);
                    $state.go("home");
                }
            });
    };
});