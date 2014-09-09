'use strict';

function RootController($scope, $routeParams, DataService, $location) {

    $scope.categoryTree = DataService.categoryTree();

    $scope.cct = {};
    $scope.c_cart = {totalPrice: 0, totalItem: 0};
    $scope.user_msg = {msg: ""};

    lglg($scope);


    $scope.logout = function() {
        DataService.logout();
    }
}

// the storeController contains two objects:
// - store: contains the product list
// - cart: the shopping cart object
function HomeController($scope, $routeParams, DataService, $location) {

    lglg($scope);



    // get store and cart from service
    $scope.store = DataService.defaultStore();
    $scope.cart = DataService.cart;


    $scope.c_cart.totalItem = $scope.cart.getTotalCount();
    $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}

function UserCtrl($scope, DataService, $location) {

    lglg($scope);



    $scope.user = {email: "", password: ""};

    $scope.r_user = {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        password: '',
        city: '',
        zip: '',
        country: '',
        address1: '',
        address2: '',
    };

    $scope.login = function() {
        DataService.login($scope.user);
    }

    $scope.register = function() {
        DataService.register($scope.r_user);
    }
}

function ProductByCategoryCtrl($scope, $routeParams, DataService) {

    lglg($scope);



    $scope.cct.parent = $routeParams.parent;
    $scope.cct.child = $routeParams.child;

    // get store and cart from service
    $scope.store = DataService.storeByCategory($routeParams.child, $routeParams.parent);
    $scope.cart = DataService.cart;

    $scope.c_cart.totalItem = $scope.cart.getTotalCount();
    $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();


    $scope.addItem = function(sku, name, price, quantity) {

        $scope.cart.addItem(sku, name, price, quantity);
        $scope.c_cart.totalItem = $scope.cart.getTotalCount();
        $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();
    }


    console.log("ProductByCategoryCtrl : " + $routeParams.productSku);
    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}

function ProductCtrl($scope, $routeParams, DataService) {

    lglg($scope);



    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;

    $scope.c_cart.totalItem = $scope.cart.getTotalCount();
    $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();


    $scope.addItem = function(sku, name, price, quantity) {

        $scope.cart.addItem(sku, name, price, quantity);
        $scope.c_cart.totalItem = $scope.cart.getTotalCount();
        $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();
    }

    if ($routeParams.productSku != null) {
        $scope.product = DataService.productBySku($routeParams.productSku);
    }
}

function CartCtrl($scope, $routeParams, DataService) {

    lglg($scope);


    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;


    $scope.c_cart.totalItem = $scope.cart.getTotalCount();
    $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();



    $scope.addItem = function(sku, name, price, quantity) {

        $scope.cart.addItem(sku, name, price, quantity);
        $scope.c_cart.totalItem = $scope.cart.getTotalCount();
        $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();
    }

    // use routing to pick the selected product
    if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }
}


function CheckoutCtrl($scope, $routeParams, DataService) {
    lglg($scope);


    // get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;


    $scope.c_cart.totalItem = $scope.cart.getTotalCount();
    $scope.c_cart.totalPrice = $scope.cart.getTotalPrice();

    $scope.b_dtls = {};
    $scope.s_dtls = {};


    this.userCheckout = function() {
        
    }


    this.registerCheckout = function() {
        
    }

    this.guestCheckout = function() {
        
    }

    $scope.checkout = function() {

        if (localStorage != null && JSON != null) {
            
            var user = localStorage['ang_user'];
            
            if (!(user == "null" || user == null || user == undefined)) {
                
                this.userCheckout();
                
            } else if($scope.register) {
                
                this.registerCheckout();
                
            } else {
                
                this.guestCheckout();
            }
            
        }

    }
}


function lglg($scope) {
    if (localStorage != null && JSON != null) {

        var user = localStorage['ang_user'];

        if (user == "null" || user == null || user == undefined) {
            $scope.user = {};
            $scope.user.loggedOut = "true";
            $scope.user.loggedIn = null;
        } else {
            $scope.user = JSON.parse(user);
            $scope.user.loggedIn = 'true';
            $scope.user.loggedOut = null;
        }
    }
}