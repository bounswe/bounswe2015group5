angular.module('XploreAppDep').controller('ViewTagCtrl', function ($scope, $http, $state, $stateParams) {
    $http.get('tags/' + $stateParams.tagId + '/contributions').success(function (data) {
        $scope.contributions = [];
        data.forEach(function (contribution) {
            var tags = [];
            $http.get('contributions/' + contribution.id + '/tags').success(function (tagsData) {
                tagsData.forEach(function (tagData) {
                    tags.push({
                        id: tagData.id,
                        name: tagData.name
                    });
                });
            }).then(function () {
                $scope.contributions.push({
                    id: contribution.id,
                    title: contribution.title,
                    content: contribution.content,
                    creator: contribution.creator,
                    creationDate: new Date(contribution.createdAt).toLocaleString(),
                    tags: tags
                });
            });


        });
    });
});