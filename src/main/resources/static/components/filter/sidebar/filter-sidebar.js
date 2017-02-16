/**
 * Created by Pavel on 02.02.2017.
 */
'use strict';
(function () {

    function filterSidebarController($http, $state) {
        let ctrl = this;

        ctrl.filters = [];

        ctrl.load = function () {
            $http.get('/filters').then(
                function (response) {
                    ctrl.filters = response.data;
                    if (ctrl.filters.length > 0) {
                        setId(ctrl.filters.id);
                    }
                }
            );
        };

        ctrl.load();

        ctrl.setId = function (id) {
            $state.go('filters.main', {filterId: id}, {reload: true});
        };

        ctrl.onEnterPress = function (keyEvent) {
            if (keyEvent.which === 13) {
                ctrl.addFilter();
            }
        };

        ctrl.addFilter = function () {
            let filter = {
                name: ctrl.tempValue,
                queries: [],
                communities: []
            };
            $http.post('/filters', filter).then(
                function (response) {
                    alert('Success!');
                    ctrl.load();
                },
                function (error) {
                    alert('Error!');
                }
            );
        }
    }

    angular
        .module('vkscanner')
        .component('filterSidebar', {
            templateUrl: 'components/filter/sidebar/filter-sidebar.html',
            controller: filterSidebarController
        });
})();