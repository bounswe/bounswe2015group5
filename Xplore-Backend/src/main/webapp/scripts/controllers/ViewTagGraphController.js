angular.module('XploreAppDep').controller('ViewTagGraphCtrl', function ($scope, $http, $state, $stateParams) {
    $http.get('/tags').success(function (data) {

        var i,
            s,
            N = data.length;

        s = new sigma({
            container: 'graph-container',
        });

        sigma.layouts.fruchtermanReingold.configure(s, {});

        var config = {
            node: {}
        };
        var tooltips = sigma.plugins.tooltips(s, s.renderers[0], config);

        tooltips.bind('shown', function (event) {

            $state.go('viewTag', {tagId: event.data.node.id});
        });


        var f = function(tag) {
            $http.get('/tags/' + tag.id + '/tags').success(function(neighbors){
                for(neighbor in neighbors) {
                    s.graph.addEdge({
                        id: 'e' + tag.id + 'to' + neighbor,
                        source: tag.id,
                        target: neighbor,
                        size: Math.log10(neighbors[neighbor]),
                        color: 'rgba(10,20,30,0.15)'
                    });

                }
                s.refresh();
                sigma.layouts.fruchtermanReingold.start(s);
                sigma.plugins.locate(s).nodes($stateParams.tagId);
            });
        }
        // Generate a random graph:
        for (i = 0; i < N; i++) {
            s.graph.addNode({
                id: data[i].id,
                label: data[i].name,
                x: data[i].id *  data[i].id,
                y: data[i].id,
                size: Math.random() * 2 + 1,
                color: '#f00'
            });
            f(data[i]);

        }


    });
});