angular.module('XploreAppDep').controller('AdvancedSearchCtrl', function ($scope, $http, $state, $httpParamSerializerJQLike) {
    $scope.searchData = {};
    $scope.searchData.tags = [];
    $scope.refreshTags = function() {
        return $http.get(
            'tags'
        ).then(function(response) {
            $scope.tags = response.data;
        });
    };

    $scope.advancedSearch = function () {
        var serializedSearchText = $httpParamSerializerJQLike($scope.searchData);
        //console.log(serializedSearchText);
        $state.go('viewSearch', {searchText: serializedSearchText});
    };
});
