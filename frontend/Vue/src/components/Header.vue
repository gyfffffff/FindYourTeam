<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid; display: flex; background-color: floralwhite">
    <!--   logo-->
    
    <div style="margin-left:0px; font-weight: bold; font-size: 35px; color: limegreen">FindYourTeam</div>
    <div style="flex: 1"></div>
    <!--    账号-->
    <div style="padding-right: 25px">
      <el-dropdown>
        <span class="el-dropdown-link, point">
          <i class="el-icon-user-solid"></i>
          {{this.user.name}}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><a @click="gohome">首页</a></el-dropdown-item>
          <el-dropdown-item><a @click="logout">退出登录</a></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      user: ''
    }
  },
  methods: {
    logout(){
      sessionStorage.removeItem("user");
      this.$router.push('/login')
    },
    gohome(){
      this.$router.push('/')
    }
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"))
  }
}
</script>

<style scoped>
    .point:hover{
      cursor: pointer;
    }
</style>