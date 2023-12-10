<script>
import Footer from "@/components/Footer.vue";
import { ref } from "vue";
import Myproj from "@/components/Mypage/Myproj.vue";
import MyTeam from "@/components/Mypage/MyTeam.vue";
import MyTask from "@/components/Mypage/MyTask.vue";
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
            infoArr: [
                {
                    label: "姓名",
                    value: "张三",
                },
                {
                    label: "学号",
                    value: "10215508888",
                },
                {
                    label: "学院",
                    value: "计算机学院",
                },
                {
                    label: "专业",
                    value: "计算机科学与技术",
                },
                {
                    label: "班级",
                    value: "计算机科学与技术1班",
                },
                {
                    label: "邮箱",
                    value: "123@123.com",
                },
            ],
            buttonArr: ref([
                { label: "我的项目", checked: false, name: "button1" },
                { label: "我的团队", checked: false, name: "button2" },
                { label: "我的任务", checked: false, name: "button3" },
            ]),
            activeButton: "button1",
        };
    },
    created() {},
    methods: {
        selectButton(val) {
            this.activeButton = val;
        },
    },
};
</script>

<template>
    <div class="main">
        <!-- 个人信息部分 -->
        <div class="info">
            <div class="avatarBox">
                <img src="../assets/avatar.png" alt="avatar" class="avatar" />
            </div>
            <div class="detailInfo">
                <p v-for="item in infoArr" class="infoItem">
                    <span class="label">{{ item.label }}：</span>
                    <span class="value">{{ item.value }}</span>
                </p>
            </div>
        </div>
        
        <div class="myView">
            <!-- 按钮部分，三个按钮查看不同的信息 -->
            <div class="buttons">
                <el-row gutter="100">
                    <el-col :span="8" v-for="item in buttonArr" :key="item">
                        <el-button
                            type="primary"
                            :class="{'buttonStyle': true, 'el-button--primary': true, 'activeButton': item.name === activeButton}"
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
        <Footer></Footer>
    </div>
</template>

<style scoped>
.main {
    width: 100%;
    height: 830px;
    display: flex;
    flex-direction: row;
}
.info {
    width: 500px;
    height: 100%;
    padding: 20px;
    border-right: 1px solid #888;
}
.avatarBox {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
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
    width: 1144px;
    height: 100%;
    padding: 20px;
}
.buttons {
    margin-bottom: 50px;
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
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
</style>
