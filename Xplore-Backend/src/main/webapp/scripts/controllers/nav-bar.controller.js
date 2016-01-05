/**
 * Created by burak on 20.12.2015.
 */
angular.module("XploreAppDep").controller("NavBarController",function($scope, $rootScope , $http){
    $http.get('user/current').success(function(data) {
        if(data!="") {
            $rootScope.userData.username = data.username;
        }else {
            $rootScope.userData.username = null;
        }
    });

    $scope.userLoggedIn = function(){
        if($rootScope.userData.username){
            return true;
        } else{
            return false;
        }
    };
});
