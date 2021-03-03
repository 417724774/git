import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Student from "../views/Student";
import Test from "../views/Test";
import Register from "../views/Register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: "Login"}
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/student',
    name: 'Student',
    component: Student
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
