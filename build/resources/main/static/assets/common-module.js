var commonModule = angular.module('CommonModule', []);

commonModule.service('httpService',
['$http', '$q', '$state',
    function ($http, $q, $state) {
	
		function appendTransform(defaults, transform) {

		  // We can't guarantee that the default transformation is an array
		  defaults = angular.isArray(defaults) ? defaults : [defaults];

		  // Append the new transformation to the defaults
		  return defaults.concat(transform);
		}

        /**
         * 调用后台方法一
         * 分析后台返回结果，如果返回预期数据则return预期数据，否则无返回
         * @param httpConfig
         * @param apiName
         * @param errorCall
         * @returns {*}
         */
        this.httpExecute = function (httpConfig, apiName, errorCall) {
            if (!httpConfig) {
                return $q.reject({
                    success: false,
                    message: 'no http config',
                    code: 'NO_HTTP_CONFIG'
                });
            }
            httpConfig.headers = httpConfig.headers || {};
            var httpHeader = {
//                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                'Content-Type': 'application/json;charset=UTF-8'
            };
            angular.extend(httpConfig.headers, httpHeader);

            return $http(httpConfig).then(function (result) {
//                if (result.data && result.data.success) {//调用成功返回
//                    alert("$http:" + result );
//                    console.log(result);
//            		console.log("result.data",result.data);
//            		alert(JSON.stringify(result.data));
                    return result.data;
//                } else {
//                    if (errorCall) {
//                        errorCall(result.data);
//                    } else {
//                        /*$ionicPopup.alert({
//                         template: apiName + "异常: " + result.data.message,
//                         title: '请求失败'
//                         });*/
//                        alert(apiName + "异常: " + result.data.message);
//                    }
//                	throw result.data;
//                }
            },function(error){
            	return error;
            });
//            .catch(function (error) {
//                var httpStatus = error.status;
//                var msg = '';
//
//                if (httpStatus === 0) {
//                    msg = '网络环境不太流畅请检查网络后重新尝试';
//                }
//                else {
//                    msg = '错误代码: ' + httpStatus;
//                }
//
//                if (httpStatus !== 200) {
//                    /*$ionicPopup.alert({
//                     template: apiName + "调用异常，" + msg,
//                     title: '请求失败'
//                     });*/
//                    alert(apiName + "调用异常，" + msg);
//                }
//                ;
//
//                if (errorCall) {
//                    errorCall();
//                }
//                //throw error;
//            });
        };

        /**
         * 调用后台方法二
         * 不分析后台返回结果，直接return
         * @param httpConfig
         * @param apiName
         * @param errorCall
         * @returns {*}
         */
        this.httpExecute2 = function (httpConfig, apiName, errorCall) {
            if (!httpConfig) {
                return $q.reject({
                    success: false,
                    message: 'no http config',
                    code: 'NO_HTTP_CONFIG'
                });
            }
            ;

            httpConfig.headers = httpConfig.headers || {};
            var httpHeader = {
                //'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                'Content-Type': 'application/json;charset=UTF-8'
            };
            angular.extend(httpConfig.headers, httpHeader);

            return $http(httpConfig).then(function (result) {
                return result;
            })
            .catch(function (error) {
                var httpStatus = error.status;
                var msg = '';

                if (httpStatus === 0) {
                    msg = '网络环境不太流畅请检查网络后重新尝试';
                }
                else {
                    msg = '错误代码: ' + httpStatus;
                }

                if (httpStatus !== 200) {
                    /*$ionicPopup.alert({
                     template: apiName + "调用异常，" + msg,
                     title: '请求失败'
                     });*/
                    alert(apiName + "调用异常，" + msg);
                }
                ;

                if (errorCall) {
                    errorCall();
                }
                //throw error;
            });
        };


        /**
         * 提交数据
         * @param entity
         * @param url
         * @returns {{}}
         */
        this.doPost = function (entity, url, apiName) {
            var outcome = {
                success: true,
                code: 0,
                message: "",
                data: null
            };
            var url = url;
            jQuery.ajax({
                type: 'POST',
                contentType: 'application/json',
                async: false,
                url: url,
                dataType: "json",
                data: JSON.stringify(entity),
                success: function (resp) {
                    if (resp.success) {
                        outcome.data = resp.data;
                    } else if (resp.error) {
                        outcome.success = false;
                        outcome.code = resp.code;
                        outcome.message = apiName + "提交数据失败";
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    outcome = {
                        error: true,
                        message: apiName + "提交数据异常"
                    };
                },
                complete: function (XMLHttpRequest, textStatus) {

                }
            });

            return outcome;
        }
    }]);
