/**
 * Created by ulusalomer on 03.01.2016.
 */
angular.module('XploreAppDep').controller('ViewContributionCtrl', function ($scope, $http, $state, $stateParams) {
    $scope.contribution;
    $scope.comments = [];
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
            $scope.contribution = {
                id: contributionData.id,
                title: contributionData.title,
                content: contributionData.content,
                creator: contributionData.creator,
                creationDate: new Date(contributionData.createdAt).toLocaleString(),
                tags: tags
            };
        });
    });
    $http.get('contributions/' + $stateParams.contributionId + '/comments').success(function(commentsData) {
        commentsData.forEach(function (commentData){
            $scope.comments.push({
               content: commentData.content,
               user: commentData.user,
               createdAt: new Date(commentData.createdAt).toLocaleString(),
               updatedAt: new Date(commentData.createdAt).toLocaleString()
           });
       });

    });

    //Post a comment
    // Warning: info of current username should be corrected
    $scope.commentContext = {
        commentBody: "",
        username: "hanefi"
    }
    $scope.addANewComment = function () {
        $http.post('contributions/' + $stateParams.contributionId + '/comments', $scope.commentContext);
        $state.reload();
    }

});