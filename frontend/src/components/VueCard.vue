<template>
    <div class="card shadow-sm">
        <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}" />
          <div class="card-body">
             <span class="card-text">
                {{ item.name }} &nbsp;
             </span>
             <span class="discount badge bg-danger">
                   {{item.discountPer}} %
              </span>
              <div class="d-flex justify-content-between align-items-center">
                <button class="btn btn-primary">구입하기</button>
                   <small class="price text-muted">
                         {{lib.getNumberFormat(item.price)}} 원
                    </small>
                   <small class="real text-danger">
                         {{ lib.getNumberFormat(item.price - (item.price * item.discountPer / 100)) }} 원
                    </small>
              </div>
          </div>
    </div>
</template>

<script>
import lib from "@/scripts/lib";

export default {
    name : 'VueCard',
    props:{
         item: Object
    },
    setup(){
        return {lib}
    }
}
</script>

<style scoped>
.card .img {
   display:inline-block;
   width: 100%;
   height:250px;
   background-size:cover;
   background-position: center;
}

.card .card-body .price{
    text-decoration: line-through;
}
</style>