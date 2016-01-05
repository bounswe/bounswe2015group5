/**
 * Created by ulusalomer on 05.01.2016.
 */

angular.module('XploreAppDep').controller('LogoutCtrl', function ($scope, $sessionStorage , $http, $state) {
    $http.get("user/logout").success(function() {
        $state.go('home');
    });
});