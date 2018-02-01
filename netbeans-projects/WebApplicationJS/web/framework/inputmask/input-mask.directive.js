app.directive("inputMask", inputMask);

function inputMask() {
    return {
        restrict: 'A',
        scope: {
            mask: "@"
        },
        link: function(scope, element, attrs) {
            $(element).mask(scope.mask);
        }
    };
}
