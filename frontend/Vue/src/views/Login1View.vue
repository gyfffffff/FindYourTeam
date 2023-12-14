<script>
import {ref} from 'vue'

export default {
    name: "Login1",
    data() {
        return {
            agreement: ref(false),
        };
    },
    methods: {
        toLogin() {
            if (this.agreement) {
                window.location.href = 'https://api.ecnu.edu.cn/oauth2/authorize?scope=ECNU-Basic&redirect_uri=http://localhost:8080/home&response_type=code&client_id=18cf6a2d57ab0f88&state=33728846'
                // window.location.href = '/'
            } else {
                alert('请阅读并同意《用户协议》')
            }
            
        }
    },
}
</script>

<template>
    <div class="wrapper">
        <div class="loginBox">
            <p class="loginTitle">登录</p>
            <el-button class="loginButton" @click="toLogin">前往 ECNU 进行统一认证</el-button>
            <div class="agreement">
                <el-checkbox v-model="agreement">我已阅读并同意《用户协议》</el-checkbox>
            </div>
        </div>
    </div>
</template>

<style scoped>

.wrapper {
    background: url('../../public/loginBackground.png') no-repeat;
    background-size: 100% 100%;
    width: 100%;
    height: 100%;
    position: fixed;
}
.loginBox {
    width: 750px;
    height: 520px;
    background-color: rgb(228, 228, 228, .7);
    display: flex;
    flex-direction: column;
    position: absolute;
    top: 50%;
    left: 50%;
    transform:translate(-50%,-50%);
}
.loginTitle {
    height: 60px;
    font-size: 40px;
    font-weight: 600;
    margin: 60px auto;
}
.loginButton {
    height: 60px;
    border-radius: 5px;
    margin: 20px auto;
    padding: 0 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    font-weight: 400;
    color: white;
    background-color: #cc0000;
}
.agreement {
    height: 20px;
    font-size: 14px;
    font-weight: 400;
    margin: 0 auto;
    margin-top: 60px;
    cursor: pointer;
}
</style>