<template>
    <div class="main">
        <el-button type="primary" @click="dialogFormVisible = true" class="newProjBtn">新增项目</el-button>
        <el-dialog title="新增项目" :visible.sync="dialogFormVisible" :before-close="handleClose" destroy-on-close=true>
            <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
                <el-form-item label="项目名称" prop="title" :label-width="formLabelWidth">
                    <el-input v-model="form.title" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目简介" prop="intro" :label-width="formLabelWidth">
                    <el-input v-model="form.intro" type="textarea" placeholder="输入项目简介，让别人对你的项目一目了然, 不超过500字"></el-input>
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
                <!-- <el-form-item label="封面图" prop="mainpic_path" :label-width="formLabelWidth">
                    <el-upload action="http://localhost:9090/project/mainpic" list-type="picture-card" :limit="1"
                        v-model="form.mainpic_path" :on-success="handleUploadSuccess" :on-error="handleUploadError">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">上传项目封面图, 大小不超过10MB</div>
                    </el-upload>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')" class="confirmButton">确 定</el-button>
            </div>
        </el-dialog>
        <div style="width: 100%; padding: 20px; padding-top: 70px;">
            <el-table :data="tableData" border stripe style="width: 100%" @row-click="openDetails">
                <el-table-column prop="title" label="名称">
                </el-table-column>
                <el-table-column prop="startdate" label="创建时间" sortable>
                </el-table-column>
                <el-table-column prop="ddl" label="截止时间" sortable>
                </el-table-column>
                <el-table-column prop="done" label="状态"
                    :filters="[{ text: '已完成', value: '已完成' }, { text: '未完成', value: '未完成' }, { text: '已终止', value: '已终止' }]"
                    :filter-method="filterTag">
                </el-table-column>
            </el-table>
            <el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" layout="prev, pager, next"
                :total="100" style="margin-top: 20px; margin-left: 220px; float: left">
            </el-pagination>
        </div>
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
            uid: '',
            mainpic_path: '',
            dialogVisible: false,
            currentPage: 1,
            form: {
                title: '',
                intro: '',
                uid: '',
                xuehao: '',
                gid: '',
                startdate: '',
                ddl: '',
                done: 0,
                mainpic_path: ''
            },
            rules: {
                title: [
                    { required: true, message: '请输入项目名称', trigger: 'blur' },
                    { max: 100, message: '不能超过100字', trigger: 'blur' }
                ],
                intro: [
                    { max: 800, message: '不能超过500字', trigger: 'blur' }
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
        getProj() {
            request.get("/project/byid", { params: { pid: this.projectId } }).then(res => {
                this.projectData = res.data;
                if (this.projectData.done === 0)
                    this.projectData.done = "未完成"
                if (this.projectData.done === 1)
                    this.projectData.done = "已完成"
                if (this.projectData.done === 2)
                    this.projectData.done = "已终止"
            })
        },
        gotoGroup() {
            this.$router.push({
                path: '/group',
                query: {
                    groupKey: this.groupData.groupKey
                }
            })
        },
        doneProj() {
            this.projectData.done = 1
            request.put("project/done", this.projectData).then(res => {
                if (res.code === '0') {
                    this.$message({
                        type: "success",
                        message: "操作成功！"
                    })
                    this.getProj()
                    this.load()
                }
            })
        },
        endProj() {
            this.projectData.done = 2
            request.put("project/end", this.projectData).then(res => {
                if (res.code === '0') {
                    this.$message({
                        type: "success",
                        message: "操作成功！"
                    })
                    this.getProj()
                    this.load()
                }
            })
        },
        filterDone(value, row) {
            return row.done === value;
        },
        filterEmer(value, row) {
            return row.emer === value;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.load()
        },
        openDetails(row) {
            this.$router.push({
                path: '/project',
                query: {
                    projectId: row.pid
                }
            })
        },
        filterTag(value, row) {
            return row.done === value;
        },
        handleUploadSuccess(response, file, fileList) {
            // 你需要根据你的服务器响应来获取文件路径
            // 这里假设响应对象中有一个 'path' 属性保存了文件路径
            console.log("上传成功");
            this.mainpic_path = response.path;
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${file.name}？`);
        },
        add() {
            this.dialogVisible = true;
            this.form = {};
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        save(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.form.uid = this.uid
                    // console.log(this.form)
                    http.post("/project/save", this.form).then(res => {
                        console.log(res)
                        if (res.data.code === '0') {
                            this.$message({
                                type: "success",
                                message: "添加成功！"
                            })
                            this.load();
                            this.dialogFormVisible = false;
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                    })
                    this.resetForm("form")
                    console.log("reset")
                } else {
                    alert("请检查！！");
                    return false;
                }
            })
        },
        load() {
            http.get("/project/load", { params: { pageNum: this.currentPage, pageSize: 8, uid: this.uid } }).then(res => {
                this.tableData = res.data.data.records;
                this.tableData.filter((item) => {
                    if (item.done === 0)
                        item.done = "未完成"
                    if (item.done === 1)
                        item.done = "已完成"
                    if (item.done === 2)
                        item.done = "已终止"
                })
                function formatDate(dateString) {
                    const date = new Date(dateString);
                    const year = date.getFullYear();
                    const month = String(date.getMonth() + 1).padStart(2, "0");
                    const day = String(date.getDate()).padStart(2, "0");
                    const hour = String(date.getHours()).padStart(2, "0");
                    const minute = String(date.getMinutes()).padStart(2, "0");
                    const second = String(date.getSeconds()).padStart(2, "0");
                    return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
                }
                // 更改 startdate 和 ddl 的格式
                this.tableData.forEach((item) => {
                    item.startdate = formatDate(item.startdate)
                    item.ddl = formatDate(item.ddl)
                })
                
            })
        },
    },
    created() {
        this.uid = sessionStorage.getItem("uid");
        this.xuehao = sessionStorage.getItem("xuehao");
        this.load()
    },
};
</script>

<style scoped>
.main {
    position: relative;
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
    background-color: #b70031 !important;
    line-height: 40px !important;
    font-size: 20px;
    font-weight: 600;
    top: 0px;
    right: 20px;
    padding: 0 10px;
    z-index: 100;

}

.newProjBtn:hover {
    border: 1px solid #b70031 !important;
}

.dialog-footer {
    height: 40px;
    align-items: right;
}

.confirmButton {
    height: 40px;
    margin-left: 20px !important;
    padding: 0 20px;
    line-height: 40px !important;
    border: 1px solid #b70031 !important;
    border-radius: 5px !important;
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
