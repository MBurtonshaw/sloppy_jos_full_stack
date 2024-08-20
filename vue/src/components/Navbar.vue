<template>
    <nav class="navbar bg-dark" v-if="isScreenWide">
        <div class="container-fluid mx-5">
            <router-link v-bind:to="{ name: 'home' }">
                <img class="nav-pic" src="@/assets/spLogo.png" alt="Joe's Sloppy Pizza" />
            </router-link>
            <div class="mt-auto">
                <router-link active-class="active" class="px-5 d-inline nonchalant"
                    v-bind:to="{ name: 'home' }">Home</router-link>
                <router-link active-class="active" class="px-5 d-inline nonchalant"
                    v-bind:to="{ name: 'about-us' }">About Us</router-link>
                <router-link active-class="active" class="px-5 d-inline nonchalant" v-bind:to="{ name: 'order' }">Order
                    Now</router-link>
                <router-link active-class="active" class="px-5 d-inline nonchalant"
                    v-bind:to="{ name: 'contact' }">Contact Us</router-link>
                <router-link class="px-5 d-inline shopping-cart" v-bind:to="{ name: 'shopping-cart' }"><img
                        src="@/assets/shopping-cart2.png" alt="shopping cart img"
                        style="width: 65px; height: 65px;"></router-link>
            </div>
        </div>
    </nav>

    <nav class="navbar bg-dark" v-else>
        <div class="container-fluid m-auto">
            <div class="dropdown m-auto">
                <button class="btn btn-secondary dropdown-toggle bg-dark" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                    <img class="menu_icon" src="@/assets/spLogo.png" />
                </button>
                <ul class="pusher dropdown-menu text-center">
                    <router-link class="px-5 menu dropdown-item" v-bind:to="{ name: 'home' }">Home</router-link>
                    <router-link class="px-5 menu dropdown-item" v-bind:to="{ name: 'about-us' }">About
                        Us</router-link>
                    <router-link class="px-5 menu dropdown-item" v-bind:to="{ name: 'order' }">Order
                        Now</router-link>
                    <router-link class="px-5 menu dropdown-item" v-bind:to="{ name: 'contact' }">Contact
                        Us</router-link>
                        <router-link class="px-5 menu dropdown-item" v-bind:to="{ name: 'shopping-cart' }">Cart</router-link>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    data() {
        return {
            screenWidth: window.innerWidth
        };
    },
    computed: {
        isScreenWide() {
            return this.screenWidth > 768;
        }
    },
    methods: {
        handleResize() {
            this.screenWidth = window.innerWidth;
        }
    },
    mounted() {
        window.addEventListener('resize', this.handleResize);
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.handleResize);
    }
};
</script>

<style scoped>
nav {
    font-family: Impact, Haettenschweiler, 'Arial', sans-serif;
    font-size: 24px;
    text-shadow: 2px 2px black;
}

.nav-pic {
    width: 50%;
    margin: -1.75em -1.5em -1.75em 0em;
}

.nonchalant {
    text-decoration: none;
    color: lightgrey;
}

.nonchalant:hover {
    color: rgb(80, 90, 100);
}

.menu {
    text-decoration: none;
    color: rgb(33, 37, 49);
    text-shadow: none;
    letter-spacing: 1.7px;
}

.active {
    background-color: rgb(112, 112, 112);
    color: white;
    padding: 1.1em;
}

.menu_icon {
    width: 70%;
    margin: -3em;
}

@media only screen and (max-width: 600px) {
    .pusher {
        margin-left: 7.5em;
    }

    .menu {
        text-decoration: none;
        color: rgb(33, 37, 49);
        text-shadow: none;
        letter-spacing: 1.7px;
    }

    .menu_icon {
        width: 75%;
        margin: -3.25em;
    }

    .btn {
        border: none;
    }
}
</style>
