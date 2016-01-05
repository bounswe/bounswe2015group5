angular.module('XploreAppDep').controller('LogoutCtrl', function ($scope, $rootScope, $http, $state) {
    $http.get("user/logout").success(function () {
        $rootScope.username = null;
        $state.go('home');
    });
});