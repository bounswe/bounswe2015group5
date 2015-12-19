angular.module('XploreAppDep').controller('HomeGraphCtrl', function ($scope, $http, $state) {
    $http.get('/tags').success(function (data) {

        var i,
            s,
            N = data.length,
            E = 2 * N,
            g = {
                nodes: [],
                edges: []
            };
        // Generate a random graph:
        for (i = 0; i < N; i++)
            g.nodes.push({
                id: 'tag' + data[i].id,
                label: data[i].name,
                x: Math.random(),
                y: Math.random(),
                size: Math.random() * 2 + 1,
                color: '#00f'
            });
        for (i = 0; i < E; i++)
            g.edges.push({
                id: 'e' + i,
                source: 'tag' + ((Math.random() * N | 0) + 1),
                target: 'tag' + ((Math.random() * N | 0) + 1),
                size: Math.random(),
                color: 'rgba(10,20,30,0.15)'
            });
        // Instantiate sigma:
        s = new sigma({
            graph: g,
            container: 'graph-container'
        });
        var fa = sigma.layouts.startForceLink(s, {autoStop: true});

        var config = {
            node: {}
        };
        var tooltips = sigma.plugins.tooltips(s, s.renderers[0], config);

        tooltips.bind('shown', function (event) {
            $state.go('route');
        });
    });
});