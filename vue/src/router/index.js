import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'
import axios from 'axios';

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';

import OrderView from '../views/OrderView.vue';
import ContactUsView from '../views/ContactUsView.vue';
import ShoppingCartView from '../views/ShoppingCartView.vue';
import AboutUsView from '../views/AboutUsView.vue';
import ToppingsView from '../views/ToppingsView.vue';
import CustomerInfoView from '../views/CustomerInfoView.vue';



/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      title: 'Login',
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/order",
    name: "order",
    component: OrderView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/contact",
    name: "contact",
    component: ContactUsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/shopping-cart",
    name: "shopping-cart",
    component: ShoppingCartView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/about-us",
    name: "about-us",
    component: AboutUsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/custom-pizza",
    name: "custom",
    component: ToppingsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/customerInfo",
    name: "customerInfo",
    component: CustomerInfoView,
    meta: {
      requiresAuth: false
    }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
