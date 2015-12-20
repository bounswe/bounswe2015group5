/**
 * Created by burak on 20.12.2015.
 */
angular.module("XploreAppDep").controller("SearchFormController",function($rootScope,$http){
    this.user = {name:"burak", surname:"kurutmaz"};
    this.searchText = function(Text){
        window.alert(Text);
    };
});
