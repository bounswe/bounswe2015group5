/**
 * Created by ulusalomer on 03.01.2016.
 */
angular.module('XploreAppDep').controller('ViewContributionCtrl', function ($scope, $http, $state, $stateParams) {
    $scope.contribution;
    $http.get('contributions/' + $stateParams.contributionId).success(function (contributionData) {
        var tags= [];
        $http.get('contributions/' + $stateParams.contributionId + '/tags').success(function (tagsData) {
            tagsData.forEach(function (tagData) {
                tags.push({
                    id: tagData.id,
                    name: tagData.name
                });
            });
        }).then(function () {
            $scope.contribution= {
                id: contributionData.id,
                title: contributionData.title,
                content: contributionData.content,
                creator: contributionData.creator,
                creationDate: new Date(contributionData.createdAt).toLocaleString(),
                tags: tags
            };
        });
    });
});