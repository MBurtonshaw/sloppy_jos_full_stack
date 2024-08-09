<template>
  <div>
    
  </div>
</template>

<script>
    export default {
            computed: {
            products() {
                return this.$store.state.shoppingCart.map((shopProduct) => {
                    return this.$store.state.products.find((prod) => {
                        return prod.productId == shopProduct.productId;
                    })
                });
            }
        },
            methods: {
            addToCart(product) {
                this.$store.commit('ADD_TO_CART', product);
            },
            removeFromCart(product) {
                this.$store.commit('REMOVE_FROM_CART', product);
            }
        }
    }

</script>

<style scoped>

</style>