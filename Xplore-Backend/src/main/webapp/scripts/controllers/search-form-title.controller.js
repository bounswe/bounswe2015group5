angular.module("XploreAppDep").controller("SearchFormTitleController",function($scope, $http, $state){
    $scope.contribution = {};
    $scope.refreshContributions = function(contribution) {
        return $http.get(
            'contributions'
        ).then(function(response) {
            $scope.contributions = response.data;
        });
    };

    $scope.searchContribution = function(item, model) {
        $state.go('viewContribution', {contributionId: model});
    };
});
