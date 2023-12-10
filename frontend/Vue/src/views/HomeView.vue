<script>
import Footer from "@/components/Footer.vue";
import NavBar from "@/components/NavBar.vue";
export default {
    name: "HomeView",
    components: {NavBar, Footer},
    data() {
        return {
            user: "",
            cardArr: [1, 2, 3],
            noticeArr: [1, 2, 3],
            tableData: [],
        };
    },
    created() {
        this.user = JSON.parse(sessionStorage.getItem("user"));
        this.check();
    },
    methods: {
        check() {
            if (!this.user) this.$router.push("/login");
        },
        // 点击导航栏时候执行的操作
        navClick(val) {
            this.$router.push(val);
        },
        gotoproj(val) {
            this.$router.push({
                path: "/project",
                query: {
                    projectId: val,
                },
            });
        },
        gotoProjlist() {
            this.$router.push("/projlist");
        },
        gotoTasklist() {
            this.$router.push("/tasklist");
        },
        load() {
            request
                .get("/project/load", {
                    params: { pageNum: 1, pageSize: 3, uid: this.user.uid },
                })
                .then((res) => {
                    this.tableData = res.data.records;
                });
            request
                .get("/task/load", {
                    params: { pageNum: 1, pageSize: 3, uid: this.user.uid },
                })
                .then((res) => {
                    this.tableData = res.data.records;
                });
        },
    },
};
</script>

<template>
    <div>
        <!-- 导航栏， '首页','项目看板','数字素养','赛事天地' 区域-->
        <NavBar></NavBar>
        <hr />
        <!-- 项目看板区域 -->
        <div class="title">
            <p>热门项目</p>
            <el-button class="titleButton" @click="gotoProjlist"
                >查看更多</el-button
            >
        </div>
        <hr />
        <div class="hotProjects">
            <el-row>
                <el-col :span="8" v-for="item in cardArr" :key="item">
                    <el-card
                        shadow="hover"
                        class="projectCard"
                        @click.native="gotoproj(item)"
                    >
                        <img src="../assets/cardImg.png" class="cardImage" />
                        <p class="cardTitle" truncated>
                            基于大模型的就业智能查询基于大模型的就业智能查询
                        </p>
                        <p class="cardDetail">
                            这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项
                        </p>
                        <el-tag type="success" effect="dark" class="cardTag"
                            >招募队友中</el-tag
                        >
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <hr />
        <!-- 赛事天地区域 -->
        <div class="title">
            <p>近期赛事</p>
            <el-button class="titleButton" @click="gotoTasklist"
                >查看更多</el-button
            >
        </div>
        <div class="competitions">
            <div class="noticeInfo" v-for="item in noticeArr" :key="item">
                <el-tag type="success" effect="dark" class="noticeTag"
                    >报名中</el-tag
                >
                <p class="noticeTitle">关于全国大学生英语能力大赛报名的通知</p>
                <p class="noticeTime">2023-09-29</p>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>

<style scoped>
.title {
    width: 100%;
    height: 60px;
    background-color: rgba(151, 163, 234, 0.15);
    line-height: 60px;
    font-size: 24px;
    font-weight: 600;
    padding-left: 70px;
    padding-right: 30px;
    display: flex;
    justify-content: space-between;
}
.titleButton {
    width: 150px;
    height: 60px;
    background-color: #b70031;
    color: white;
    font-size: 24px;
    line-height: 40px;
    align-items: center;
    text-align: center;
    border: 0;
}
.titleButton:hover {
    background-color: #fff;
    color: #b70031;
    border: 0;
}

.hotProjects {
    width: 100%;
    height: 500px;
    background-color: white;
}

.projectCard {
    width: 80%;
    height: 400px;
    margin-top: 30px;
    margin-left: 40px;
    background-color: #fff;
}
.cardImage {
    width: 100%;
    height: 250px;
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
    height: 40px;
    margin-top: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.cardTag {
    border-radius: 20px;
    background-color: #008672 !important;
}
.competitions {
    width: 100%;
    height: 500px;
    background-color: white;
    padding: 20px 40px;
}
.noticeInfo {
    width: 100%;
    height: 60px;
    background-color: rgba(151, 163, 234, 0.15);
    margin-bottom: 40px;
    display: flex;
    line-height: 40px;
    align-items: center;
}
.noticeTag {
    border-radius: 20px;
    border: 0;
    height: 40px;
    background-color: purple !important;
    line-height: 40px;
    font-size: 20px;
    margin: 0 20px;
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
