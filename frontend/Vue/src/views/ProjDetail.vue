<template>
    <div id="app">
        <div class="top-panel">
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

                <p style="margin-top: 10px; font-weight: bold; text-align: center; font-size: 30px;">
                    项目title——项目title项目title项目title项目title项目title</p>

                <div class="avatars">
                    <p style="margin-left: 60px;">团队成员</p>
                    <img src="../assets/avatar.png" alt="team member" class="avatar" />
                    <img src="../assets/avatar.png" alt="team member" class="avatar" />
                    <img src="../assets/avatar.png" alt="team member" class="avatar" />
                </div>
                <el-tag type="success" effect="dark" class="cardTag">招募队友中</el-tag>
            </div>

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
                    <p>要求：<br />
                        有软件开发经验（小程序、app均可）<br />
                        - 有团队合作意识，主动沟通<br />
                        - 项目已经和指导老师沟通，得到了老师认可，很有前景！！<br />
                        现有的团队成员非常友善，队长曾获浙江高中生创新大赛二等奖。</p>
                </div>
            </div>
            <a class="link" @click="apply">我要申请加入团队</a>
            <el-dialog title="申请问卷" :visible.sync="dialogVisible" width="45%">
                <el-form ref="form" :model="form" status-icon label-width="120px">
                    <!-- <el-form-item label="符合程度" prop="match_level" placeholder="您的符合要求程度，范围1-5， ">
                        <el-input v-model="form.match_level" style="width: 80%"></el-input>
                    </el-form-item> -->
                    <span class="demonstration">我的条件符合程度</span>
                    <el-slider v-model="form.match_level" :format-tooltip="formatTooltip"></el-slider>
                    <span class="demonstration">我的加入团队意愿值</span>
                    <el-slider v-model="form.desire_level" :format-tooltip="formatTooltip"></el-slider>
                    <el-form-item label="我的邮箱" prop="email">
                        <el-input v-model="form.email" style="width: 80%" placeholder="团队负责人可以通过邮箱联系您"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save('form')">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script scoped>
import http from "@/http";
import axios from "axios";
export default {
    data() {
        return {
            project: {
                pid: '',
                name: '项目名称',
                description: '项目简介项目简介项目简介项目简介项目简介项目简介',
                status: '进行中',
                startDate: '2023/12/12',
                manager: 'hh',
            },
            xuehao: '',
            dialogVisible: false,
            form: {
                uid: '',
                pid: '',
                status: -1,
                match_level: '',
                desire_level: '',
                email: '',
            },
            rules: {
                groupName: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                    { max: 10, message: '不能超过10位', trigger: 'blur' }
                ]
            }
        };
    },
    created() {
        this.xuehao = sessionStorage.getItem("xuehao");
        this.load();
    },
    methods: {
        apply() {
            this.dialogVisible = true;
        },
        formatTooltip(val) {
            return val / 10;
        },
        save(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.form.uid = this.xuehao
                    this.form.pid = 1
                
                    var data = JSON.stringify({
                        "uid": this.form.uid,
                        "pid": this.form.pid,
                        "desire_level": this.form.desire_level,
                        "match_level": this.form.match_level,
                        "status": this.form.status,
                        "email": this.form.email
                    });

                    var config = {
                        method: 'post',
                        url: 'http://localhost:9090/project/apply',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        data: data
                    };

                    axios(config)
                        .then(response => {
                            console.log(JSON.stringify(response.data));
                            if (response.data.code == 0) {
                                alert("申请已发送");
                                this.dialogVisible = false;
                            } else if (response.data.code != 0) {
                                alert(response.data.msg);
                            } 
                        })
                        
                } else {
                    alert("请检查！！");
                    return false;
                }
            })
        }
    }
};
</script>

<style>
#app {
    display: flex;
    flex-direction: column;
}

.top-panel {
    display: flex;
}

.left-panel {
    width: 470px;
    height: 720px;
    padding: 20px;
    margin-left: 40px;
    float: left;
}

.details {
    padding-top: 10px;
    border-radius: 10px;
    background-color: #fff;
    height: 650px;
}

.right-panel {
    width: 650px;
    height: 720px;
    padding: 20px;

}

.container {
    margin-top: 10px;
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
    margin-top: 10px;
    margin-bottom: 20px;

}

.subtitle {
    font-size: 20px;
    font-weight: bold;
    color: #b70031;
}

img {
    max-width: 100%;
    height: auto;
}

.avatars {
    font-size: 30px;
    margin-top: 20px;
    display: flex;
}

.avatar {

    margin-left: 20px;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
}

.cardTag {
    margin-top: 20px;
    margin-left: 60px;
    border-radius: 40px;
    background-color: #008672 !important;
}


.contents {
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
    font-size: 20px;
    text-align: left;
}


.content {
    padding: 10px;
}

.join {
    padding-top: 10px;
    border-radius: 10px;
    background-color: #fff;
    height: auto;
}

.link {
    display: flex;
    color: #b70031;
    justify-content: center;
    text-decoration: underline;
    cursor: pointer;
}
</style>