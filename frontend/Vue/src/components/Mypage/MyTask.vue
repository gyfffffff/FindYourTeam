<template>
  <div>
      <el-button type="primary" class="newTeamButton">新建项目</el-button>
      <table>
          <thead>
              <tr>
                  <th>任务名称</th>
                  <th>来自项目</th>
                  <th>截止时间</th>
                  <th>详细描述</th>
                  <th>标记完成</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="item in tasks" :key="item.name">
                  <td>{{item.name}}</td>
                  <td>{{item.fromProj}}</td>
                  <td>{{item.deadline}}</td>
                  <td>{{item.description}}</td>
                  <td>
                      <div v-if="item.finished === true" class="finishTag">完成</div>
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
export default {
  name: "MyTeam",
  data() {
      return {
          tasks: [{
            name: "接入 API 并测试",
            fromProj: "基于大模型的智能问答",
            deadline: "2021-06-30",
            description: "这里是详细描述",
            finished: true,
          },{
            name: "接入 API 并测试",
            fromProj: "基于大模型的智能问答",
            deadline: "2021-06-30",
            description: "这里是详细描述",
            finished: false,
          }]
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
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  text-align: center;
  vertical-align: middle;
  padding: 10px;
}

th {
  background-color: #e4ecf3;
  color: black;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}
.newTeamButton {
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
.finishTag {
  width: 60px;
  height: 30px;
  background-color: #b70031;
  color: white;
  line-height: 30px;
  font-size: 12px;
  border-radius: 5px;
  border: 0;
}
</style>
