<template>
  <paginate
    :page-count="pageCount"
    :page-range="3"
    :margin-pages="2"
    :click-handler="clickCallback"
    :prev-text="'前一页'"
    :prev-class="'page-item'"
    :prev-link-class="'page-link'"
    :next-text="'后一页'"
    :next-class="'page-item'"
    :next-link-class="'page-link'"
    :container-class="'pagination justify-content-center'"
    :page-class="'page-item'"
    :page-link-class="'page-link'"
  ></paginate>
</template>
<script>
import paginate from "vuejs-paginate";
export default {
  name: "v-page",
  data() {
    return {
      total: 10, //每页显示数量
      front: 0,
      back: 10
    };
  },
  components: {
    paginate
  },
  props: {
    local: "" //父键组件名
  },
  created(){
    this.$store.commit("setPageArray", this.rangeList);
  },
  beforeUpdate() {
    this.$store.commit("setPageArray", this.rangeList);
  },
  methods: {
    clickCallback(pageNum) {
      this.front = this.total * (pageNum - 1);
      this.back = this.total * pageNum;
      this.$store.commit("setPageArray", this.rangeList);
    }
  },
  computed: {
      pageCount() {
        let len = this.$store.state[this.$utils.getLocalArrayName(this.local)].length;
        let size = Math.floor(len / this.total);
        let mod = len % this.total;
        if (mod > 0) {
          size += 1;
        } else if (mod == 0 && size == 0) {
          size = 1;
        }
        return size;
    },
    rangeList() {
      let values = [];
      for (let item in this.$store.state[
        this.$utils.getLocalArrayName(this.local)
      ]) {
        if (item >= this.front && item < this.back) {
          values.push(
            this.$store.state[this.$utils.getLocalArrayName(this.local)][item]
          );
        }
      }
      console.log(values);
      return values;
    }
  }
};
</script>
<style scoped>
</style>
