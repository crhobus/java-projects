app.controller('LoginCtrl', LoginCtrl);

LoginCtrl.$inject = ['$scope', '$location'];
function LoginCtrl($scope, $location) {
    $scope.titulo = 'Login';

    $scope.login = function (usuario) {
        $location.path("/app/home");
        delete $scope.usuario;
    };
    $scope.cancelar = function () {
        window.alert('novo');
        delete $scope.usuario;
    };
}
