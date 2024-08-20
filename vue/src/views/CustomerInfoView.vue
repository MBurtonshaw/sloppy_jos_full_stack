<template>
    <navbar />
    <div class="w-50 m-auto">
        <h1 class="customer-information text-start my-5">Customer Information</h1>
        <div class="mb-4">
            <form>
                <h2 class="text-end">Total : ${{ cartTotal }}</h2>
                <table class="table">
                    <tbody>
                        <tr>
                            <td><label for="customerName">Name:</label></td>
                            <td><input type="text" class="form-control" id="customerName" v-model="customerName" placeholder=""></td>
                        </tr>
                        <tr>
                            <td><label for="customerEmail">Email:</label></td>
                            <td><input type="email" class="form-control" id="customerEmail" v-model="customerEmail" placeholder=""></td>
                        </tr>
                        <tr>
                            <td><label for="customerPhone">Phone Number:</label></td>
                            <td><input type="tel" class="form-control" id="customerPhone" v-model="customerPhone" placeholder=""></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="delivery">Delivery</label>
                                <input name='orderType' type="radio" v-model="orderType" value="delivery" style="margin-left: 0.5em;" />
                            </td>
                            <td>
                                <label for="takeout">Takeout</label>
                                <input name='orderType' type="radio" v-model="orderType" value="takeout" style="margin-left: 0.5em;" />
                            </td>
                        </tr>
                        <tr v-if="orderType === 'delivery'">
                            <td><label for="customerAddress">Address:</label></td>
                            <td><textarea class="form-control" id="customerAddress" v-model="customerAddress" rows="3" placeholder=""></textarea></td>
                        </tr>
                        <tr>
                            <td><label for="creditCardNumber">Credit Card Number:</label></td>
                            <td><input type="text" class="form-control" id="creditCardNumber" v-model="creditCardNumber" placeholder=""></td>
                        </tr>
                        <tr>
                            <td><label for="creditCardExpiry">Expiry Date:</label></td>
                            <td><input type="text" class="form-control" id="creditCardExpiry" v-model="creditCardExpiry" placeholder=""></td>
                        </tr>
                        <tr>
                            <td><label for="creditCardCVV">CVV:</label></td>
                            <td><input type="text" class="form-control" id="creditCardCVV" v-model="creditCardCVV" placeholder=""></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="submit" style="display: flex; justify-content: center; width: 100%; margin: auto;">
        <p style="margin-top: 2em;">Click Me to Place the Order!</p>
        <button v-on:click="checkout" style="border: none; background: none;">
            <img src="@/assets/spLogo.png" alt="Joe's Sloppy Head" class="img-fluid custom-img" style="width:12.5em; padding: 0;" />
        </button>
    </div>
    <pizza-footer />
</template>

<script>
import Navbar from '../components/Navbar.vue';
import PizzaFooter from '../components/PizzaFooter.vue';

export default {
    components: {
        Navbar,
        PizzaFooter
    },
    data() {
        return {
            customerName: this.CustomerName || '',
            customerEmail: '',
            customerPhone: '',
            orderType: '',
            customerAddress: '',
            creditCardNumber: '',
            creditCardExpiry: '',
            creditCardCVV: '',
        };
    },
    computed: {
        cartTotal() {
            return this.$store.state.shoppingCart.reduce((total, food) => {
                return total + food.obj.price;
            }, 0).toFixed(2);
        },
    },
    methods: {
        checkout() {
            if (this.validateForm()) {
                this.$store.commit('CHECKOUT');
                this.$store.commit('CLEAR_CART');
                window.alert('Order Submitted');
                this.$router.push('/');
            } else {
                window.alert('Please fill in all required fields.');
            }
        },
        validateForm() {
            return this.customerName && this.customerEmail && this.customerPhone && this.creditCardNumber && this.creditCardExpiry && this.creditCardCVV;
        }
    },
}
</script>

<style>
body {
    font-family: Impact, Haettenschweiler, 'Arial', sans-serif;
    font-size: 20px;
}
</style>