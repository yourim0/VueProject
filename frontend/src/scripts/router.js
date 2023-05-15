import Home from '@/pages/VueHome';
import Login from '@/pages/VueLogin';
import Cart from '@/pages/Cart';
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";

const routes= [
    {path:'/', component: Home}, //root(/) 경로로 들어올때는 컴포넌트를 home으로 연결해라.
    {path:'/login', component: Login},
    {path:'/cart',component:Cart}
]

const router = createRouter({
    history : createWebHistory(),
    routes
})

export default router;