import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Student from "../views/Student";
import Test from "../views/Test";
import Company_register from "../views/Company_register";
import Student_detail from "../views/Student_detail"
import Index from "../views/Index";
import Student_uppwd from "../views/Student_uppwd";
import Student_resume from "../views/Student_resume";
import Student_delivery from "../views/Student_delivery";
import Student_company from "../views/Student_company";
import Student_company_detail from "../views/Student_company_detail";
import Student_policy from "../views/Student_policy";
import Student_policy_detail from "../views/Student_policy_detail";
import Student_register from "../views/Student_register";
import Company from "../views/Company";
import Company_detail from "../views/Company_detail";
import Company_uppwd from "../views/Company_uppwd";
import Company_job from "@/views/Company_job";
import Company_job_detail from "../views/Company_job_detail";
import Company_resume from "../views/Company_resume";
import Company_resume_detail from "../views/Company_resume_detail";
import Company_jion from "../views/Company_jion";
import Company_student from "../views/Company_student"
import Company_student_detail from "../views/Company_student_detail";
import Company_statistic from "../views/Company_statistic";
import Company_job_add from "../views/Company_job_add";
import Teacher from "../views/Teacher";
import Teacher_detail from "../views/Teacher_detail";
import Teacher_uppwd from "../views/Teacher_uppwd";
import Teacher_jobfair from "../views/Teacher_jobfair";
import Teacher_jobfair_add from "../views/Teacher_jobfair_add";
import Teacher_jobfair_detail from "../views/Teacher_jobfair_detail";

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location){
  return originalPush.call(this, location).catch(err => err)

}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '',
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
    component: Student,
    children:[
    {
      path: '/student_detail',
      name: 'Student_detail',
      component: Student_detail
    },{
      path: '/student_index',
      name: 'Student_index',
      component: Index
    },{
      path: '/student_uppwd',
      name: 'Student_uppwd',
      component: Student_uppwd
    },{
      path: '/student_resume',
      name: 'Student_resume',
      component: Student_resume
    },{
      path: '/student_company',
      name: 'Student_company',
      component: Student_company
    },{
      path: '/student_delivery',
      name: 'Student_delivery',
      component: Student_delivery
    },{
      path: '/student_company_detail/:cuserid',
      name: 'Student_company_detail',
      component: Student_company_detail
    },{
      path: '/Student_policy',
      name: 'Student_policy',
      component: Student_policy
    },{
      path: '/Student_policy_detail/:pid',
      name: 'Student_policy_detail',
      component: Student_policy_detail
    },{
      path: '/student_register',
      name: 'Student_register',
      component: Student_register
    }]
  },
  {
    path: '/company',
    name: 'Company',
    component: Company,
    children: [
      {
        path: '/company_detail',
        name: Company_detail,
        component: Company_detail
      },{
        path: '/company_index',
        name: 'Company_index',
        component: Index
      },{
        path: '/company_uppwd',
        name: 'Company_uppwd',
        component: Company_uppwd
      },{
        path: '/company_job',
        name: 'Company_job',
        component: Company_job
      },{
        path: '/company_job_add',
        name: 'Company_job_add',
        component: Company_job_add
      },{
        path: '/company_job_detail',
        name: 'Company_job_detail',
        component: Company_job_detail
      },{
        path: '/company_resume',
        name: 'Company_resume',
        component: Company_resume
      },{
        path: '/company_resume_detail',
        name: 'Company_resume_detail',
        component: Company_resume_detail
      },{
        path: '/company_jion',
        name: 'Company_jion',
        component: Company_jion
      },{
        path: '/company_student',
        name: 'Company_student',
        component: Company_student
      },{
        path: '/company_student_detail',
        name: 'Company_student_detail',
        component: Company_student_detail
      },{
        path: '/company_statistic',
        name: 'Company_statistic',
        component: Company_statistic
      }
    ]
  },
  {
    path: '/teacher',
    name: 'Teacher',
    component: Teacher,
    children: [
      {
        path: '/teacher_detail',
        name: 'Teacher_detail',
        component: Teacher_detail
      },{
        path: '/teacher_index',
        name: 'Teacher_index',
        component: Index
      },{
        path: '/teacher_uppwd',
        name: 'Teacher_uppwd',
        component: Teacher_uppwd
      },{
        path: '/teacher_jobfair',
        name: 'Teacher_jobfair',
        component: Teacher_jobfair
      },{
        path: '/teacher_jobfair_add',
        name: 'Teacher_jobfair_add',
        component: Teacher_jobfair_add
      },{
        path: '/teacher_jobfair_detail',
        name: 'Teacher_jobfair_detail',
        component: Teacher_jobfair_detail
      }
    ]
  },
  {
    path: '/test',
    name: 'Test',
    component: Test

  },
  {
    path: '/company_register',
    name: 'Company_register',
    component: Company_register
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})



export default router
