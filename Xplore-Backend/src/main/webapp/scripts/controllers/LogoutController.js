/**
 * Created by burak on 20.12.2015.
 */

angular.module('XploreAppDep').controller('LogoutCtrl', function ($scope, $sessionStorage , $http, $state) {
    $http.get("user/logout").success(function() {
        $state.reload();
    });
});