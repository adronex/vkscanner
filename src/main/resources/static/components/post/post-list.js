/**
 * Created by Pavel on 19.02.2017.
 */
'use strict';
(function () {

    function postListController($http, $state) {
        let ctrl = this;

        ctrl.posts = [];

        ctrl.load = function () {
            $http.get('/posts').then(
                function (response) {
                    ctrl.posts = response.data.content;
                }
            );
        };

        ctrl.load();
    }

    angular
        .module('vkscanner')
        .component('postList', {
            templateUrl: 'components/post/post-list.html',
            controller: postListController
        });
})();