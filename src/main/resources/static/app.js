'use strict';
(function () {
    angular
        .module("vkscanner", ['ui.router'])
        .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            let filtersUrl = '/filters';
            $stateProvider.state('filters', {
                url: filtersUrl,
                views: {
                    'content': {
                        template: '<h1>asdfasdf</h1>'
                        // templateUrl: 'components/filter/filter-list.html',
                        // controller: 'filterListController'
                    }
                }
            });
            $urlRouterProvider.otherwise('/404');
        }])
        .controller('mainController', mainController);

    function mainController() {
    }
})();