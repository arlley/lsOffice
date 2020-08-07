<template>
  <div class="editable-cell">
    <div v-if="editable" class="editable-cell-input-wrapper">
      <a-date-picker format="YYYY-MM-DD" :value="value" @change="handleChange" @pressEnter="check" :placeholder="placeholder"/><a-icon
      type="check"
      class="editable-cell-icon-check"
      @click="check"
    />
    </div>
    <div v-else class="editable-cell-text-wrapper">
      {{ value || ' ' }}
      <a-icon type="edit" class="editable-cell-icon" @click="edit" />
    </div>
  </div>
</template>
<script>
    export default {
        props: {
            value: String,
            placeholder:String
        },
        data() {
            return {
                editable: false,
            };
        },
        methods: {
            handleChange(e) {
                const value = e.target.value;
                this.value = value;
            },
            check() {
                this.editable = false;
                this.$emit('change', this.value);
            },
            edit() {
                this.editable = true;
            },
        },
    }
</script>
