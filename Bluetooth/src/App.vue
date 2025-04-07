<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'
import { BleClient } from './plugins/bluetooth'

const response = ref('')
const error = ref('')

async function onClick() {
  try {
    await BleClient.initialize()
    await BleClient.requestLEScan(
      {
        // services: [HEART_RATE_SERVICE],
      },
      (result) => {
        console.log('received new scan result', result);
      },
    );

    setTimeout(async () => {
      await BleClient.stopLEScan();
      console.log('stopped scanning');
    }, 5000);
  } catch(error: any) {
    console.error(error)
    error.value = JSON.stringify(error)
  }
}
</script>

<template>
  <div>
    <button type="button" @click="onClick">Scan</button>
    <div>{{ response }}</div>
    <div>{{ error }}</div>
  </div>
  <HelloWorld msg="Vite + Vue" />
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
