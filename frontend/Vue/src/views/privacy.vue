<template>
    <div class="container">
        <pdf src="/static/privacy.pdf"></pdf>
    </div>
</template>

<script>
import pdf from 'vue-pdf'
export default {
    components: {
        pdf
    }
}
</script>

<style scoped>
.container {
    font-family: PingFang SC;
    width: 100%;
    height: 500px;
}
</style>
