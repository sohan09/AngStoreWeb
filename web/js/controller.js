'use strict';

function RootController($scope, $routeParams, DataService) {

	$scope.categoryTree = DataService.categoryTree();

	console.log("RootController");
}

// the storeController contains two objects:
// - store: contains the product list
// - cart: the shopping cart object
function StoreController($scope, $routeParams, DataService) {

    // get store and cart from service
    $scope.store = DataService.storeByCategory('Apple', 'Laptop');
    $scope.cart = DataService.cart;

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}

function ProductByCategoryCtrl($scope, $routeParams, DataService) {

    // get store and cart from service
    $scope.store = DataService.storeByCategory($routeParams.child, $routeParams.parent);
    $scope.cart = DataService.cart;

	console.log("ProductByCategoryCtrl : " + $routeParams.productSku);
    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}

function ProductCtrl($scope, $routeParams, DataService) {

    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;

	console.log("ProductByCategoryCtrl : " + $routeParams.productSku);
    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = DataService.productBySku($routeParams.productSku);
    }
}

function CartCtrl($scope, $routeParams, DataService) {

    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}