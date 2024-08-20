<template>
    <h4 class="my-3 text-center">World Famous Sloppy Specialty Pizzas! (Medium Only)</h4>
    <div class="container mt-3">
        <div class="row justify-content-center" v-if="isScreenWide">
            <div class="col-lg-3">
                <div class="card grid-item">
                    <img src="@/assets/orderSloppyPizza-min.png" alt="Sloppy Joe Special" class="card-img-top">
                    <p class="card-text description">Sloppy Joe Special<br>$25<br>Loaded with
                        seasoned ground beef, tangy tomato sauce, and a melty cheese blend that brings the classic
                        Sloppy Joe to
                        your plate</p>
                    <button class="btn btn-primary add-to-cart-btn"
                        v-on:click="addSpecialtyPizzaToCart(1, 'Sloppy Joe Special', 25.00)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card grid-item">
                    <img src="@/assets/orderSupreme-min.png" alt="Supreme Pizza" class="card-img-top">
                    <p class="card-text description">The Sloppy Supreme<br>$23<br>Pepperoni,
                        sausage, bell peppers, onions, and black olives, all layered over a rich tomato base and gooey
                        mozzarella
                    </p>
                    <button class="btn btn-primary add-to-cart-btn"
                        v-on:click="addSpecialtyPizzaToCart(2, 'The Sloppy Supreme', 23.00)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card grid-item">
                    <img src="@/assets/orderZestyTrio-min.png" alt="Zesty Trio" class="card-img-top">
                    <p class="card-text description">Jo's Zesty Trio<br>$23.50<br>A bold combo
                        of spicy pepperoni, earthy mushrooms, and fiery jalapeños, all on a bed of rich tomato sauce and
                        bubbling
                        cheese</p>
                    <button class="btn btn-primary add-to-cart-btn"
                        v-on:click="addSpecialtyPizzaToCart(4, 'The Zesty Trio', 23.50)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card grid-item">
                    <img src="@/assets/orderMeatlovers-min.png" alt="Meatlover's Pizza" class="card-img-top">
                    <p class="card-text description">Sloppy's Carnivore
                        Delight<br>$22<br>Piled high with pepperoni, sausage, ham, and bacon, all
                        perfectly balanced by a hearty tomato sauce and melted cheese</p>
                    <button class="btn btn-primary add-to-cart-btn"
                        v-on:click="addSpecialtyPizzaToCart(3, 'Sloppy\'s Carnivore Delight', 22.00)">Add to
                        Cart</button>
                </div>
            </div>
        </div>



        <div v-else>
            <div class="col-12">
                <div class="card">
                    <img src="@/assets/orderSloppyPizza-min.png" alt="Sloppy Joe Special" class="card-img-top">
                    <p class="text-center">Sloppy Joe Special<br>$25<br></p>
                    <p class='p-2'>Loaded with
                        seasoned ground beef, tangy tomato sauce, and a melty cheese blend that brings the classic
                        Sloppy Joe to
                        your plate</p>
                    <button class="btn btn-primary w-50 m-auto m-2 mb-2"
                        v-on:click="addSpecialtyPizzaToCart(1, 'Sloppy Joe Special', 25.00)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card">
                    <img src="@/assets/orderSupreme-min.png" alt="Supreme Pizza" class="card-img-top">
                    <p class="text-center">The Sloppy Supreme<br>$23<br></p><p class='p-2'>Pepperoni,
                        sausage, bell peppers, onions, and black olives, all layered over a rich tomato base and gooey
                        mozzarella
                    </p>
                    <button class="btn btn-primary w-50 m-auto m-2 mb-2"
                        v-on:click="addSpecialtyPizzaToCart(2, 'The Sloppy Supreme', 23.00)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card">
                    <img src="@/assets/orderZestyTrio-min.png" alt="Zesty Trio" class="card-img-top">
                    <p class="text-center">Jo's Zesty Trio<br>$23.50<br></p><p class='p-2'>A bold combo
                        of spicy pepperoni, earthy mushrooms, and fiery jalapeños, all on a bed of rich tomato sauce and
                        bubbling
                        cheese</p>
                    <button class="btn btn-primary w-50 m-auto m-2 mb-2"
                        v-on:click="addSpecialtyPizzaToCart(4, 'The Zesty Trio', 23.50)">Add to Cart</button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card">
                    <img src="@/assets/orderMeatlovers-min.png" alt="Meatlover's Pizza" class="card-img-top">
                    <p class="text-center">Sloppy's Carnivore
                        Delight<br>$22<br></p><p class='p-2'>Piled high with pepperoni, sausage, ham, and bacon, all
                        perfectly balanced by a hearty tomato sauce and melted cheese</p>
                    <button class="btn btn-primary w-50 m-auto m-2 mb-2"
                        v-on:click="addSpecialtyPizzaToCart(3, 'Sloppy\'s Carnivore Delight', 22.00)">Add to
                        Cart</button>
                </div>
            </div>
        </div>
    </div>
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
        addSpecialtyPizzaToCart(id, name, price) {
            this.$store.commit('ADD_TO_CART', { type: 'Specialty', obj: { id: id, name: name, price: price } });
        },
        handleResize() {
            this.screenWidth = window.innerWidth;
        },
        mounted() {
        window.addEventListener('resize', this.handleResize);
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.handleResize);
    }
}
}
</script>

<style scoped>
.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
  color: #e0ba20c7;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}


.navbar-logo {
  max-height: 100px;
  /* Adjust the max height as needed */
  width: auto;
  /* Maintain aspect ratio */
  margin-top: -25px;
}

@media (max-width: 768px) {
  .navbar-logo {
    max-height: 40px;
    /* Adjust size for smaller screens */
  }
}


#page-header {
  box-shadow: 0 4px 5px 0 rgba(0, 0, 0, 0.14), 0 1px 10px 0 rgba(0, 0, 0, 0.12), 0 2px 4px -1px rgba(0, 0, 0, 0.2);
  padding-left: 10px;
  padding-right: 10px;
  grid-area: header;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #daa520;
}

#page-footer {
  box-shadow: 0 4px 5px 0 rgba(0, 0, 0, 0.14), 0 1px 10px 0 rgba(0, 0, 0, 0.12), 0 2px 4px -1px rgba(0, 0, 0, 0.2);
  padding-left: 10px;
  padding-right: 10px;
  grid-area: footer;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #e0ba20c1;
}

.resize-image {
  max-width: 200px;
  max-height: auto;
  margin-top: 20px;
}

footer {
  background-color: #e0ba20c7;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}

.footer-image {
  display: grid;
  place-items: center;
}

.inline-footer-links {
  display: grid;
  grid-template-columns: repeat(3, auto);
  column-gap: 50px;
  justify-content: center;
}

.nav-link {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav-link li {
  margin-right: 50px;
  font-size: 35px;
}

.nav-link li:last-child {
  margin-right: 50px;
}

.nav-link a {
  text-decoration: none;
  color: black;
}

.nav-link a:hover {
  color: #0000ff;
}



/* grid for photos /////////////////////////////////////////////////////////////////// */
.container {
  padding-bottom: 40px;
}

.row {
  margin-bottom: 40px;
}

.grid-item {
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
  border: 1px solid black;
}

.grid-item:hover {
  transform: scale(1.15);
  z-index: 10;
}

.card-img-top {
  transition: transform 0.3s ease;
  height: 200px;
  object-fit: cover;
}

.grid-item:hover .card-img-top {
  transform: scale(1.1);
}

.description {
  opacity: 0;
  transition: opacity 0.3s ease;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 10px;
  border-radius: 5px;
  position: absolute;
  bottom: 20%;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
  font-size: 0.8em;
}

.grid-item:hover .description {
  opacity: 1;
  margin: 0.5em 0;
}

.add-to-cart-btn {
  position: absolute;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.grid-item:hover .add-to-cart-btn {
  opacity: 1;
  transform: translateX(-50%) scale(1.1);
}

h4 {
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.padding-left {
  padding-left: 5%;
}

h1,
h3 {

  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}
</style>
