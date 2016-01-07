angular.module('XploreAppDep').controller('LoginCtrl', function ($scope, $rootScope, $http, $state) {
    $scope.userData = {
        username: "",
        password: ""
    };

    $scope.loginUser = function () {
        $http.post('user/login', $scope.userData).success(function (response) {
            if (response) {
                $rootScope.username = $scope.userData.username;
                $state.go("home");
            } else {
                window.alert("Wrong username or password");
            }
        });
    };

});