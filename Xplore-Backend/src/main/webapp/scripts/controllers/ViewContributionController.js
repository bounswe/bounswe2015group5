angular.module('XploreAppDep').controller('ViewContributionCtrl', function ($scope, $rootScope, $http, $state, $stateParams) {

    $http.get('contributions/' + $stateParams.contributionId).then(function (contributionData) {
        $scope.contribution = contributionData.data;
        $scope.contribution.createdAt = new Date($scope.contribution.createdAt).toLocaleString();
    });

    $http.get('contributions/' + $stateParams.contributionId + '/tags').then(function (tagsData) {
        $scope.tags = tagsData.data;
    });

    $http.get('contributions/' + $stateParams.contributionId + '/comments').then(function (commentsData) {
        $scope.comments = commentsData.data;
        $scope.comments.forEach(function (comment) {
            comment.createdAt = new Date(comment.createdAt).toLocaleString();
        });
    });

    //Post a comment
    // Warning: info of current username should be corrected
    $scope.commentContext = {
        commentBody: "",
        username: $rootScope.username
    }
    $scope.addANewComment = function () {
        $http.post('contributions/' + $stateParams.contributionId + '/comments', $scope.commentContext);
        $state.reload();
    }

    $scope.delete = function (commentId) {
        var requestAddress = "comments/" + commentId;
        console.log("delete to " + commentId);
        $http.delete(requestAddress).success(function (response) {
            $state.reload();
        });

    }

});