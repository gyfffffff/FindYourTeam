<script>
import Footer from "@/components/Footer.vue";
import { ref } from "vue";
import Myproj from "@/components/Mypage/Myproj.vue";
import MyTeam from "@/components/Mypage/MyTeam.vue";
import MyTask from "@/components/Mypage/MyTask.vue";
import http from "@/http"; // 引入新创建的http实例

export default {
    name: "Mypage",
    components: {
        Myproj,
        MyTeam,
        MyTask,
        Footer,
    },
    data() {
        return {
            xuehao: "",
            infoArr: [],
            buttonArr: ref([
                { label: "我的项目", checked: false, name: "button1" },
                { label: "我的团队", checked: false, name: "button2" },
                { label: "我的任务", checked: false, name: "button3" },
            ]),
            activeButton: "button1",
            avatar: "",
            avatar_alt: require("../assets/avatar.png"),
        };
    },
    created() {
        this.check();
        this.fetchData();
        this.fetchAvatar();
    },
    methods: {
        check() {
            this.xuehao = sessionStorage.getItem("xuehao");
            if (this.xuehao == "fake") {
                alert("请先登录, 访问/login ");
            }
        },
        avatarURL() {
            http.get(
                "/user/getavatar?uid=" +
                    JSON.parse(sessionStorage.getItem("user")).uid
            )
                .then((res) => {
                    return res.data.data;
                })
                .catch((err) => {
                    console.log(err);
                    return this.avatar_alt;
                });
        },
        selectButton(val) {
            this.activeButton = val;
        },
        navHome() {
            this.$router.push("/");
        },
        fetchData() {
            const uid = sessionStorage.getItem("xuehao");
            http.get("/user/byxuehao?xuehao=" + uid)
                .then((res) => {
                    console.log(54, res);
                    this.infoArr = [
                        { label: "姓名", value: res.data.data.name },
                        { label: "学号", value: res.data.data.xuehao },
                    ];
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        fetchAvatar() {
            const url =
                "/user/getavatar?xuehao=" + sessionStorage.getItem("xuehao");

            http.get(url)
                .then((res) => {
                    console.log("70", res.data.data);
                    this.avatar = res.data.data;
                })
                .catch((err) => {
                    console.log(err);
                });
        },
    },
};
</script>

<template>
    <div>
        <el-button type="primary" class="returnButton" @click="navHome()"
            >返回首页</el-button
        >
        <el-row>
            <el-col :span="8">
                <!-- 个人信息部分 -->
                <div class="info">
                    <div class="avatarBox">
                        <img
                            :src="this.avatar"
                            alt="avatar_alt"
                            class="avatar"
                        />
                    </div>
                    <div class="detailInfo">
                        <p
                            v-for="(item, index) in infoArr"
                            :key="item.uid || index"
                            class="infoItem"
                        >
                            <span class="label">{{ item.label }}：</span>
                            <span class="value">{{ item.value }}</span>
                        </p>
                    </div>
                </div>
            </el-col>
            <el-col :span="16">
                <div class="myView">
                    <!-- 按钮部分，三个按钮查看不同的信息 -->
                    <div class="buttons">
                        <el-row gutter="100">
                            <el-col
                                :span="8"
                                v-for="item in buttonArr"
                                :key="item"
                            >
                                <el-button
                                    type="primary"
                                    :class="{
                                        buttonStyle: true,
                                        'el-button--primary': true,
                                        activeButton:
                                            item.name === activeButton,
                                    }"
                                    :dark="isDark"
                                    @click="selectButton(item.name)"
                                    >{{ item.label }}</el-button
                                >
                            </el-col>
                        </el-row>
                    </div>
                    <!-- 信息展示部分 -->
                    <div class="infoView">
                        <Myproj v-if="activeButton == 'button1'"></Myproj>
                        <MyTeam v-if="activeButton == 'button2'"></MyTeam>
                        <MyTask v-if="activeButton == 'button3'"></MyTask>
                    </div>
                </div>
            </el-col>
        </el-row>

        <Footer></Footer>
    </div>
</template>

<style scoped>
.returnButton {
    height: 40px;
    width: 100px;
    position: absolute;
    top: 20px;
    left: 20px;
    z-index: 100;
    padding: 0;
    line-height: 40px !important;
    font-size: 20px;
    font-weight: 400;
}

.main {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
    position: relative;
}

.info {
    width: 100%;
    height: 100%;
    padding: 20px;
    
}

.avatarBox {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    cursor: pointer;
}

.avatar {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    margin-top: 80px;
}

.detailInfo {
    width: 100%;
    height: 400px;
    background-color: white;
    border-radius: 20px;
    padding-top: 30px;
    padding-left: 50px;
}

.infoItem {
    width: 100%;
    height: 60px;
    line-height: 40px;
    align-items: center;
    font-size: 20px;
}

.label {
    font-size: 20px;
    color: #b70031;
}

.value {
    font-size: 20px;
    color: #888;
}

.myView {
    width: 100%;
    height: 100%;
    padding: 20px;
    border-left: 1px solid #888;
}

.buttons {
    margin-bottom: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}

.buttonStyle {
    background-color: pink !important;
    width: 150px;
    height: 60px;
    line-height: 60px !important;
    align-items: center !important;
    padding: 0;
    font-size: 20px;
    font-weight: 600;
    border: 1px solid #b70031 !important;
}

.activeButton {
    background-color: #b70031 !important;
    color: white !important;
    border: 1px solid #b70031 !important;
}

.infoView {
    width: 100%;
    height: 600px;
    background-color: white;
    border-radius: 20px;
    padding: 20px;
    padding-top: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
</style>
