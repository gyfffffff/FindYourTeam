<script>
import Footer from "@/components/Footer.vue";
import NavBar from "@/components/NavBar.vue";
import http from "@/http";
import axios from "axios";
export default {
    name: "HomeView",
    components: { NavBar, Footer },
    data() {
        return {
            user: {
                xuehao: "",
                name: "",
                avatar: "",
            },
            cardArr: [],
            compArr: [],
            tableData: [],
            token: "",
            token_type: "",
        };
    },
    created() {
        this.initialize();
    },
    methods: {
        async initialize() {
            await this.getuser(); // Wait for getuser to complete
            this.check()
            this.load()
        },
        async getCode() {
            const name = "code"
            const url = window.location.href;
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return results[2].replace(/\+/g, " ");
        },
        async getToken(code) {
            try {
                const url2 = "https://api.ecnu.edu.cn/oauth2/token?grant_type=authorization_code&client_id=18cf6a2d57ab0f88&client_secret=0b157bbd4fb9ec63aeb6f06ac3f9dd8b&scope=ECNU-Basic&redirect_uri=http://localhost:8080/home&code=" + code
                const response = await axios.post(url2);
                return { token: response.data.access_token, tokenType: response.data.token_type };
            } catch (error) {
                console.error('Error fetching token:', error);
                // alert('Error fetching token:', error);
                // throw error;
            }
        },

        async getUserInfo(token, tokenType) {
            try {
                const config = {
                    method: 'get',
                    url: 'https://api.ecnu.edu.cn/oauth2/userinfo',
                    headers: {
                        'Authorization': tokenType + ' ' + token,
                    },
                };

                const response = await axios(config);
                return { userId: response.data.data.userId, userName: response.data.data.name };
            } catch (error) {
                console.error('Error fetching user info:', error);
                throw error;
            }
        },

        // Usage in your component method
        async getuser() {
            try {
                if(sessionStorage.getItem("uid")) return;  // 如果application里本来就有东西，返回
                else {
                    const code = await this.getCode();
                    // if (code===null) {
                    //     sessionStorage.setItem("xuehao", 'fake')
                    //     sessionStorage.setItem("uid", 'fake')
                    //     sessionStorage.setItem("name", 'fake')
                    //     console.log("7878", sessionStorage.getItem("xuehao"))
                    //     return;
                    // }
                    const { token, tokenType } = await this.getToken(code);
                    //console.log('Access Token:', token);
                    const { userId, userName } = await this.getUserInfo(token, tokenType);
                    //console.log('User ID:', userId);
                    this.user.xuehao = userId;
                    this.user.name = userName;
                    this.user.avatar = "https://i.imgs.ovh/2023/12/15/6wIg0.png";
                    console.log("user", this.user);
                    const res = await http.post("user/register", this.user)
                    this.user.uid = res.data.msg
                    sessionStorage.setItem("xuehao", this.user.xuehao)
                    sessionStorage.setItem("uid", this.user.uid)
                    sessionStorage.setItem("name", this.user.name)
                }
            } catch (error) {
                console.error('Data fetching failed:', error);
                // alert('Data fetching failed:', error);
            }
        },
        check() {
            if (!sessionStorage.getItem("xuehao")) this.$router.push("/login");
        },
        // 点击导航栏时候执行的操作
        navClick(val) {
            this.$router.push(val);
        },
        gotoproj(val) {
            this.$router.push({
                path: "/projdetail",
                query: {
                    projectId: val,
                },
            });
            document.documentElement.scrollTop = 0;
        },
        gotoProjlist() {
            this.$router.push("/projlist");
        },
        gotoComplist() {
            this.$router.push("/competition");
            // window.location.href = "http://localhost:8080/competition"
        },
        gotoComp() {
            this.$router.push("/comdetail")
            document.documentElement.scrollTop = 0;
        },
        load() {
            http.get("/project/homeload", {
                params: { pageNum: 1, pageSize: 3 },
            }).then((res) => {
                this.tableData = res.data.data.records;
                //console.log('112', this.tableData)
            });

            http.get("/compitition/load", {
                params: { pageNum: 1, pageSize: 3 },
            })
                .then((res) => {
                    //console.log(res);
                    this.compArr = res.data.data.records;
                });
        },
    },
};
</script>

<template>
    <div class="homeMain">
        <!-- 导航栏， '首页','项目看板','数字素养','赛事天地' 区域-->
        <NavBar></NavBar>
        <!-- 项目看板区域 -->
        <div class="title">
            <p>热门项目</p>
            <el-button class="titleButton" @click="gotoProjlist">查看更多</el-button>
        </div>
        <div class="hotProjects">
            <el-row style="margin-left: 70px;">
                <el-col :span="8" v-for="item in tableData" :key="item.pid">
                    <el-card shadow="hover" class="projectCard" @click.native="gotoproj(item.pid)">
                        <img :src="item.pic" class="cardImage" />
                        <p class="cardTitle" truncated>
                            {{ item.title }}
                        </p>
                        <p class="cardDetail">
                            {{ item.intro }}
                        </p>
                        <el-tag type="success" effect="dark" class="cardTag">{{ item.tag }}</el-tag>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <!-- 赛事天地区域 -->
        <div class="title">
            <p>近期赛事</p>
            <el-button class="titleButton" @click="gotoComplist">查看更多</el-button>
        </div>
        <div class="competitions">
            <div class="noticeInfo" v-for="item in compArr" :key="item.tag" @click="gotoComp">
                <!-- 这里可以多加一些条件渲染 -->
                <div class="noticeTagBox">
                    <el-tag type="success" effect="dark" class="noticeTag">{{ item.tag }}</el-tag>
                </div>
                
                <p class="noticeTitle">{{ item.title }}</p>
                <p class="noticeTime">{{ item.date }}</p>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>

<style scoped>
.homeMain {
    width: 100%;
}

.title {
    width: 100%;
    height: 63px;
    /* background-color: rgba(151, 163, 234, 0.15); */
    background-color: #fff;
    line-height: 60px;
    font-size: 24px;
    font-weight: 600;
    padding-left: 70px;
    padding-right: 30px;
    padding-top: 3px;
    display: flex;
    justify-content: space-between;
}

.titleButton {
    width: 150px;
    height: 58px;  /* 58px 比大盒子略小一些 */
    background-color: #b70031;
    color: white;
    font-size: 24px;
    line-height: 40px;
    align-items: center;
    text-align: center;
    border: 2px solid #b70031 !important;
}

.titleButton:hover {
    background-color: #fff;
    color: #b70031;
    border: 0;
}

.hotProjects {
    width: 100%;
    height: 500px;
    background-color: #e7e7e7;
}

.projectCard {
    height: 400px;
    margin-top: 30px;
    margin-right: 30px;
    background-color: #fff;
}

.cardImage {
    width: 100%;
    height: 250px;
    object-fit: contain;
}

.cardTitle {
    margin: 5px 0;
    font-size: 20px;
    font-weight: 600;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.cardDetail {

    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.cardTag {
    margin-top: 20px;
    border-radius: 20px;
    background-color: #008672 !important;
}

.competitions {
    width: 100%;
    height: 500px;
    background-color: #e7e7e7;
    padding: 20px 30px;
    padding-left: 70px;
}

.noticeInfo {
    width: 100%;
    height: 60px;
    background-color: rgba(151, 163, 234, 0.15);
    margin-bottom: 20px;
    display: flex;
    line-height: 40px;
    align-items: center;
    cursor: pointer;
}

.noticeTagBox {
    width: 160px;
    height: 40px;
}

.noticeTag {
    border-radius: 20px;
    border: 0;
    height: 40px;
    background-color: purple !important;
    line-height: 40px;
    font-size: 20px;
    margin-left: 20px;
    
}

.noticeTitle {
    width: 70%;
    font-size: 20px;
}

.noticeTime {
    width: 200px;
    margin-left: 50px;
    font-size: 20px;
}
</style>
