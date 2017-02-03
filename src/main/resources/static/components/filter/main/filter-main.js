/**
 * Created by Pavel on 02.02.2017.
 */
'use strict';
(function () {

    function filterMainController($http) {
        let ctrl = this;

        ctrl.filter = [];

        ctrl.load = function () {
            $http.get('/filters/5886434af36d2855565452a6').then(
                function (response) {
                    ctrl.filter = response.data;
                }
            );
        };

        ctrl.load();

        ctrl.onEnterPress = function (keyEvent) {
            if (keyEvent.which === 13) {
                ctrl.addToFilter();
            }
        };

        ctrl.addToFilter = function () {
            if (ctrl.filter.queries.indexOf(ctrl.tempValue) !== -1) {
                alert('Value exists!');
            } else {
                ctrl.filter.queries.push(ctrl.tempValue);
                ctrl.tempValue = '';
            }
        };

        ctrl.removeFromFilter = function (name) {
            let index = ctrl.filter.queries.indexOf(name);
            if (index !== -1) {
                ctrl.filter.queries.splice(index, 1);
            }
        };

        ctrl.save = function () {
            $http.post('/filters', ctrl.filter).then(
                function (response) {
                    alert('Success!');
                },
                function (error) {
                    alert('Error!');
                }
            );
        };

        ctrl.delete = function () {
            $http.delete('/filters/' + ctrl.filter.id).then(
                function (response) {
                    alert('Success!');
                },
                function (error) {
                    alert('Error!');
                }
            );
        }
    }

    angular
        .module('vkscanner')
        .component('filterMain', {
            templateUrl: 'components/filter/main/filter-main.html',
            controller: filterMainController
        });
})();