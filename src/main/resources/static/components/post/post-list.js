/**
 * Created by Pavel on 19.02.2017.
 */
'use strict';
(function () {

    function postListController($http, $state) {
        let ctrl = this;

        ctrl.posts = [];
        ctrl.interestingOnly = true;

        ctrl.load = function (page) {
            let url = '/posts?interestingOnly=' + ctrl.interestingOnly;
            if (page) {
                url = url + '&page=' + page;
            }
            $http.get(url).then(
                function (response) {
                    ctrl.posts = response.data.content;
                    ctrl.isLastPage = response.data.last;
                    ctrl.isFirstPage = response.data.first;
                    ctrl.currentPage = response.data.number;
                }
            );
        };

        ctrl.load();
        
        ctrl.loadPreviousPage = function(){
            ctrl.load(ctrl.currentPage - 1);
        };
        
        ctrl.loadNextPage = function(){
            ctrl.load(ctrl.currentPage + 1);
        };

        ctrl.setInteresting = function(post){
            $http.post('/posts/' + post.postId + '/setInteresting?interesting=' + !post.interesting).then(
                function(response) {
                    alert('Success!');
                }
            )
        }
    }

    angular
        .module('vkscanner')
        .component('postList', {
            templateUrl: 'components/post/post-list.html',
            controller: postListController
        });
})();