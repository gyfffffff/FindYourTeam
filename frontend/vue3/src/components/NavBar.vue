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
      <!-- <img
        src="https://i.imgs.ovh/2023/12/15/6H0Pd.jpeg"
        height="90px"
        width="90px"
      /> -->
      <el-avatar :size="90" shape="square" > <span class="nameavatar">{{ name }}</span> </el-avatar>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  data() {
    return {
      name: '',
      // 导航栏的数据，标题和需要跳转到的路由
      navArr: [
        { title: '首页', router: 'home' },
        { title: '项目看板', router: 'projlist' },
        { title: '数字素养', router: 'digital' },
        { title: '赛事天地', router: 'competition' },
      ],
    }
  },
  created() {
    this.name = sessionStorage.getItem('name')
  },
  methods: {
    // 点击导航栏时候执行的操作
    navClick(val) {
      this.$router.push(val)
    },
    // 点击头像执行的操作
    avatarClick() {
      this.$router.push('mypage')
    },
  },
}
</script>

<style scoped>
.navBar {
  width: 100%;
  height: 130px;
  background-color: #fff;
  padding-top: 15px;
  padding-left: 70px;
  display: flex;
  overflow: hidden;
  border-bottom: 1px solid #888;
}
.navButtons {
  width: 90%;
  height: 90px;
  background-color: #b70031;
  display: flex;
  border-radius: 10px;
  overflow: hidden;
}
.avatar {
  width: 90px;
  height: 90px;
  border-radius: 10px;
  margin-left: 15px;
  overflow: hidden;
  cursor: pointer;
}
.divButton {
  width: 25%;
  height: 90px;
  color: white;
  font-size: 28px;
  line-height: 90px;
  align-items: center;
  text-align: center;
  border: 5px solid #b70031 !important;
  border-bottom: 5px solid #b70031 !important;
}
.nameavatar{
  font-size: 28px;
}
</style>
