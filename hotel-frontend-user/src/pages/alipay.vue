<template>
  <!-- 支付宝支付界面 -->
  <div class="ali-pay box-shadow mt-4 border-radius">
    <div v-html="alipay">{{alipay}}</div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      alipay: '',
    };
  },
  created() {
    this.alipay = this.$route.query.htmlData;
    this.$nextTick(()=> {
      document.forms[0].submit()
    })
  },
};
</script>
<style lang="scss">
</style>