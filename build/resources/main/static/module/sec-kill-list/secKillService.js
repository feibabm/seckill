/**
 * Created by feibabm on 2017/9/6 0006.
 */
angular.module('app').service('secKillConfigService', function () {
    this.GET_SEC_KILL_LIST = {
        url: "/seckill/product/list",
        method: "GET"
    }
});
angular.module('app').service('secKillDataService', function (secKillConfigService, httpService) {
   
    this.getSecKillList = function () {
        var httpConfig = {
        };
        angular.extend(httpConfig, secKillConfigService.GET_SEC_KILL_LIST);
        return httpService.httpExecute(httpConfig, "获得卡券列表数据");
    }
});