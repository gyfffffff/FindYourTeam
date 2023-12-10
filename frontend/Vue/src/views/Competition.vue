<script>
import NavBar from '@/components/NavBar.vue';
import Footer from '@/components/Footer.vue';
export default {
    name: "Competition",
    components: {
        NavBar,
        Footer
    },
    data(){
        return{
            noticeArr: [1, 2, 3],
        }
    },
    created(){
    },
    methods:{
    }
    
}
</script>

<template>
<div>
    <NavBar/>
    <div class="competitions">
        <div class="noticeInfo" v-for="item in noticeArr" :key="item">
            <!-- 这里可以多加一些条件渲染 -->
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
.competitions {
    width: 100%;
    height: 700px;
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