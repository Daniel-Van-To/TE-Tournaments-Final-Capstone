import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import AboutUsView from '../views/AboutUsView.vue';
import FAQView from '../views/FAQView.vue';
import BrowseTournamentsView from '../views/BrowseTournamentsView.vue';
import CreateTeamView from '../views/CreateTeamView.vue';
import BrowseTeamsView from '../views/BrowseTeamsView.vue';
import TeamView from '../views/TeamView.vue';
import SeeTeamJoinRequestsView from '../views/SeeTeamJoinRequestsView.vue';
import CreateTournamentView from '../views/CreateTournamentView.vue';


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
    path: "/about-us",
    name: "about-us",
    component: AboutUsView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/faq",
    name: "faq",
    component: FAQView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/browse-tournaments",
    name: "browse-tournaments",
    component: BrowseTournamentsView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/create-team",
    name: "create-team",
    component: CreateTeamView,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: "/teams",
    name: "teams",
    component: BrowseTeamsView,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/teams/:teamId',
    name: 'team-view',
    component: TeamView,
    props: true,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/teams/:teamId/captain',
    name: 'see-team-join-requests-view',
    component: SeeTeamJoinRequestsView,
    meta: {
      requiresAuth: true
    }
  },
  
  {
    path: '/tournaments/create-tournament',
    name: 'create-tournament-view',
    component: CreateTournamentView,
    meta: {
      requiresAuth: true,
    }
  },
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
