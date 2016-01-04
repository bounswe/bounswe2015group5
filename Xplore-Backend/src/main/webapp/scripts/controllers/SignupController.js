/**
 * Created by ulusalomer on 04.01.2016.
 */
angular.module('XploreAppDep').controller('SignUpCtrl', function ($scope, $rootScope , $http, $state) {
    $scope.passwordcheck = "";
    $scope.userData = {
        password: "",
        email: "",
        username: ""
    }
    $scope.signupUser = function(){
        if ($scope.userData.password == $scope.passwordcheck) {
            $http.post('user', $scope.userData);
            $state.go('home');
        }
        else{
            window.alert("Type again your password correctly.");
            $scope.userData.password = "";
            $scope.passwordcheck = "";
        }
    };
});