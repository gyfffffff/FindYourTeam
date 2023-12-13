<template>
    <div class="main">
        <!-- <el-button type="text" @click="dialogFormVisible = true">新增项目</el-button> -->
        <el-button type="primary" @click="dialogFormVisible = true" class="newProjBtn">新增项目</el-button>
        <el-dialog title="新增项目" :visible.sync="dialogFormVisible">
            <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
                <el-form-item label="项目名称" prop="title" :label-width="formLabelWidth">
                    <el-input v-model="form.title" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目简介" prop="intro" :label-width="formLabelWidth">
                    <el-input v-model="form.intro" type="textarea" placeholder="输入项目简介，让别人对你的项目一目了然"></el-input>
                </el-form-item>
                <el-form-item label="团队ID" prop="gid">
                    <el-input v-model="form.gid" style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item label="创建时间" prop="startdate">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.startdate"
                        style="width: 80%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="截止时间" prop="ddl">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.ddl" style="width: 80%;"></el-date-picker>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')">确 定</el-button>
            </span>
        </el-dialog>
        <el-row gutter=20>
            <el-col :span="12" v-for="item in cardArr" :key="item.title">
                <el-card shadow="hover">
                    <img src="../../assets/cardImg.png" alt="cardImg" class="cardImage" />
                    <div class="cardTitle">
                        {{ item.title }}
                    </div>
                    <el-tag type="success" effect="dark" class="cardTag">{{ item.tag }}</el-tag>
                </el-card>
            </el-col>
        </el-row>
        <!-- <el-button type="primary" class="newProjButton">新建项目</el-button> -->
    </div>
</template>

<script>
import http from "@/http";  // 引入新创建的http实例
export default {
    name: "Myproj",
    data() {
        return {
            dialogTableVisible: false,
            dialogFormVisible: false,
            formLabelWidth: '120px',
            // 后面可能需要多定义一些数据，无论是数据的数量还是数据的格式
            cardArr: [
                { title: "基于大模型的项目1", tag: "国创" },
                { title: "基于大模型的项目2", tag: "国创" },
            ],
            user: '',
            dialogVisible: false,
            currentPage: 1,
            form: {
                title: '',
                intro: '',
                user: '',
                gid: '',
                startdate: '',
                ddl: '',
                done: 0
            },
            rules: {
                title: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                    { max: 10, message: '不能超过10位', trigger: 'blur' }
                ],
                intro: [
                    { max: 30, message: '不能超过30位', trigger: 'blur' }
                ],
                gid: [
                    { required: true, message: '请输入团队ID', trigger: 'blur' }
                ],
                startdate: [
                    { required: true, message: '请选择日期', trigger: 'blur' }
                ],
                ddl: [
                    { required: true, message: '请选择日期', trigger: 'blur' }
                ]
            },
            tableData: []
        };
    },
    methods: {
        add() {
            this.dialogVisible = true;
            this.form = {};
        },
        save(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.form.uid = this.user.uid
                    // console.log(this.form)
                    http.post("/project/save", this.form).then(res => {
                        console.log(res)
                        if (res.data.code === '0') {
                            this.$message({
                                type: "success",
                                message: "添加成功！"
                            })
                            // this.load();
                            this.dialogFormVisible = false;
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                    })
                } else {
                    alert("请检查！！");
                    return false;
                }
            })
        },
        load() {
            http.get("/project/load", { params: { pageNum: this.currentPage, pageSize: 9, uid: this.user.uid } }).then(res => {
                this.tableData = res.data.records;
                this.tableData.filter((item) => {
                    if (item.done === 0)
                        item.done = "未完成"
                    if (item.done === 1)
                        item.done = "已完成"
                    if (item.done === 2)
                        item.done = "已终止"
                })
            })
        },
    },
    created() {
        this.user = JSON.parse(sessionStorage.getItem("user"));
    },
};
</script>

<style scoped>
.main {
    position: relative;
    width: 875px;
}

.cardTitle {
    margin: 5px 0;
    font-size: 20px;
    font-weight: 600;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.cardImage {
    width: 100%;
    height: 250px;
}

.newProjBtn {
    position: absolute;
    width: 150px;
    height: 40px;
    background-color: #888 !important;
    line-height: 40px !important;
    font-size: 20px;
    font-weight: 600;
    top: 0px;
    right: 0px;
    padding: 0 10px;
    z-index: 100;

}

.cardView {
    height: 100%;
    width: 100%;
}

.cardTag {
    height: 20px;
    background-color: purple !important;
    line-height: 20px;
    margin-top: 10px;
    font-size: 12px;
    border-radius: 20px;
    border: 0;
}
</style>
