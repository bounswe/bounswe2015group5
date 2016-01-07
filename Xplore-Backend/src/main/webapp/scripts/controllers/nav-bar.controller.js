angular.module("XploreAppDep").controller("NavBarController", function ($scope, $rootScope, $http) {
    $http.get('user/current').success(function (data) {
        if (data != "") {
            $rootScope.username = data.username;
        } else {
            $rootScope.username = null;
        }
    });

    $scope.userLoggedIn = function () {
        return $rootScope.username;
    };
});
