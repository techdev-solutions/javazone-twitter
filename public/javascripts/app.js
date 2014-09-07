function MessageController($scope, $http) {

    $scope.init = function() {
        cleanup();
    };

    $http.get('http://localhost:9000/alexhanschke/messages')
        .success(function(data) {
            $scope.messages = data;
        });

    var ws = new WebSocket("ws://localhost:9000/socket");

    ws.onmessage = function(message) {
        $scope.$apply(function () {
            $scope.messages.push(JSON.parse(message.data));
        });
    };

    $scope.submit = function() {
        $http({
            method : 'POST',
            url : 'http://localhost:9000/alexhanschke/messages',
            data : '{"content": "' + $scope.content + '"}',
            headers : {
                'Content-Type' : 'application/json'
            }
        }).success(function() {
            cleanup();
        }).error(function() {
            $scope.error = 'Ooops. Something went wrong!';
        });
    };

    var cleanup = function() {
        $scope.error = '';
        $scope.content = '';
    }
}
