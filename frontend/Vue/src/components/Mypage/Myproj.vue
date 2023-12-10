<template>
    <div>
        <el-row gutter="20">
            <el-col :span="12" v-for="item in cardArr" :key="item.title">
                <el-card shadow="hover">
                    <img
                        src="../../assets/cardImg.png"
                        alt="cardImg"
                        class="cardImage"
                    />
                    <div class="cardTitle">
                        {{ item.title }}
                    </div>
                    <el-tag type="success" effect="dark" class="cardTag">{{ item.tag }}</el-tag>
                </el-card>
            </el-col>
        </el-row>
        <el-button type="primary" class="newProjButton">新建项目</el-button>

        
    </div>
</template>

<script>
export default {
    name: "Myproj",
    data() {
        return {
            // 后面可能需要多定义一些数据，无论是数据的数量还是数据的格式
            cardArr: [
                { title: "基于大模型的项目1", tag: "国创" },
                { title: "基于大模型的项目2", tag: "国创" },
            ],
        };
    },
    methods: {
        // Your methods here
    },
    mounted() {
        // Your mounted code here
    },
};
</script>

<style scoped>
.main {
}
.cardTitle {
    margin: 5px 0;
    font-size: 20px;
    font-weight: 600;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.cardImage {
    width: 100%;
    height: 250px;
}
.newProjButton {
    position: absolute;
    width: 150px;
    height: 40px;
    background-color: #888 !important;
    line-height: 40px !important;
    font-size: 20px;
    font-weight: 600;
    top: 130px;
    right: 20px;
    padding: 0 10px;
}
.cardView {
    height: 100%;
    width: 100%;
}
.cardTag {
    height: 20px;
    background-color: purple !important;
    line-height: 20px;
    margin-top: 10px;
    font-size: 12px;
    border-radius: 20px;
    border: 0;
}
</style>
