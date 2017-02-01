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
                        templateUrl: 'components/filter/filter-list.html',
                        controller: 'filterListController'
                    }
                }
            });
            $urlRouterProvider.otherwise('/404');
        }])
        .controller('mainController', mainController);

    function mainController() {
    }
})();