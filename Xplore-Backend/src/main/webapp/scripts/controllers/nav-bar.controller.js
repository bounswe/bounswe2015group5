/**
 * Created by ulusalomer on 05.01.2016.
 */
angular.module("XploreAppDep").controller("NavBarController",function($scope, $rootScope , $http){
    $scope.username;
    $http.get('user/current').success(function(data) {
        if(data!="") {
            $scope.username = data.username;
        }else {
            $scope.username = null;
        }
    });

    $scope.userLoggedIn = function(){
        if($scope.username){
            return true;
        } else{
            return false;
        }
    };
});
