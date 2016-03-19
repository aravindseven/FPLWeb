var app = angular.module('fpl', ['ngFileUpload']);
var tips = angular.module('tips', ['ui.bootstrap']);
var communication = angular.module('communication', ['ui.bootstrap']);
var Erecord = angular.module('erecord', ['ui.bootstrap']);
var Headerapp = angular.module('Headerapp', ['ui.bootstrap']);
Headerapp.value('appName', 'Headerapp');
Erecord.value('appName', 'erecord');
app.value('appName', 'Financial Planner');
communication.value('appName', 'Communication');
tips.value('appName', 'Tips');
app.factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){
	
    return { }
}])/*.factory('myHttpInterceptor', function($q) {
    return {
        request: function(config) {
        	alert(config.url);
        	return config;
        }
    }
})*/.config(['$httpProvider',function($httpProvider) {
    //Http Intercpetor to check auth failures for xhr requests
    $httpProvider.interceptors.push('authHttpResponseInterceptor');
    //$httpProvider.interceptors.push('myHttpInterceptor');
}]);

communication.factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){
    return {
        response: function(response){
            if (response.status === 500) {
                window.location.href = "index1.jsp";
            }
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status >= 400 && rejection.status <= 416) {
                //alert("Response Code: " + rejection.status + ";Client Error status codes; redirect to error page.");
            }
            if (rejection.status >= 500 && rejection.status <= 505) {
                //alert("Response Code: " + rejection.status + ";Server Error status codes; redirect to error page.");
            	window.location.href = "index1.jsp";
            }
            return $q.reject(rejection);
        }
    }
}])/*.factory('myHttpInterceptor', function($q) {
    return {
        request: function(config) {
        	alert(config.url);
        	return config;
        }
    }
})*/.config(['$httpProvider',function($httpProvider) {
    //Http Intercpetor to check auth failures for xhr requests
    $httpProvider.interceptors.push('authHttpResponseInterceptor');
    //$httpProvider.interceptors.push('myHttpInterceptor');
}]);

//initial setup
$(document).ready(function() {
	$("#forgotPassword").click(function() {
		$("#passwordRecovery").toggle();
		$("#usernameRecovery").hide();
		$(".glyphicon", $("#forgotPassword")).toggleClass("glyphicon-chevron-down glyphicon-chevron-up");
		
		if($(".glyphicon", $("#forgotUsername")).hasClass("glyphicon-chevron-up")){
			$(".glyphicon", $("#forgotUsername")).removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}
	
	});
	$("#forgotUsername").click(function() {
		$("#usernameRecovery").toggle();
		$("#passwordRecovery").hide();
		$(".glyphicon", $("#forgotUsername")).toggleClass("glyphicon-chevron-down glyphicon-chevron-up");
		
		if($(".glyphicon", $("#forgotPassword")).hasClass("glyphicon-chevron-up")){
			$(".glyphicon", $("#forgotPassword")).removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}
	});
	
	$('#myModal').on('show.bs.modal', function () {
		$("#usernameRecovery").hide();
		$("#passwordRecovery").hide();
		
		$("span[.glyphicon]", $("#forgotPassword")).removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		$("span[.glyphicon]", $("#forgotUsername")).removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
	});
});

/*communication.config(function(IdleProvider, KeepaliveProvider) {
	console.log("idle provider and keepaliveprovider");
    KeepaliveProvider.interval(10);
  })
  communication.run(function($rootScope, Idle, $log, Keepalive){
    Idle.watch();

    $log.debug('app started.');
  });*/



