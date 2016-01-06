angular.module('XploreAppDep').controller('ContributeCtrl', function ($scope, $rootScope, $http, $state) {
    // Add a new contribution
    // Warning: info of current username should be corrected
    $scope.contribution = {
        title: "",
        content: "",
        referenseList: "",
        username: $rootScope.username
    }
    $scope.contribute = function () {
        $http.post('contributions', $scope.contribution).success(function (contribution) {
            $scope.form.selectedTags.forEach(function (tagObj) {
                var requestAddress = "contributions/" + contribution.id + "/addTag/" + tagObj;
                $http.get(requestAddress);
            });
            $state.go('viewContribution', {contributionId: contribution.id});
        });
    };

    $scope.form = {};
    $scope.form.selectedTags = [];
    $scope.refreshTags = function () {
        return $http.get(
            'tags'
        ).then(function (response) {
            $scope.tags = response.data;
        });
    };
    /*
     $http.get('tags').success(function(tags){
     $scope.tags = tags;
     $scope.form = {};
     $scope.form.selectedTags = [];
     }); */
});