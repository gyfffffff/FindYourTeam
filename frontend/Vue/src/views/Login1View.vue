<script>
import { ref } from 'vue'
import axios from 'axios';
export default {
    name: "Login1",
    data() {
        return {
            agreement: ref(false),
        };
    },
    methods: {
        toLogin() {
            var config = {
                method: 'get',
                url: 'https://api.ecnu.edu.cn/oauth2/authorize?scope=ECNU-Basic&redirect_uri=http://localhost:8080&response_type=code&client_id=18cf6a2d57ab0f88&state=33728846',
                // headers: {
                //     'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
                //     'Accept': '*/*',
                //     'Host': 'api.ecnu.edu.cn',
                //     'Connection': 'keep-alive',
                //     'Referer': 'https://api.ecnu.edu.cn/oauth2/authorize?scope=ECNU-Basic&redirect_uri=http://localhost:8080&response_type=code&client_id=18cf6a2d57ab0f88&state=33728846'
                // }
            };

            axios(config)
                .then(function (response) {
                    console.log(JSON.stringify(response.data));
                })
                .catch(function (error) {
                    console.log(error);
                });
            // const url = "https://api.ecnu.edu.cn/oauth2/authorize?scope=ECNU-Basic&redirect_uri=http://localhost:8080/&response_type=code&client_id=18cf6a2d57ab0f88&state=789613143"
            // axios.get(url).then(res => {
            //     console.log("24", res)
            // })
            // const url2 = "https://portal1.ecnu.edu.cn/cas/login?service=https://api.ecnu.edu.cn/user/authorize"
            // const cookie = "Language=zh_CN; Hm_lvt_f76f8c7e3ddd48018c54d9d37f42086a=1699595503,1701054166; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218c4391af278c2-0eb0d8e14edf338-26031051-921600-18c4391af288db%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.google.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjNDM5MWFmMjc4YzItMGViMGQ4ZTE0ZWRmMzM4LTI2MDMxMDUxLTkyMTYwMC0xOGM0MzkxYWYyODhkYiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218c4391af278c2-0eb0d8e14edf338-26031051-921600-18c4391af288db%22%7D; __bid_n=18c4391b0347456a38b3b9; jsessionid_tpass=0X9maesEAV5dkK2OB2GTpdm8dv192II1Ub4bvezcCto7_3H2mqul!830185455"
            // const Header = {
            //     'Cookie': cookie
            // }
            // const config = {
            //     // headers: Header,
            //     // withCredentials: true
            // }
            // axios.get(url2, config).then(res => {
            //     console.log("27", res)
            // }).catch(error =>{
            //     console.log(error)
            // })
        }
    },
}
</script>

<template>
    <div class="wrapper">
        <div class="loginBox">
            <p class="loginTitle">登录</p>
            <el-button class="loginButton" @click="toLogin()">前往 ECNU 进行统一认证</el-button>
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
    min-height: 1080px;
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
    transform: translate(-50%, -50%);
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