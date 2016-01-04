angular.module('XploreAppDep').controller('ContributeCtrl', function ($scope, $rootScope , $http, $state) {
    // Add a new contribution
    // Warning: info of current username should be corrected
    $scope.contribution = {
        title: "Enter a title",
        content: "Enter a content",
        username: "hanefi"
    }
    $scope.contribute = function () {
        $http.post('contributions', $scope.contribution);
    }

    /*
    $http.get('tags').success(function(tags){
        $scope.tags = tags;
        $scope.form = {};
        $scope.form.selectedTags = [];
    }); */
});