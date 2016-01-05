angular.module('XploreAppDep').controller('AdvancedSearchCtrl', function ($scope, $http, $state, $httpParamSerializerJQLike) {
    $scope.advancedSearch = function () {
        var serializedSearchText = $httpParamSerializerJQLike($scope.searchData);
        //console.log(serializedSearchText);
        $state.go('viewSearch', {searchText: serializedSearchText});
    };
});
