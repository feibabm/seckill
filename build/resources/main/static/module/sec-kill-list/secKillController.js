/**
 * Created by feibabm on 2017/9/6 0006.
 */
angular.module('app').controller('seckillListController', function ($scope, secKillDataService) {
    secKillDataService.getSecKillList().then(function (data) {
        if(data.success){
            $scope.secKillList = data.data;
        }
    });
});