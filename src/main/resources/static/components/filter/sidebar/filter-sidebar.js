/**
 * Created by Pavel on 02.02.2017.
 */
'use strict';
(function () {

    function filterSidebarController($http) {
        let ctrl = this;

        ctrl.filters = [];
        $http.get('/filters').then(
            function (response) {
                ctrl.filters = response.data;
            }
        );
        console.log('ololo');
    }

    angular
        .module('vkscanner')
        .component('filterSidebar', {
            templateUrl: 'components/filter/sidebar/filter-sidebar.html',
            controller: filterSidebarController
        });
})();