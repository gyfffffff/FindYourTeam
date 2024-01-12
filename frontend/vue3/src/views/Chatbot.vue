<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="60px"></el-aside>
      <el-container>
        <el-header>
          <div class="title">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 14 14" fill="#ffffff">
              <path
                d="M1.735 11.8755L3.8445 11.1725L4.2195 11.3445C5.07383 11.7822 6.001 12.001 7.001 12.001C8.699 11.949 10.113 11.3943 11.243 10.337C12.373 9.27967 12.959 8.001 13.001 6.501C12.9593 5.001 12.3733 3.72233 11.243 2.665C10.1127 1.60767 8.69867 1.053 7.001 1.001C5.303 1.053 3.889 1.60767 2.759 2.665C1.629 3.72233 1.043 5.001 1.001 6.501C1.001 7.04267 1.09483 7.57917 1.2825 8.1105C1.47017 8.64183 1.74617 9.14183 2.1105 9.6105L2.4545 10.0635L1.735 11.8755ZM1.016 13.157C0.818 13.2197 0.648667 13.1753 0.508 13.024C0.367333 12.8727 0.3335 12.6982 0.4065 12.5005L1.313 10.219C0.896333 9.667 0.573333 9.07583 0.344 8.4455C0.114667 7.81517 0 7.16667 0 6.5C0.0416667 4.78133 0.708333 3.284 2 2.008C3.29167 0.732 4.95833 0.0626667 7 0C9.04167 0.0626667 10.7083 0.732 12 2.008C13.2917 3.284 13.9583 4.78133 14 6.5C13.9583 8.21867 13.2917 9.716 12 10.992C10.7083 12.268 9.04167 12.9373 7 13C5.86467 13 4.7865 12.75 3.7655 12.25L1.016 13.157ZM7.0005 7.2975C6.8545 7.2975 6.72167 7.26367 6.602 7.196C6.48233 7.12833 6.386 7.02933 6.313 6.899C6.24 6.76867 6.2035 6.63583 6.2035 6.5005C6.2035 6.36517 6.24 6.23233 6.313 6.102C6.386 5.97167 6.48233 5.87267 6.602 5.805C6.72167 5.73733 6.8545 5.7035 7.0005 5.7035C7.22983 5.7035 7.42 5.779 7.571 5.93C7.722 6.081 7.7975 6.27117 7.7975 6.5005C7.7975 6.72983 7.722 6.92 7.571 7.071C7.42 7.222 7.22983 7.2975 7.0005 7.2975ZM10.0005 7.2975C9.8545 7.2975 9.72167 7.26367 9.602 7.196C9.48233 7.12833 9.386 7.02933 9.313 6.899C9.24 6.76867 9.2035 6.63583 9.2035 6.5005C9.2035 6.36517 9.24 6.23233 9.313 6.102C9.386 5.97167 9.48233 5.87267 9.602 5.805C9.72167 5.73733 9.8545 5.7035 10.0005 5.7035C10.2298 5.7035 10.42 5.779 10.571 5.93C10.722 6.081 10.7975 6.27117 10.7975 6.5005C10.7975 6.72983 10.722 6.92 10.571 7.071C10.42 7.222 10.2298 7.2975 10.0005 7.2975ZM4.0005 7.2975C3.8545 7.2975 3.72167 7.26367 3.602 7.196C3.48233 7.12833 3.386 7.02933 3.313 6.899C3.24 6.76867 3.2035 6.63583 3.2035 6.5005C3.2035 6.36517 3.24 6.23233 3.313 6.102C3.386 5.97167 3.48233 5.87267 3.602 5.805C3.72167 5.73733 3.8545 5.7035 4.0005 5.7035C4.22983 5.7035 4.42 5.779 4.571 5.93C4.722 6.081 4.7975 6.27117 4.7975 6.5005C4.7975 6.72983 4.722 6.92 4.571 7.071C4.42 7.222 4.22983 7.2975 4.0005 7.2975Z"
                fill="#000" fill-opacity="0.96" />
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
                  :timestamp="activity.timestamp" placement="top">
                  <el-card v-if="activity.userType == 2">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                      <path
                        d="M4.5 5C4.521 5.98967 4.86217 6.81517 5.5235 7.4765C6.18483 8.13783 7.01033 8.479 8 8.5C8.98967 8.479 9.81517 8.13783 10.4765 7.4765C11.1378 6.81517 11.479 5.98967 11.5 5C11.479 4.01033 11.1378 3.18483 10.4765 2.5235C9.81517 1.86217 8.98967 1.521 8 1.5C7.01033 1.521 6.18483 1.86217 5.5235 2.5235C4.86217 3.18483 4.521 4.01033 4.5 5ZM13 14.5H2.5C2.354 14.5 2.23417 14.4532 2.1405 14.3595C2.04683 14.2658 2 14.146 2 14V12.5C2.021 11.7917 2.26583 11.2032 2.7345 10.7345C3.20317 10.2658 3.79167 10.021 4.5 10H11.5C12.2083 10.021 12.7968 10.2658 13.2655 10.7345C13.7342 11.2032 13.979 11.7917 14 12.5V14C14 14.146 13.9532 14.2658 13.8595 14.3595C13.7658 14.4532 13.646 14.5 13.5 14.5H13Z"
                        fill="#A11" fill-opacity="0.96" />
                    </svg>
                    <span v-html="activity.content" style="padding-left: 5px; font-size: 16px;"></span>
                  </el-card>
                  <el-card v-if="activity.userType == 1">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                      <path
                        d="M6.00007 15V14H9.00007V15H6.00007ZM13.0001 6.50001C13.0001 7.33335 12.8256 8.11718 12.4766 8.85151C12.1276 9.58585 11.6354 10.2135 11.0001 10.7345C10.6981 10.9845 10.4559 11.2788 10.2736 11.6175C10.0912 11.9562 10.0001 12.3233 10.0001 12.719V13.0005H5.00007V12.7815C5.00007 12.3962 4.92457 12.0315 4.77357 11.6875C4.62257 11.3435 4.40124 11.057 4.10957 10.828C3.28657 10.1717 2.69807 9.36701 2.34407 8.41401C1.99007 7.46101 1.90674 6.46885 2.09407 5.43751C2.31274 4.35418 2.79974 3.42451 3.55507 2.64851C4.3104 1.87251 5.22974 1.36468 6.31307 1.12501C7.13607 0.948014 7.94857 0.95318 8.75057 1.14051C9.55257 1.32785 10.2817 1.68718 10.9381 2.21851C11.5944 2.74985 12.1022 3.38535 12.4616 4.12501C12.8209 4.86468 13.0006 5.65635 13.0006 6.50001H13.0001ZM4.50007 7.00001C4.5104 5.94801 4.7839 5.10685 5.32057 4.47651C5.85724 3.84618 6.58374 3.52068 7.50007 3.50001V2.50001C6.2814 2.53135 5.31524 2.96101 4.60157 3.78901C3.8879 4.61701 3.52074 5.68735 3.50007 7.00001H4.50007Z"
                        fill="#39e" fill-opacity="0.96" />
                    </svg>
                    <span v-html="activity.content" style="padding-left: 5px; font-size: 16px;"></span>
                  </el-card>
                  <!-- <span v-html="activity.content"></span> -->
                </el-timeline-item>
              </el-timeline>
            </div>
            <div class="wrapper">
              <el-card shadow="hover" class="inputarea">
                <el-input v-model="query" placeholder="Please input" size="large">
                  <template #append>
                    <el-button @click="sendChat" class="sendbtn">
                      Send</el-button>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                      <path d="M1 7L14 2L12 13L5.031 9.203L13 3L3.797 8.5155L1 7ZM5 15V10.2655L8 12L5 15Z" fill="#08080A"
                        fill-opacity="0.96" />
                    </svg>
                  </template>
                </el-input>
              </el-card>
            </div>

          </div>
        </el-main>
      </el-container>
      <el-aside width="60px"></el-aside>
    </el-container>

    <Footer></Footer>
  </div>
</template>

<script>
import axios from 'axios'
import Footer from '@/components/Footer.vue'
import { ElMessage } from 'element-plus'
import { ref, shallowReactive, shallowRef } from "vue";
import { MoreFilled, CircleCheck } from "@element-plus/icons-vue";
export default {
  name: 'Chatbot',
  components: {
    Footer
  },
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
      await this.main()
      setTimeout(() => {
        let chatWindow = document.getElementById("chatWindow");
        chatWindow.scrollTo(0, chatWindow.scrollHeight);
      }, 0);
    },



    async main() {
      // this.result = 'result test'
      this.access_token = await this.getAccessToken()
      console.log(2020, this.access_token)
      var options = {
        'method': 'POST',
        'url': '/rpc/2.0/ai_custom/v1/wenxinworkshop/plugin/fupvnjquq2qj28ba/?access_token=' + this.access_token,
        'headers': {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          "query": this.query,
          "plugins": [
            "uuid-zhishiku"
          ],
          "verbose": false
        })

      };
      axios.post(options.url, options.body, options.headers).then(res => {
        console.log(160, options.body)
        this.result = res.data.result
        console.log(163, this.result)
        this.activities[this.activities.length - 1] = {
          content: this.result,
          timestamp: new Date(),
          size: "large",
          userType: '1',
          type: "primary",
          icon: CircleCheck,
        };
        this.result = "Robot: thinking..."
        console.log(170, this.activities)
      }).catch(err => {
        console.log(err)
      })
      this.query = ""
      
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
.common-layout {
  background-color: #fff;
}

.title {
  width: 100%;
  text-align: center;
  border: 1px solid #eee;
  border-color: rgb(217, 217, 217);
  font-size: 26px;
  margin: 0 auto;
}

.wrapper {
  border-radius: 16px 16px 16px 16px;
  background: linear-gradient(to right, rgba(255, 0, 0, 0.8), rgba(255, 255, 0, 0.5), rgba(0, 128, 0, 0.5), rgba(0, 0, 255, 0.5), rgba(75, 0, 130, 0.5), rgba(238, 130, 238, 0.5));
  padding: 3px;

}

.inputarea {
  border-radius: 16px;

}

#chatWindow {
  padding-left: 10px;
  overflow-y: scroll;
  height: calc(110vh - 260px);
  scrollbar-width: none;
  /* Firefox */
  -ms-overflow-style: none;
  /* IE 10+ */
}

#chatWindow::-webkit-scrollbar {
  /* WebKit */
  width: 0;
  height: 0;
}

.sendbtn {
  padding-right: 23px;
}
</style>
