<template>
  <div class="container">
    <button @click="clicktrue">btn</button>
    <div class="show1" v-show="show1">
      show1
    </div>
    <div class="show2" v-show="show2">
      show2
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      show1:true,
      show2:false,
    }
  },
  methods:{
    clicktrue(){
      this.show1 = !this.show1;
      this.show2 = !this.show2;
    }
  }
}
</script>