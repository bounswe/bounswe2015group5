angular.module("XploreAppDep").directive("searchFormTitle",function(){
    return {
        restrict: 'E',
        templateUrl: 'views/search-form-title.html',
        controller: 'SearchFormTitleController',
        controllerAs: 'searchFormTitleCtrl'
    };
});