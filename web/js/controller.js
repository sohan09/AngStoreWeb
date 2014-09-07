'use strict';

function RootController($scope, $routeParams, DataService, $location) {

    $scope.categoryTree = DataService.categoryTree();

    var user = localStorage['user'];
    
    $scope.user_msg = {msg: ""};

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }





    $scope.logout = function() {
        DataService.logout();
    }
}

// the storeController contains two objects:
// - store: contains the product list
// - cart: the shopping cart object
function StoreController($scope, $routeParams, DataService, $location) {

    var user = localStorage['user'];

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }



    // get store and cart from service
    $scope.store = DataService.defaultStore();
    $scope.cart = DataService.cart;

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}

function UserCtrl($scope, DataService, $location) {

    var user = localStorage['user'];

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }



    $scope.user = {email: "", password: ""};
    $scope.login = function() {
        DataService.login($scope.user);
    }

    $scope.register = function() {
        DataService.register($scope.user);
    }
}

function ProductByCategoryCtrl($scope, $routeParams, DataService) {

    var user = localStorage['user'];

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }



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

    var user = localStorage['user'];

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }



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

    var user = localStorage['user'];

    $scope.user = user;

    if (user == "null") {
        $scope.user_act = function() {
            $location.path('/login');
        }
        $scope.user_msg.msg = "Log In or Register"
    } else {

        $scope.user_act = function() {
            DataService.logout();
        }
        
        $scope.user_msg.msg = "Log Out"
    }



    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}