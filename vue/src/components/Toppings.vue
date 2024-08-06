<template>
    <div>
        <h1>Toppings</h1>
        <label for="topping_1" >Pepperoni </label>
        <input id="topping_1" name="topping_1" type="checkbox" v-on:click="addTopping('pepperoni')">
        <label for="topping_2" >Sausage </label>
        <input id="topping_2" name="topping_2" type="checkbox" v-on:click="addTopping('sausage')">
        <label for="topping_3" >Cheese </label>
        <input id="topping_3" name="topping_3" type="checkbox" v-on:click="addTopping('extra_cheese')">
    </div>
</template>

<script>
export default {
        methods: {
            addTopping(topping) {
                this.$store.state.pizzas.ADD_TOPPING(topping);
            }
        }
    }
</script>

<style scoped>
</style>