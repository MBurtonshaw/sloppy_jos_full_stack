<template>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News</title>
        <!-- <link rel="stylesheet" href="styles.css"> -->
    </head>

    <body>
    <header>
        <h1> Menu </h1>
    </header>
    <toppings />
    </body>
</template>

<script>
    import Toppings from '../components/Toppings.vue';
    export default {
        components: {
            Toppings
        }
    }
</script>

<style scoped>
h1 {
 font-family: arial;
}
</style>