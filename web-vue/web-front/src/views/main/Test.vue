<template>
  <div>
    <a-checkable-tag v-model="checked1" @change="handleChange">
      Tag1
    </a-checkable-tag>
    <a-checkable-tag v-model="checked2" @change="handleChange">
      Tag2
    </a-checkable-tag>
    <a-checkable-tag v-model="checked3" @change="handleChange">
      Tag3
    </a-checkable-tag>
  </div>
</template>
<script>
    export default {
        data() {
            return {
                checked1: false,
                checked2: false,
                checked3: false,
            };
        },
        methods: {
            handleChange(checked) {
                console.log(checked);
            },
        },
    };
</script>
