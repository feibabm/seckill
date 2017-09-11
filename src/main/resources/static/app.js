'use strict';
angular.module('app',[
                      'ui.router','oc.lazyLoad',
                      'CommonModule'])
	.config(['$stateProvider', '$urlRouterProvider',function($stateProvider,$urlRouterProvider){
		$urlRouterProvider.otherwise("/secList");
		
		$stateProvider.state('secList',{
			url: "/secList",
			controller: "seckillListController",
			templateUrl: "module/sec-kill-list/secKillList.html",
            resolve: {
                deps: ['$ocLazyLoad',
                    function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                        	name: 'app',
                            files:
                        	[
                            'module/sec-kill-list/secKillService.js',
                            'module/sec-kill-list/secKillController.js'
                            ]});
                    }]

            }
		}).state('login',{
            url: "/login",
            controller: "loginController",
            templateUrl: "module/login/login.html",
            resolve: {
                deps: ['$ocLazyLoad',
                    function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: 'app',
                            files:
                                [
                                    'module/login/loginController.js',
                                    // 'module/login/login.css',
                                    'module/login/loginService.js'
                                ]});
                    }]

            }
        });
	}]).config(function ($stateProvider, $urlRouterProvider,$httpProvider) {
	       if (!$httpProvider.defaults.headers.get) {
	           $httpProvider.defaults.headers.get = {};
	       }
	      $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	      $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	      $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
	  });;