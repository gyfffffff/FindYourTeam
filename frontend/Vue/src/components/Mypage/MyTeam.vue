<template>
    <div>
        <el-button type="primary" class="newTeamButton">新建项目</el-button>
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
                <tr>
                    <td>Team A</td>
                    <td>001</td>
                    <td>Project X</td>
                    <td>John, Mary, Alice</td>
                </tr>
                <tr>
                    <td>Team B</td>
                    <td>002</td>
                    <td>Project Y</td>
                    <td>Bob, Emily, David</td>
                </tr>
                <tr>
                    <td>Team C</td>
                    <td>003</td>
                    <td>Project Z</td>
                    <td>Lisa, Michael, Sarah</td>
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
            // Your data properties here
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
</style>
