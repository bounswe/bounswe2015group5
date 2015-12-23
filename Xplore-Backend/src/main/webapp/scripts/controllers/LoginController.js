/**
 * Created by burak on 20.12.2015.
 */
angular.module('XploreAppDep').controller('LoginCtrl', function ($scope, $rootScope, $http, $state, $httpParamSerializerJQLike) {
    $scope.loginUser = function () {
        $http({
            method: 'POST',
            url: 'user/log',
            data: $httpParamSerializerJQLike($scope.userData),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
        })
            .success(function (data) {
                if (data === true) {
                    $rootScope.user = {username: $scope.userData.username};
                    $state.go("home");
                }
            });
    };
});