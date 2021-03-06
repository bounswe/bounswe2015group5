angular.module('XploreAppDep').controller('GraphCtrl', function ($scope, $http, $state) {
    $http.get('tags').success(function (data) {

        var i,
            s,
            N = data.length;

        s = new sigma({
            container: 'graph-container',
            settings: {
                maxEdgeSize: 5,
                maxNodeSize: 12
            }
        });

        sigma.layouts.fruchtermanReingold.configure(s, {});

        var config = {
            node: {}
        };
        var tooltips = sigma.plugins.tooltips(s, s.renderers[0], config);

        tooltips.bind('shown', function (event) {

            $state.go('viewTag', {tagId: event.data.node.id});
        });


        var addEdges = function (tag) {
            $http.get('tags/' + tag.id + '/tags').success(function (neighbors) {
                for (neighbor in neighbors) {
                    if (neighbor == tag.id) {
                        s.graph.nodes(tag.id).size = neighbors[neighbor] + 1;
                    } else {
                        s.graph.addEdge({
                            id: 'e' + tag.id + 'to' + neighbor,
                            source: tag.id,
                            target: neighbor,
                            size: Math.log10(neighbors[neighbor]),
                            color: 'rgba(10,20,30,0.15)'
                        });
                    }
                }
                s.refresh();
                sigma.layouts.fruchtermanReingold.start(s);
            });
        }
        // Generate a random graph:
        for (i = 0; i < N; i++) {
            s.graph.addNode({
                id: data[i].id,
                label: data[i].name,
                x: Math.random(),
                y: Math.random(),
                size: 1,
                color: '#f00'
            });
            addEdges(data[i]);
        }

    });
});