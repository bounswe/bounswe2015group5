angular.module("XploreAppDep").controller("SearchFormController", function ($scope, $rootScope, $http, $state) {
    $scope.tag = {};
    $scope.refreshTags = function (tag) {
        return $http.get(
            'tags'
        ).then(function (response) {
            $scope.tags = response.data;
        });
    };

    $scope.searchTag = function (item, model) {
        $state.go('viewTag', {tagId: model});
    };
});
