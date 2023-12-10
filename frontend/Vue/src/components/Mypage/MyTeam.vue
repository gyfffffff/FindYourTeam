<template>
    <div class="main">
        <el-button type="primary" class="newTeamButton">新建团队</el-button>
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
                    <td>{{item.name}}</td>
                    <td>{{item.id}}</td>
                    <td>{{item.proj}}</td>
                    <td>{{item.members}}</td>
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
            teamArr: [{
                name: "Team A",
                id: "001",
                proj: "Project X",
                members: "John, Mary, Alice",
            },{
                name: "Team B",
                id: "002",
                proj: "Project Y",
                members: "Bob, Emily, David",
            },{
                name: "Team C",
                id: "003",
                proj: "Project Z",
                members: "Lisa, Michael, Sarah",
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
