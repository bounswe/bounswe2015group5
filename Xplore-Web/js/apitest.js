var rootUrl = 'http://localhost:8080'
function ApiTest($scope, $http) {
  $http.get(rootUrl + '/tags')
    .success(function(data) {
      $scope.tags = data._embedded.tags;
      console.log(data);
    });
}
