import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import '@/assets/css/global.css'
import '@/assets/css/element-ui-reset.scss'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css';

const app = createApp(App)

app.use(ElementPlus)
app.use(store)
app.use(router)

app.mount('#app')

app.config.globalProperties.routerAppend = (path, pathToAppend) => {
    return path + (path.endsWith('/') ? '' : '/') + pathToAppend
}

const originalPush = router.push
router.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch((err) => err)
}