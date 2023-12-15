<template>
    <div id="app">
    <div class="article-container">
        <!-- <h1 class="article-title">{{ competition.title }}</h1> -->
        <h1 class="article-title">论文大赛</h1>
        
        <div class="author-info">
            <!-- <p>Author: {{ competition.author }}</p> -->
            <p>发布日期：2023/12/12</p>
        </div>
        <div class="article-content">
            <p>文章内容xxx</p>
        </div>
    </div>
    </div>
</template>

<script>
export default {
    props: {
        competition: {
            type: Object,
            required: true,
        },
    },
};
</script>

<style scoped>
#app{
    background-color: white;
}
.article-container {
    text-align: center;
    margin: 0 auto;

}

.article-title {
    margin-top: 40px;
    font-size: 30px;
    margin-bottom: 10px;
    color: #b70031; 
    margin-bottom: 20px;
}

.author-info {
    font-style: italic;
    margin-bottom: 20px;
}

.article-content {
    line-height: 1.6;
}
</style>