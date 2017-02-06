'use strict';
(function () {
    angular
        .module("vkscanner", ['ui.router'])
        .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider
                .state('filters', {
                    url: '/filters',
                    views: {
                        'content': {
                            templateUrl: 'components/filter/filter.html'
                        }
                    }
                })
                .state('404', {
                    url: '/404',
                    views: {
                        'content': {
                            templateUrl: 'components/404/404.html'
                        }
                    }
                });
            $urlRouterProvider.otherwise('/404');
        }])
        .controller('mainController', mainController);

    function mainController() {
    }
})();