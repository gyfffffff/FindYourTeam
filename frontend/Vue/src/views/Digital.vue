<script>
import NavBar from "@/components/NavBar.vue";
import { ref } from "vue";
export default {
    name: "Digital",
    components: { NavBar },
    data() {
        return {
            user: "",
            categories: ref([
                { name: "SQL 入门", id: 1, checked: false },
                { name: "Python 入门", id: 2, checked: false },
                { name: "Python 数据分析", id: 3, checked: false },
                { name: "计算机网络", id: 4, checked: false },
                { name: "计算机安全", id: 5, checked: false },
                { name: "计算机应用", id: 6, checked: false },
                { name: "其他", id: 7, checked: false },
            ]),
            cardArr: [1, 2, 3, 4, 5, 6]
        };
    },
    created() {
        this.user = JSON.parse(sessionStorage.getItem("user"));
        // this.check();
    },
    methods: {
        check() {
            if (!this.user) this.$router.push("/login");
        },
        navClick(val) {
            this.$router.push(val);
        }
    },
};
</script>

<template>
    <div>
        <NavBar></NavBar>
        <hr />
        <div class="title">数字素养</div>
        <!-- 数字素养的主体区域 -->
        <div class="main">
            <!-- 搜索框，使用 form 实现，把搜索结果传回给后端 -->
            <div class="searchBox">
                <el-form :model="form" label-width="100px">
                    <el-form-item label="类别">
                        <el-checkbox v-for="item in categories" :v-model="item.checked" :label="item.name" border></el-checkbox>
                    </el-form-item>
                    <el-form-item label="关键字">
                        <el-input class='formSearchText' placeholder="请输入搜索内容"></el-input>
                        <el-button class="formButton" type="primary">查询</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <!-- 搜索结果，使用 el-col + el-card 实现 -->
            <el-row>
                <el-col :span="6" v-for="item in cardArr" :key="item">
                    <el-card
                        shadow="hover"
                        class="projectCard"
                        @click.native="gotoproj(item)"
                    >
                        <img src="../assets/cardImg.png" class="cardImage" />
                        <p class="cardTitle" truncated>
                            Python 入门111111111111
                        </p>
                        <p class="cardDetail">
                            这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项目描述这是项
                        </p>
                        <div class="cardBottom">
                            <p class="cardTime">2023-11-11  12:00</p>
                            <el-tag type="success" effect="dark" class="cardTag"
                                >HOT</el-tag
                            >
                        </div>
                        
                    </el-card>
                </el-col>
            </el-row>
        </div>
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
}
.main {
    width: 100%;
    height: 100%;
    background-color: #cccccc;
    padding: 20px 40px;
}
.searchBox {
    width: 100%;
    background-color: #fff;
    padding: 20px
}
.formSearchText {
    width: 500px;
}
.formButton {
    width: 100px;
    height: 40px;
    background-color: #b70031;
    margin-left: 200px !important;
    color: white;
    font-size: 16px !important;
    line-height: 40px !important;
    padding: 0;
    align-items: center;
    text-align: center;
    border: 0;
}
.projectCard {
    width: 90%;
    height: 400px;
    margin-top: 30px;
    margin-left: 20px;
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
    height: 30px;
    background-color: red !important;
    margin-left: 30px;
    line-height: 30px;
    font-size: 12px;
    border-radius: 20px;
    border: 0;
}
.cardBottom {
    height: 30px;
    display: flex;
}
.cardTime {
    height: 30px;
    background-color: #cccccc;
    border-radius: 5px;
    line-height: 30px;
    font-size: 12px;
    padding: 0 15px;
}

</style>
