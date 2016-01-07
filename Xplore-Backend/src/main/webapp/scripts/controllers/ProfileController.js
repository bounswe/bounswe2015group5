/**
 * Created by burak on 20.12.2015.
 */
angular.module('XploreAppDep').controller('ProfileCtrl', function ($scope, $rootScope , $http) {
    $scope.userData;
    $scope.userContributions = [];

    //get the contributions of user
    $http.get('user/current').success(function(response){
        $scope.userData = response;
        $http.get('user/'+response.username+'/contributions').success(function(contributionsData){
            contributionsData.forEach(function (contribution) {
                $http.get('contributions/'+contribution.id+'/tags').success(function (tags) {
                    $scope.userContributions.push({
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
});