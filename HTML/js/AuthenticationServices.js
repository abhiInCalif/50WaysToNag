var authService = app.factory('authService', function($http, $templateCache) {
	return {
		login: function(username, password, callback)
		{
			$http({method: "GET", 
					url: "/Bday/login?username=" + username + "&password=" + password, 
					cache: $templateCache
					}).success(function(data, status, headers, config)
					{
						callback(data)
					}).error(function(data, status, headers, config)
					{
						// handle the error in a delicate manner
					});
		},
		
		register: function(email, password, callback)
		{
			$http({method: "POST", 
					url: "/Bday/login", 
					data: {"email": email, "password": password},
					cache: $templateCache
					}).success(function(data, status, headers, config)
					{
						callback(data)
					}).error(function(data, status, headers, config)
					{
						// handle the error in a delicate manner
					});
		}
	};
	
	
});