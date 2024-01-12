<template>
  <div class="common-layout">
    <el-container>
      <el-header height="60px">
        <div class="title">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 14 14" fill="#ffffff">
            <path
              d="M1.735 11.8755L3.8445 11.1725L4.2195 11.3445C5.07383 11.7822 6.001 12.001 7.001 12.001C8.699 11.949 10.113 11.3943 11.243 10.337C12.373 9.27967 12.959 8.001 13.001 6.501C12.9593 5.001 12.3733 3.72233 11.243 2.665C10.1127 1.60767 8.69867 1.053 7.001 1.001C5.303 1.053 3.889 1.60767 2.759 2.665C1.629 3.72233 1.043 5.001 1.001 6.501C1.001 7.04267 1.09483 7.57917 1.2825 8.1105C1.47017 8.64183 1.74617 9.14183 2.1105 9.6105L2.4545 10.0635L1.735 11.8755ZM1.016 13.157C0.818 13.2197 0.648667 13.1753 0.508 13.024C0.367333 12.8727 0.3335 12.6982 0.4065 12.5005L1.313 10.219C0.896333 9.667 0.573333 9.07583 0.344 8.4455C0.114667 7.81517 0 7.16667 0 6.5C0.0416667 4.78133 0.708333 3.284 2 2.008C3.29167 0.732 4.95833 0.0626667 7 0C9.04167 0.0626667 10.7083 0.732 12 2.008C13.2917 3.284 13.9583 4.78133 14 6.5C13.9583 8.21867 13.2917 9.716 12 10.992C10.7083 12.268 9.04167 12.9373 7 13C5.86467 13 4.7865 12.75 3.7655 12.25L1.016 13.157ZM7.0005 7.2975C6.8545 7.2975 6.72167 7.26367 6.602 7.196C6.48233 7.12833 6.386 7.02933 6.313 6.899C6.24 6.76867 6.2035 6.63583 6.2035 6.5005C6.2035 6.36517 6.24 6.23233 6.313 6.102C6.386 5.97167 6.48233 5.87267 6.602 5.805C6.72167 5.73733 6.8545 5.7035 7.0005 5.7035C7.22983 5.7035 7.42 5.779 7.571 5.93C7.722 6.081 7.7975 6.27117 7.7975 6.5005C7.7975 6.72983 7.722 6.92 7.571 7.071C7.42 7.222 7.22983 7.2975 7.0005 7.2975ZM10.0005 7.2975C9.8545 7.2975 9.72167 7.26367 9.602 7.196C9.48233 7.12833 9.386 7.02933 9.313 6.899C9.24 6.76867 9.2035 6.63583 9.2035 6.5005C9.2035 6.36517 9.24 6.23233 9.313 6.102C9.386 5.97167 9.48233 5.87267 9.602 5.805C9.72167 5.73733 9.8545 5.7035 10.0005 5.7035C10.2298 5.7035 10.42 5.779 10.571 5.93C10.722 6.081 10.7975 6.27117 10.7975 6.5005C10.7975 6.72983 10.722 6.92 10.571 7.071C10.42 7.222 10.2298 7.2975 10.0005 7.2975ZM4.0005 7.2975C3.8545 7.2975 3.72167 7.26367 3.602 7.196C3.48233 7.12833 3.386 7.02933 3.313 6.899C3.24 6.76867 3.2035 6.63583 3.2035 6.5005C3.2035 6.36517 3.24 6.23233 3.313 6.102C3.386 5.97167 3.48233 5.87267 3.602 5.805C3.72167 5.73733 3.8545 5.7035 4.0005 5.7035C4.22983 5.7035 4.42 5.779 4.571 5.93C4.722 6.081 4.7975 6.27117 4.7975 6.5005C4.7975 6.72983 4.722 6.92 4.571 7.071C4.42 7.222 4.22983 7.2975 4.0005 7.2975Z"
              fill="#fff" fill-opacity="0.96" />
          </svg>
          CompGPT在线体验
        </div>
      </el-header>
      <el-main>
        <div class="mainContent" style="height: 80vh; overflow: hidden; ">
          <div id="chatWindow">
            <el-timeline style="font-size:large">
              <el-timeline-item v-for="(activity, index) in activities" :key="index" :icon="activity.icon"
                :type="activity.type" :color="activity.color" :size="activity.size" :hollow="activity.hollow"
                :timestamp="activity.timestamp">
                <el-icon v-if="activity.userType == 2">
                  <User />
                </el-icon>
                <el-icon v-if="activity.userType == 1">
                  <Bell />
                </el-icon>
                <span v-html="activity.content"></span>
              </el-timeline-item>
            </el-timeline>
          </div>
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>输入对话</span>
              </div>
            </template>
            <el-input v-model="query" :rows="4" type="textarea" placeholder="Please input" />
            <el-button type="primary" :disabled="sendDisabled" @click="sendChat"
              style="width: 100%; height: 50px; margin-top: 10px">Send</el-button>
          </el-card>
        </div>
      </el-main>
      <el-footer height="5vh"><el-icon>
          <Sunny />
        </el-icon>体验版(网络问题 回复时间会长，耐心等待) power by zhoufc</el-footer>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { ref, shallowReactive, shallowRef } from "vue";
import { MoreFilled, CircleCheck } from "@element-plus/icons-vue";
export default {
  name: 'Chatbot',
  data() {
    return {
      AK: "MRPKgdCEzyvfNtqQWqQUPjgc",
      SK: "denRkDRoGLSDeQMBOBthtbharjqGOCj8",
      result: "Robot: thinking...",
      query: '',
      activities: shallowReactive([
        {
          content: "Robot: 你可以开始跟我对话了",
          timestamp: new Date(),
          size: "large",
          userType: '1',
          type: "primary",
          icon: CircleCheck,
        },
      ])
    }
  },
  methods: {
    sleep(ms) {
      return new Promise(resolve => setTimeout(resolve, ms));
    },
    async sendChat() {
      if (this.query == '') {
        ElMessage({
          message: '请输入内容.',
          type: 'warning',
        })
        return
      }
      ElMessage({
        message: '已发送请等待.',
        type: 'success',
      })
      this.activities.push({
        content: 'You:' + this.query,
        timestamp: new Date(),
        userType: '2',
        size: "large",
        color: "#0bbd87",
        icon: CircleCheck,
      }, {
        content: this.result,
        timestamp: new Date(),
        userType: '1',
        size: "large",
        type: "primary",
        icon: MoreFilled
      });

      // await this.sleep(3000)
      // this.result = 'result test'
      await this.main().then(() => {
        console.log(110, this.result)
      })
      setTimeout(() => {
        let chatWindow = document.getElementById("chatWindow");
        chatWindow.scrollTo(0, chatWindow.scrollHeight);
      }, 0);
    },



    async main() {
      this.result = 'result test'
      // this.access_token = await this.getAccessToken()
      // console.log(2020, this.access_token)
      // var options = {
      //   'method': 'POST',
      //   'url': '/rpc/2.0/ai_custom/v1/wenxinworkshop/plugin/fupvnjquq2qj28ba/?access_token=' + this.access_token,
      //   'headers': {
      //     'Content-Type': 'application/json'
      //   },
      //   body: JSON.stringify({
      //     "query": this.query,
      //     "plugins": [
      //       "uuid-zhishiku"
      //     ],
      //     "verbose": false
      //   })

      // };
      // axios.post(options.url, options.body, options.headers).then(res => {
      //   console.log(3737, options.body)
      //   console.log(3737, res.data.result)
      //   this.result = res.data.result
      //   this.activities[this.activities.length - 1] = {
      //     content: this.result,
      //     timestamp: new Date(),
      //     size: "large",
      //     type: "primary",
      //     icon: CircleCheck,
      //   };
      // }).catch(err => {
      //   console.log(err)
      // })
      this.query = ""
      this.result = "Robot: thinking..."
    },

    /**
     * 使用 AK，SK 生成鉴权签名（Access Token）
     * @return string 鉴权签名信息（Access Token）
     */
    async getAccessToken() {
      this.url = '/oauth/2.0/token?grant_type=client_credentials&client_id=' + this.AK + '&client_secret=' + this.SK
      try {
        const response = await axios.post(this.url);
        console.log(5454, response.data.access_token);
        return response.data.access_token;
      } catch (err) {
        console.log(err);
      }
    }
  }
}

</script>

<style scoped>
.title {
  width: 100%;
  text-align: center;
  background-color: #409eff;
  color: #fff;
  font-size: 2rem;
  margin: 0 auto;
}

#chatWindow {
  padding-left: 10px;
  overflow-y: scroll;
  height: calc(80vh - 260px);
}
</style>
