angular.module('XploreAppDep').controller('ContributeCtrl', function ($scope, $rootScope , $http, $state) {
    $http.get('/tags').success(function(tags){
        $scope.tags = tags;
        $scope.form = {};
        $scope.form.selectedTags = [];
    });



});