app.controller('ClienteCtrl', ClienteController);

ClienteController.$inject = ['$scope'];
function ClienteController($scope) {
    $scope.titulo = 'Cadastro de Clientes';
    $scope.lista = 'Listagem de Clientes';

    $scope.clientes = [];
    $scope.possuiClientes = false;

    $scope.salvar = function(cliente) {
        $scope.clientes.push(cliente);
        delete $scope.cliente;
        $scope.possuiClientes = $scope.clientes.length > 0;
    };
    $scope.novo = function() {
        delete $scope.cliente;
    };
    $scope.editar = function(index) {
        $scope.cliente = $scope.clientes[index];
    };

    $scope.remover = function(index) {
        $scope.clientes.splice(index, 1);
        $scope.possuiClientes = $scope.clientes.length > 0;
    };
}
