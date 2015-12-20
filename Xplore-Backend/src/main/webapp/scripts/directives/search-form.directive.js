/**
 * Created by burak on 20.12.2015.
 */
angular.module("XploreAppDep").directive("searchForm",function(){
    return {
        restrict: 'E',
        templateUrl: 'views/search-form.html',
        controller: 'SearchFormController',
        controllerAs: 'searchFormCtrl'
    };
});