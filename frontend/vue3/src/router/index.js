import * as Vue from 'vue'
import * as VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/views/Login1View.vue'
import App from '@/App.vue'
import Register from '@/views/Register.vue'
import Project from '@/views/Project.vue'
import ProjList from '@/views/ProjList.vue'
import ProjDetail from '@/views/ProjDetail.vue'
import Task from '@/views/Task.vue'
import TaskList from '@/views/TaskList.vue'
import Group from '@/views/Group.vue'
import GroupList from '@/views/GroupList.vue'
import FileList from '@/views/FileList.vue'
import Comment from '@/views/Comment.vue'
import Chat from '@/views/Chat.vue'
import Digital from '@/views/Digital.vue'
import Competition from '@/views/Competition.vue'
import ComDetail from '@/views/ComDetail.vue'
import Mypage from '@/views/Mypage.vue'
import Privacy from '@/views/privacy.vue'
import Chatbot from '@/views/Chatbot.vue'

const routes = [
  {
    path: '/',
    name: 'app',
    component: App,
    redirect: '/home',
    children: [
      {
        // 主页面
        path: '/home',
        name: 'home',
        component: HomeView,
      },
    ],
  },
  {
    // 登录页面
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    // 隐私政策页面
    path: '/privacy',
    name: 'privacy',
    component: Privacy,
  },
  {
    // 注册页面
    path: '/register',
    name: 'register',
    component: Register,
  },
  {
    // 项目页面
    path: '/project',
    name: 'project',
    component: Project,
  },
  {
    // 项目列表
    path: '/projlist',
    name: 'projlist',
    component: ProjList,
  },
  {
    // 项目详情
    path: '/projdetail',
    name: 'projdetail',
    component: ProjDetail,
  },
  {
    // 任务页面
    path: '/task',
    name: 'task',
    component: Task,
  },
  {
    // 任务列表
    path: '/tasklist',
    name: 'tasklist',
    component: TaskList,
  },
  {
    // 团队页面
    path: '/group',
    name: 'group',
    component: Group,
  },
  {
    // 团队列表
    path: '/grouplist',
    name: 'grouplist',
    component: GroupList,
  },
  {
    // 文件列表
    path: '/filelist',
    name: 'filelist',
    component: FileList,
  },
  {
    // 审批列表
    path: '/comment',
    name: 'comment',
    component: Comment,
  },
  {
    // 聊天
    path: '/chat',
    name: 'chat',
    component: Chat,
  },
  {
    // 数字素养
    path: '/digital',
    name: 'digital',
    component: Digital,
  },
  {
    // 赛事天地
    path: '/competition',
    name: 'competition',
    component: Competition,
  },
  {
    // 赛事详情
    path: '/comdetail',
    name: 'comdetail',
    component: ComDetail,
  },
  {
    // 个人信息
    path: '/mypage',
    name: 'mypage',
    component: Mypage,
  },
  {
    path: '/chatbot',
    name: 'chatbot',
    component: Chatbot,
  }
]

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
