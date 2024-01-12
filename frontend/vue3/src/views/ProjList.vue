<template>
  <div>
    <NavBar></NavBar>
    <!-- 项目的主体区域 -->
    <div class="main">
      <!-- 搜索框，使用 form 实现，把搜索结果传回给后端 -->
      <div class="searchBox">
        <el-form :model="form" label-width="100px">
          <el-form-item label="筛选">
            <el-checkbox
              v-for="item in categories"
              :v-model="item.checked"
              :label="item.name"
              border
            ></el-checkbox>
          </el-form-item>
          <el-form-item label="关键字">
            <el-input
              class="formSearchText"
              placeholder="请输入搜索内容"
            ></el-input>
            <el-button class="formButton" type="primary">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <!-- 搜索结果，使用 el-col + el-card 实现 -->
      <el-row>
        <el-col :span="8" v-for="item in tableData" :key="item.pid">
          <el-card
            shadow="hover"
            class="projectCard"
            @click="gotoproj(item.pid)"
          >
            <img :src="item.mainpic" class="cardImage" />
            <p class="cardTitle" truncated>
              {{ item.title }}
            </p>
            <p class="cardDetail">
              {{ item.intro }}
            </p>
            <el-tag type="success" effect="dark" class="cardTag">{{
              item.tags
            }}</el-tag>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import { ref } from 'vue'
import http from '@/http'
export default {
  name: 'Digital',
  components: { NavBar },
  data() {
    return {
      tableData: [],
      user: '',
      categories: ref([
        { name: '国创', id: 1, checked: false },
        { name: '市创', id: 2, checked: false },
        { name: '校创', id: 3, checked: false },
        { name: '招募队友中', id: 4, checked: false },
        { name: '已结项', id: 5, checked: false },
        { name: '进行中', id: 6, checked: false },
        { name: '其他', id: 7, checked: false },
      ]),
      cardArr: [1, 2, 3, 4, 5, 6],
    }
  },
  created() {
    this.stuid = sessionStorage.getItem('stuid')
    this.load()
  },
  methods: {
    load() {
      http
        .get('/project/homeload', {
          params: { pageNum: 1, pageSize: 9 },
        })
        .then((res) => {
          this.tableData = res.data.data.records
          console.log('33', this.tableData)
        })
    },
    check() {
      if (!this.user) this.$router.push('/login')
    },
    navClick(val) {
      this.$router.push(val)
    },
    gotoproj(val) {
      this.$router.push({
        path: '/projdetail',
        query: {
          projectId: val,
        },
      })
    },
  },
}
</script>

<style scoped>
.title {
  width: 100%;
  height: 60px;
  background-color: #f5ddb0;
  line-height: 60px;
  font-size: 24px;
  font-weight: 600;
  padding-left: 70px;
}
.main {
  width: 100%;
  height: 100%;
  background-color: #fff;
  padding: 20px 40px;
}
.searchBox {
  width: 100%;
  background-color: #fff;
  border: 1px solid #dfdfdf;
  border-radius: 10px;
  padding: 20px;
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
  height: 360px;
  background-color: #fff;
  margin: 20px 10px 0;
  padding-bottom: 10px;
}
.cardImage {
  width: 100%;
  height: 200px;
  object-fit: contain;
}
.cardTitle {
  margin: 10px 0;
  font-size: 20px;
  font-weight: 600;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.cardDetail {
  height: 26px;
  margin-top: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.cardTag {
  height: 30px;
  margin-top: 20px;
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
