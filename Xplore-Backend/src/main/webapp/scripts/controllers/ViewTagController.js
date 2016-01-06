angular.module('XploreAppDep').controller('ViewTagCtrl', function ($scope, $http, $state, $stateParams) {
    $http.get('tags/' + $stateParams.tagId + '/contributions').success(function (data) {
        $scope.contributions = [];
        data.forEach(function (contribution) {
            contribution.createdAt = new Date(contribution.createdAt).toLocaleString();
            var tags = [];
            $http.get('contributions/' + contribution.id + '/tags').success(function (tagsData) {
                tagsData.forEach(function (tagData) {
                    tags.push({
                        id: tagData.id,
                        name: tagData.name
                    });
                });
                contribution.tags = tags;


            });
            //$http.get("contributions/" + contribution.id + "/rates").success(function (ratesData) {
            //    contribution.rate = ratesData.up - ratesData.down;
            //    contribution.currentUserVote = ratesData.currentUser;
            //});
            var getRandomInt = function (min, max) {
                return Math.floor(Math.random() * (max - min)) + min;
            };
            contribution.rate = getRandomInt(0, 10);
            contribution.currentUserVote = getRandomInt(-1, 2);

            $scope.contributions.push(contribution);
        });
    });

    $scope.vote = function (contributionId, rate) {
        var requestPayload = {
            vote: rate
        };
        var requestAddress = "contribution/" + contributionId + "/rates";
        $http.post(requestAddress, requestPayload).then(function (response) {
            $state.reload();
        });
    };

});
