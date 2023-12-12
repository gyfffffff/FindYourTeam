<template>
    <div class="main">
        <el-button type="primary" @click="dialogFormVisible = true" class="newTeamButton">新建团队</el-button>
        <el-dialog title="新增团队" :visible.sync="dialogFormVisible" width="30%">
            <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
                <el-form-item label="团队名称" prop="groupName">
                    <el-input v-model="form.groupName" style="width: 80%"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')">确 定</el-button>
            </span>
        </el-dialog>
        <table>
            <thead>
                <tr>
                    <th>团队名称</th>
                    <th>团队 ID</th>
                    <th>项目</th>
                    <th>团队成员</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in teamArr" :key="item.name">
                    <td>{{ item.name }}</td>
                    <td>{{ item.id }}</td>
                    <td>{{ item.proj }}</td>
                    <td>{{ item.members }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import http from "@/http";  // 引入新创建的http实例
export default {
    name: "MyTeam",
    data() {
        return {
            user: '',
            tableData: [],
            currentPage: 1,
            form: {
                groupName: '',
                uid: '',
                resp: ''
            },
            dialogFormVisible: false,
            rules: {
                groupName: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                    { max: 10, message: '不能超过10位', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        save(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.form.uid = this.user.uid
                    this.form.resp = 1
                    console.log(this.user.uid)
                    http.post("/group/add", this.form).then(res => {
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "添加成功！"
                            })
                            this.load()
                            this.dialogFormVisible = false
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
        }
    },
    mounted() {
        this.user = JSON.parse(sessionStorage.getItem("user"));
    },
};
</script>

<style scoped>
.main {
    position: relative;
    padding-top: 50px;
}

table {
    width: 100%;
    table-layout: fixed;
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
    top: 0px;
    right: 0px;
    padding: 0 10px;
}
</style>
