/**
 * Created by Pavel on 02.02.2017.
 */
'use strict';
(function () {

    function filterMainController($http) {
        let ctrl = this;

        ctrl.filter = [];
        $http.get('/filters/5886434af36d2855565452a6').then(
            function (response) {
                ctrl.filter = response.data;
            }
        );

        ctrl.removeFromFilter = function (name) {
            let index = ctrl.filter.queries.indexOf(name);
            if (index !== -1) {
                ctrl.filter.queries.splice(index, 1);
            }
        }
    }

    angular
        .module('vkscanner')
        .component('filterMain', {
            templateUrl: 'components/filter/main/filter-main.html',
            controller: filterMainController
        });
})();