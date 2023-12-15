<script>
export default {
    name: "NavBar",
    data() {
        return {
            // 导航栏的数据，标题和需要跳转到的路由
            navArr: [
                { title: "首页", router: "home" },
                { title: "项目看板", router: "projlist" },
                { title: "数字素养", router: "digital" },
                { title: "赛事天地", router: "competition" },
            ],
        };
    },
    methods: {
        // 点击导航栏时候执行的操作
        navClick(val) {
            this.$router.push(val);
        },
        // 点击头像执行的操作
        avatarClick() {
            this.$router.push("mypage");
        },
    },
};
</script>

<template>
    <!-- 导航栏， '首页','项目看板','数字素养','赛事天地' 区域-->
    <div class="navBar">
        <div class="navButtons">
            <el-button
                type="primary"
                class="divButton el-button--primary"
                v-for="item in navArr"
                :key="item"
                color="#b70031"
                :dark="isDark"
                @click="navClick(item.router)"
            >
                {{ item.title }}
            </el-button>
        </div>
        <div class="avatar" ref="avatar" v-on:click="avatarClick()">
            <img src="../assets/avatar.png" height="90px" width="90px" />
        </div>
    </div>
</template>

<style scoped>
.navBar {
    width: 100%;
    height: 130px;
    background-color: rgba(151, 163, 234, 0.15);
    padding-top: 15px;
    padding-left: 70px;
    display: flex;
    border-radius: 5px;
}
.navButtons {
    width: 90%;
    height: 90px;
    background-color: #b70031;
    display: flex;
    border-radius: 5px;

}
.avatar {
    width: 90px;
    height: 90px;
    border-radius: 5px;
    margin-left: 30px;
}
.divButton {
    width: 25%;
    color: white;
    font-size: 28px;
    line-height: 90px;
    align-items: center;
    text-align: center;
}
</style>
