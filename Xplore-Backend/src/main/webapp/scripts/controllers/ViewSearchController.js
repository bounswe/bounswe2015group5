angular.module('XploreAppDep').controller('ViewSearchCtrl', function ($scope, $http, $state, $stateParams) {
    var searchBaseUrl = "search?";  // Assumes an api call at search URL
    var fullSearchUrl = searchBaseUrl + $stateParams.searchText;    // Search query is appended to GET requests address
    console.log(fullSearchUrl); // Printed for debug purposes, should be removed when tested with API

    // Gets a list of contributions from the server
    $http.get(fullSearchUrl).then(function () {
        $scope.contributions = [];
        data.forEach(function (contribution) {
            var tags = [];

            // Gets a list of tags for each contribution from the server
            $http.get('contributions/' + contribution.id + '/tags').then(function (tagsData) {
                tagsData.forEach(function (tagData) {
                    tags.push({
                        id: tagData.id,
                        name: tagData.name
                    });
                });

                // Contributions list is constructed for view
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
