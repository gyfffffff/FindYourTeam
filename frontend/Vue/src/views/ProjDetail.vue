<template>
    <div id="app">
        <!-- 左侧 -->
        <div class="left-panel">
            <h2 style="text-align: center;margin-bottom:25px;">项目基本信息</h2>
            <div class="details">
                <!-- 后端传回数据 -->
                <div class="item">
                    <label class="subtitle">项目名称:</label>
                    <p style="margin-top: 10px; margin-bottom: 10px;">{{ project.name }}</p>
                </div>

                <div class="item">
                    <label class="subtitle">项目简介:</label>
                    <p style="margin-top: 10px; margin-bottom: 10px;">{{ project.description }}</p>
                </div>

                <div class="item">
                    <label class="subtitle">项目状态:</label>
                    <p style="margin-top: 10px; margin-bottom: 10px;">{{ project.status }}</p>
                </div>

                <div class="item">
                    <label class="subtitle">开始日期:</label>
                    <p style="margin-top: 10px; margin-bottom: 10px;">{{ project.startDate }}</p>
                </div>

                <div class="item">
                    <label class="subtitle">团队名称:</label>
                    <p style="margin-top: 10px; margin-bottom: 10px; font-size: 20px;">{{ project.manager }}</p>
                </div>
            </div>
        </div>

        <!-- 右侧 -->
        <div class="right-panel">
            <div class="container">
                <img src="../assets/cardImg.png" alt="Project Image" class="centered-image" />
            </div>

            <p style="margin-top: 10px; font-weight: bold; text-align: center; font-size: 30px;">项目title——项目title项目title项目title项目title项目title</p>
         
            <div class="avatars">
                <p style="margin-left: 60px;">团队成员</p>
                    <img src="../assets/avatar.png" alt="team member" class="avatar" />
                    <img src="../assets/avatar.png" alt="team member"  class="avatar"/>
                    <img src="../assets/avatar.png" alt="team member"  class="avatar"/>     
            </div>
            <el-tag type="success" effect="dark" class="cardTag">招募队友中</el-tag>
        </div>

        <div class="contents">
            <div class="section">
            <div class="title-bar">演示视频</div>
            <div class="content">
                <p>暂无演示视频</p>
            </div>
        </div>

        
        <div class="section">
            <div class="title-bar">资源下载</div>
            <div class="content">
            </div>
        </div>

        
        <div class="section">
            <div class="title-bar">队友招募</div>
            <div class="join">
                <p>要求：<br/> 
                有软件开发经验（小程序、app均可）<br/>                
                - 有团队合作意识，主动沟通<br/> 
                - 项目已经和指导老师沟通，得到了老师认可，很有前景！！<br/> 
                现有的团队成员非常友善，队长曾获浙江高中生创新大赛二等奖。</p>
            </div> 
        </div>
        <a class="link" @click="gotoProj">我要申请加入团队</a>
      </div>
    </div>
</template>

<script scoped>
export default {
    data() {
        return {
            project: {
                name: '项目名称',
                description: '项目简介项目简介项目简介项目简介项目简介项目简介',
                status:'进行中',
                startDate: '2023/12/12',
                manager: 'hh',
            },
        };
    },
    methods:{
        gotoProj(){
            this.$router.push({
                path: '/project',
                query: {
                    projectId: this.projectData.pid
                }
            })
        }
    }
};
</script>

<style>
#app {
    display: flex;
}

.left-panel {
    width: 40%;
    height: 720px;
    padding: 20px;
    margin-left:40px;
    float:left;
}

.details{
    padding-top: 10px;
    border-radius: 10px;
    background-color: #fff;
    height: 650px;
}

.right-panel {
    width: 60%;
    height: 720px;
    padding: 20px;
    
}

.container {
    margin-top:10px;
    display: flex;
    justify-content: center;
    height: 500px;
}

.centered-image {
  width: 760px; 
  height: 500px; 
  object-fit: cover; 
}

.item {
    margin-left: 30px;
    margin-right: 30px;
    margin-top:10px;
    margin-bottom: 20px;
    
}

.subtitle{
    font-size: 20px;
    font-weight: bold; 
    color: #b70031;
}

img {
    max-width: 100%;
    height: auto;
}

.avatars {
    font-size: 30px ;
    margin-top: 20px;
    display: flex;
}

.avatar {
    
    margin-left:20px;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
}

.cardTag {
    margin-top: 20px;
    margin-left:60px;
    border-radius: 40px;
    background-color: #008672 !important;
}


.contents{
    margin-top: 50px;
    clear: both;
}

.section {
  margin: 20px;
  border: 1px solid #ccc;
}


.title-bar {
  background-color: #666;
  color: #fff;
  padding: 10px;
  font-size:20px;
  text-align: left;
}


.content {
  padding: 10px;
}

.join{
    padding-top: 10px;
    border-radius: 10px;
    background-color: #fff;
    height: auto;
}

.link{
   display: flex;
   color: #b70031; 
   justify-content: center;
}

</style>