<template>
  <div class="mainCompetition">
    <NavBar></NavBar>
    <div class="competitions">
      <div
        class="noticeInfo"
        v-for="item in compArr"
        :key="item"
        @click="gotoComp"
      >
        <!-- 这里可以多加一些条件渲染 -->
        <div class="noticeTagBox">
          <el-tag type="success" effect="dark" class="noticeTag">{{
            item.tag
          }}</el-tag>
        </div>
        <p class="noticeTitle">{{ item.title }}</p>
        <p class="noticeTime">{{ item.date }}</p>
      </div>
    </div>
    <div class="chatbot">
      <el-button type="success" @click="gotochatbot" round>竞赛信息小助手</el-button>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import http from '@/http'
export default {
  name: 'Competition',
  components: {
    NavBar,
    Footer,
  },
  data() {
    return {
      compArr: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      http
        .get('/compitition/load?pageNum=1&pageSize=9')
        .then((response) => {
          console.log(response.data.data.records)
          this.compArr = response.data.data.records
          console.log(this.compArr)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    gotoComp() {
      this.$router.push('/comdetail')
      document.documentElement.scrollTop = 0
    },
    gotochatbot() {
      this.$router.push('/chatbot')
    },
  },
}
</script>

<style scoped>
.mainCompetition {
  width: 100%;
}
.competitions {
  width: 100%;
  height: 250px;
  background-color: white;
  padding: 20px 40px;
}
.noticeTagBox {
  width: 160px;
  height: 40px;
}
.noticeInfo {
  width: 100%;
  height: 60px;
  background-color: rgba(151, 163, 234, 0.15);
  margin-bottom: 20px;
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
.chatbot {
  width: 100%;
  height: 100px;
  background-color: rgb(253, 255, 255);
  padding: 10px 560px;
}
</style>
