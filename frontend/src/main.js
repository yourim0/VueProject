import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/pages/VueHome';
import Login from '@/pages/VueLogin';

const routes= [
    {path:'/', component: Home}, //root(/) 경로로 들어올때는 컴포넌트를 home으로 연결해라.
    {path:'/login', component: Login}
]

const router = createRouter({
    history : createWebHistory(),
    routes
})

createApp(App).use(router).mount('#app')
