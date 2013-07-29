function AuthenticationController($scope, authService, $location)
{
  $scope.error = false;

  $scope.onClickOfRegister = function()
  {
    // check basic frontend validation then issue the service call.
    if ($scope.register.$valid != false)
    {
      // double check that the two passwords are equal
      if($scope.password != $scope.retype)
      {
        // we need to handle this somehow
        console.log('passwords not equal');
        return false;
      }
      // proceed to issue service call
      $.mobile.showPageLoadingMsg( "b", "Registering...", false);
      authService.register($scope.username, $scope.password, $scope.phone, function(data)
      {
        if (data != -1)
        {
          // Post message then redirect....
          $.mobile.showPageLoadingMsg( "b", "Registration Success!", true);
          setTimeout(function()
          {
            // either store cookie and forward or just forward, needs to be discussed.
            $.mobile.hidePageLoadingMsg();
            // because asynch call
            $scope.$apply(function() {
              $location.path('/login');
            });
          }, 1000);
        }
        else
        {
          $.mobile.showPageLoadingMsg("a", "Registration Failed (user probably exists already)", true);
          setTimeout(function()
          {
            // either store cookie and forward or just forward, needs to be discussed.
            $.mobile.hidePageLoadingMsg();
          }, 2000);
        }
      });
    }
    return false;
  };

  $scope.onClickOfLogin = function(event)
  {
    // prevents page from refreshing
    event.preventDefault();
    if ($scope.login.$valid != false)
    {
      // proceed to issue service call
      $.mobile.showPageLoadingMsg( "b", "Logging In...", false);
      authService.login($scope.username, $scope.password, function(data)
      {
        if(data == -1)
        {
          // // invalid user, display errors
          //          $.mobile.showPageLoadingMsg( "b", "Error invalid Username / Password", true);
          //          // hide after delay
          //          setTimeout( $.mobile.hidePageLoadingMsg, 1500 );
          $scope.error = true;
          $.mobile.showPageLoadingMsg( "b", "Log In Failed!", true);
          setTimeout(function()
          {
            // either store cookie and forward or just forward, needs to be discussed.
            $.mobile.hidePageLoadingMsg();
          }, 2000);
        }
        else
        {
          // redirect the user, store the session object so that you have the authentication
          // token, the token should be sent back from the server, and needs to be incorporated
          // in every call (ensures an authenticated user....);
          $location.path("/familyList");
        }
      });
    }
  };
}