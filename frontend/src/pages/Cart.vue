<template>
   <div class="cart">
카트
   </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";

export default{
    setup(){
        const state= reactive({
        items:[]
        })

        axios.get("/api/cart/items").then(({data}) =>{
        console.log(data);
        state.items = data;
        })

    return {state}
    }
}
</script>

<style scoped>

</style>